package forms.tables;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import models.Order;

public class ConfirmDoneModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	String[] columnNames = {"No.", "결제 승인  날짜", "승인 시간", "건수", "총 액"};
	private ArrayList<Order> list;
	
	
	public ConfirmDoneModel() {
		this.list = Order.buylist;
	}
	public int getRowCount() {
        return list.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Order data = list.get(rowIndex);
        
       switch(columnIndex) {
       case 0:
    	   Integer row = rowIndex+1;
    	   return row;
       case 1:
    	   return data.getDate();
       case 2:
    	   return data.getTime();
       case 3:
    	   return Integer.parseInt(data.getCount());
       case 4:
    	   return data.getTotalPrice();
    	   default:
    		   return null;
       }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    // This method is used by the JTable to define the default
    // renderer or editor for each cell. For example if you have
    // a boolean data it will be rendered as a check box. A
    // number value is right aligned.
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }
}
