package forms.host;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import forms.panels.main.OrderListPanel;
import forms.tables.ConfirmTableModel;
import forms.tables.OrderInfoTableModel;
import models.Code;
import models.ConfirmAsk;
import service.Services;

public class ConfirmListForm extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrderListPanel tablepanel;
	private Boolean buttonflag = false;
	
	public ConfirmListForm() {
		this.setTitle("주문 요청 목록");
		this.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
		panel.add(new JLabel("주문 요청 목록"));
		this.add(panel, "North");
		this.add(tablePanel());
		
		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public JPanel tablePanel() {
		JPanel panel = new JPanel(new BorderLayout());
		Services.getInstance().getMap().get(Code.HOST_CONFIRM_ASK_LIST).activation();
		tablepanel = new OrderListPanel(
				new ConfirmTableModel(),
				new OrderInfoTableModel());
		
		tablepanel.setAdapter(new ConfirmAskTableAdapter(tablepanel.getOrderListTable(), tablepanel));
		tablepanel.setOpaque(true);
		panel.add(tablepanel.setup(), "North");
		
		tablepanel.setTableCell(tablepanel.getOrderListTable(), 100, 6);
		tablepanel.setTableCell(tablepanel.getOrderListTable(), 20, 0);
		ActionListener l = e->  {
			String command = e.getActionCommand();
			
			switch(command) {
			case "전체 체크/해제":
				if(ConfirmAsk.list.size()== 0) {
					JOptionPane.showMessageDialog(null, "주문 요청 목록이 비어있습니다."
							, "Fail", JOptionPane.ERROR_MESSAGE);
					break;
				}
								
				if(!buttonflag) {
					for(ConfirmAsk d : ConfirmAsk.list) {
						d.setChecked(true);
					}
					buttonflag = true;
				}else {
					for(ConfirmAsk d : ConfirmAsk.list) {
						d.setChecked(false);
					}
					buttonflag = false;
				}
				tablepanel.orderListCheckUpdate(new ConfirmTableModel());	
				tablepanel.setTableCell(tablepanel.getOrderListTable(), 100, 6);
				tablepanel.setTableCell(tablepanel.getOrderListTable(), 20, 0);
				
				break;
			case "구매 승인":
				for(ConfirmAsk d : ConfirmAsk.list) {
					if(d.isChecked())
						System.out.println("Confirm() : "+d.getOrder_code());
				}
				break;
			}
		};
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
		JButton button = new JButton("전체 체크/해제");
		button.addActionListener(l);
		buttonPanel.add(button);
		
		button = new JButton("구매 승인");
		button.addActionListener(l);
		buttonPanel.add(button);
		
		panel.add(buttonPanel, "South");
		
		return panel;
	}
	
	public static void main(String[] args) {
		 SwingUtilities.invokeLater(new Runnable() {
			 public void run() {
				 new ConfirmListForm();
			 }
		 });
	}
}

class ConfirmAskTableAdapter extends MouseAdapter{
	JTable table;
	OrderListPanel orderlistpanel;
	public ConfirmAskTableAdapter(JTable table, OrderListPanel orderlistpanel) {
		this.table = table;
		this.orderlistpanel = orderlistpanel;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		
		ConfirmAsk data = ConfirmAsk.list.get(row);
		if(data.isChecked())
			data.setChecked(false);
		else
			data.setChecked(true);
		String order_code = data.getOrder_code();
		orderlistpanel.getSelectNo().setText(Integer.toString(row+1));
		
		orderlistpanel.orderListCheckUpdate(new ConfirmTableModel());
		orderlistpanel.orderInfoUpdate(order_code);
		
		orderlistpanel.setTableCell(orderlistpanel.getOrderListTable(), 100, 6);
		orderlistpanel.setTableCell(orderlistpanel.getOrderListTable(), 20, 0);
	}
}
