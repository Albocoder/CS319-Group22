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
    private final static String PLAYER_DATA = "user";
    private final static String SEAT_DATA = "seat";
    private final static String CHARACTER_DATA = "charac";
    
    public static ArrayList<Message> getMessages(long id){
        ResultSet r =DBInterface.getConnection().selectStuff("SELECT " + CHAT_DATA +
                ".body AS body, " + CHARACTER_DATA +
                ".name AS charac, " + PLAYER_DATA +
                ".username AS user FROM " + CHAT_DATA +
                " LEFT JOIN " + PLAYER_DATA +
                " ON " + PLAYER_DATA +
                ".id = " + CHAT_DATA +
                ".user LEFT JOIN " + SEAT_DATA +
                " ON " + SEAT_DATA +
                ".user = " + CHAT_DATA +
                ".user AND " + SEAT_DATA +
                ".lobby = " + CHAT_DATA +
                ".lobby LEFT JOIN " + CHARACTER_DATA +
                " ON " + SEAT_DATA +
                ".charac = " + CHARACTER_DATA +
                ".id WHERE " + CHAT_DATA +
                ".lobby = " + id + " GROUP BY " + CHAT_DATA +
                ".id");
        return new ArrayList<Message>(Arrays.asList(DBInterface.resultSetToMessageArray(r)));
    }
    public static ArrayList<Message> getMessages(long lobby, long lastMessage){
        ResultSet r =DBInterface.getConnection().selectStuff("SELECT " + CHAT_DATA +
                ".body AS body, " + CHARACTER_DATA +
                ".name AS charac, " + PLAYER_DATA +
                ".username AS user FROM " + CHAT_DATA +
                " LEFT JOIN " + PLAYER_DATA +
                " ON " + PLAYER_DATA +
                ".id = " + CHAT_DATA +
                ".user LEFT JOIN " + SEAT_DATA +
                " ON " + SEAT_DATA +
                ".user = " + CHAT_DATA +
                ".user AND " + SEAT_DATA +
                ".lobby = " + CHAT_DATA +
                ".lobby LEFT JOIN " + CHARACTER_DATA +
                " ON " + SEAT_DATA +
                ".charac = " + CHARACTER_DATA +
                ".id WHERE " + CHAT_DATA +
                ".lobby = 9 AND " + CHAT_DATA +
                ".id > " + lastMessage + " GROUP BY " + CHAT_DATA +
                ".id");
        return new ArrayList<Message>(Arrays.asList(DBInterface.resultSetToMessageArray(r)));
    }
    public static long sendMessage(String message, long lobby, long user){
        message = DBInterface.escapeString(message);
        DBInterface.getConnection().executeStuff("INSERT INTO " + CHAT_DATA + "(user, body, lobby) VALUES(" + user +", '" + message + "', " + lobby + ")");
        long[] temp = DBInterface.selectIntArray(CHAT_DATA, "id", "lobby", lobby);
        return temp[temp.length - 1];
    }
    
}
