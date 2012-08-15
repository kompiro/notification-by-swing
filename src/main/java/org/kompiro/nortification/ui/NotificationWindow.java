package org.kompiro.nortification.ui;

import java.net.URL;

import javax.swing.JWindow;

import net.sf.jcarrierpigeon.Notification;
import net.sf.jcarrierpigeon.NotificationQueue;
import net.sf.jcarrierpigeon.WindowPosition;

public class NotificationWindow extends JWindow{
	
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_DURATION = 3000;
	private NotificationPanel contentPane;
	private NotificationQueue queue = new NotificationQueue();
	private int duration = DEFAULT_DURATION;
	
	public NotificationWindow() {
		contentPane = new NotificationPanel();
        contentPane.setOpaque(false);
        setContentPane(contentPane);
	}
	
	public void setIconURL(final URL iconURL){
		contentPane.setIconURL(iconURL);
	}
	
	public void setTitle(final String title){
		contentPane.setTitle(title);
	}
	
	public void setMessage(final String message) {
		contentPane.setMessage(message);
	}
	
	public void setType(final NotificationType type){
		contentPane.setType(type);
	}
	
	public void setDuration(int duration){
		this.duration = duration;
	}
	
	public void notifyUI(){
		Notification note = new Notification(this, WindowPosition.BOTTOMRIGHT, 25, 25, duration);
		queue.add(note);
	}
	
}