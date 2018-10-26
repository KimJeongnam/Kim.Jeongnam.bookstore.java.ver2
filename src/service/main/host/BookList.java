package service.main.host;

import java.sql.SQLException;

import db.Oracledb;
import db.command.Command;
import db.command.host.BookList_Command;
import models.Book;
import service.Service;

public class BookList implements Service {
	Command command;

	@Override
	public void activation() {
		// TODO Auto-generated method stub
		command = new BookList_Command();

		try {
			command.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Oracledb.printSQLError(e);
		}

		System.out.println("getBooks()");
		for (Book book : Book.getShelfList()) {
			System.out.println(book);
		}
	}
}
