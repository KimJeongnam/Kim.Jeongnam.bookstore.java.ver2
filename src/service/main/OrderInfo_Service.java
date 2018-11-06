package service.main;

import java.sql.SQLException;

import db.Oracledb;
import db.command.Command;
import db.command.main.OrderInfo_Command;
import service.Service;

public class OrderInfo_Service implements Service{
	private String order_code;
	
	public OrderInfo_Service(String order_code) {
		this.order_code = order_code;
	}
	
	@Override
	public void activation() {
		// TODO Auto-generated method stub
		Command command = new OrderInfo_Command(order_code);
		
		try {
		command.execute();
		}catch(SQLException e) {
			Oracledb.printSQLError(e);
		}
	}

}
