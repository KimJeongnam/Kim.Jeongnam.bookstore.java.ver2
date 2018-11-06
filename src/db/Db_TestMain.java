package db;

import models.CreateCode;

public class Db_TestMain {
	public static void main(String[] args) {
		/*Command command = new getbooks_Command();
		
		try {
			command.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Oracledb.printSQLError(e);
		}
		
		for(Book book : Book.getShelfList()) {
			System.out.println(book);
		}*/
		System.out.println(CreateCode.orderCode());
		System.out.println(CreateCode.bookCode());
		
	}
}
