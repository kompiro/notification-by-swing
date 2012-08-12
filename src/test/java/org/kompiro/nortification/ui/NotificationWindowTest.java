package org.kompiro.nortification.ui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NotificationWindowTest {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JPanel jPanel = new JPanel();
		final NotificationWindow window = new NotificationWindow();
		window.setTitle("This is title.");
		window.setMessage("message");
		window.setType(NotificationType.SUCCESS);
		window.pack();
		
		jPanel.add(new JButton(new AbstractAction() {
			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.notifyUI();
			}
		}));
		
		frame.setContentPane(jPanel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
