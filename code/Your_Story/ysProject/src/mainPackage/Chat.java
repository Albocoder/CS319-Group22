package mainPackage;
import java.util.ArrayList;
import java.util.Arrays;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class is used for handling chatting system of a lobby
 * @author Cevat
 */
public class Chat {
    
    private long lobby;
    private long user;
    private long threshold;
    
    /**
     * Default constructor
     * @param lobby ID of lobby
     * @param user ID of user in client
     */
    public Chat(long lobby, long user){
        this.lobby = lobby;
        this.user = user;
    }

    /**
     * Returns all messages of chat
     * @return Arraylist of messages
     */
    public ArrayList<Message> getMessages(){
        ArrayList<Message> result = ChatConnection.getMessages(lobby);
        updateThreshold();
        return result;
    }

    /**
     * Gets new messages
     * @return Arraylist of messages
     */
    public ArrayList<Message> getNewMessages(){
        ArrayList<Message> result = ChatConnection.getMessages(lobby, threshold);
        updateThreshold();
        return result;
    }
    private void updateThreshold(){
        long[] temp = DBInterface.selectIntArray(ChatConnection.CHAT_DATA, "id", "lobby", lobby);
        threshold = temp[temp.length - 1];
    }

    /**
     * Sends a new message to other clients
     * @param message
     * @return ID of new message
     */
    public long sendMessage(String message){
        return ChatConnection.sendMessage(message, lobby, user);
    }
    
}