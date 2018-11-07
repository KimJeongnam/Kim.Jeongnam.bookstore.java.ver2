package forms.listener.host;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import forms.host.HostMenuForm;
import forms.host.OrderMenuForm;
import forms.panels.host.InsertBookPanel;
import models.Code;
import service.Services;

public class HostMenuButtonLitener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		int result = 0;
		
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
			result = JOptionPane.showConfirmDialog(null,  "책을 수정하시겠습니까?", "책 코드 : "+
					InsertBookPanel.getInstance().getLabel_bookCode().getText().trim()
					, JOptionPane.OK_CANCEL_OPTION);
			
			if(result!=0)	return;
			
			Services.getInstance().getMap().get(Code.HOST_BOOK_UPDATE).activation();
			try {
				HostMenuForm.getInstance().getTablepanel().update();
			}catch(NullPointerException e2) {
				e2.printStackTrace();
				System.err.println("HostMenuButtonLitener->HostMenuForm.getInstance()");
			}
			break;
		case "책 삭제":
			result = JOptionPane.showConfirmDialog(null,  "책을 삭제하시겠습니까?", "책 코드 : "+
					InsertBookPanel.getInstance().getLabel_bookCode().getText().trim()
					, JOptionPane.OK_CANCEL_OPTION);
			
			if(result!=0)	return;
			
			Services.getInstance().getMap().get(Code.HOST_BOOK_DELETE).activation();
			try {
				HostMenuForm.getInstance().getTablepanel().update();
			}catch(NullPointerException e2) {
				e2.printStackTrace();
				System.err.println("HostMenuButtonLitener->HostMenuForm.getInstance()");
			}
			break;
		case "주문 관리":
			new OrderMenuForm();
			break;
		}
		
		
	}
	
}
