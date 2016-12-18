package view;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import mainPackage.*;

public class MessageView {
	
	Message m;
	int width;
	
	public MessageView(Message message) {
		m = message;
		width = ChatView.FINAL_WIDTH;
	}
	
	public BufferedImage createImage() {
		ArrayList<String> messageContent = InGameView.fitString(m.messageContent, width);
		messageContent.add(0, m.charName + " (" + m.senderName + ")");
		setNewWidth(messageContent);
		int height = InGameView.LINE_HEIGHT * (messageContent.size());
		BufferedImage img = new BufferedImage(width + 1, height + 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) img.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		
		if (m.senderName.equals(AccessHandler.username)) {
			g.setColor(new Color(135, 206, 250));
		} else {
			g.setColor(Color.WHITE);
		}
		g.fillRoundRect(0, 0, width, height, 30, 30);
		
		g.setColor(Color.BLACK);
		g.setFont(InGameView.font);
		for (int i = 0; i < messageContent.size(); i++) {
			g.drawString(messageContent.get(i), 10, i*InGameView.LINE_HEIGHT + 15);
		}
		g.drawRoundRect(0, 0, width, height, 30, 30);
		
		return img;
	}
	
	private void setNewWidth(ArrayList<String> modified) {
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
		int newWidth = 0;
		for (int i = 0; i < modified.size(); i++) {
			int length = (int)(InGameView.font.getStringBounds(modified.get(i), frc).getWidth());
			if (newWidth < length + 30) {
				newWidth = length + 30;
			}
		}
		width = newWidth;
	}
}
