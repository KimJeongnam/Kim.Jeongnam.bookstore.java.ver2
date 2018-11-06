package db.command.guest;

import java.sql.SQLException;
import java.util.ArrayList;

import models.Cart;

public class CartBuy_Command extends NowBuy_Command{

	public CartBuy_Command(String id, ArrayList<Cart> cartList) {
		super(id, cartList);
	}
	
	@Override
	public void execute() throws SQLException{
		super.execute();
		new CartAllDelete_Command(id, "메롱~").execute();
	}
	
}
