package forms.guest;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import forms.MainForm;
import forms.panels.host.BookTablePanel;
import models.Code;
import service.Session;

public class GuestMenuForm extends JFrame {

	private static GuestMenuForm instance = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static synchronized GuestMenuForm createInstance(String userid) {
		if(instance == null) {
			instance = new GuestMenuForm(userid);
		}
		return  instance;
	}
	
	public static synchronized GuestMenuForm getInstance() throws NullPointerException{
		return instance;
	}
	
	private String userid;
	private JLabel cartCount = null;	// 장바구니 건수 
	private JLabel cartTotal = null;	// 총액
	private JLabel selectBook_code = null;
	private JLabel selectBook_name = null;
	private JTextField tfWishStock = null;
	
	private JPanel bookTablePanel = null;

	public GuestMenuForm(String userid) {
		this.userid = userid;
		this.setTitle(MainForm.getInstance().getTitle());
		this.setLayout(new BorderLayout());
		
		JPanel panel = null;
		// 상단부 패널

		this.add(northPanel(), "North"); 
		
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
		bookTablePanel = new BookTablePanel(Code.PERMISSION_GUEST);
		panel.add(bookTablePanel);
		this.add(panel, "West");
		
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
		panel.add(cartAddPanel());
		this.add(panel, "East");

		this.pack();
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				GuestMenuForm frame = (GuestMenuForm) e.getWindow();
				JFrame mainFrame = MainForm.getInstance();
				mainFrame.setVisible(true);
				Session.getInstance().logout(); // 로그아웃 처리
				frame.close();
			}
		});
	}
	
	private JPanel northPanel() {
		JPanel outPanel = new JPanel(new BorderLayout());
		JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
		
		JLabel labal = new JLabel("User ID : "+ userid);
		labelPanel.add(labal);
		outPanel.add(labelPanel, "West");
		JPanel cartCountPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
		cartCountPanel.add(cartCountPanel());
		outPanel.add(cartCountPanel, "East");
		
		return outPanel;
	}
	
	// 장바구니 패널
	private JPanel cartCountPanel() {
		JPanel panel = new JPanel();
		JLabel label = null;
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxlayout);
		
		JPanel boxPanel = new JPanel();
		boxPanel.setPreferredSize(new Dimension(100,50));	//패널의 크기조정

		boxPanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder(), 
                "장바구니", 
                TitledBorder.CENTER, 
                TitledBorder.TOP)); 
		cartCount = new JLabel("0");
		label = new JLabel("건");
		boxPanel.add(cartCount);
		boxPanel.add(label);
		
		panel.add(boxPanel);
		label = new JLabel("총액 : ");
		cartTotal = new JLabel("0");
		JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		totalPanel.add(label);
		totalPanel.add(cartTotal);
		
		panel.add(totalPanel);
		
		return panel;
	}
	// 장바구니 추가
	private JPanel cartAddPanel() {
		JLabel label = null;
		JPanel cartAddPanel = new JPanel(new BorderLayout());
		cartAddPanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder(), 
                "장바구니 추가 / 바로 구매", 
                TitledBorder.CENTER, 
                TitledBorder.TOP)); 
		
		JPanel selectShowPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(selectShowPanel, BoxLayout.Y_AXIS);
		selectShowPanel.setLayout(boxlayout);
		
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
		label = new JLabel("책 코드 : ");
		selectBook_code = new JLabel("");
		panel.add(label);
		panel.add(selectBook_code);
		selectShowPanel.add(panel);
		
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
		label = new JLabel("책 제목 : ");
		selectBook_name = new JLabel();
		panel.add(label);
		panel.add(selectBook_name);
		selectShowPanel.add(panel);
		
		cartAddPanel.add(selectShowPanel, "North");
		
		JPanel insertPanel = new JPanel();
		label = new JLabel("수량 : ");
		tfWishStock = new JTextField(5);
		JButton btnCratAdd = new JButton("장바구니에 추가");
		JButton btnNowBuy = new JButton("바로 구매");
		insertPanel.add(label);
		insertPanel.add(tfWishStock);
		insertPanel.add(Box.createVerticalStrut(10));
		insertPanel.add(btnCratAdd);
		insertPanel.add(btnNowBuy);
		
		cartAddPanel.add(insertPanel, "South");
		
		return cartAddPanel;
	}
	
	public void close() {
		this.dispose();
		instance = null;
		System.gc();
	}

	public JLabel getCartCount() {
		return cartCount;
	}

	public JLabel getCartTotal() {
		return cartTotal;
	}

}
