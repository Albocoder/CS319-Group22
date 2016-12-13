package mainPackage;

import java.sql.ResultSet;
import thirdparty.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cevat
 */
public class ChatConnection {
    
    public final static String CHAT_DATA = "message";
    
    public static String[] getMessages(int id){
        return DBInterface.selectStringArray(CHAT_DATA, "body", "lobby", id);
    }
    public static String[] getMessages(int lobby, int lastMessage){
        ResultSet r = null;
        r = DBInterface.getConnection().selectStuff("SELECT body FROM " + CHAT_DATA + " WHERE lobby = " + lobby + " AND id > " + lastMessage);
        return DBInterface.resultSetToStringArray(r);
    }
    public static int sendMessage(String message, int lobby){
        message = DBInterface.escapeString(message);
        DBInterface.getConnection().executeStuff("INSERT INTO " + CHAT_DATA + "(body, lobby) VALUES('" + message + "', " + lobby + ")");
        int[] temp = DBInterface.selectIntArray(CHAT_DATA, "id", "lobby", lobby);
        return temp[temp.length - 1];
    }
    
}