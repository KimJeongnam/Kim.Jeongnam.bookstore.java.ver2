package forms.panels.guest;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import forms.listener.guest.GuestMenuListener;

public class CartAddNowBuyPanel extends JPanel{
	
	/**
	 * 	장바구니 추가 패널
	 */
	private static final long serialVersionUID = 1L;
	private static JLabel selectBook_code = new JLabel();	// 선택한 책의 코드
	private static JLabel selectBook_name = new JLabel();	// 선택한 책의 제목
	private static JTextField tfWishStock = new JTextField(5);	// 희망 수량
	
	public static void init() {
		selectBook_code.setText("");
		selectBook_name.setText("");
		tfWishStock.setText("");
	}

	public CartAddNowBuyPanel() {
		JLabel label = null;
		
		this.setLayout(new BorderLayout());
		this.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder(), 
                "장바구니 추가 / 바로 구매", 
                TitledBorder.CENTER, 
                TitledBorder.TOP)); 
		
		JPanel selectShowPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(selectShowPanel, BoxLayout.Y_AXIS);
		selectShowPanel.setLayout(boxlayout);
		
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
		label = new JLabel("책 코드 : ");
		panel.add(label);
		panel.add(selectBook_code);
		selectShowPanel.add(panel);
		
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
		label = new JLabel("책 제목 : ");
	
		panel.add(label);
		panel.add(selectBook_name);
		selectShowPanel.add(panel);
		
		this.add(selectShowPanel, "North");
		
		JPanel insertPanel = new JPanel();
		label = new JLabel("수량 : ");
		JButton btnCratAdd = new JButton("장바구니에 추가");
		JButton btnNowBuy = new JButton("바로 구매");
		btnCratAdd.addActionListener(new GuestMenuListener());
		btnNowBuy.addActionListener(new GuestMenuListener());
		insertPanel.add(label);
		insertPanel.add(tfWishStock);
		insertPanel.add(Box.createVerticalStrut(10));
		insertPanel.add(btnCratAdd);
		insertPanel.add(btnNowBuy);
		
		this.add(insertPanel, "South");
	}

	public static String getSelectBook_code() {
		return selectBook_code.getText();
	}

	public static void setSelectBook_code(String selectBook_code) {
		CartAddNowBuyPanel.selectBook_code.setText(selectBook_code);
	}

	public static String getSelectBook_name() {
		return selectBook_name.getText();
	}

	public static void setSelectBook_name(String selectBook_name) {
		CartAddNowBuyPanel.selectBook_name.setText(selectBook_name);
	}

	public static String getTfWishStock() {
		return tfWishStock.getText();
	}

	public static void setTfWishStock(String tfWishStock) {
		CartAddNowBuyPanel.tfWishStock.setText(tfWishStock);
	}
}
