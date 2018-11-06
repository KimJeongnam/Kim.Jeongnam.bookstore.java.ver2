package service.guest;

import java.sql.SQLException;

import db.Oracledb;
import db.command.Command;
import db.command.guest.BuyAskList_Command;
import service.Service;
import service.Session;

public class BuyAskList implements Service{

	@Override
	public void activation() {
		// TODO Auto-generated method stub
		/*Session.getInstance().getId()*/
		Command command = new BuyAskList_Command(Session.getInstance().getId());
		
		try {
			command.execute();
		}catch(SQLException e) {
			Oracledb.printSQLError(e);
		}
	}
}
