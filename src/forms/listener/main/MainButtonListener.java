package forms.listener.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import forms.MainForm;
import models.Code;
import service.Services;
import service.main.MainCardViewHandeler;

public class MainButtonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String command = arg0.getActionCommand();
/*		JPanel cardPanel = MainForm.getInstance().getCardPanel();
		JButton hostLoginbtn = MainForm.getInstance().getBtn_Host();
		JButton guestLoginbtn = MainForm.getInstance().getBtn_Guest();
		JButton signUpbtn = MainForm.getInstance().getBtn_SignUp();*/
		
		switch(command) {
		case "관리자 Login":
			new MainCardViewHandeler(Code.MAIN_HOST_LOGIN_VIEW).activation();
			break;
		case "고 객 Login":
			new MainCardViewHandeler(Code.MAIN_GUST_LOGIN_VIEW).activation();
			break;
		case "회원 가입":
			new MainCardViewHandeler(Code.MAIN_USER_ADD_VIEW).activation();
			break;
		case Code.PERMISSION_HOST+" Login":
			/*MainImpl.getInstance().HostLogin();*/
			Services.getInstance().getMap().get(Code.MAIN_HOST_LOGIN).activation();
			break;
		case Code.PERMISSION_GUEST+" Login":
			/*MainImpl.getInstance().GuestLogin();*/
			Services.getInstance().getMap().get(Code.MAIN_GUEST_LOGIN).activation();
			break;
		case "Sign UP!":
			/*MainImpl.getInstance().userAdd();*/
			Services.getInstance().getMap().get(Code.MAIN_USER_ADD).activation();
			break;
		}
	}

}
