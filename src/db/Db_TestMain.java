package db;

import bookstore.exception.PriceStockException;
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
		
		System.out.println(CreateCode.run());
		
		String num = "-101";
		
		int n = PriceStockException.validation(num);
		
		System.out.println(n);
	}
}
