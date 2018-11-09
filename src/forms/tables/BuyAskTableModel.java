package forms.tables;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import models.BuyAsk;

public class BuyAskTableModel extends AbstractTableModel{
	/**
	 *  구매요청 목록 테이블 모델
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnNames = {"No.", "요청 날짜", "요청 시간", "건수", "총 액"};
	private ArrayList<BuyAsk> list;
	
	public BuyAskTableModel() {
		this.list = BuyAsk.list;
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
		BuyAsk data = list.get(rowIndex);
		
		switch(columnIndex){
		case 0:
			return rowIndex+1;
		case 1:
			return data.getDate();
		case 2:
			return data.getTime();
		case 3:
			return Integer.parseInt(data.getCount());
		case 4:
			return data.getTotalPrice();
		}
		
		return null;
	}

}
