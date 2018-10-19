package DB.user_command;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DB.Command;
import DB.Oracledb;
import Forms.Panels.LoginPanel;
import Models.Code;
import service.Session;

public class Login_Command implements Command{
	PreparedStatement pstmt;
	ResultSet rs;
	String id = "";
	String pw = "";
	String permission = "";
	
	@SuppressWarnings("deprecation")
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
		
		String sql = "SELECT user_pw FROM users WHERE user_id=? AND permission=?";
		
		pstmt = Oracledb.getInstance().getConnection().prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, permission);

		rs = pstmt.executeQuery();
		
		if (rs.next()) {
			String user_pw = rs.getString(1);
			if(user_pw.equals(pw)) {
				Session.getInstance().setId(id);
				Session.getInstance().setStatus(true);
			}else {
				JOptionPane.showMessageDialog(null, "password가 다릅니다.", "Login Fail!", JOptionPane.ERROR_MESSAGE);
				Session.getInstance().setStatus(false);
			}
		}else
			JOptionPane.showMessageDialog(null, "등록되지 않은 id 입니다.", "Login Fail!", JOptionPane.ERROR_MESSAGE);
		
		pstmt.close();
		rs.close();
	}

}
