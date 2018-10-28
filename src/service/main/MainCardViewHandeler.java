package service.main;

import javax.swing.JButton;
import javax.swing.JPanel;

import forms.MainForm;
import models.Code;
import service.Service;

public class MainCardViewHandeler implements Service{
	private int option;
	private JPanel cardPanel;
	private JButton hostLoginbtn;
	private JButton guestLoginbtn;
	private JButton signUpbtn;
	
	public MainCardViewHandeler(int option) {
		this.option = option;
		cardPanel = MainForm.getInstance().getCardPanel();
		hostLoginbtn = MainForm.getInstance().getBtn_Host();
		guestLoginbtn = MainForm.getInstance().getBtn_Guest();
		signUpbtn = MainForm.getInstance().getBtn_SignUp();
	}
	
	@Override
	public void activation() {
		// TODO Auto-generated method stub
		selectedShow();
	}

	private void selectedShow() {
		switch(option) {
		case Code.MAIN_GUST_LOGIN_VIEW:
			hostLoginbtn.setEnabled(true);
			guestLoginbtn.setEnabled(false);
			signUpbtn.setEnabled(true);
			
			MainForm.getInstance().getCardLayout().show(cardPanel, Code.PERMISSION_GUEST);
			break;
		case Code.MAIN_HOST_LOGIN_VIEW:
			hostLoginbtn.setEnabled(false);
			guestLoginbtn.setEnabled(true);
			signUpbtn.setEnabled(true);
			
			MainForm.getInstance().getCardLayout().show(cardPanel, Code.PERMISSION_HOST);
			break;
		case Code.MAIN_USER_ADD_VIEW:
			hostLoginbtn.setEnabled(true);
			guestLoginbtn.setEnabled(true);
			signUpbtn.setEnabled(false);
			
			MainForm.getInstance().getCardLayout().show(cardPanel, "SignUp");
			break;
		}
	}
}
