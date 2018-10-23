package Forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import service.Session;

public class HostMenuForm extends JFrame{
	/**
	 * 호스트 메뉴 Form
	 */
	private static final long serialVersionUID = 1L;
	private static HostMenuForm instance = null;
	
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
		panel = createRightButtons();
		this.add(panel, "East");
		
		
		panel = new JPanel();
		
		
		
		this.pack();
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				HostMenuForm frame = (HostMenuForm)e.getWindow();
				JFrame mainFrame = MainForm.getInstance();
				Session.getInstance().logout();	// 로그아웃 처리
				mainFrame.setVisible(true);
				frame.close(); 		// 현재프레임 닫기.
			}
		});
	}
	
	//우측 버튼 생성
	private JPanel createRightButtons() {
		JPanel panel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxlayout);
		panel.setBorder(new EmptyBorder(new Insets(50,20,50,20)));
		
		btnBookAdd = new JButton("책 추가");
		btnBookUpdate = new JButton("책 수정");
		btnBookDel = new JButton("책 삭제");
		
		panel.add(btnBookAdd);
		panel.add(Box.createVerticalStrut(50));
		panel.add(btnBookUpdate);
		panel.add(Box.createVerticalStrut(50));
		panel.add(btnBookDel);
		return panel;
	}
	
	public static synchronized HostMenuForm getInstance() {
		if(instance == null) {
			instance = new HostMenuForm();
		}
		return  instance;
	}
	
	public void close() {
		this.dispose();
		instance = null;
		System.gc();	//시스템에 Garbage Collector 요청
	}
	
	public JButton getBtnBookAdd() {
		return btnBookAdd;
	}

	public JButton getBtnBookUpdate() {
		return btnBookUpdate;
	}

	public JButton getBtnBookDel() {
		return btnBookDel;
	}
}
