package Forms.Panels;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Models.Code;

public class LoginPanel extends JPanel{

	/**
	 *  로그인 패널
	 */
	private static final long serialVersionUID = 1L;
	private static LoginPanel hostLoginPanel = new LoginPanel(Code.PERMISSION_HOST);
	private static LoginPanel guestLoginPanel = new LoginPanel(Code.PERMISSION_GUEST);
	
	private JTextField tf_id = null;
	private JTextField tf_pw = null;
	
	public LoginPanel(String permission) {
		
		JPanel insertPanel = new JPanel();
		
		
	}
	
	public static LoginPanel getHostLoginPanel() {
		return hostLoginPanel;
	}

	public static LoginPanel getGuestLoginPanel() {
		return guestLoginPanel;
	}
	
	public JTextField getTf_id() {
		return tf_id;
	}

	public void setTf_id(JTextField tf_id) {
		this.tf_id = tf_id;
	}

	public JTextField getTf_pw() {
		return tf_pw;
	}

	public void setTf_pw(JTextField tf_pw) {
		this.tf_pw = tf_pw;
	}

}
