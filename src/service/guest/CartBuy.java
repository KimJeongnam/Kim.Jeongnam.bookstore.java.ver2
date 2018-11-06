package service.guest;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import db.Oracledb;
import db.command.Command;
import db.command.guest.CartBuy_Command;
import models.Cart;
import service.Service;
import service.Session;

public class CartBuy implements Service{

	@Override
	public void activation() {
		Command command = new CartBuy_Command(Session.getInstance().getId(), Cart.getList());
		try {
			command.execute();
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()
					, "구매 요청 실패", JOptionPane.ERROR_MESSAGE);
			Oracledb.printSQLError(e);
		}
	}
}
