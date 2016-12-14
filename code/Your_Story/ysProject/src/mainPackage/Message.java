package mainPackage;

import java.sql.Date;

public class Message {
	public String senderName;
	public String charName;
	public String messageContent;
	
	public Message(String senderName, String charName, String messageContent) {
		this.senderName = senderName;
		this.charName = charName;
		this.messageContent = messageContent;
	}
}
