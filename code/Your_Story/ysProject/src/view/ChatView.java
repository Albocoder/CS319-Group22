package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;
import mainPackage.*;

/**
 *
 * @author kaxell
 */
public class ChatView extends JPanel {
	
	private ArrayList<Message> messages;
	static int FINAL_WIDTH;
	static String username = "karaali"; // for trial
	private int height;
	private Lobby lobby;
	
    /**
     *
     * @param l
     */
    public ChatView(Lobby l) {
		lobby = l;
		FINAL_WIDTH = 300;
		messages = ChatConnection.getMessages(l.getID());
		setBackground(Color.LIGHT_GRAY);
	}
	
    /**
     *
     * @param g
     */
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		height = 10;
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		
		FINAL_WIDTH = getWidth() - 100;
		
		for (Message m : messages) {
			MessageView mv = new MessageView(m);
			BufferedImage img = mv.createImage();
			if (m.senderName.equals(AccessHandler.username)) {
				g2.drawImage(img, null, getWidth() - img.getWidth() - 10, height);
			} else {
				g2.drawImage(img, null, 10, height);
			}
			height = height + 10 + img.getHeight();
		}
		setPreferredSize(new Dimension(380, height));
	}
	
    /**
     *
     */
    public void fetchNewMessages() {
		ArrayList<Message> newMessages = ChatConnection.getMessages(lobby.getID());
		if (newMessages.size() != messages.size()) {
			messages = newMessages;
			repaint();
		}
	}
	
    /**
     *
     * @return
     */
    public int getPreferredHeight() {
		return height;
	}
}
