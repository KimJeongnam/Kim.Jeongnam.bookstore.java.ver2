package forms.panels.guest;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import forms.panels.main.MyTablePanel;
import forms.tables.CartTableModel;
import models.Cart;
import models.Code;
import service.Services;

public class CartTablePanel extends MyTablePanel{

	/**
	 * 	장바구니 테이블 패널
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable table = null;
	
	public CartTablePanel() {
		getCartList();
		this.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		this.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder(), 
                "장바구니 목록", 
                TitledBorder.CENTER, 
                TitledBorder.TOP));
		
		
		AbstractTableModel tableModel = new CartTableModel(Cart.getList());
		
		table = new JTable(tableModel);
		table.addMouseListener(new CartTableClickEvent(table));
		DefaultTableRenderer(table);
		table.getTableHeader().setReorderingAllowed(false); // 셀 이동 불가
		table.getTableHeader().setResizingAllowed(false);	// 셀 크기 조정 불가
		
		JScrollPane scrollPane;
		
		// 입력한 컬럼의 셀 좌우크기를 키우는 함수
		setTableCell(table, 200, 0,3,5);
		setTableCell(table, 130, 1,2,4);
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(650, 500));
		this.add(scrollPane);
	}
	
	public void update() {
		getCartList();
		AbstractTableModel tableModel = new CartTableModel(Cart.getList());
		table.setModel(tableModel);
		DefaultTableRenderer(table);
		setTableCell(table, 200, 0,3,5);
		setTableCell(table, 130, 1,2,4);
	}
	
	public void getCartList() {
		Services.getInstance().getMap().get(Code.GUEST_CART_LIST).activation();
	}
	
	public JTable getTable() {
		return table;
	}

}

class CartTableClickEvent extends MouseAdapter{
	private JTable table;
	
	public CartTableClickEvent(JTable table) {
		this.table = table;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		
		CartUpdateDeletePanel.setLabel_bookCode((String)table.getValueAt(row, 0));
		CartUpdateDeletePanel.setLabel_bookName((String)table.getValueAt(row, 1));
		CartUpdateDeletePanel.setTf_wishStock((String)table.getValueAt(row, 4));
	}
}
