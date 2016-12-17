package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;
import mainPackage.*;


public class ChatView extends JPanel {
	
	private ArrayList<Message> messages;
	static int FINAL_WIDTH;
	static String username = "karaali"; // for trial
	
	public ChatView() {
		FINAL_WIDTH = 300;
		messages = new ArrayList<Message>();
		// these messages are for trial
		messages.add(new Message("alboCoder", "Prince", "Im gonna kill you."));
		messages.add(new Message("kaxell", "King", "You know, if I dont let you kill, you cant."));
		messages.add(new Message("cevatBaris", "Knight", "Yeah, he s right, even you cant touch me."));
		messages.add(new Message("karaali", "Executioner", "But I can ahahahahhah."));
		messages.add(new Message("alboCoder", "Prince", "You fucked the game."));
		messages.add(new Message("karaali", "Executioner", "Cause Im the fucker. THUG LIFE B)_"));
		messages.add(new Message("alboCoder", "Prince", "Thanks for this fucking game, "
				+ "we are gonna kick you ahahahahahahahahahahah. You are fucked now"));
		messages.add(new Message("cevatBaris", "Knight", "Yeah, he s right, even you cant touch me."));
		messages.add(new Message("karaali", "Executioner", "But I can ahahahahhah."));
		messages.add(new Message("alboCoder", "Prince", "You fucked the game."));
		messages.add(new Message("karaali", "Executioner", "Cause Im the fucker. THUG LIFE B)_"));
		messages.add(new Message("alboCoder", "Prince", "Thanks for this fucking game, "
				+ "we are gonna kick you ahahahahahahahahahahah. You are fucked now"));
		// fetch messages from db
		setBackground(Color.LIGHT_GRAY);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int y = 10;
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		
		FINAL_WIDTH = getWidth() - 100;
		
		for (Message m : messages) {
			MessageView mv = new MessageView(m);
			BufferedImage img = mv.createImage();
			if (m.senderName == username) {
				g2.drawImage(img, null, getWidth() - img.getWidth() - 10, y);
			} else {
				g2.drawImage(img, null, 10, y);
			}
			y = y + 10 + img.getHeight();
		}
		setPreferredSize(new Dimension(380, y));
	}
}
