package Forms;

import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LoginForm extends JFrame{

	/**
	 *  로그인 Form
	 */
	private static final long serialVersionUID = 1L;
	private CardLayout cards = new CardLayout();
	
	JPanel guestLoginPanel = null;
	JPanel hostLoginPanel = null;
	JPanel signUpPanel = null;
	
	public LoginForm() {
		this.setTitle("Book Store");
		BoxLayout boxlayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
		this.setLayout(boxlayout);
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		this.add(buttonPanel);
		
		
		
		this.setVisible(true);
		
	}
	
}
