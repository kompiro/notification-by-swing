package org.kompiro.notification.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Set;

import javax.swing.JWindow;
import javax.swing.Timer;

import org.pushingpixels.trident.Timeline;
import org.pushingpixels.trident.TridentConfig;
import org.pushingpixels.trident.Timeline.TimelineState;
import org.pushingpixels.trident.callback.TimelineCallback;
import org.pushingpixels.trident.ease.Sine;
import org.pushingpixels.trident.interpolator.PropertyInterpolator;
import org.pushingpixels.trident.swing.AWTPropertyInterpolators;

public class NotificationWindow extends JWindow{
	
	private static final int DEFAULT_BASE_BORDER = 50;
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_DURATION = 3000;
	private NotificationPanel contentPane;
	private int duration = DEFAULT_DURATION;
	private int border = DEFAULT_BASE_BORDER;
	private boolean stickMode;

	static {
		// can't load property interpolators from trident-plugin.properties
		// because TridentConfig constructor calls Thread.currentThread().getContextClassLoader().
		// it's not good for some environment.(ex. maven)
		// So ,these code adds interpolators manually.
        TridentConfig config = TridentConfig.getInstance();
        @SuppressWarnings("rawtypes")
		Set<PropertyInterpolator> propertyInterpolators = new AWTPropertyInterpolators().getPropertyInterpolators();
        for (PropertyInterpolator<?> propertyInterpolator : propertyInterpolators) {
            config.addPropertyInterpolator(propertyInterpolator);
		}
	}
	
	public NotificationWindow() {
		contentPane = new NotificationPanel();
        contentPane.setOpaque(false);
        setContentPane(contentPane);
        contentPane.addCloseMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		close();
        	}
		});
        setAlwaysOnTop(true);
	}
	
	/**
	 * set icon image url
	 * @param iconURL
	 */
	public void setIconURL(final URL iconURL){
		contentPane.setIconURL(iconURL);
	}
	
	/**
	 * set title
	 * @param title
	 */
	public void setTitle(final String title){
		contentPane.setTitle(title);
	}
	
	/**
	 * set message
	 * @param message
	 */
	public void setMessage(final String message) {
		contentPane.setMessage(message);
	}
	
	/**
	 * set window color
	 * @param color
	 */
	public void setColor(final Color color){
		contentPane.setColor(color);
	}
	
	/**
	 * set duration to show the window
	 * @param duration
	 */
	public void setDuration(int duration){
		this.duration = duration;
	}
	
	/**
	 * set border from display edge
	 * @param border
	 */
	public void setBorder(int border) {
		this.border = border;
	}
	
	/**
	 * set stick window mode
	 * @param stickMode
	 */
	public void setStickMode(boolean stickMode){
		this.stickMode = stickMode;
	}
	
	/**
	 * set window size
	 * @param size
	 */
	public void setSize(Dimension size){
		contentPane.setSize(size);
	}
	
	/**
	 * set window size
	 * @param width
	 * @param height
	 */
	public void setSize(int width, int height){
		contentPane.setSize(width, height);
	}
	
	public void notifyUI(){
		this.pack();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
		Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
		int x = (int)rect.getMaxX() - this.getWidth() - border;
		int y = (int)rect.getMaxY();
		this.setLocation(x, y);
		this.setVisible(true);

		final Timeline moveTimeline = new Timeline(this);
		moveTimeline.addPropertyToInterpolate("location", new Point(x, y), new Point(x,y - this.getHeight() - border));
		moveTimeline.setEase(new Sine());
		int delay = duration + (int) moveTimeline.getDuration();
		Timer hideTimer = new Timer(delay, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				moveTimeline.addCallback(new TimelineCallback() {
					
					@Override
					public void onTimelineStateChanged(TimelineState oldState,
							TimelineState newState, float durationFraction,
							float timelinePosition) {
						if(newState.equals(TimelineState.DONE)){
							close();
						}
					}
					
					@Override
					public void onTimelinePulse(float durationFraction, float timelinePosition) {
					}
				});
				moveTimeline.replayReverse();
			}

		});
		moveTimeline.play();
		if(stickMode == false){
			hideTimer.start();
		}
	}

	public void close() {
		dispose();
		notifyClose();
	}
	
	protected void notifyClose() {
	}
	
	@Override
	public synchronized void addMouseListener(MouseListener l) {
		super.addMouseListener(l);
		contentPane.addMouseListener(l);
	}
	
}
