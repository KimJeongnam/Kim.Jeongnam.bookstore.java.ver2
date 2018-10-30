package db.command.guest;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import db.Oracledb;
import db.command.Command;

public class CartUpdate_Command implements Command{
	private PreparedStatement pstmt;
	private String bookCode;
	private String id;
	private int stock;
	
	public CartUpdate_Command(String bookCode, String id, int stock) {
		this.bookCode = bookCode;
		this.id = id;
		this.stock = stock;
	}
	
	@Override
	public void execute() throws SQLException {
		int rowsInserted = 0;
		
		String sql = "UPDATE carts SET wish_stock=?\r\n" + 
				"	WHERE user_id=? \r\n" + 
				"	AND book_code=?";

		pstmt = Oracledb.getInstance().getConnection().prepareStatement(sql);

		pstmt.setInt(1, stock);
		pstmt.setString(2, id);
		pstmt.setString(3, bookCode);

		rowsInserted = pstmt.executeUpdate();
		
		if(rowsInserted >0)
			JOptionPane.showMessageDialog(null, "'"+bookCode+"' 장바구니 수정 완료", "Success"
					, JOptionPane.INFORMATION_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, "수정할 항목을 선택한 후 진행해 주세요!"
					, "Fail!", JOptionPane.ERROR_MESSAGE);
		
		pstmt.close();
	}

}
