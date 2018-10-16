package Forms;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import service.Session;

public class HostMenuForm extends JFrame{
	
	private static HostMenuForm instance = new HostMenuForm();

	/**
	 * 호스트 메뉴 Form
	 */
	private static final long serialVersionUID = 1L;
	
	public HostMenuForm() {
		this.setTitle(MainForm.getInstance().getTitle());
		this.add(new JLabel("관리자님 환영합니다."));
		
		this.setSize(300, 300);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				JFrame frame = (JFrame)e.getWindow();
				JFrame mainFrame = MainForm.getInstance();
				mainFrame.setVisible(true);
				frame.setVisible(false);
				Session.getInstance().logout();	// 로그아웃 처리
			}
		});
	}
	
	public static HostMenuForm createInstance() {
		return  new HostMenuForm();
	}
	
	public static HostMenuForm getInstance() {
		return instance;
	}
}
