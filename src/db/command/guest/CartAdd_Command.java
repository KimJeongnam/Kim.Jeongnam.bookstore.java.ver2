package db.command.guest;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import db.Oracledb;
import db.command.Command;

public class CartAdd_Command implements Command{
	PreparedStatement pstmt;
	private String user_id;
	private String book_code;
	private int wish_stock;
	
	public CartAdd_Command(String user_id, String book_code, int wish_stock) {
		this.user_id = user_id;
		this.book_code = book_code;
		this.wish_stock = wish_stock;
	}
	
	@Override
	public void execute() throws SQLException {
		int rowsInserted = 0;


		String sql = "MERGE INTO carts c\r\n" + 
				"USING ( SELECT ? user_id, ? book_code, ? wish_stock   -- USING절에 뷰가 올수 있다.\r\n" + 
				"        FROM dual) s\r\n" + 
				"ON ( c.user_id = s.user_id \r\n" + 
				"    AND c.book_code = s.book_code)\r\n" + 
				"WHEN MATCHED THEN\r\n" + 
				"  UPDATE SET c.wish_stock = c.wish_stock + s.wish_stock\r\n" + 
				"  WHERE c.wish_stock+s.wish_stock <= (SELECT stock FROM books WHERE book_code = s.book_code)\r\n" + 
				"WHEN NOT MATCHED THEN\r\n" + 
				"    INSERT (c.user_id, c.book_code, c.wish_stock)\r\n" + 
				"    VALUES (s.user_id, s.book_code, s.wish_stock)\r\n" + 
				"    WHERE s.wish_stock <= (SELECT stock FROM books WHERE book_code = s.book_code)";

		pstmt = Oracledb.getInstance().getConnection().prepareStatement(sql);

		pstmt.setString(1, user_id);
		pstmt.setString(2, book_code);
		pstmt.setInt(3, wish_stock);
		

		rowsInserted = pstmt.executeUpdate();
		
		if(rowsInserted==0)
			JOptionPane.showMessageDialog(null, "장바구니에 추가할 책의 수량이 책목록의 수량보다 큽니다.!"
					, "Cart Add Fail!", JOptionPane.ERROR_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, "'"+book_code+"' 장바구니 추가 완료", "Insert Success"
					, JOptionPane.INFORMATION_MESSAGE);
		pstmt.close();
	}

}
