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

import forms.panels.host.TotalPricePanel;
import forms.panels.main.OrderListPanel;
import forms.tables.OrderInfoTableModel;
import forms.tables.RefundTableModel;
import models.Code;
import models.Order;
import service.Services;
import service.main.host.RefundConfirm;

public class RefundTableForm extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrderListPanel tablepanel;
	private Boolean buttonToggle_flag = false;
	private ActionListener l = e->{
		String command = e.getActionCommand();
		
		switch(command) {
		case "전체 체크/해제":
			if(Order.refundlist.size()== 0) {
				JOptionPane.showMessageDialog(null, "환불요청 목록이 비어있습니다."
						, "Fail", JOptionPane.ERROR_MESSAGE);
				break;
			}
			if(!buttonToggle_flag) {
				for(Order d: Order.refundlist) {
					d.setChecked(true);
				}
				buttonToggle_flag = true;
			}else {
				for(Order d: Order.refundlist) {
					d.setChecked(false);
				}
				buttonToggle_flag = false;
			}
			listTableUpdate();
			break;
		case "환불 요청 승인":
			if(Order.refundlist.size()== 0) {
				JOptionPane.showMessageDialog(null, "구매완료 목록이 비어있습니다."
						, "Fail", JOptionPane.ERROR_MESSAGE);
				break;
			}
			int result = JOptionPane.showConfirmDialog(null,  "총 "+Order.getCheckCount(Order.refundlist)+"건 환불요청 승인을 하시겠습니까?", 
					"환불 요청 승인"
					, JOptionPane.OK_CANCEL_OPTION);
			if(result!=0)
				break;
			
			ArrayList<String> order_codes = new ArrayList<String>();
			for(Order o : Order.refundlist) {
				if(o.isChecked())
					order_codes.add(o.getOrder_code());
			}
			
			/*new RefundAsk(order_codes).activation();*/
			new RefundConfirm(order_codes).activation();
			
			initList();
			listTableUpdate();
			infoTableUpdate();
			HostMenuForm.getInstance().getTablepanel().update();
			TotalPricePanel.update();
			break;
		}
	};
	
	public RefundTableForm() {
		this.setLayout(new BorderLayout());
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
		panel.add(new JLabel("환불 요청 승인"));
		
		this.add(panel, "North");
		
		this.add(southPanel(), "South");
		
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
	
	private JPanel southPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		
		initList();
		
		//테이블 패널 생성
		tablepanel = new OrderListPanel(
				new RefundTableModel(),
				new OrderInfoTableModel());
		
		//어댑터 설정
		tablepanel.setAdapter(new BuyListTableAdapter(tablepanel.getOrderListTable(), tablepanel));
		
		tablepanel.setOpaque(true);	// true면 불투명
		panel.add(tablepanel.setup(), "North"); // 어뎁터 추가 후 setup하며 바깥 패널에 추가 
		tablepanel.setBorderName("환불 요청 목록");
		setTabellcell();
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
		JButton button = new JButton("전체 체크/해제");
		button.addActionListener(l);
		buttonPanel.add(button);
		
		button = new JButton("환불 요청 승인");
		button.addActionListener(l);
		buttonPanel.add(button);
		
		panel.add(buttonPanel, "South"); 
		
		return panel;
	}
	
	private void listTableUpdate() {
		tablepanel.orderListCheckUpdate(new RefundTableModel());	
		setTabellcell();
	}
	
	private void infoTableUpdate() {
		tablepanel.getSelectNo().setText("None");
		
		tablepanel.orderListCheckUpdate(new RefundTableModel());
		tablepanel.orderInfoUpdate("");
		
		setTabellcell();
	}
	
	private void initList() {
		Services.getInstance().getMap().get(Code.HOST_REFUND_LIST).activation();
	}
	
	private void setTabellcell() {
		if(tablepanel != null) {
			tablepanel.setTableCell(tablepanel.getOrderListTable(), 100, 5);
			tablepanel.setTableCell(tablepanel.getOrderListTable(), 20, 0);
		}
	}
	
	class BuyListTableAdapter extends MouseAdapter{
		JTable table;
		OrderListPanel orderlistpanel;
		public BuyListTableAdapter(JTable table, OrderListPanel orderlistpanel) {
			this.table = table;
			this.orderlistpanel = orderlistpanel;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			int row = table.getSelectedRow();
			
			Order data = Order.refundlist.get(row);
			
			if(data.isChecked())
				data.setChecked(false);
			else
				data.setChecked(true);
			
			String order_code = data.getOrder_code();
			orderlistpanel.getSelectNo().setText(Integer.toString(row+1));
			
			orderlistpanel.orderListCheckUpdate(new RefundTableModel());
			orderlistpanel.orderInfoUpdate(order_code);
			
			orderlistpanel.setTableCell(orderlistpanel.getOrderListTable(), 100, 5);
			orderlistpanel.setTableCell(orderlistpanel.getOrderListTable(), 20, 0);
		}
	}
	
/*	public static void main(String[] args) {
		new RefundTableForm();
	}*/
}
