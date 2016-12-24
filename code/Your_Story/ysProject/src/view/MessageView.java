package view;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import mainPackage.*;

/**
 *
 * @author kaxell
 */
public class MessageView {
	
	Message m;
	int width;
	
    /**
     *
     * @param message
     */
    public MessageView(Message message) {
		m = message;
		width = ChatView.FINAL_WIDTH;
	}
	
    /**
     *
     * @return
     */
    public BufferedImage createImage() {
		ArrayList<String> messageContent = InGameView.fitString(m.messageContent, width);
		String name = m.charName + " (" + m.senderName + ")";
		setNewWidth(messageContent, name);
		int height = InGameView.LINE_HEIGHT * (messageContent.size() + 1);
		BufferedImage img = new BufferedImage(width + 1, height + 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) img.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		
		if (m.senderName.equals(AccessHandler.username)) {
			g.setColor(new Color(135, 206, 250));
			g.fillRoundRect(0, 0, width, height, 30, 30);
			g.setColor(Color.BLACK);
			g.setFont(InGameView.boldFont);
			g.drawString(name, width - 10 - getWidthOfString(InGameView.boldFont, name), 15);
			g.setFont(InGameView.font);
			for (int i = 0; i < messageContent.size(); i++) {
				g.drawString(messageContent.get(i), 10, (i + 1)*InGameView.LINE_HEIGHT + 15);
			}
		} else {
			g.setColor(Color.WHITE);
			g.fillRoundRect(0, 0, width, height, 30, 30);
			g.setColor(Color.BLACK);
			g.setFont(InGameView.boldFont);
			g.drawString(name, 10, 15);
			g.setFont(InGameView.font);
			for (int i = 0; i < messageContent.size(); i++) {
				g.drawString(messageContent.get(i), 10, (i + 1)*InGameView.LINE_HEIGHT + 15);
			}
		}
		g.drawRoundRect(0, 0, width, height, 30, 30);
		
		return img;
	}
	
	private void setNewWidth(ArrayList<String> modified, String name) {
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
		int newWidth = 0;
		for (int i = 0; i < modified.size(); i++) {
			int length = (int)(InGameView.font.getStringBounds(modified.get(i), frc).getWidth());
			if (newWidth < length + 23) {
				newWidth = length + 23;
			}
		}
		int length = (int)(InGameView.boldFont.getStringBounds(name, frc).getWidth());
		if (newWidth < length + 20) {
			newWidth = length + 20;
		}
		width = newWidth;
	}
	
	private int getWidthOfString(Font font, String str) {
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
		return (int) font.getStringBounds(str, frc).getWidth();
	}
}
