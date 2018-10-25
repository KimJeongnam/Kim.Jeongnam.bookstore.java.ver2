package forms.listener.host;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HostMenuButtonLitener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		
		switch(command) {
		case "책 추가|수정":
			break;
		}
		
	}
	
}
