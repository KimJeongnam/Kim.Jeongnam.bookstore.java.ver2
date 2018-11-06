package db.command.host;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import db.Oracledb;
import db.command.Command;
import models.Book;
import models.CreateCode;

public class BookAdd_Command implements Command {
	PreparedStatement pstmt;
	private String book_name = null;
	private String author = null;
	private String price = null;
	private String stock = null;

	public BookAdd_Command(Book book) {
		this.book_name = book.getBook_name();
		this.author = book.getAuthor();
		this.price = book.getPrice();
		this.stock = book.getStock();
	}
	
	@Override
	public void execute() throws SQLException {
		// TODO Auto-generated method stub
		int rowsInserted = 0;

		while (rowsInserted == 0) {
			String book_code = CreateCode.bookCode();

			String sql = "INSERT INTO books(book_code, book_name, author, price, stock, delete_status)" + 
					"    VALUES(?, INITCAP(?), ?, ?, ?, 0)";

			pstmt = Oracledb.getInstance().getConnection().prepareStatement(sql);

			pstmt.setString(1, book_code);
			pstmt.setString(2, book_name);
			pstmt.setString(3, author);
			pstmt.setString(4, price);
			pstmt.setString(5, stock);

			rowsInserted = pstmt.executeUpdate();
		}
		
		if(rowsInserted>0)
			JOptionPane.showMessageDialog(null, "책 추가 완료", "Insert Success"
					, JOptionPane.INFORMATION_MESSAGE);
		pstmt.close();
	}
}
