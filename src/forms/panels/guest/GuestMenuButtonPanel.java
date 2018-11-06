package forms.panels.guest;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import forms.listener.guest.GuestMenuListener;

public class GuestMenuButtonPanel extends JPanel {
	
	/**
	 *  고객 메뉴 버튼 패널
	 */
	private static final long serialVersionUID = 1L;

	public GuestMenuButtonPanel() {
		BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(boxlayout);
		
		JButton button = null;
		JPanel panel = null;
		
		this.add(add(Box.createVerticalStrut(50)));
		
		panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		button = new JButton("장바구니 관리");
		button.addActionListener(new GuestMenuListener());
		button.setPreferredSize(new Dimension(130,30));
		panel.add(button);
		this.add(panel);
		this.add(add(Box.createVerticalStrut(65)));
		
		panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		button = new JButton("구매요청 목록");
		button.addActionListener(new GuestMenuListener());
		button.setPreferredSize(new Dimension(130,30));
		panel.add(button);
		this.add(panel);
		this.add(add(Box.createVerticalStrut(65)));

		panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		button = new JButton("환불 요청");
		button.addActionListener(new GuestMenuListener());
		button.setPreferredSize(new Dimension(130,30));
		panel.add(button);
		this.add(panel);
	}
}
