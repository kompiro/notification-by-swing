package org.kompiro.notification.ui;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import org.kompiro.notification.ui.NotificationWindow;



public class NotificationWindowTest {
	
	public static void main(String[] args) throws InterruptedException, InvocationTargetException {
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				@SuppressWarnings("serial")
				final NotificationWindow window = new NotificationWindow(){
					@Override
					protected void notifyClose() {
						System.exit(0);
					}
				};
				window.setLocation(0, -1000);
				window.setTitle("This is title.");
				window.setMessage("message");
				window.setIconURL(getClass().getResource("weather_sun.png"));
				window.notifyUI();
			}
		});
	}
}
