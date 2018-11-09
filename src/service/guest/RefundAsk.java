package service.guest;

import java.sql.SQLException;
import java.util.ArrayList;

import db.Oracledb;
import db.command.Command;
import db.command.guest.RefundAsk_Command;
import service.Service;

public class RefundAsk implements Service{
	private ArrayList<String> orders;
	
	public RefundAsk(ArrayList<String> orders) {
		this.orders = orders;
	}
	
	@Override
	public void activation() {
		// TODO Auto-generated method stub
		Command command = new RefundAsk_Command(orders);
		
		try {
			command.execute();
		}catch(SQLException e) {
			Oracledb.printSQLError(e);
		}
	}

}
