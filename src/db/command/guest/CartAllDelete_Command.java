package db.command.guest;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import db.Oracledb;
import db.command.Command;

public class CartAllDelete_Command implements Command{
	private PreparedStatement pstmt;
	private String[] args;

	public CartAllDelete_Command(String ...args) {
		this.args = args;
	}
	@Override
	public void execute() throws SQLException {
		int rowsInserted = 0;
		
		String sql = "DELETE FROM carts\r\n" + 
				"	WHERE user_id=?";

		pstmt = Oracledb.getInstance().getConnection().prepareStatement(sql);

		pstmt.setString(1, args[0]);

		rowsInserted = pstmt.executeUpdate();
		
		if(args.length==1) {
			if(rowsInserted >0)
				JOptionPane.showMessageDialog(null, "장바구니 전체 삭제 완료", "Success"
						, JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "삭제할 항목이 없습니다."
						, "Fail!", JOptionPane.ERROR_MESSAGE);
		}
		
		pstmt.close();
	}
}
