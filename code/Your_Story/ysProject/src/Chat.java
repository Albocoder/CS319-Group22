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
    private int threshold;
    
    public Chat(int lobby, String username){
        this.lobby = lobby;
        this.username = username;
    }
    public String[] getMessages(){
        String[] result = ChatConnection.getMessages(lobby);
        updateThreshold();
        return result;
    }
    public String[] getNewMessages(){
        String result[] = ChatConnection.getMessages(lobby, threshold);
        updateThreshold();
        return result;
    }
    private void updateThreshold(){
        int[] temp = DBInterface.selectIntArray(ChatConnection.CHAT_DATA, "id", "lobby", lobby);
        threshold = temp[temp.length - 1];
    }
    public int sendMessage(String message){
        message = username + ": " + message;
        return ChatConnection.sendMessage(message, lobby);
    }
    
}