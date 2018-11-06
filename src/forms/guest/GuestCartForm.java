package forms.guest;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import forms.panels.guest.CartTablePanel;
import forms.panels.guest.CartManagePanel;


public class GuestCartForm extends JFrame{

	private static final long serialVersionUID = 1L;
	private static GuestCartForm instance = null;
	public static synchronized GuestCartForm createInstance() {
		if(instance == null) {
			instance = new GuestCartForm();
		}
		return  instance;
	}
	
	public static synchronized GuestCartForm getInstance() throws NullPointerException{
		return instance;
	}
	
	private CartTablePanel cartTablePanel = null;
	private JLabel labelBookCode =  null;
	
	private JTextField textFieldWishStock = null;
	
	public GuestCartForm() {
		this.setTitle("장바구니 관리");
		this.setLayout(new BorderLayout());
		
		cartTablePanel = new CartTablePanel();
		this.add(cartTablePanel, "North");
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
		panel.add(new CartManagePanel());
		
		this.add(panel, "South");
		
		this.pack();
		this.setLocation(1100, 0);
		this.setResizable(false);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				GuestCartForm frame = (GuestCartForm) e.getWindow();
				frame.close();
			}
		});
	}
	
	public void close() {
		this.dispose();
		instance = null;
		System.gc();
	}

	public CartTablePanel getCartTablePanel() {
		return cartTablePanel;
	}

	public JLabel getLabelBookCode() {
		return labelBookCode;
	}

	public JTextField getTextFieldWishStock() {
		return textFieldWishStock;
	}
	
}
