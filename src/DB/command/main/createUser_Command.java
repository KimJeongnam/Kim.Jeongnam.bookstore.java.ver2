package db.command.main;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import db.Oracledb;
import db.command.Command;
import forms.panels.main.SignUpPanel;
import models.Code;

public class createUser_Command implements Command{
	PreparedStatement pstmt;
	Statement stmt;

	@Override
	public void execute() throws SQLException {
		// TODO Auto-generated method stub
		

		String id = SignUpPanel.getInstance().getTf_id().getText();
		@SuppressWarnings("deprecation")
		String pw = SignUpPanel.getInstance().getTf_pw().getText();
		
		/*String sql = "INSERT INTO users VALUES('"+id+"', '"+pw+"', '"+Code.PERMISSION_GUEST+"')";*/
		String sql = "INSERT INTO users VALUES(?, ?, ?)";
		
		pstmt = Oracledb.getInstance().getConnection().prepareStatement(sql);
		
		pstmt.setString(1, id);
		pstmt.setString(2, pw);
		pstmt.setString(3, Code.PERMISSION_GUEST);
		
		/*stmt = Oracledb.getInstance().getStatement();*/

		int rowsInserted = pstmt.executeUpdate();
		if (rowsInserted > 0) {
			JOptionPane.showMessageDialog(null, "회원 가입 완료", "Signup success", JOptionPane.INFORMATION_MESSAGE);
			System.out.println("A new user was inserted successfully!");
		}else {
			JOptionPane.showMessageDialog(null, "이미 있는 id 입니다. 다른 id를 입력해 주세요.", "Signup Fail!", JOptionPane.ERROR_MESSAGE);
			System.out.println("createUser_Command.execute() user id 중복");
		}
		pstmt.close();

	}

}
