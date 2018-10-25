package forms.panels.host;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InsertBookPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static InsertBookPanel instance = null;
	
	public static InsertBookPanel createInstance() {
		if(instance == null)
			instance = new InsertBookPanel();
		return instance;
	}
	
	public static InsertBookPanel getInstance() {
		if(instance == null)
			JOptionPane.showMessageDialog(null,InsertBookPanel.getClassPath()+" is Null!!\n존재하지않는 Panel입니다.", "getInstance Error", JOptionPane.ERROR_MESSAGE);
		return instance;
	}
	
	public static String getClassPath() {
		return "[Package:forms.panels.host  Class:InsertBookPanel]";
	}

	private JTextField tf_book_name = null;
	private JTextField tf_author = null;
	private JTextField tf_price = null;
	private JTextField tf_stock = null;
	
	
	private InsertBookPanel() {
		// 현재 패널 수직 배치
		BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(boxlayout);
		
		this.add(textFieldPanel());
	}
	
	private JPanel textFieldPanel() {
		JPanel tfPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(tfPanel, BoxLayout.Y_AXIS);
		tfPanel.setLayout(boxlayout);
		
		JPanel panel = null;
		JLabel label = null;
		// 책제목
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));	// 왼쪽정렬  상하 좌우 간격 15
		label = new JLabel("책 제목 : ");									// 라벨 생성
		tf_book_name = new JTextField(10);								// 책 제목 tf 생성
		panel.add(label);												// panel에 라벨 추가
		panel.add(tf_book_name);										// panel에 tf책제목 추가
		tfPanel.add(panel);								// 큰패널(tfPanel)에 하위 panel 추가
		
		// 책 저자 
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
		label = new JLabel("책 저자 : ");
		tf_author = new JTextField(10);
		panel.add(label);
		panel.add(tf_author);
		tfPanel.add(panel);
		
		// 책 가격
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
		label = new JLabel("책 가격 : ");
		tf_price = new JTextField(10);
		panel.add(label);
		panel.add(tf_price);
		tfPanel.add(panel);
	
		// 책 수량
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
		label = new JLabel("책 수량 : ");
		tf_stock = new JTextField(10);
		panel.add(label);
		panel.add(tf_stock);
		tfPanel.add(panel);
		
		panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
		JButton button = new JButton("Clear");
		clearButtonAction(button);
		panel.add(button);
		tfPanel.add(panel);
		
		return tfPanel;
	}
	
	private void clearButtonAction(JButton button) {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tf_book_name.setText("");
				tf_author.setText("");
				tf_price.setText("");
				tf_stock.setText("");
			}
			
		});
	}
	
	public JTextField getTf_book_name() {
		return tf_book_name;
	}

	public void setTf_book_name(JTextField tf_book_name) {
		this.tf_book_name = tf_book_name;
	}

	public JTextField getTf_author() {
		return tf_author;
	}

	public void setTf_author(JTextField tf_author) {
		this.tf_author = tf_author;
	}

	public JTextField getTf_price() {
		return tf_price;
	}

	public void setTf_price(JTextField tf_price) {
		this.tf_price = tf_price;
	}

	public JTextField getTf_stock() {
		return tf_stock;
	}

	public void setTf_stock(JTextField tf_stock) {
		this.tf_stock = tf_stock;
	}
}
