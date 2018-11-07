package forms.host;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class OrderMenuForm extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OrderMenuForm() {
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 30,30));
		
		ActionListener l = e->{
			String command = e.getActionCommand();
			
			switch(command) {
			case "주문 요청 목록":
				new ConfirmListForm();
				break;
			}
		};
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15,15));
		JButton button = new JButton("주문 요청 목록");
		button.addActionListener(l);
		panel.add(button);
		this.add(panel);
		
		
		panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15,15));
		button = new JButton("환불 요청 목록");
		button.addActionListener(l);
		panel.add(button);
		this.add(panel);
		
		panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15,15));
		button = new JButton("결산");
		button.addActionListener(l);
		panel.add(button);
		this.add(panel);
		
		
		this.setVisible(true);		
		this.pack();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
}
