package Forms.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Forms.MainForm;
import Models.Code;
import service.MainImpl;

public class MainButtonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String command = arg0.getActionCommand();
		JPanel cardPanel = MainForm.getInstance().getCardPanel();
		JButton hostLoginbtn = MainForm.getInstance().getBtn_Host();
		JButton guestLoginbtn = MainForm.getInstance().getBtn_Guest();
		JButton signUpbtn = MainForm.getInstance().getBtn_SignUp();
		
		switch(command) {
		case "관리자 Login":
			hostLoginbtn.setEnabled(false);
			guestLoginbtn.setEnabled(true);
			signUpbtn.setEnabled(true);
			MainForm.getInstance().getCardLayout().show(cardPanel, Code.PERMISSION_HOST);
			break;
		case "고 객 Login":
			hostLoginbtn.setEnabled(true);
			guestLoginbtn.setEnabled(false);
			signUpbtn.setEnabled(true);
			MainForm.getInstance().getCardLayout().show(cardPanel, Code.PERMISSION_GUEST);
			break;
		case "회원 가입":
			hostLoginbtn.setEnabled(true);
			guestLoginbtn.setEnabled(true);
			signUpbtn.setEnabled(false);
			MainForm.getInstance().getCardLayout().show(cardPanel, "SignUp");
			break;
		case "Sign UP!":
			MainImpl.getInstance().userAdd();
			break;
		case Code.PERMISSION_HOST+" Login":
			MainImpl.getInstance().HostLogin();
			break;
		case Code.PERMISSION_GUEST+" Login":
			MainImpl.getInstance().GuestLogin();
			break;
		}
	}

}
