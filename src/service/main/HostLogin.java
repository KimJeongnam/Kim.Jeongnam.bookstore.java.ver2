package service.main;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import DB.Command;
import DB.Oracledb;
import DB.user_command.Login_Command;
import Forms.HostMenuForm;
import Forms.MainForm;
import Forms.Panels.LoginPanel;
import Models.Code;
import service.Service;
import service.Session;

/*
 * 호스트 로그인 서비스
 */
public class HostLogin implements Service{

	@Override
	public void activation() {
		// TODO Auto-generated method stub
		Command command = new Login_Command(Code.PERMISSION_HOST);
		try {
			command.execute();
		}catch(SQLException e) {
			Oracledb.printSQLError(e);
		}
		LoginPanel.getHostInstance().getTf_id().setText("");
		LoginPanel.getHostInstance().getTf_pw().setText("");
		
		if(Session.getInstance().isStatus()) {
			JOptionPane.showMessageDialog(null, "관리자님 로그인 되었습니다.", "login success", JOptionPane.INFORMATION_MESSAGE);
			HostMenuForm.getInstance();
			MainForm.getInstance().setVisible(false);
			System.out.println("host login success");
		}
	}

}
