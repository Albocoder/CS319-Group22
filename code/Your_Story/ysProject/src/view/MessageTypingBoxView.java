package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

import mainPackage.*;

public class MessageTypingBoxView extends JPanel implements KeyListener, MouseListener {
	
	private String typingString;
	private boolean typingBarIsActive;
	private boolean markerIsActive;
	private RoundRectangle2D messageTypingBar;
	private int boxHeight;
	private JComponent panel;
	private JButton button;
	
	public MessageTypingBoxView(JComponent panel, Lobby l) {
		typingString = "";
		typingBarIsActive = false;
		markerIsActive = false;
		boxHeight = 40;
		this.panel = panel;
		button = new JButton("Send");
		
		setLayout(null);
		
		add(this.panel);
		panel.setLocation(0, 0);
		
		add(button);
		button.setLayout(null);
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				l.getChat().sendMessage(typingString);
				typingString = "";
				repaint();
//				panel.repaint();
				addNotify();
			}
		});
		
		addKeyListener(this);
		addMouseListener(this);
	}
	
	public void addNotify() {
        super.addNotify();
        requestFocus();
    }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		
		ArrayList<String> modified = InGameView.fitString(typingString, ChatView.FINAL_WIDTH);
		int height = modified.size() * InGameView.LINE_HEIGHT;
		boxHeight = height + 20;
		
		panel.setSize(new Dimension(getWidth(), getHeight() - boxHeight));
		button.setBounds(getWidth() - 70, getHeight() - 35, 70, 30);
		
		messageTypingBar = new RoundRectangle2D.Float(10, getHeight() - height - 10, 
													ChatView.FINAL_WIDTH, height, 10, 10);
		g2.setColor(Color.WHITE);
		g2.fill(messageTypingBar);
		
		g2.setColor(Color.BLACK);
		g2.setFont(InGameView.font);
		for (int i = 0; i < modified.size(); i++) {
			g2.drawString(modified.get(i), 15, getHeight() + i * InGameView.LINE_HEIGHT - height + 5);
		}
		
		if (markerIsActive) {
			String last = modified.get(modified.size() - 1);
			int width = g2.getFontMetrics().stringWidth(last);
			g2.drawLine(width + 15, getHeight() - 27, 
					width + 15, getHeight() - 13);
		}
	}
	
	public void activateMarker() {
		markerIsActive = true;
		repaint();
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (!typingBarIsActive) {
			    	markerIsActive = false;
			    	timer.cancel();
			    	repaint();
			    	return;
			    }
			    markerIsActive = !markerIsActive;
			    repaint();
			}
		}, 500, 500);
	}
	
	public boolean typingBarIsActive() {
		return typingBarIsActive;
	}
	
	public void setTypingBarIsActive(boolean b) {
		typingBarIsActive = b;
	}
	
	public RoundRectangle2D getMessageTypingBar() {
		return messageTypingBar;
	}
	
	public int getBoxHeight() {
		return boxHeight;
	}

	public void keyPressed(KeyEvent e) {}
	
	@Override
	public void keyTyped(KeyEvent e) {
		if (typingBarIsActive) {
			typingString += e.getKeyChar();
			repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void mouseClicked(MouseEvent e) {
//		System.out.println(e.getX() + ", " + e.getY());
		if (messageTypingBar.contains(new Point(e.getX(), e.getY()))) {
			addNotify();
			if (!typingBarIsActive) {
				activateMarker();
			}
			setTypingBarIsActive(true);
		} else {
			setTypingBarIsActive(false);
			repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
