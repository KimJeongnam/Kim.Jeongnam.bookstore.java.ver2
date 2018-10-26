package service.main.host;

import java.sql.SQLException;

import db.Oracledb;
import db.command.Command;
import db.command.host.BookDelete_Command;
import models.Book;
import service.Service;

public class BookDelete implements Service{
	private Command command;
	@Override
	public void activation() {
		// TODO Auto-generated method stub
		Book book = Book.validation("delete");
		if(book == null)
			return;
		command = new BookDelete_Command(book);
		try {
			command.execute();
		}catch(SQLException e) {
			Oracledb.printSQLError(e);
		}
	}
}
