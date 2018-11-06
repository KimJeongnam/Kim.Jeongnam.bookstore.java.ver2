package forms.panels.guest;


import javax.swing.JFrame;

import forms.panels.main.OrderListPanel;
import forms.tables.BuyAskTableModel;
import forms.tables.OrderInfoTableModel;
import models.Code;
import service.Services;

public class BuyAskTablePanel extends OrderListPanel{
	
	public BuyAskTablePanel() {
		super(Services.getInstance().getMap().get(Code.GUEST_BUY_ASK_LIST),
				new BuyAskTableModel(),
				new OrderInfoTableModel());
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		BuyAskTablePanel panel = new BuyAskTablePanel();
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

