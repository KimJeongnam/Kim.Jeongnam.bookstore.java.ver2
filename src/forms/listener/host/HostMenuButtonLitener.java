package forms.listener.host;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import forms.host.HostMenuForm;
import models.Code;
import service.Services;

public class HostMenuButtonLitener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		
		switch(command) {
		case "책 추가":
			Services.getInstance().getMap().get(Code.HOST_BOOK_ADD).activation();
			try {
				HostMenuForm.getInstance().getTablepanel().update();
			}catch(NullPointerException e2) {
				e2.printStackTrace();
				System.err.println("HostMenuButtonLitener->HostMenuForm.getInstance()");
			}
			break;
			
		case "책 수정":
			Services.getInstance().getMap().get(Code.HOST_BOOK_UPDATE).activation();
			try {
				HostMenuForm.getInstance().getTablepanel().update();
			}catch(NullPointerException e2) {
				e2.printStackTrace();
				System.err.println("HostMenuButtonLitener->HostMenuForm.getInstance()");
			}
			break;
		case "책 삭제":
			Services.getInstance().getMap().get(Code.HOST_BOOK_DELETE).activation();
			try {
				HostMenuForm.getInstance().getTablepanel().update();
			}catch(NullPointerException e2) {
				e2.printStackTrace();
				System.err.println("HostMenuButtonLitener->HostMenuForm.getInstance()");
			}
			break;
		}
		
		
	}
	
}
