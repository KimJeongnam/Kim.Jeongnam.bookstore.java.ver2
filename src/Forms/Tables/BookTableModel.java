package Forms.Tables;

import javax.swing.table.AbstractTableModel;

public class BookTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String[] columnNames  = {"Book Code", "제목", "저자", "가격", "수량"};
	private Object[][] datas;
	
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return datas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return datas[rowIndex][columnIndex];
	}

}
