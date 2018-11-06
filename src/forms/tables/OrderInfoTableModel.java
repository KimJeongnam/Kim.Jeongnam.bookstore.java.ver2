package forms.tables;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import models.OrderInfo;

public class OrderInfoTableModel extends AbstractTableModel{

	private String[] columnNames = {"책 이름", "저자", "가격", "희망 수량"};
	private ArrayList<OrderInfo> list;
	
	public OrderInfoTableModel() {
		this.list = OrderInfo.list;
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		OrderInfo data = list.get(rowIndex);
		
		switch(columnIndex) {
		case 0:
			return data.getBook_name();
		case 1:
			return data.getAuthor();
		case 2:
			return data.getPrice();
		case 3:
			return data.getOrder_stock();
		}
		
		return null;
	}

}
