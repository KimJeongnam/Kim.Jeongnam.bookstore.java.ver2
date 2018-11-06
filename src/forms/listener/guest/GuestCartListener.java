package forms.listener.guest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import forms.guest.GuestCartForm;
import forms.guest.GuestMenuForm;
import forms.panels.guest.CarTotalCountPricePanel;
import forms.panels.guest.CartManagePanel;
import models.Code;
import service.Services;

public class GuestCartListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String command = arg0.getActionCommand();
		int result = 0;
		switch(command) {
		case "수정":
			result = JOptionPane.showConfirmDialog(null,  "정말 수정하시겠습니까?", "책 코드 :"+
					CartManagePanel.getLabel_bookCode()+ "수량 : "+CartManagePanel.getTf_wishStock()
					, JOptionPane.OK_CANCEL_OPTION);
			if(result!=0)
				return;
			Services.getInstance().getMap().get(Code.GUEST_CART_UPDATE).activation();
			break;
		
		case "삭제":
			result = JOptionPane.showConfirmDialog(null,  "정말 삭제하시겠습니까?", "책 코드 :"+
					CartManagePanel.getLabel_bookCode()+ "수량 : "+CartManagePanel.getTf_wishStock()
					, JOptionPane.OK_CANCEL_OPTION);
			if(result!=0)
				return;
			Services.getInstance().getMap().get(Code.GUEST_CART_DEL).activation();
			break;
		case "전체 삭제":
			result = JOptionPane.showConfirmDialog(null,  "정말 장바구니 전체를 삭제하시겠습니까?", "장바구니 전체 삭제"
					, JOptionPane.OK_CANCEL_OPTION);
			if(result!=0)
				return;
			Services.getInstance().getMap().get(Code.GUEST_CART_ALLDEL).activation();
			break;
		case "구매 하기":
			result = JOptionPane.showConfirmDialog(null,  "현재 장바구니를 구매 요청 하시겠습니까?", "장바구니 구매 요청"
					, JOptionPane.OK_CANCEL_OPTION);
			if(result!=0)
				return;
			Services.getInstance().getMap().get(Code.GUEST_CART_BUY).activation();
			break;
		}
		
		update();
	}
	
	private void update() {
		CartManagePanel.clear();
		GuestCartForm.getInstance().getCartTablePanel().update();
		GuestMenuForm.getInstance().getBookTablePanel().update();
		CarTotalCountPricePanel.update();
	}
	
}
