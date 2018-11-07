package db.command.host;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.Oracledb;
import db.command.Command;
import models.TotalPrice;

public class GetTotalPrice_Command implements Command{
	PreparedStatement pstmt;
	ResultSet rs;
	
	public GetTotalPrice_Command() {
	}
	
	@Override
	public void execute() throws SQLException {
		
		String sql = "SELECT TO_CHAR(SUM(totalprice), 'L999,999,999,999') \"totalprice\"\r\n" + 
				"    FROM orders\r\n" + 
				"    WHERE payment_status = 1";

		pstmt = Oracledb.getInstance().getConnection().prepareStatement(sql);


		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			TotalPrice.totalPrice = rs.getString("totalprice");
		}
		
		pstmt.close();
		rs.close();
	}
}
