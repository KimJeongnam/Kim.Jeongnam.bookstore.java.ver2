package forms.panels.guest;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import forms.listener.guest.GuestCartListener;

public class CartUpdateDeletePanel extends JPanel{

	/**
	 *  장바구니 관리 수정 입력 패널
	 */
	private static final long serialVersionUID = 1L;
	private static JLabel label_bookCode = new JLabel("1234");
	private static JLabel label_bookName = new JLabel();
	private static JTextField tf_wishStock = new JTextField(5);
	
	public CartUpdateDeletePanel() {
		this.setLayout(new BorderLayout());
		
		this.add(selectPanel(), "North");
		this.add(buttonPanel(), "South");
		
	}
	
	private JPanel selectPanel() {
		JPanel panel = new JPanel();
		panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder(), 
                "Selected",
                TitledBorder.LEFT, 
                TitledBorder.TOP)); 
		JLabel label = new JLabel("Book Code : ");
		panel.add(label);
		
		label_bookCode.setPreferredSize(new Dimension(180, 25));
		panel.add(label_bookCode);
		
		label = new JLabel("책 제목 : ");
		panel.add(label);
		
		label_bookName.setPreferredSize(new Dimension(70, 25));
		panel.add(label_bookName);
		
		label = new JLabel("수량 : ");
		panel.add(label);
		panel.add(tf_wishStock);
		
		return panel;
	}
	
	
	private JPanel buttonPanel() {
		JPanel panel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
		panel.setLayout(boxlayout);
		
		
		JButton button = new JButton("수정");
		button.setPreferredSize(new Dimension(100, 25));
		button.addActionListener(new GuestCartListener());
		panel.add(button);
		panel.add(Box.createVerticalStrut(25));
		
		button = new JButton("삭제");
		button.setPreferredSize(new Dimension(100, 25));
		button.addActionListener(new GuestCartListener());
		panel.add(button);
		panel.add(Box.createVerticalStrut(25));
		
		button = new JButton("전체 삭제");
		button.setPreferredSize(new Dimension(100, 25));
		button.addActionListener(new GuestCartListener());
		panel.add(button);
		
		
		return panel;
	}
	
	public static void clear() {
		setLabel_bookCode("");
		setLabel_bookName("");
		setTf_wishStock("");
	}

	public static String getLabel_bookCode() {
		return label_bookCode.getText();
	}

	public static void setLabel_bookCode(String label_bookCode) {
		CartUpdateDeletePanel.label_bookCode.setText(label_bookCode);
	}

	public static String getLabel_bookName() {
		return label_bookName.getText();
	}

	public static void setLabel_bookName(String label_bookName) {
		CartUpdateDeletePanel.label_bookName.setText(label_bookName);
	}

	public static String getTf_wishStock() {
		return tf_wishStock.getText();
	}

	public static void setTf_wishStock(String tf_wishStock) {
		CartUpdateDeletePanel.tf_wishStock.setText(tf_wishStock);
	}
	
	/*public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.add(new CartUpdateDeletePanel());
		
		frame.pack();
		frame.setVisible(true);
	}*/
}
