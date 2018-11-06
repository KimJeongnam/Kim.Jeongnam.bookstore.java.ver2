package service.guest;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import db.Oracledb;
import db.command.Command;
import db.command.guest.NowBuy_Command;
import forms.panels.guest.CartAddNowBuyPanel;
import models.Cart;
import service.Service;
import service.Session;

public class NowBuy implements Service{
	
	@Override
	public void activation() {
		String id = Session.getInstance().getId();
		String book_code = CartAddNowBuyPanel.getSelectBook_code().trim();
		String strStock = CartAddNowBuyPanel.getTfWishStock().trim();
		
		Cart cart = new Cart();
		cart.setBook_code(book_code);
		cart.setWish_stock(strStock);
		ArrayList<Cart> data = new ArrayList<Cart>();
		data.add(cart);
		
		Command command = new NowBuy_Command(id, data);
		
		try {
			command.execute();
			CartAddNowBuyPanel.setTfWishStock("");
			CartAddNowBuyPanel.setSelectBook_code("");
			CartAddNowBuyPanel.setSelectBook_name("");
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()
					, "구매 요청 실패", JOptionPane.ERROR_MESSAGE);
			Oracledb.printSQLError(e);
		}
		
	}
	
}
