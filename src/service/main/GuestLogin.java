package service.main;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import db.Oracledb;
import db.command.Command;
import db.command.main.Login_Command;
import forms.MainForm;
import forms.guest.GuestMenuForm;
import forms.panels.main.LoginPanel;
import models.Code;
import service.Service;
import service.Session;

/*
 * 게스트 로그인 서비스
 */

public class GuestLogin implements Service{

	@Override
	public void activation() {
		// TODO Auto-generated method stub
		Command command = new Login_Command(Code.PERMISSION_GUEST);
		
		try {
			command.execute();
		}catch(SQLException e) {
			Oracledb.printSQLError(e);
		}
		LoginPanel.getGuestInstance().getTf_id().setText("");
		LoginPanel.getGuestInstance().getTf_pw().setText("");		
		if(Session.getInstance().isStatus()) {
			JOptionPane.showMessageDialog(null, Session.getInstance().getId()+
					"님 로그인 되었습니다.", "login success", JOptionPane.INFORMATION_MESSAGE);
			
			GuestMenuForm.getInstance().getLabelUserId().setText(
					"User Id : "+ Session.getInstance().getId());
			
			GuestMenuForm.getInstance().setVisible(true);
			MainForm.getInstance().setVisible(false);
			System.out.println(Session.getInstance().getId()+" login success");
		}
	}

}
