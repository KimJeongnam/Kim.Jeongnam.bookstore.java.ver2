package db.command.main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.Oracledb;
import db.command.Command;
import models.OrderInfo;

public class OrderInfo_Command implements Command{
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private String order_code = null;
	
	public OrderInfo_Command(String order_code) {
		this.order_code = order_code;
	}
	
	@Override
	public void execute() throws SQLException {
		String sql = "SELECT b.book_name, b.author, TO_CHAR(b.price, 'L999,999') price, o.order_stock\r\n" + 
				"    FROM order_info o JOIN books b\r\n" + 
				"    ON o.book_code = b.book_code\r\n" + 
				"    WHERE o.order_code = ?\r\n" + 
				"    ORDER BY b.book_name ASC";

		pstmt = Oracledb.getInstance().getConnection().prepareStatement(sql);

		pstmt.setString(1, order_code);

		rs = pstmt.executeQuery();
		OrderInfo.list.clear();
		
		while(rs.next()) {
			OrderInfo data = new OrderInfo();
			
			data.setBook_name(rs.getString("book_name"));
			data.setAuthor(rs.getString("author"));
			data.setPrice(rs.getString("price"));
			data.setOrder_stock(rs.getString("order_stock"));
			
			OrderInfo.list.add(data);
		}
		
		pstmt.close();
		rs.close();
	}
}
