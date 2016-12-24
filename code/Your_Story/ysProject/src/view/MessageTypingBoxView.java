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

/**
 *
 * @author ali
 */
public class MessageTypingBoxView extends JPanel implements KeyListener, MouseListener {
	
	private String typingString;
	private boolean typingBarIsActive;
	private boolean markerIsActive;
	private RoundRectangle2D messageTypingBar;
	private int boxHeight;
	private JComponent panel;
	private JButton button;
	
    /**
     *
     * @param panel
     * @param l
     */
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
				addNotify();
			}
		});
		
		addKeyListener(this);
		addMouseListener(this);
	}
	
    /**
     *
     */
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
	
    /**
     *
     * @param g
     */
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		
		ArrayList<String> modified = InGameView.fitString(typingString, InGameView.font, 
															ChatView.FINAL_WIDTH);
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
	
    /**
     *
     */
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
	
    /**
     *
     * @return
     */
    public boolean typingBarIsActive() {
		return typingBarIsActive;
	}
	
    /**
     *
     * @param b
     */
    public void setTypingBarIsActive(boolean b) {
		typingBarIsActive = b;
	}
	
    /**
     *
     * @return
     */
    public RoundRectangle2D getMessageTypingBar() {
		return messageTypingBar;
	}
	
    /**
     *
     * @return
     */
    public int getBoxHeight() {
		return boxHeight;
	}

    /**
     *
     * @param e
     */
    @Override
	public void keyTyped(KeyEvent e) {}
	
    /**
     *
     * @param e
     */
	public void keyPressed(KeyEvent e) {
		if (typingBarIsActive) {
			if (e.getKeyCode() > 0x28 && e.getKeyCode() < 0x60 ||
					e.getKeyCode() == KeyEvent.VK_SPACE ||
					e.getKeyCode() == KeyEvent.VK_ENTER) {
				typingString += e.getKeyChar();
			} else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && typingString.length() != 0) {
				int length = typingString.length();
				typingString = typingString.substring(0, length - 1);
			}
			repaint();
		}
	}

    /**
     *
     * @param e
     */
    @Override
	public void keyReleased(KeyEvent e) {}
	
    /**
     *
     * @param e
     */
    @Override
	public void mouseClicked(MouseEvent e) {
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

    /**
     *
     * @param e
     */
    @Override
	public void mousePressed(MouseEvent e) {}

    /**
     *
     * @param e
     */
    @Override
	public void mouseReleased(MouseEvent e) {}

    /**
     *
     * @param e
     */
    @Override
	public void mouseEntered(MouseEvent e) {}

    /**
     *
     * @param e
     */
    @Override
	public void mouseExited(MouseEvent e) {}
}
