package forms.panels.main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import forms.tables.OrderInfoTableModel;
import models.BuyAsk;
import models.OrderInfo;
import service.Service;
import service.main.OrderInfo_Service;

public class OrderListPanel extends MyTablePanel{

	/**
	 *  주문 목록 & 구매요청 목록 & 환불요청 등등 기본 Table 패널
	 */
	private static final long serialVersionUID = 1L;
	private JTable orderListTable = null;
	private JTable orderInfoTable = null;
	AbstractTableModel orderListModel = null;
	AbstractTableModel orderInfoModel = null;
	
	private JLabel selectNo = null;
	
	public OrderListPanel(Service service, AbstractTableModel orderListModel, AbstractTableModel orderInfoModel) {
		service.activation();
		this.orderListModel = orderListModel;
		this.orderInfoModel = orderInfoModel;
		
		this.setLayout(new BorderLayout());
		
		JPanel panel= new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
		panel.add(orderListPanel());
		
		this.add(panel, "West");
		
		this.add(new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15)).add(new JLabel("▶")), "Center");
		
		panel= new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
		panel.add(orderInfoPanel());
		
		this.add(panel, "East");
	}
	
	private JPanel orderListPanel() {
		JPanel panel = new JPanel();
		
		
		panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder(), 
				"목록",
                TitledBorder.LEFT, 
                TitledBorder.TOP));
		
		if(orderListModel == null) return null;
		
		orderListTable = new JTable(orderListModel);
		orderListTable.addMouseListener(new BuyAskTableAdapter(orderListTable, this));
		DefaultTableRenderer(orderListTable);
		orderListTable.getTableHeader().setReorderingAllowed(false);
		orderListTable.getTableHeader().setResizingAllowed(false);
		
		setTableCell(orderListTable, 100);
		
		JScrollPane scrollPane = new JScrollPane(orderListTable);
		
		panel.add(scrollPane);
		
		return panel;
	}

	
	private JPanel orderInfoPanel() {
		JPanel panel = new JPanel();
		OrderInfo.list.clear();
		
		if(orderInfoModel == null) return null;
		
		panel.setLayout(new BorderLayout());
		
		panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder(), 
				"상세 내역",
                TitledBorder.LEFT, 
                TitledBorder.TOP));
		                                     
		orderInfoTable = new JTable(orderInfoModel);
		DefaultTableRenderer(orderInfoTable);
		orderInfoTable.getTableHeader().setReorderingAllowed(false);
		orderInfoTable.getTableHeader().setResizingAllowed(false);
		
		setTableCell(orderInfoTable, 100, 2);
		
		JScrollPane scrollPane = new JScrollPane(orderInfoTable);
		
		JPanel labelPaenl = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		labelPaenl.add(new JLabel("선택된 No : "));
		selectNo = new JLabel("none");
		labelPaenl.add(selectNo);
		
		panel.add(labelPaenl, "North");
		panel.add(scrollPane, "South");
		
		return panel;
	}
	
	public void orderInfoUpdate(String order_code) {
		new OrderInfo_Service(order_code).activation();
		
		OrderInfoTableModel model = new OrderInfoTableModel();
		orderInfoTable.setModel(model);
		DefaultTableRenderer(orderInfoTable);
		setTableCell(orderInfoTable, 100, 2);
	}
	
	public void addOrderListTableListener(MouseAdapter adapter) {
		if(orderListTable != null) {
			orderListTable.addMouseListener(adapter);
		}
	}
	
	
	public void addOrderInfoTableListener(MouseAdapter adapter) {
		if(orderInfoTable != null) {
			orderInfoTable.addMouseListener(adapter);
		}
	}

	public JTable getOrderListTable() {
		return orderListTable;
	}

	public void setOrderListTable(JTable orderListTable) {
		this.orderListTable = orderListTable;
	}

	public JTable getOrderInfoTable() {
		return orderInfoTable;
	}

	public void setOrderInfoTable(JTable orderInfoTable) {
		this.orderInfoTable = orderInfoTable;
	}
	
	public JLabel getSelectNo() {
		return selectNo;
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