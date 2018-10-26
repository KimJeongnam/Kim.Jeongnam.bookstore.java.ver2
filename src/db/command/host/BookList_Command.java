package db.command.host;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.Oracledb;
import db.command.Command;
import models.Book;

public class BookList_Command implements Command{
	Statement stmt;
	ResultSet rs;

	@Override
	public void execute() throws SQLException{
		// TODO Auto-generated method stub
		
		String sql = "SELECT book_code" + 
				", book_name" + 
				", author" + 
				", TO_CHAR(price, 'L999,999,999') as price" + 
				", TO_CHAR(stock) as stock " + 
				" FROM books"+
				" WHERE delete_status <> 1"+
				" ORDER BY book_name ASC";
		
		stmt = Oracledb.getInstance().getStatement();
		
		rs = stmt.executeQuery(sql);
		
		Book.getShelfList().clear();	// 기존 책장 초기화
		
		//ResultSet에 데이터가 없을때 까지 반복
		while (rs.next()) {
			Book book = new Book();
			book.setBook_code(rs.getString("book_code"));
			book.setBook_name(rs.getString("book_name"));
			book.setAuthor(rs.getString("author"));
			book.setPrice(rs.getString("price"));
			book.setStock(rs.getString("stock"));
			
			Book.getShelfList().add(book);	// 책장에 책추가
		}
		
		stmt.close();
		rs.close();
	}
}
