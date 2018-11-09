package service.main.host;

import java.sql.SQLException;

import db.Oracledb;
import db.command.Command;
import db.command.host.RefundList_Command;
import service.Service;

public class RefundList implements Service{

	@Override
	public void activation() {
		// TODO Auto-generated method stub
		Command command = new RefundList_Command();
		
		try {
			command.execute();
		}catch(SQLException e) {
			Oracledb.printSQLError(e);
		}
	}

}
