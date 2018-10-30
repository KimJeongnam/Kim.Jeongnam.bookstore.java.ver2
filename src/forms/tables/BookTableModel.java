package forms.tables;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import models.Book;

public class BookTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] columnNames = { "Book Code", "제목", "저자", "가격", "수량" };
	private ArrayList<Book> list;
	
	public BookTableModel(ArrayList<Book> list){
		this.list = list;
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
		// TODO Auto-generated method stub
		Book t = list.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return ((Book) t).getBook_code();
		case 1:
			return ((Book) t).getBook_name();
		case 2:
			return ((Book) t).getAuthor();
		case 3:
			return ((Book) t).getPrice();
		case 4:
			return ((Book) t).getStock();
		default:
			return null;
		}
	}
	
	public Object getRowClass(int row) {
		return list.get(row);
	}
}
