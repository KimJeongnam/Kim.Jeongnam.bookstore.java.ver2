package Forms.Panels;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Forms.Listener.MainButtonListener;

public class SignUpPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4992851183147753411L;
	private static final SignUpPanel instance = new SignUpPanel();
	
	private JPanel panel = null;
	private JLabel label = null;
	
	private JTextField tf_id;
	private JPasswordField tf_pw;
	
	private JButton btn_Signup = null;
	
	public SignUpPanel() {
		BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(boxlayout);
		
		label = new JLabel("회원 가입");
		this.add(label);
		
		panel = new JPanel();
		
		label = new JLabel("ID : ");
		tf_id = new JTextField(10);
		
		panel.add(label);
		panel.add(tf_id);
		this.add(panel);
		
		panel = new JPanel();
		label = new JLabel("PW : ");
		tf_pw = new JPasswordField(10);
		panel.add(label);
		panel.add(tf_pw);
		this.add(panel);
		
		btn_Signup = new JButton("Sign UP!");
		btn_Signup.addActionListener(new MainButtonListener());
		panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.add(btn_Signup);
		
		this.add(panel);
	}
	
	public static SignUpPanel getInstance() {
		return instance;
	}
	
	public JTextField getTf_id() {
		return tf_id;
	}

	public JPasswordField getTf_pw() {
		return tf_pw;
	}

	public JButton getBtn_Signup() {
		return btn_Signup;
	}

	
}
