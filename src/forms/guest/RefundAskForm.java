package forms.guest;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import forms.panels.guest.BuyListTablePanel;

public class RefundAskForm extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BuyListTablePanel buyListpanel = null;
	
	public RefundAskForm() {
		this.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20 , 20));
		JLabel label = new JLabel("환불 요청");
		panel.add(label);
		
		this.add(panel, "North");
		
		buyListpanel = new BuyListTablePanel();
		this.add(buyListpanel, "South");
		
		this.setVisible(true);
		this.pack();
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				JFrame frame = (JFrame) e.getWindow();
				GuestMenuForm.frames.remove(frame);
				frame.dispose();
			}
		});
	}
	
/*	public static void main(String[] args) {
		new RefundAskForm();
	}*/
}
