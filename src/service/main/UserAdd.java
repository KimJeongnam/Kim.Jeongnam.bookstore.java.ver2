package service.main;

import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;

import db.Oracledb;
import db.command.Command;
import db.command.main.CreateUser_Command;
import forms.MainForm;
import service.Service;

/*
 * 유저 추가 서비스
 */
public class UserAdd implements Service{

	@Override
	public void activation() {
		// TODO Auto-generated method stub
		JPanel cardPanel = MainForm.getInstance().getCardPanel();
		JButton hostLoginbtn = MainForm.getInstance().getBtn_Host();
		JButton guestLoginbtn = MainForm.getInstance().getBtn_Guest();
		JButton signUpbtn = MainForm.getInstance().getBtn_SignUp();
		
		hostLoginbtn.setEnabled(true);
		guestLoginbtn.setEnabled(true);
		signUpbtn.setEnabled(false);
		
		Command command = new CreateUser_Command();
		try {
			command.execute();
		}catch(SQLException e) {
			Oracledb.printSQLError(e);
		}
	}

}
