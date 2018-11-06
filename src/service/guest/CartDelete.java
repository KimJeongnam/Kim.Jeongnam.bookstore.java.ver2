package service.guest;

import java.sql.SQLException;

import db.Oracledb;
import db.command.Command;
import db.command.guest.CartDelete_Command;
import forms.panels.guest.CartManagePanel;
import service.Service;
import service.Session;

public class CartDelete implements Service{

	@Override
	public void activation() {	
		Command command = new CartDelete_Command(CartManagePanel.getLabel_bookCode()
				, Session.getInstance().getId());
		try {
			command.execute();
		}catch(SQLException e) {
			Oracledb.printSQLError(e);
		}
	}

}
