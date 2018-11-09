package forms.guest;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import forms.MainForm;
import forms.panels.guest.CarTotalCountPricePanel;
import forms.panels.guest.CartAddNowBuyPanel;
import forms.panels.guest.GuestMenuButtonPanel;
import forms.panels.host.BookTablePanel;
import models.Code;
import service.Session;

public class GuestMenuForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private static GuestMenuForm instance = null;
	public static ArrayList<JFrame> frames = new  ArrayList<JFrame>();
	
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
	private BookTablePanel bookTablePanel = null;

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
		JPanel rightPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(rightPanel, BoxLayout.Y_AXIS);
		rightPanel.setLayout(boxlayout);
		rightPanel.add(new CartAddNowBuyPanel());
		rightPanel.add(new GuestMenuButtonPanel());
		panel.add(rightPanel);
		this.add(panel, "East");

		
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				GuestMenuForm frame = (GuestMenuForm) e.getWindow();
				JFrame mainFrame = MainForm.getInstance();
				mainFrame.setVisible(true);
				Session.getInstance().logout(); // 로그아웃 처리
				
				for(JFrame a: frames) {
					a.dispose();
				}
				
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
		cartCountPanel.add(new CarTotalCountPricePanel());
		outPanel.add(cartCountPanel, "East");
		
		return outPanel;
	}
	
	public void close() {
		this.dispose();
		if(GuestCartForm.getInstance() != null)
			GuestCartForm.getInstance().close();
		instance = null;
		System.gc();
	}

	public BookTablePanel getBookTablePanel() {
		return bookTablePanel;
	}

}
