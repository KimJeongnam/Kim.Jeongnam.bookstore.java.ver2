package db.command.guest;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import db.Oracledb;
import db.command.Command;

public class CartDelete_Command implements Command{
	private PreparedStatement pstmt;
	private String bookCode;
	private String id;

	public CartDelete_Command(String bookCode, String id) {
		this.bookCode = bookCode;
		this.id = id;
	}
	@Override
	public void execute() throws SQLException {
		int rowsInserted = 0;
		
		String sql = "DELETE FROM carts\r\n" + 
				"	WHERE user_id=? \r\n" + 
				"	AND book_code=?";

		pstmt = Oracledb.getInstance().getConnection().prepareStatement(sql);

		pstmt.setString(1, id);
		pstmt.setString(2, bookCode);

		rowsInserted = pstmt.executeUpdate();
		
		if(rowsInserted >0)
			JOptionPane.showMessageDialog(null, "'"+bookCode+"' 장바구니 삭제 완료", "Success"
					, JOptionPane.INFORMATION_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, "삭제할 항목을 선택한 후 진행해 주세요!"
					, "Fail!", JOptionPane.ERROR_MESSAGE);
		
		pstmt.close();
	}

}
