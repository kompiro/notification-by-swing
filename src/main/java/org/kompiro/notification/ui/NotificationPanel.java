package org.kompiro.notification.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

class NotificationPanel extends JPanel {

	private static final Color DEFAULT_COLOR = new Color(255,255,153);
	private static final long serialVersionUID = 1L;
	private static final URL URL_OF_CLOSE_ICON = NotificationPanel.class.getResource("close.png");
	private JTextArea message;
	private JLabel title;
	private JLabel icon;
	private JLabel closeIcon;

	/**
	 * Create the panel.
	 */
	NotificationPanel() {
		setBorder(LineBorder.createGrayLineBorder());
		setLayout(new MigLayout("insets 0", "[:50:][:150:][]", "[][][]"));
		setOpaque(false);
		
		icon = new JLabel();
		add(icon, "cell 0 0,span 1 3,center");
		
		title = new JLabel();
		title.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
		add(title, "cell 1 1");
		
		closeIcon = new JLabel(new ImageIcon(URL_OF_CLOSE_ICON));
		add(closeIcon, "cell 2 0,top,right");
		
		message = new JTextArea();
		message.setEditable(false);
		message.setLineWrap(true);
		message.setSize(new Dimension(200,60));
		message.setOpaque(false);
		add(message, "cell 1 2");
		setColor(DEFAULT_COLOR);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint gp = new GradientPaint(0, 0,
                getBackground().brighter().brighter(), 0, getHeight(),
                getBackground());

        graphics.setPaint(gp);
        graphics.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(graphics);
	}
	
	void setTitle(final String title){
		this.title.setText(title);
	}
	
	void setMessage(final String message) {
		this.message.setText(message);
	}

	void setIconURL(URL iconURL) {
		if(iconURL == null) throw new IllegalArgumentException("iconURL is null");
		this.icon.setIcon(new ImageIcon(iconURL));
	}

	void setColor(Color color) {
		setBackground(color);
	}
	
	void addCloseMouseListener(MouseListener listener){
		closeIcon.addMouseListener(listener);
	}

}
