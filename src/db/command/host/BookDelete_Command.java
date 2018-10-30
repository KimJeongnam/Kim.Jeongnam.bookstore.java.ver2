package db.command.host;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import db.Oracledb;
import db.command.Command;
import models.Book;

public class BookDelete_Command implements Command{
	PreparedStatement pstmt;
	private String book_code = null;

	public BookDelete_Command(Book book) {
		this.book_code = book.getBook_code();
	}

	@Override
	public void execute() throws SQLException {
		// TODO Auto-generated method stub
		int rowsInserted = 0;


		String sql = "UPDATE books SET delete_status=1 WHERE book_code=?";

		pstmt = Oracledb.getInstance().getConnection().prepareStatement(sql);

		pstmt.setString(1, book_code);

		rowsInserted = pstmt.executeUpdate();
		
		if(rowsInserted==0)
			JOptionPane.showMessageDialog(null, "수정을 원하는 책을 테이블에서 책을 선택 후 진행해주세요!", "Book Code 오류"
					, JOptionPane.WARNING_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, "책 삭제 완료", "Delete Success"
					, JOptionPane.INFORMATION_MESSAGE);
		
		pstmt.close();
	}

}
