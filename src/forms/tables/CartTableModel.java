package forms.tables;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import models.Cart;

/**
 * 	장바구니 테이블 모델
 */
public class CartTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private String[] columns = {"Book_code", "책 제목", "저자", "단가", "구매 수량", "결제 금액"};
	private ArrayList<Cart> list = null;
	
	public CartTableModel(ArrayList<Cart> list) {
		this.list = list;
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columns.length;
	}

	public String getColumnName(int col) {
		return columns[col];
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Cart cart = list.get(row);
		
		switch(col) {
		case 0:
			return cart.getBook_code();
		case 1:
			return cart.getBook_name();
		case 2:
			return cart.getAuthor();
		case 3:
			return cart.getPrice();
		case 4:
			return cart.getWish_stock();
		case 5:
			return cart.getAllPrice();
		}
		
		return null;
	}

}
