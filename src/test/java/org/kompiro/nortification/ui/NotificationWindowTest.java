package org.kompiro.nortification.ui;

import javax.swing.SwingUtilities;



public class NotificationWindowTest {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				final NotificationWindow window = new NotificationWindow();
				window.setLocation(0, -1000);
				window.setTitle("This is title.");
				window.setMessage("message");
				window.notifyUI();
			}
		});
	}
}
