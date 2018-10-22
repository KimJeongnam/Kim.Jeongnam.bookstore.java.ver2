package Forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import service.Session;

public class HostMenuForm extends JFrame{
	
	private static HostMenuForm instance = new HostMenuForm();

	/**
	 * 호스트 메뉴 Form
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel = null;
	private JButton btnBookAdd = null;
	private JButton btnBookUpdate = null;
	private JButton btnBookDel = null;
	
	public HostMenuForm() {
		this.setTitle(MainForm.getInstance().getTitle());
		this.setLayout(new BorderLayout());
		
		// 상단 라벨 
		panel = new JPanel();
		panel.add(new JLabel("관리자님 환영합니다."), FlowLayout.LEFT);
		this.add(panel, "North");
		
		// 우측 버튼
		panel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxlayout);
		
		btnBookAdd = new JButton("책 추가");
		btnBookUpdate = new JButton("책 수정");
		btnBookDel = new JButton("책 삭제");
		
		panel.add(btnBookAdd);
		panel.add(Box.createVerticalGlue());
		panel.add(btnBookUpdate);
		panel.add(Box.createVerticalGlue());
		panel.add(btnBookDel);
		
		this.add(panel, "East");
		
		
		panel = new JPanel();
		
		
		
		this.pack();
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
