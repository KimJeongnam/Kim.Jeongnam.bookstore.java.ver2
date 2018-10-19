package service;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import DB.Command;
import DB.mysqldb;
import DB.user_command.Login_Command;
import DB.user_command.createUser_Command;
import Forms.GuestMenuForm;
import Forms.HostMenuForm;
import Forms.MainForm;
import Forms.Panels.LoginPanel;
import Models.Code;

public class MainImpl implements MainService{
	private static MainImpl mainImpl = new MainImpl();
	
	public static MainImpl getInstance() { return mainImpl; }
	
	private Command command = null;
	
	@Override
	public void GuestLogin() {
		// TODO Auto-generated method stub
		command = new Login_Command(Code.PERMISSION_GUEST);
		try {
			command.execute();
		}catch(SQLException e) {
			mysqldb.printSQLError(e);
		}
		
		if(Session.getInstance().isStatus()) {
			LoginPanel.getGuestInstance().getTf_id().setText("");
			LoginPanel.getGuestInstance().getTf_pw().setText("");
			JOptionPane.showMessageDialog(null, Session.getInstance().getId()+
					"님 로그인 되었습니다.", "login success", JOptionPane.INFORMATION_MESSAGE);
			GuestMenuForm.getInstance().setVisible(true);
			MainForm.getInstance().setVisible(false);
			System.out.println(Session.getInstance().getId()+" login success");
		}
	}

	@Override
	public void HostLogin() {
		// TODO Auto-generated method stub
		command = new Login_Command(Code.PERMISSION_HOST);
		try {
			command.execute();
		}catch(SQLException e) {
			mysqldb.printSQLError(e);
		}
		
		if(Session.getInstance().isStatus()) {
			LoginPanel.getHostInstance().getTf_id().setText("");
			LoginPanel.getHostInstance().getTf_pw().setText("");
			JOptionPane.showMessageDialog(null, "관리자님 로그인 되었습니다.", "login success", JOptionPane.INFORMATION_MESSAGE);
			HostMenuForm.getInstance().setVisible(true);
			MainForm.getInstance().setVisible(false);
			System.out.println("host login success");
		}
	}

	@Override
	public void userAdd() {
		// TODO Auto-generated method stub
		command = new createUser_Command();
		try {
			command.execute();
		}catch(SQLException e) {
			mysqldb.printSQLError(e);
		}
	}

}
