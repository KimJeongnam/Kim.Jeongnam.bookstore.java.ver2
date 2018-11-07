package service.main.host;

import java.sql.SQLException;
import java.util.ArrayList;

import db.Oracledb;
import db.command.Command;
import db.command.host.OderConfirm_Command;
import service.Service;

public class OrderConfirm implements Service{
	private ArrayList<String> orders;
	
	public OrderConfirm(ArrayList<String> orders) {
		this.orders = orders;
	}

	@Override
	public void activation() {
		// TODO Auto-generated method stub
		Command command = new OderConfirm_Command(orders);
		
		try {
			command.execute();
		}catch(SQLException e) {
			Oracledb.printSQLError(e);
		}
	}

}
