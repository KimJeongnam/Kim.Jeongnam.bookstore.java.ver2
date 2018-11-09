package service.main.host;

import java.sql.SQLException;
import java.util.ArrayList;

import db.Oracledb;
import db.command.Command;
import db.command.host.RefundConfirm_Command;
import service.Service;

public class RefundConfirm implements Service{
	private ArrayList<String> order_codes;
	
	public RefundConfirm(ArrayList<String> order_codes) {
		this.order_codes = order_codes;
	}
	@Override
	public void activation() {
		Command command = new RefundConfirm_Command(order_codes);
		
		try {
			command.execute();
		}catch(SQLException e) {
			Oracledb.printSQLError(e);
		}
	}
}
