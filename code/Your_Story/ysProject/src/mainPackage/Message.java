package mainPackage;

import java.sql.Date;

/**
 *
 * @author kaxell
 * Constructor for a message. 
 * 
 */
public class Message {

    /**
     *
     */
    public String senderName;

    /**
     *
     */
    public String charName;

    /**
     *
     */
    public String messageContent;
	
    /**
     *
     * @param senderName
     * @param charName
     * @param messageContent
     */
    public Message(String senderName, String charName, String messageContent) {
		this.senderName = senderName;
		this.charName = charName;
		this.messageContent = messageContent;
	}
}