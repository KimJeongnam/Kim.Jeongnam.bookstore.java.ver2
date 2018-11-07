package forms.panels.guest;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import forms.panels.main.OrderListPanel;
import forms.tables.BuyAskTableModel;
import forms.tables.OrderInfoTableModel;
import models.BuyAsk;
import models.Code;
import service.Services;

public class BuyAskTablePanel extends JPanel{
	
	public BuyAskTablePanel() {
		Services.getInstance().getMap().get(Code.GUEST_BUY_ASK_LIST).activation();
		
		OrderListPanel panel = new OrderListPanel(
				new BuyAskTableModel(),
				new OrderInfoTableModel());
		
		panel.setAdapter(new BuyAskTableAdapter(panel.getOrderListTable(), panel));
		this.add(panel.setup());
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

class BuyAskTableAdapter extends MouseAdapter{
	JTable table;
	OrderListPanel orderlistpanel;
	public BuyAskTableAdapter(JTable table, OrderListPanel orderlistpanel) {
		this.table = table;
		this.orderlistpanel = orderlistpanel;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		
		String order_code = BuyAsk.list.get(row).getOrder_code();
		orderlistpanel.getSelectNo().setText(Integer.toString(row+1));
		
		
		orderlistpanel.orderInfoUpdate(order_code);
	}
}