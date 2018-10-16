package DB.user_command;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DB.Command;
import DB.mysqldb;
import Forms.Panels.LoginPanel;
import Models.Code;
import service.Session;

public class Login_Command implements Command{
	Statement stmt;
	ResultSet rs;
	String id = "";
	String pw = "";
	String permission = "";
	
	public Login_Command(String permission) {
		this.permission = permission;
		
		switch(permission) {
		case Code.PERMISSION_GUEST:
			id = LoginPanel.getGuestInstance().getTf_id().getText();
			pw = LoginPanel.getGuestInstance().getTf_pw().getText();
			break;
		case Code.PERMISSION_HOST:
			id = LoginPanel.getHostInstance().getTf_id().getText();
			pw = LoginPanel.getHostInstance().getTf_pw().getText();
			break;
		}
	}
	
	@Override
	public void execute() throws SQLException{
		// TODO Auto-generated method stub
		
		stmt = mysqldb.getInstance().getStatement();
		
		String sql = "SELECT user_pw, PASSWORD('"+pw+"') FROM USERS WHERE user_id='"
				+id
				+"' AND permission='"+permission+"'";

		rs = stmt.executeQuery(sql);

		if (rs.next()) {
			String user_pw = rs.getString(1);
			String input_pw = rs.getString(2);
			if(user_pw.equals(input_pw)) {
				Session.getInstance().setId(id);
				Session.getInstance().setStatus(true);
			}else {
				JOptionPane.showMessageDialog(null, "password가 다릅니다.", "Login Fail!", JOptionPane.ERROR_MESSAGE);
				Session.getInstance().setStatus(false);
			}
		}else
			JOptionPane.showMessageDialog(null, "등록되지 않은 id 입니다.", "Login Fail!", JOptionPane.ERROR_MESSAGE);
		
		stmt.close();
		rs.close();
	}

}
