package mainPackage;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
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
    
    public static ArrayList<String> getMessages(long id){
        return new ArrayList<String>(Arrays.asList(DBInterface.selectStringArray(CHAT_DATA, "body", "lobby", id)));
    }
    public static ArrayList<String> getMessages(long lobby, long lastMessage){
        ResultSet r;
        r = DBInterface.getConnection().selectStuff("SELECT body FROM " + CHAT_DATA + " WHERE lobby = " + lobby + " AND id > " + lastMessage);
        return new ArrayList<String>(Arrays.asList(DBInterface.resultSetToStringArray(r)));
    }
    public static long sendMessage(String message, int lobby){
        message = DBInterface.escapeString(message);
        DBInterface.getConnection().executeStuff("INSERT INTO " + CHAT_DATA + "(body, lobby) VALUES('" + message + "', " + lobby + ")");
        long[] temp = DBInterface.selectIntArray(CHAT_DATA, "id", "lobby", lobby);
        return temp[temp.length - 1];
    }
    
}