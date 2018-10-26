package service.main.host;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import db.Oracledb;
import db.command.Command;
import db.command.host.BookAdd_Command;
import models.Book;
import service.Service;

public class BookAdd implements Service{
	Command command;
	
	@Override
	public void activation() {
		// TODO Auto-generated method stub
		Book book = Book.validation("add");
		if(book == null)
			return;
		command = new BookAdd_Command(book);
		try {
			command.execute();
		}catch(SQLException e) {
			if(e.getMessage().contains("unique")) 
				JOptionPane.showMessageDialog(null, "책 이름이 중복 됩니다."
						, "Book Add Fail!", JOptionPane.ERROR_MESSAGE);
			else if(e.getMessage().contains("BOOKS_PRICE_CK"))
				JOptionPane.showMessageDialog(null, "가격은 0보다 커야합니다."
						, "Book Add Fail!", JOptionPane.ERROR_MESSAGE);
			else if(e.getMessage().contains("BOOKS_STOCK_CK"))
				JOptionPane.showMessageDialog(null, "수량은 0이상입니다."
						, "Book Add Fail!", JOptionPane.ERROR_MESSAGE);
			Oracledb.printSQLError(e);
		}
	}
}
