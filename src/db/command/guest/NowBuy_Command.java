package db.command.guest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import db.Oracledb;
import db.command.Command;
import models.Cart;
import models.CreateCode;

public class NowBuy_Command implements Command{
	private PreparedStatement pstmt;
	private Connection con;
	private ArrayList<Cart> cartList = null;
	protected String id = "";
	private Savepoint savepoint1 = null;
	
	public NowBuy_Command(String id, ArrayList<Cart> cartList) {
		this.id = id;
		this.cartList = cartList;
	}
	
	
	@Override
	public void execute() throws SQLException {
		buy();
	}
	
	private void buy() throws SQLException{
		int rowsInserted = 0;
		
		con = Oracledb.getInstance().getConnection();
		con.setAutoCommit(false);
		
		savepoint1 = con.setSavepoint("Savepoint1");
		
		/*String sql = "INSERT INTO orders\r\n" + 
				"    VALUES(?, ?, 0, 0, 0)";*/
		
		String sql = "INSERT INTO orders(\r\n" + 
				"    order_code,\r\n" + 
				"    user_id,\r\n" + 
				"    totalprice,\r\n" + 
				"    payment_status,\r\n" + 
				"    refund_ask)\r\n" + 
				"    VALUES(?, ?, 0, 0, 0)";
		
		String code = "";
		while(rowsInserted == 0) {
			code = CreateCode.orderCode();
			
			pstmt = Oracledb.getInstance().getConnection().prepareStatement(sql);

			pstmt.setString(1, code);
			pstmt.setString(2, id);

			rowsInserted = pstmt.executeUpdate();
		}
		
		
		for(Cart cart : cartList) {
			sql = "INSERT INTO order_info\r\n" + 
					"    SELECT d.ordercode, d.bookcode, d.orderstock\r\n" + 
					"    FROM (SELECT ? ordercode, ? bookcode, ? orderstock\r\n" + 
					"            FROM dual) d\r\n" + 
					"    WHERE d.orderstock <= (SELECT stock\r\n" + 
					"                            FROM books\r\n" + 
					"                            WHERE book_code=d.bookcode\r\n" + 
					"                            AND delete_status <> 1)";
			
			pstmt = Oracledb.getInstance().getConnection().prepareStatement(sql);
			
			pstmt.setString(1, code);
			pstmt.setString(2, cart.getBook_code());
			pstmt.setString(3, cart.getWish_stock());
			
			rowsInserted = pstmt.executeUpdate();
			if(rowsInserted == 0) {
				con.rollback(savepoint1);
				commit();
				throw new SQLException("해당 책의 수량이 남아있지 않습니다.! 책 코드 : "+cart.getBook_code()+
						" 책제목 : "+cart.getBook_name());
			}
			
			sql = "Update books \r\n" + 
					"    SET stock = stock-?\r\n" + 
					"    WHERE book_code = ?";
			
			pstmt = Oracledb.getInstance().getConnection().prepareStatement(sql);
			
			pstmt.setString(1, cart.getWish_stock());
			pstmt.setString(2, cart.getBook_code());
			
			rowsInserted = pstmt.executeUpdate();
			if(rowsInserted == 0) {
				con.rollback(savepoint1);
				commit();
				throw new SQLException("Books Table Update Error 책 코드 : "+cart.getBook_code()+
						" 책제목 : "+cart.getBook_name());
			}
		}
				
		commit();
		
		JOptionPane.showMessageDialog(null, "구매요청 완료  Order Code : "+code, "Success"
				, JOptionPane.INFORMATION_MESSAGE);
		pstmt.close();
	}
	
	private void commit() throws SQLException{
		con.commit();
		con.setAutoCommit(true);
	}
}
