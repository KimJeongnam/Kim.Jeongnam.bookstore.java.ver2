package db;

import java.sql.SQLException;

import db.command.Command;
import db.command.host.getbooks_Command;
import models.Book;

public class Db_TestMain {
	public static void main(String[] args) {
		Command command = new getbooks_Command();
		
		try {
			command.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Oracledb.printSQLError(e);
		}
		
		for(Book book : Book.getShelfList()) {
			System.out.println(book);
		}
	}
}
