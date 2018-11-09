package service.main.host;

import java.sql.SQLException;

import db.Oracledb;
import db.command.Command;
import db.command.host.ConfirmDoneList_Command;
import service.Service;

public class ConfirmDoneList implements Service{

	@Override
	public void activation() {
		// TODO Auto-generated method stub
		Command command = new ConfirmDoneList_Command();
		
		try {
			command.execute();
		}catch(SQLException e) {
			Oracledb.printSQLError(e);
		}
	}

}
