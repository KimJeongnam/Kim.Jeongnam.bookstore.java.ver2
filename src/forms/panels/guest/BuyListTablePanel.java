package forms.panels.guest;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import forms.panels.main.OrderListPanel;
import forms.tables.BuyListTableModel;
import forms.tables.OrderInfoTableModel;
import models.Code;
import models.Order;
import service.Services;
import service.guest.RefundAsk;

public class BuyListTablePanel extends JPanel{

	/**
	 * 환불요청시 보여줄 구매리스트
	 */
	private static final long serialVersionUID = 1L;
	private OrderListPanel tablepanel;
	private Boolean buttonToggle_flag = false;
	private ActionListener l = e->{
		String command = e.getActionCommand();
		
		switch(command) {
		case "전체 체크/해제":
			if(Order.buylist.size()== 0) {
				JOptionPane.showMessageDialog(null, "구매완료 목록이 비어있습니다."
						, "Fail", JOptionPane.ERROR_MESSAGE);
				break;
			}
			if(!buttonToggle_flag) {
				for(Order d: Order.buylist) {
					d.setChecked(true);
				}
				buttonToggle_flag = true;
			}else {
				for(Order d: Order.buylist) {
					d.setChecked(false);
				}
				buttonToggle_flag = false;
			}
			listTableUpdate();
			break;
		case "환불 요청":
			if(Order.buylist.size()== 0) {
				JOptionPane.showMessageDialog(null, "구매완료 목록이 비어있습니다."
						, "Fail", JOptionPane.ERROR_MESSAGE);
				break;
			}
			int result = JOptionPane.showConfirmDialog(null,  "총 "+Order.getCheckCount(Order.buylist)+"건 환불요청 하시겠습니까?", 
					"환불 요청"
					, JOptionPane.OK_CANCEL_OPTION);
			if(result!=0)
				break;
			
			ArrayList<String> order_codes = new ArrayList<String>();
			for(Order o : Order.buylist) {
				if(o.isChecked())
					order_codes.add(o.getOrder_code());
			}
			new RefundAsk(order_codes).activation();
			initBuyList();
			listTableUpdate();
			infoTableUpdate();
			break;
		}
	};
	
	public BuyListTablePanel() {
		this.setLayout(new BorderLayout());
		initBuyList();
		
		//테이블 패널 생성
		tablepanel = new OrderListPanel(
				new BuyListTableModel(),
				new OrderInfoTableModel());
		
		//어댑터 설정
		tablepanel.setAdapter(new BuyListTableAdapter(tablepanel.getOrderListTable(), tablepanel));
		
		tablepanel.setOpaque(true);	// true면 불투명
		this.add(tablepanel.setup(), "North"); // 어뎁터 추가 후 setup하며 바깥 패널에 추가 
		tablepanel.setBorderName("구매완료 목록");
		setTabellcell();
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
		JButton button = new JButton("전체 체크/해제");
		button.addActionListener(l);
		buttonPanel.add(button);
		
		button = new JButton("환불 요청");
		button.addActionListener(l);
		buttonPanel.add(button);
		
		this.add(buttonPanel, "South"); 
		
	}
	
	private void listTableUpdate() {
		tablepanel.orderListCheckUpdate(new BuyListTableModel());	
		setTabellcell();
	}
	
	private void infoTableUpdate() {
		tablepanel.getSelectNo().setText("None");
		
		tablepanel.orderListCheckUpdate(new BuyListTableModel());
		tablepanel.orderInfoUpdate("");
		
		setTabellcell();
	}
	
	private void initBuyList() {
		Services.getInstance().getMap().get(Code.GUEST_BUY_LIST).activation();
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
			
			Order data = Order.buylist.get(row);
			
			if(data.isChecked())
				data.setChecked(false);
			else
				data.setChecked(true);
			String order_code = data.getOrder_code();
			orderlistpanel.getSelectNo().setText(Integer.toString(row+1));
			
			orderlistpanel.orderListCheckUpdate(new BuyListTableModel());
			orderlistpanel.orderInfoUpdate(order_code);
			
			orderlistpanel.setTableCell(orderlistpanel.getOrderListTable(), 100, 5);
			orderlistpanel.setTableCell(orderlistpanel.getOrderListTable(), 20, 0);
		}
	}
}


