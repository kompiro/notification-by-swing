package org.kompiro.nortification.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

public class NotificationPanel extends JPanel {

	private NotificationType type = NotificationType.SUCCESS;
	private static final long serialVersionUID = 1L;
	private JTextArea message;
	private JLabel title;
	private JLabel icon;
	
	

	/**
	 * Create the panel.
	 */
	public NotificationPanel() {
		setBorder(LineBorder.createGrayLineBorder());
		setLayout(new MigLayout("", "[][]", "[][][]"));
		setOpaque(false);
		
		icon = new JLabel();
		add(icon, "cell 0 0");
		
		title = new JLabel();
		title.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
		add(title, "cell 1 0");
		
		message = new JTextArea();
		message.setEditable(false);
		message.setLineWrap(true);
		message.setSize(new Dimension(200,60));
		message.setOpaque(false);
		add(message, "cell 1 1 2 1,grow");
		
		setBackground(type.getColor());
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
	
	public void setTitle(final String title){
		this.title.setText(title);
	}
	
	public void setMessage(final String message) {
		this.message.setText(message);
	}

	public void setIconURL(URL iconURL) {
		if(iconURL == null) throw new IllegalArgumentException("iconURL is null");
		this.icon.setIcon(new ImageIcon(iconURL));
	}
	
	public void setType(NotificationType type){
		this.type = type;
		setBackground(type.getColor());
	}

}
