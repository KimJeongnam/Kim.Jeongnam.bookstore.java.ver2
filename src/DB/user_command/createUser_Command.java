package DB.user_command;

import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import DB.Command;
import DB.mysqldb;
import Forms.Panels.SignUpPanel;
import Models.Code;

public class createUser_Command implements Command{
	Statement stmt;

	@Override
	public void execute() throws SQLException {
		// TODO Auto-generated method stub
		

		String id = SignUpPanel.getInstance().getTf_id().getText();
		@SuppressWarnings("deprecation")
		String pw = SignUpPanel.getInstance().getTf_pw().getText();
		
		String sql = "INSERT INTO USERS VALUES('"+id+"', PASSWORD('"+pw+"'), '"+Code.PERMISSION_GUEST+"')";
		
		stmt = mysqldb.getInstance().getStatement();

		int rowsInserted = stmt.executeUpdate(sql);
		if (rowsInserted > 0) {
			JOptionPane.showMessageDialog(null, "회원 가입 완료", "Signup success", JOptionPane.INFORMATION_MESSAGE);
			System.out.println("A new user was inserted successfully!");
		}else {
			JOptionPane.showMessageDialog(null, "이미 있는 id 입니다. 다른 id를 입력해 주세요.", "Signup Fail!", JOptionPane.ERROR_MESSAGE);
			System.out.println("createUser_Command.execute() user id 중복");
		}
		stmt.close();

	}

}
