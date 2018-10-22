package Forms;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import service.Session;

public class GuestMenuForm extends JFrame {
	
	private static GuestMenuForm instance = new GuestMenuForm();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labelUserId= null;

	public GuestMenuForm() {
		this.setTitle(MainForm.getInstance().getTitle());
		labelUserId = new JLabel();
		this.add(labelUserId); //Session.getInstance().getId()+"님 환영합니다."
		
		this.setSize(300, 300);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				JFrame frame = (JFrame) e.getWindow();
				JFrame mainFrame = MainForm.getInstance();
				mainFrame.setVisible(true);
				frame.setVisible(false);
				Session.getInstance().logout(); // 로그아웃 처리
			}
		});
	}
	
	public JLabel getLabelUserId() {
		return labelUserId;
	}

	public static GuestMenuForm getInstance() {
		return instance;
	}
}
