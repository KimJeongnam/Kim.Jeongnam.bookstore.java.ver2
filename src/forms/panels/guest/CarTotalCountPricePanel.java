package forms.panels.guest;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import models.Cart;
import models.Code;
import service.Services;

public class CarTotalCountPricePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String totalCount = null;
	public static String totalPrice = null;
	
	private static JLabel label_totalCount = null;
	private static JLabel label_totalPrice = null;
	
	public CarTotalCountPricePanel() {
		JLabel label = null;
		BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(boxlayout);
		
		getCartCountAndTotalPrice();
		
		JPanel boxPanel = new JPanel();
		boxPanel.setPreferredSize(new Dimension(100,50));	//패널의 크기조정

		boxPanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder(), 
                "장바구니", 
                TitledBorder.CENTER, 
                TitledBorder.TOP)); 
		label_totalCount = new JLabel(totalCount);
		label = new JLabel("건");
		boxPanel.add(label_totalCount);
		boxPanel.add(label);
		this.add(boxPanel);
		
		label = new JLabel("총액 : ");
		label_totalPrice = new JLabel(totalPrice);
		JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		totalPanel.add(label);
		totalPanel.add(label_totalPrice);
		
		this.add(totalPanel);
	}
	
	public static void getCartCountAndTotalPrice() {
		Services.getInstance().getMap().get(Code.GUEST_CART_COUNT_TOTAL).activation();
	}
	
	
	public static void update() {
		getCartCountAndTotalPrice();
		label_totalCount.setText(totalCount);
		label_totalPrice.setText(totalPrice);
	}
}
