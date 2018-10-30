package service.main.host;

import java.sql.SQLException;

import db.Oracledb;
import db.command.Command;
import db.command.guest.CartCountAndTotal_Command;
import service.Service;
import service.Session;

public class GetCartCount_TotalPrice implements Service{
	Command command;
	
	@Override
	public void activation() {
		// TODO Auto-generated method stub
		command = new CartCountAndTotal_Command(Session.getInstance().getId());
		
		try {
			command.execute();	
		}catch(SQLException e) {
			Oracledb.printSQLError(e);
		}
		
	}
	
}
