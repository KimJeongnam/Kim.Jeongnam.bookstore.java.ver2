package db.command.guest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.Oracledb;
import db.command.Command;
import models.Cart;

public class CartList_Command implements Command{
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private String id = null;
	
	public CartList_Command(String id) {
		this.id = id;
	}
	
	@Override
	public void execute() throws SQLException {
		String sql = "SELECT c.book_code\r\n" + 
				"    , b.book_name\r\n" + 
				"    , b.author\r\n" + 
				"    , TO_CHAR(b.price\r\n" + 
				"    , 'L999,999,999') price\r\n" + 
				"    , c.wish_stock\r\n" + 
				"    , TO_CHAR(b.price*c.wish_stock, 'L999,999,999') all_price\r\n" + 
				"    FROM carts c JOIN books b\r\n" + 
				"    ON c.book_code = b.book_code\r\n" + 
				"    WHERE c.user_id = ?";

		pstmt = Oracledb.getInstance().getConnection().prepareStatement(sql);

		pstmt.setString(1, id);

		rs = pstmt.executeQuery();
		Cart.getList().clear();
		while(rs.next()) {
			
			String book_code = rs.getString("book_code");
			String book_name = rs.getString("book_name");
			String author = rs.getString("author");
			String price = rs.getString("price");
			String wish_stock = rs.getString("wish_stock");
			String allPrice = rs.getString("all_price");
			
			Cart cart = new Cart(book_code, book_name, author, price, wish_stock, allPrice);
			
			Cart.getList().add(cart);
		}
		
		pstmt.close();
		rs.close();
	}

}
