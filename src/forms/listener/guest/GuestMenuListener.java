package forms.listener.guest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import forms.guest.GuestBuyAskForm;
import forms.guest.GuestCartForm;
import forms.guest.GuestMenuForm;
import forms.guest.RefundAskForm;
import forms.panels.guest.CarTotalCountPricePanel;
import forms.panels.guest.CartManagePanel;
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
			break;
		case "바로 구매":
			Services.getInstance().getMap().get(Code.GUEST_NOW_BUY).activation();
			break;
			
		case "장바구니 관리":
			GuestMenuForm.frames.add(GuestCartForm.createInstance());
			break;
		case "구매요청 목록":
			GuestMenuForm.frames.add(new GuestBuyAskForm());
			break;
		case "환불 요청":
			GuestMenuForm.frames.add(new RefundAskForm());
			break;
		}
		
		update();
	}
	
	private void update() {
		CartManagePanel.clear();
		if(GuestCartForm.getInstance() != null) 
			GuestCartForm.getInstance().getCartTablePanel().update();
		GuestMenuForm.getInstance().getBookTablePanel().update();
		CarTotalCountPricePanel.update();
	}

}
