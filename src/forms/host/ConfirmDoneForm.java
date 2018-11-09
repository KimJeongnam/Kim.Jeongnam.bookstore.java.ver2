package forms.host;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import forms.panels.main.OrderListPanel;
import forms.tables.ConfirmDoneModel;
import forms.tables.OrderInfoTableModel;
import models.Code;
import models.Order;
import service.Services;

public class ConfirmDoneForm extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ConfirmDoneForm() {
		this.setTitle("Host");
		
		this.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
		panel.add(new JLabel("결제 완료  목록"));
		this.add(panel, "North");
		
		this.add(southPanel());
		
		this.setVisible(true);
		this.pack();
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				JFrame frame = (JFrame) e.getWindow();
				HostMenuForm.frames.remove(frame);
				frame.dispose();
			}
		});
		
	}
	
	private JPanel southPanel() {
		JPanel panel = new JPanel();
		Services.getInstance().getMap().get(Code.HOST_CONFIRM_DONE_LIST).activation();
		
		OrderListPanel tablepanel = new OrderListPanel(
				new ConfirmDoneModel(),
				new OrderInfoTableModel());
		
		tablepanel.setAdapter(new TableAdapter(tablepanel.getOrderListTable(), tablepanel));
		panel.add(tablepanel.setup());
		
		return panel;
	}


	class TableAdapter extends MouseAdapter{
	JTable table;
	OrderListPanel orderlistpanel;
	public TableAdapter(JTable table, OrderListPanel orderlistpanel) {
		this.table = table;
		this.orderlistpanel = orderlistpanel;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		
		String order_code = Order.buylist.get(row).getOrder_code();
		orderlistpanel.getSelectNo().setText(Integer.toString(row+1));
		
		orderlistpanel.orderInfoUpdate(order_code);
	}
}
}
