package service.guest;

import java.sql.SQLException;

import db.Oracledb;
import db.command.Command;
import db.command.guest.CartList_Command;
import service.Service;
import service.Session;

public class CartList implements Service{
	private Command command = null;
	@Override
	public void activation() {
		command = new CartList_Command(Session.getInstance().getId());
		
		try {
			command.execute();
		}catch(SQLException e) {
			Oracledb.printSQLError(e);
		}
	}

}
