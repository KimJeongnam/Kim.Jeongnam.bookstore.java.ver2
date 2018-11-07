package forms.tables;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import models.Order;

public class ConfirmTableModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] columnNames = {"No.", "Check", "요청인", "Date", "Time", "건수", "총 액"};
	private ArrayList<Order> list;
	
	/*Object[][] data = null;*/
	
	public ConfirmTableModel() {
		this.list = Order.confirmAsklist;
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
    	   return rowIndex+1;
       case 1:
    	   return data.isChecked();
       case 2:
    	   return data.getUser_id();
       case 3:
    	   return data.getDate();
       case 4:
    	   return data.getTime();
       case 5:
    	   return Integer.parseInt(data.getCount());
       case 6:
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
