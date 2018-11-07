package service.guest;

import java.sql.SQLException;

import db.Oracledb;
import db.command.Command;
import db.command.guest.BuyList_Command;
import service.Service;
import service.Session;

public class BuyList implements Service{

	@Override
	public void activation() {
		// TODO Auto-generated method stub
		Command command = new BuyList_Command(Session.getInstance().getId());
		
		try {
			command.execute();
		}catch(SQLException e) {
			Oracledb.printSQLError(e);
		}
	}
}
