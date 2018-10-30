package db.command.guest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.Oracledb;
import db.command.Command;
import forms.panels.guest.CarTotalCountPricePanel;

public class CartCountAndTotal_Command implements Command{
	PreparedStatement pstmt;
	ResultSet rs;
	private String id;
	
	public CartCountAndTotal_Command(String id) {
		this.id = id;
	}
	
	@Override
	public void execute() throws SQLException {
		
		String sql = "SELECT count(c.book_code) \"count\", TO_CHAR(sum(c.wish_stock*b.price), 'L999,999,999,999') \"total_price\"\r\n" + 
				"    FROM carts c JOIN books b\r\n" + 
				"    ON c.book_code = b.book_code\r\n" + 
				"    WHERE user_id = ?";

		pstmt = Oracledb.getInstance().getConnection().prepareStatement(sql);

		pstmt.setString(1, id);

		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			CarTotalCountPricePanel.totalCount = rs.getString("count");
			CarTotalCountPricePanel.totalPrice = rs.getString("total_price");
		}
		
		pstmt.close();
		rs.close();
	}
	
}
