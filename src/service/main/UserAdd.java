package service.main;

import java.sql.SQLException;

import DB.Command;
import DB.Oracledb;
import DB.user_command.createUser_Command;
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
