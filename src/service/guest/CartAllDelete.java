package service.guest;

import java.sql.SQLException;

import db.Oracledb;
import db.command.Command;
import db.command.guest.CartAllDelete_Command;
import service.Service;
import service.Session;

public class CartAllDelete implements Service{

	@Override
	public void activation() {	
		Command command = new CartAllDelete_Command(Session.getInstance().getId());
		try {
			command.execute();
		}catch(SQLException e) {
			Oracledb.printSQLError(e);
		}
	}
}
