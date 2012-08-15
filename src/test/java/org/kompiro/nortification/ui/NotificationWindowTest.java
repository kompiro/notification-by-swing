package org.kompiro.nortification.ui;


public class NotificationWindowTest {
	
	public static void main(String[] args) {
		final NotificationWindow window = new NotificationWindow();
		window.setLocation(0, -1000);
		window.setTitle("This is title.");
		window.setMessage("message");
		window.setType(NotificationType.SUCCESS);
		window.pack();
		window.notifyUI();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
