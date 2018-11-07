package forms.panels.host;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import models.TotalPrice;
import service.main.host.GetTotalPrice;

public class TotalPricePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private static JLabel label_totalPrice = null;
	
	public TotalPricePanel() {
		JLabel label = null;
		
		getTotalPrice();
		
		JPanel boxPanel = new JPanel();
		boxPanel.setPreferredSize(new Dimension(300,50));	//패널의 크기조정

		boxPanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder(), 
                "총 결산", 
                TitledBorder.CENTER, 
                TitledBorder.TOP)); 
		label_totalPrice = new JLabel(TotalPrice.totalPrice);
		label = new JLabel("총액 : ");
		
		boxPanel.add(label);
		boxPanel.add(label_totalPrice);
		this.add(boxPanel);
		
	}
	
	public static void getTotalPrice() {
		/*Services.getInstance().getMap().get(Code.GUEST_CART_COUNT_TOTAL).activation();*/
		new GetTotalPrice().activation();
	}
	
	public static void update() {
		getTotalPrice();
		label_totalPrice.setText(TotalPrice.totalPrice);
	}
}
