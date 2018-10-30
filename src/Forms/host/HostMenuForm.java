package forms.host;

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

import forms.MainForm;
import forms.listener.host.HostMenuButtonLitener;
import forms.panels.host.BookTablePanel;
import forms.panels.host.InsertBookPanel;
import models.Code;
import service.Session;

public class HostMenuForm extends JFrame{
	/**
	 * 호스트 메뉴 Form
	 */
	private static final long serialVersionUID = 1L;
	private static HostMenuForm instance = null;
	
	public static synchronized HostMenuForm createInstance() {
		if(instance == null) {
			instance = new HostMenuForm();
		}
		return  instance;
	}
	
	public static synchronized HostMenuForm getInstance() throws NullPointerException{
		return instance;
	}
	
	
	private JPanel panel = null;
	private BookTablePanel tablepanel = null;
	
	public HostMenuForm() {
		this.setTitle(MainForm.getInstance().getTitle());
		this.setLayout(new BorderLayout());
		
		// 상단 라벨 
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		panel.add(new JLabel("관리자님 환영합니다."));
		this.add(panel, "North");
		
		tablepanel = createLeftPanel();
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		panel.add(tablepanel);
		this.add(panel, "West");
		
		// 책 입력, 우측 버튼
		panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
		
		/*panel.add(InsertBookPanel.createInstance());*/
		panel.add(createRightButtons());
		this.add(panel, "East");
		
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
		// 현재 폼이 닫힐때의 이벤트 처리
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				HostMenuForm frame = (HostMenuForm)e.getWindow();
				JFrame mainFrame = MainForm.getInstance();	// 메인 폼의 객체 get
				Session.getInstance().logout();	// 로그아웃 처리
				mainFrame.setVisible(true);
				frame.close(); 		// 현재프레임 닫기.
			}
		});
	}
	//좌측 테이블 생성
	private BookTablePanel createLeftPanel() {
		BookTablePanel panel = new BookTablePanel(Code.PERMISSION_HOST);
		return panel;
	}
	
	//우측 버튼 생성
	private JPanel createRightButtons() {
		JPanel panel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		JButton button = null;
		
		panel.setLayout(boxlayout);
		panel.setBorder(new EmptyBorder(new Insets(50,20,50,20)));
		
		button = new JButton("책 추가");
		button.addActionListener(new HostMenuButtonLitener());
		panel.add(button);
		
		//버튼 사이 위아래 간격 30
		panel.add(Box.createVerticalStrut(30));
		
		button = new JButton("책 수정");
		button.addActionListener(new HostMenuButtonLitener());
		panel.add(button);
		
		panel.add(Box.createVerticalStrut(30));
		
		button = new JButton("책 삭제");
		button.addActionListener(new HostMenuButtonLitener());
		panel.add(button);
		
		panel.add(Box.createVerticalStrut(150));
		
		button = new JButton("주문 관리");
		button.addActionListener(new HostMenuButtonLitener());
		panel.add(button);
		
		return panel;
	}
	
	public void close() {
		this.dispose();
		instance = null;
		System.gc();	//시스템에 Garbage Collector 요청
	}
	
	public BookTablePanel getTablepanel() {
		return tablepanel;
	}
}
