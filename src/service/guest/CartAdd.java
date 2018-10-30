package service.guest;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import bookstore.exception.PriceStockException;
import db.Oracledb;
import db.command.Command;
import db.command.guest.CartAdd_Command;
import forms.guest.GuestMenuForm;
import forms.panels.guest.CarTotalCountPricePanel;
import forms.panels.guest.CartAddNowBuyPanel;
import service.Service;
import service.Session;

public class CartAdd implements Service{
	Command command;
	@Override
	public void activation() {
		// TODO Auto-generated method stub
		String id = Session.getInstance().getId();
		String book_code = CartAddNowBuyPanel.getSelectBook_code().trim();
		String strStock = CartAddNowBuyPanel.getTfWishStock().trim();
		
		if(book_code.length() == 0) {
			JOptionPane.showMessageDialog(null, "책목록에서 책을 선택후 진행해주세요!"
					, "Book Code Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int wish_stock = PriceStockException.validation(strStock);
		
		if(wish_stock == -1)
			return;
		
		command = new CartAdd_Command(id, book_code, wish_stock);
		
		try {
			command.execute();
			CartAddNowBuyPanel.setTfWishStock("");
			CarTotalCountPricePanel.update();
		}catch(SQLException e) {
			Oracledb.printSQLError(e);
		}
	}

}
