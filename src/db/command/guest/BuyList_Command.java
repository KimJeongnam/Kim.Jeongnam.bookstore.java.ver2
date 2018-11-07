package db.command.guest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.Oracledb;
import db.command.Command;
import models.Order;

public class BuyList_Command implements Command{
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private String id = null;
	
	public BuyList_Command(String id) {
		this.id = id;
	}
	
	@Override
	public void execute() throws SQLException {
		String sql = "SELECT o1.order_code\r\n" + 
				"        , TO_CHAR(o1.confirm_date, 'YYYY-MM-DD') \"date\"\r\n" + 
				"        , TO_CHAR(o1.confirm_date, 'HH24:MI:SS') \"time\"\r\n" + 
				"        , (SELECT COUNT(*) \r\n" + 
				"            FROM order_info o2\r\n" + 
				"            WHERE o2.order_code = o1.order_code) count\r\n" + 
				"        ,  TO_CHAR(o1.totalprice, 'L999,999,999') \"totalprice\"\r\n" + 
				"        FROM orders o1 \r\n" + 
				"        WHERE o1.payment_status = 1\r\n" + 
				"        AND o1.refund_ask=0\r\n" + 
				"        AND o1.user_id =?\r\n" + 
				"        GROUP BY o1.order_code, TO_CHAR(o1.confirm_date, 'YYYY-MM-DD'), TO_CHAR(o1.confirm_date, 'HH24:MI:SS'), user_id, TO_CHAR(o1.totalprice, 'L999,999,999')\r\n" + 
				"        ORDER BY TO_CHAR(o1.confirm_date, 'YYYY-MM-DD') ASC, TO_CHAR(o1.confirm_date, 'HH24:MI:SS') ASC";

		pstmt = Oracledb.getInstance().getConnection().prepareStatement(sql);

		pstmt.setString(1, id);

		rs = pstmt.executeQuery();
		
		Order.buylist.clear();
		
		while(rs.next()) {
			Order data = new Order();
			
			data.setChecked(Boolean.FALSE);
			data.setOrder_code(rs.getString("order_code"));
			data.setDate(rs.getString("date"));
			data.setTime(rs.getString("time"));
			data.setCount(Integer.toString(rs.getInt("count")));
			data.setTotalPrice(rs.getString("totalprice"));
			
			Order.buylist.add(data);
		}
		
		pstmt.close();
		rs.close();
	}
}
