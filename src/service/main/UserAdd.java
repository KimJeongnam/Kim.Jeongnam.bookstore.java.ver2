package service.main;

import java.sql.SQLException;

import db.Oracledb;
import db.command.Command;
import db.command.main.createUser_Command;
import service.Service;

/*
 * 유저 추가 서비스
 */
public class UserAdd implements Service{

	@Override
	public void activation() {
		// TODO Auto-generated method stub
		Command command = new createUser_Command();
		try {
			command.execute();
		}catch(SQLException e) {
			Oracledb.printSQLError(e);
		}
	}

}
