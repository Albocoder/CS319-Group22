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
    
    private int lobby;
    private String username;
    private long threshold;
    
    public Chat(int lobby, String username){
        this.lobby = lobby;
        this.username = username;
    }
    public ArrayList<String> getMessages(){
        ArrayList<String> result = ChatConnection.getMessages(lobby);
        updateThreshold();
        return result;
    }
    public ArrayList<String> getNewMessages(){
        ArrayList<String> result = ChatConnection.getMessages(lobby, threshold);
        updateThreshold();
        return result;
    }
    private void updateThreshold(){
        long[] temp = DBInterface.selectIntArray(ChatConnection.CHAT_DATA, "id", "lobby", lobby);
        threshold = temp[temp.length - 1];
    }
    public long sendMessage(String message){
        message = username + ": " + message;
        return ChatConnection.sendMessage(message, lobby);
    }
    
}