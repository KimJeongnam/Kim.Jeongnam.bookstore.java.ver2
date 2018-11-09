package forms.host;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import forms.panels.guest.CartManagePanel;
import forms.panels.host.TotalPricePanel;
import forms.panels.main.OrderListPanel;
import forms.tables.ConfirmTableModel;
import forms.tables.OrderInfoTableModel;
import models.Code;
import models.Order;
import service.Services;
import service.main.host.OrderConfirm;

public class ConfirmListForm extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrderListPanel tablepanel;
	private Boolean buttonflag = false;
	private ActionListener l = e->  {
		String command = e.getActionCommand();
		
		switch(command) {
		case "전체 체크/해제":
			if(Order.confirmAsklist.size()== 0) {
				JOptionPane.showMessageDialog(null, "주문 요청 목록이 비어있습니다."
						, "Fail", JOptionPane.ERROR_MESSAGE);
				break;
			}
			
			if(!buttonflag) {
				for(Order d : Order.confirmAsklist) {
					d.setChecked(true);
				}
				buttonflag = true;
			}else {
				for(Order d : Order.confirmAsklist) {
					d.setChecked(false);
				}
				buttonflag = false;
			}
			listtableUpdate();
			
			break;
		case "구매 승인":
			if(Order.confirmAsklist.size() == 0){ 
				JOptionPane.showMessageDialog(null, "주문 요청 목록이 비어있습니다."
					, "Fail", JOptionPane.ERROR_MESSAGE); 
				break;
			}
			int result = JOptionPane.showConfirmDialog(null,  "총 "+Order.getCheckCount(Order.confirmAsklist)+"건 구매 승인 하시겠습니까?", "책 코드 :"+
					CartManagePanel.getLabel_bookCode()+ "수량 : "+CartManagePanel.getTf_wishStock()
					, JOptionPane.OK_CANCEL_OPTION);
			if(result!=0)
				break;
			ArrayList<String> order_codes = new ArrayList<String>();
			for(Order d : Order.confirmAsklist) {
				if(d.isChecked())
					order_codes.add(d.getOrder_code());
			}
			new OrderConfirm(order_codes).activation();
			Services.getInstance().getMap().get(Code.HOST_CONFIRM_ASK_LIST).activation();
			listtableUpdate();
			infotableUpdate();
			TotalPricePanel.update();
			break;
		}
	};
	
	public ConfirmListForm() {
		this.setTitle("주문 요청 목록");
		this.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
		panel.add(new JLabel("주문 요청 목록"));
		this.add(panel, "North");
		this.add(tablePanel());
		
		this.setVisible(true);
		this.pack();
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				JFrame frame = (JFrame)e.getWindow();
				HostMenuForm.frames.remove(frame);
				frame.dispose();
			}
		});
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
	private void listtableUpdate() {
		tablepanel.orderListCheckUpdate(new ConfirmTableModel());	
		tablepanel.setTableCell(tablepanel.getOrderListTable(), 100, 6);
		tablepanel.setTableCell(tablepanel.getOrderListTable(), 20, 0);
	}
	
	private void infotableUpdate() {
		tablepanel.getSelectNo().setText("None");
		
		tablepanel.orderListCheckUpdate(new ConfirmTableModel());
		tablepanel.orderInfoUpdate("");
		
		tablepanel.setTableCell(tablepanel.getOrderListTable(), 100, 6);
		tablepanel.setTableCell(tablepanel.getOrderListTable(), 20, 0);
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
		
		Order data = Order.confirmAsklist.get(row);
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
