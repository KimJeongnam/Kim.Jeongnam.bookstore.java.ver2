package service.guest;

import java.sql.SQLException;

import bookstore.exception.PriceStockException;
import db.Oracledb;
import db.command.Command;
import db.command.guest.CartUpdate_Command;
import forms.panels.guest.CartManagePanel;
import service.Service;
import service.Session;

public class CartUpdate implements Service{
	
	@Override
	public void activation() {
		// TODO Auto-generated method stub
		int stock = PriceStockException.validation(CartManagePanel.getTf_wishStock().trim());
		
		if(stock<0)
			return;
		
		Command command = new CartUpdate_Command(CartManagePanel.getLabel_bookCode()
				, Session.getInstance().getId(), stock);
		
		try {
			command.execute();
		}catch(SQLException e) {
			Oracledb.printSQLError(e);
		}
		
		
	}

}
