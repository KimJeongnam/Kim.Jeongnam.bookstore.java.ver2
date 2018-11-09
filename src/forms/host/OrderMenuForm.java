package forms.host;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import forms.MainForm;
import service.Session;

public class OrderMenuForm extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OrderMenuForm() {
		this.setTitle("주문 관리 메뉴");
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 30,30));
		
		ActionListener l = e->{
			String command = e.getActionCommand();
			
			switch(command) {
			case "주문 요청 목록":
				HostMenuForm.frames.add(new ConfirmListForm());
				break;
			case "환불 요청 목록":
				HostMenuForm.frames.add(new RefundTableForm());
				break;
			case "결제 완료 목록":
				HostMenuForm.frames.add(new ConfirmDoneForm());
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
		button = new JButton("결제 완료 목록");
		button.addActionListener(l);
		panel.add(button);
		this.add(panel);
		
		
		this.setVisible(true);		
		this.pack();
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				JFrame frame = (JFrame)e.getWindow();
				HostMenuForm.frames.remove(frame);
				frame.dispose();
			}
		});
	}
	
}
