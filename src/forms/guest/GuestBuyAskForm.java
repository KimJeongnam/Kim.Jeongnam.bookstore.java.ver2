package forms.guest;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import forms.MainForm;
import forms.panels.guest.BuyAskTablePanel;
import service.Session;

public class GuestBuyAskForm extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GuestBuyAskForm() {
		this.setTitle("구매요청 목록");
		
		this.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
		panel.add(new JLabel("구매 요청 목록"));
		this.add(panel, "North");
		
		this.add(new BuyAskTablePanel());
		
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

}
