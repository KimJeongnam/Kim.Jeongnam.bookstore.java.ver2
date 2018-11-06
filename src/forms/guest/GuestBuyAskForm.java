package forms.guest;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import forms.panels.guest.BuyAskTablePanel;

public class GuestBuyAskForm extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GuestBuyAskForm() {
		this.setTitle("구매요청 목록");
		
		this.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
		panel.add(new JLabel("구매 요청 목록"));
		this.add(panel, "North");
		
		this.add(new BuyAskTablePanel());
		
		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
