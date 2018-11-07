package service.main.host;

import java.sql.SQLException;

import db.Oracledb;
import db.command.Command;
import db.command.host.ConfirmList_Command;
import service.Service;

public class ConfirmAskList implements Service{

	@Override
	public void activation() {
		// TODO Auto-generated method stub
		Command command = new ConfirmList_Command();
		
		try {
			command.execute();
		}catch(SQLException e) {
			Oracledb.printSQLError(e);
		}
	}
	
}
