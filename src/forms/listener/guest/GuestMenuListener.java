package forms.listener.guest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import forms.guest.GuestCartForm;
import models.Code;
import service.Services;

public class GuestMenuListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String command = arg0.getActionCommand();
		
		switch(command) {
		case "장바구니에 추가":
			Services.getInstance().getMap().get(Code.GUEST_CART_ADD).activation();
			if(GuestCartForm.getInstance() != null) 
				GuestCartForm.getInstance().getCartTablePanel().update();
			break;
		case "바로 구매":
			break;
			
		case "장바구니 관리":
			GuestCartForm.createInstance();
			break;
		}
	}

}