package db.command.host;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.Oracledb;
import db.command.Command;
import models.Order;

public class ConfirmAskList_Command implements Command{
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	public void execute() throws SQLException {
		String sql = "SELECT o1.order_code\n" + 
				"        , o1.user_id\n" + 
				"        , TO_CHAR(o1.order_date, 'YYYY-MM-DD') \"date\"\n" + 
				"        , TO_CHAR(o1.order_date, 'HH24:MI:SS') \"time\"\n" + 
				"        , (SELECT COUNT(*) \n" + 
				"            FROM order_info o2\n" + 
				"            WHERE o2.order_code = o1.order_code) count\n" + 
				"        , TO_CHAR(o1.totalprice, 'L999,999,999') \"totalprice\"\n" + 
				"        FROM orders o1 \n" + 
				"        WHERE o1.payment_status = 0\n" + 
				"        AND o1.refund_ask=0\n" + 
				"        GROUP BY o1.order_code, o1.user_id, TO_CHAR(o1.order_date, 'YYYY-MM-DD'), TO_CHAR(o1.order_date, 'HH24:MI:SS'), user_id, TO_CHAR(o1.totalprice, 'L999,999,999')\n" + 
				"        ORDER BY TO_CHAR(o1.order_date, 'YYYY-MM-DD') ASC, TO_CHAR(o1.order_date, 'HH24:MI:SS') ASC" + 
				"        ";

		pstmt = Oracledb.getInstance().getConnection().prepareStatement(sql);


		rs = pstmt.executeQuery();
		
		Order.confirmAsklist.clear();
		
		while(rs.next()) {
			Order data = new Order();
			data.setChecked(false);
			data.setOrder_code(rs.getString("order_code"));
			data.setUser_id(rs.getString("user_id"));
			data.setDate(rs.getString("date"));
			data.setTime(rs.getString("time"));
			data.setCount(Integer.toString(rs.getInt("count")));
			data.setTotalPrice(rs.getString("totalprice"));
			
			
			
			Order.confirmAsklist.add(data);
		}
		
		pstmt.close();
		rs.close();
	}
}
