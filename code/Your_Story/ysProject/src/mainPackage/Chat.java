package mainPackage;
import java.util.ArrayList;
import java.util.Arrays;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cevat
 */
public class Chat {
    
    private long lobby;
    private long user;
    private long threshold;
    
    /**
     *
     * @param lobby
     * @param user
     */
    public Chat(long lobby, long user){
        this.lobby = lobby;
        this.user = user;
    }

    /**
     *
     * @return
     */
    public ArrayList<Message> getMessages(){
        ArrayList<Message> result = ChatConnection.getMessages(lobby);
        updateThreshold();
        return result;
    }

    /**
     *
     * @return
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
     *
     * @param message
     * @return
     */
    public long sendMessage(String message){
        return ChatConnection.sendMessage(message, lobby, user);
    }
    
}