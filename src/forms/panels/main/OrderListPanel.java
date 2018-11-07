package forms.panels.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;

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
	protected JTable orderListTable = null;
	protected JTable orderInfoTable = null;
/*	private AbstractTableModel orderListModel = null;
	private AbstractTableModel orderInfoModel = null;*/
	private MouseAdapter adapter = null;
	private JLabel selectNo = null;
	
	public OrderListPanel(AbstractTableModel orderListModel, AbstractTableModel orderInfoModel) {
		orderListTable = new JTable(orderListModel);
		orderInfoTable = new JTable(orderInfoModel);
	}
	
	public JPanel setup() {
		if(adapter == null)
			
		this.setLayout(new BorderLayout());
		
		JPanel panel= new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
		panel.add(orderListPanel());
		
		this.add(panel, "West");
		
		this.add(new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15)).add(new JLabel("▶")), "Center");
		
		panel= new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
		panel.add(orderInfoPanel());
		
		this.add(panel, "East");
		return this;
	}
	
	private JPanel orderListPanel() {
		JPanel panel = new JPanel();
		
		
		panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder(), 
				"목록",
                TitledBorder.LEFT, 
                TitledBorder.TOP));
		
		orderListTable.addMouseListener(adapter);
		/*DefaultTableRenderer(orderListTable);*/
		orderListTable.getTableHeader().setReorderingAllowed(false);
		orderListTable.getTableHeader().setResizingAllowed(false);
		orderListTable.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(orderListTable);
		scrollPane.setPreferredSize(new Dimension(650, 500));
		
		panel.add(scrollPane);
		
		return panel;
	}

	
	private JPanel orderInfoPanel() {
		JPanel panel = new JPanel();
		OrderInfo.list.clear();

		
		panel.setLayout(new BorderLayout());
		
		panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder(), 
				"상세 내역",
                TitledBorder.LEFT, 
                TitledBorder.TOP));
		                                     
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
	
	public void orderListCheckUpdate(AbstractTableModel model) {
		if(model == null) return;
		orderListTable.setModel(model);
	}
	
	
	public void orderInfoUpdate(String order_code) {
		new OrderInfo_Service(order_code).activation();
		
		OrderInfoTableModel model = new OrderInfoTableModel();
		orderInfoTable.setModel(model);
		DefaultTableRenderer(orderInfoTable);
		setTableCell(orderInfoTable, 100, 2);
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

	public MouseAdapter getAdapter() {
		return adapter;
	}

	public void setAdapter(MouseAdapter adapter) {
		this.adapter = adapter;
	}

}

