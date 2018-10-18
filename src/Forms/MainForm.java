package Forms;

import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Forms.Listener.Main.MainButtonListener;
import Forms.Panels.LoginPanel;
import Forms.Panels.SignUpPanel;
import Models.Code;

public class MainForm extends JFrame{

	/**
	 *  로그인 Form
	 */
	private static final long serialVersionUID = 1L;
	private static final MainForm instance = new MainForm();
	
	private CardLayout cards = new CardLayout();
	
	private JPanel guestLoginPanel = null;
	private JPanel hostLoginPanel = null;
	private JPanel signUpPanel = null;
	private JPanel cardPanel = null;
	private JButton btn_Guest = null;
	private JButton btn_Host = null;
	private JButton btn_SignUp = null;

	public MainForm() {
		this.setTitle("Book Store");
		BoxLayout boxlayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
		this.setLayout(boxlayout);
		
		JPanel buttonPanel = new JPanel();
		cardPanel = new JPanel(cards);
		btn_Guest = new JButton("고 객 Login");
		btn_Host = new JButton("관리자 Login");
		btn_SignUp = new JButton("회원 가입");
		btn_Guest.addActionListener(new MainButtonListener());
		btn_Host.addActionListener(new MainButtonListener());
		btn_SignUp.addActionListener(new MainButtonListener());
		
		buttonPanel.setLayout(new FlowLayout());
		
		btn_Guest.setEnabled(false);
		
		buttonPanel.add(btn_Guest);
		buttonPanel.add(btn_Host);
		buttonPanel.add(btn_SignUp);
		this.add(buttonPanel);
		
		hostLoginPanel = LoginPanel.getHostInstance();
		guestLoginPanel = LoginPanel.getGuestInstance();
		signUpPanel = SignUpPanel.getInstance();

		cardPanel.add(Code.PERMISSION_GUEST, guestLoginPanel);
		cardPanel.add(Code.PERMISSION_HOST,hostLoginPanel);
		cardPanel.add("SignUp", signUpPanel);
		
		this.add(cardPanel);
		
		this.pack();
		this.setResizable(false);
	}
	
	public static MainForm getInstance() { return instance; }
	
	public JPanel getCardPanel() {
		return cardPanel;
	}
	
	public CardLayout getCardLayout() {
		return cards;
	}
	
	public JButton getBtn_Guest() {
		return btn_Guest;
	}

	public JButton getBtn_Host() {
		return btn_Host;
	}
	
	public JButton getBtn_SignUp() {
		return btn_SignUp;
	}
	
}
