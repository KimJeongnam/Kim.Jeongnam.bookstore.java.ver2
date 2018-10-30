package db.command.host;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import db.Oracledb;
import db.command.Command;
import models.Book;

public class BookUpdate_Command implements Command{
	PreparedStatement pstmt;
	private String book_code = null;
	private String book_name = null;
	private String author = null;
	private String price = null;
	private String stock = null;
	
	public BookUpdate_Command(Book book) {
		this.book_code = book.getBook_code();
		this.book_name = book.getBook_name();
		this.author = book.getAuthor();
		this.price = book.getPrice();
		this.stock =  book.getStock();
	}

	@Override
	public void execute() throws SQLException {
		// TODO Auto-generated method stub
		int rowsInserted = 0;


		String sql = "UPDATE books SET book_name=INITCAP(?), author=?, price=?, stock=?  \r\n"
				+ "    WHERE book_code=?";

		pstmt = Oracledb.getInstance().getConnection().prepareStatement(sql);

		pstmt.setString(1, book_name);
		pstmt.setString(2, author);
		pstmt.setString(3, price);
		pstmt.setString(4, stock);
		pstmt.setString(5, book_code);

		rowsInserted = pstmt.executeUpdate();
		
		if(rowsInserted==0)
			JOptionPane.showMessageDialog(null, "테이블에서 수정할 책을 선택 후 진행하세요!"
					, "Book Update Fail!", JOptionPane.WARNING_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, "책 수정 완료", "Insert Success"
					, JOptionPane.INFORMATION_MESSAGE);
		pstmt.close();
	}

}
