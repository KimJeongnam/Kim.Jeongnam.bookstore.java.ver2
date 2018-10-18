package Forms.Panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Forms.Listener.Main.MainButtonListener;
import Models.Code;

public class LoginPanel extends JPanel{

	/**
	 *  로그인 패널
	 */
	private static final long serialVersionUID = 1L;
	private static LoginPanel hostLoginPanel = new LoginPanel(Code.PERMISSION_HOST);
	private static LoginPanel guestLoginPanel = new LoginPanel(Code.PERMISSION_GUEST);
	
	private JLabel title = null;
	private JTextField tf_id = null;
	private JPasswordField tf_pw = null;
	private JLabel label = null;
	private JButton btn_login = null;
	
	public LoginPanel(String permission) {
		if(permission.equals(Code.PERMISSION_GUEST)) {
			title = new JLabel("고객 로그인");
		}else {
			title = new JLabel("관리자 로그인");
		}
		JPanel panel = new JPanel();
		JPanel insertPanel = new JPanel();
		JPanel idPanel = new JPanel();
		JPanel pwPanel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		insertPanel.setLayout(new BorderLayout());
		idPanel.setLayout(new FlowLayout());
		pwPanel.setLayout(new FlowLayout());
		
		label = new JLabel("ID : ");
		tf_id = new JTextField(10);

		idPanel.add(label);
		idPanel.add(tf_id);
		
		label = new JLabel("PW : ");
		tf_pw = new JPasswordField(10);
		
		pwPanel.add(label);
		pwPanel.add(tf_pw);
		
		insertPanel.add(idPanel, "North");
		insertPanel.add(pwPanel, "South");
		
		if(permission.equals(Code.PERMISSION_GUEST))
			btn_login = new JButton(Code.PERMISSION_GUEST+" Login");
		else
			btn_login = new JButton(Code.PERMISSION_HOST+" Login");
		btn_login.addActionListener(new MainButtonListener());
		
		panel.add(title, "North");
		panel.add(insertPanel, "West");
		panel.add(btn_login, "East");
		
		this.add(panel);
	}
	
	public static LoginPanel getHostInstance() {
		return hostLoginPanel;
	}

	public static LoginPanel getGuestInstance() {
		return guestLoginPanel;
	}
	
	public JTextField getTf_id() {
		return tf_id;
	}

	public void setTf_id(JTextField tf_id) {
		this.tf_id = tf_id;
	}

	public JPasswordField getTf_pw() {
		return tf_pw;
	}

	public void setTf_pw(JPasswordField tf_pw) {
		this.tf_pw = tf_pw;
	}

}
