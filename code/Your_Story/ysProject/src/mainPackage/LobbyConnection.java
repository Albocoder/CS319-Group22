/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class LobbyConnection {
    
    private static final String LOBBY_DATA = "lobby";
    private static final String PLAYER_DATA = "user";
    private static final String SEAT_DATA = "seat";
    private static final String STORY_DATA = "story";
    
    public static Story getStory(long storyID){
        return new Story(DBInterface.selectString(STORY_DATA, "description", storyID),
        DBInterface.selectString(STORY_DATA, "timeline", storyID), storyID);
    }
    
    public static Lobby[] getWaitingLobbies(){
        ResultSet r = DBInterface.getConnection().selectStuff("SELECT " + LOBBY_DATA +
                ".*, " + STORY_DATA + 
                ".quota - COUNT(" + SEAT_DATA +
                ".id) AS quota FROM " + LOBBY_DATA +
                " LEFT JOIN " + SEAT_DATA + 
                " ON " + SEAT_DATA +
                ".lobby = " + LOBBY_DATA +
                ".id AND " + SEAT_DATA +
                ".user IS NOT NULL INNER JOIN " + STORY_DATA +
                " ON " + LOBBY_DATA +
                ".story = " + STORY_DATA +
                ".ID WHERE " + LOBBY_DATA +
                ".state = 0 GROUP BY " + LOBBY_DATA + 
                ".id HAVING quota > 0");
        return DBInterface.resultSetToLobbyArray(r);
    }
    
    public static Lobby[] getLobbies(long player){
        ResultSet r = DBInterface.getConnection().selectStuff("SELECT " + LOBBY_DATA +
                ".*, " + STORY_DATA +
                ".quota - COUNT(b.id) AS quota, " + STORY_DATA +
                ".id AS storyid FROM " + LOBBY_DATA +
                " INNER JOIN " + SEAT_DATA +
                " a ON a.lobby = " + LOBBY_DATA + 
                ".id AND a.user = " + player + " LEFT JOIN " + SEAT_DATA +
                " b ON b.lobby = " + LOBBY_DATA +
                ".id AND b.user IS NOT NULL LEFT JOIN " + STORY_DATA +
                " ON " + LOBBY_DATA +
                ".story = " + STORY_DATA +
                ".id GROUP BY " + LOBBY_DATA + ".id");
        return DBInterface.resultSetToLobbyArray(r);
        
    }
    
    public static int getOnlineUsers(){
        return DBInterface.selectIntArray(PLAYER_DATA, "id", "online", 1).length;
    }
    
    public static Player[] getOnlineUsersOfLobby(long lobby){
        ResultSet r = DBInterface.getConnection().selectStuff("SELECT " + PLAYER_DATA +
                ".* FROM " + PLAYER_DATA +
                " INNER JOIN " + SEAT_DATA +
                " ON " + PLAYER_DATA +
                ".online = 1 AND " + SEAT_DATA +
                ".user = " + SEAT_DATA +
                ".id AND " + SEAT_DATA +
                ".lobby = " + lobby);
        return /*DBInterface.resultSetToPlayerArray(r);*/null;
    }
    
    public static Story[] getStories(long player){
        ResultSet r = DBInterface.getConnection().selectStuff("SELECT " + STORY_DATA +
                ".* FROM " + STORY_DATA + 
                " INNER JOIN " + PLAYER_DATA +
                " ON " + PLAYER_DATA + ".id = " + player);
        return DBInterface.resultSetToStoryArray(r);
    }
    
    public static long createLobby(String name, long story){
        name = DBInterface.escapeString(name);
        DBInterface.getConnection().executeStuff("INSERT INTO " + LOBBY_DATA + "(name, state, story) VALUES('" + name + "', 0, " + story + ")");
        long[] result = DBInterface.selectIntArray(LOBBY_DATA, "id", "story", story);
        long lobby = result[result.length - 1];
        long quota = DBInterface.selectInt(STORY_DATA, "quota", story);
        for(long i = 0; i < quota; i++){
            DBInterface.getConnection().executeStuff("INSERT INTO " + SEAT_DATA + "(lobby) VALUES(" + lobby + ")");
        }
        return lobby;
    }
    
    public static int getQuota(long lobby){
        try {
            ResultSet r = DBInterface.getConnection().selectStuff("SELECT " + STORY_DATA +
                    ".quota - COUNT(" + SEAT_DATA +
                    ".id) AS quota FROM " + LOBBY_DATA +
                    " LEFT JOIN " + STORY_DATA +
                    " ON " + LOBBY_DATA +
                    ".story = " + STORY_DATA +
                    ".id LEFT JOIN " + SEAT_DATA +
                    " ON " + SEAT_DATA +
                    ".user IS NOT NULL AND " + SEAT_DATA +
                    ".lobby = " + LOBBY_DATA +
                    ".id WHERE lobby.id = " + lobby);
            r.first();
            return r.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(LobbyConnection.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public static boolean addPlayerToLobby(long player, long lobby){
        try {
            ResultSet r = DBInterface.getConnection().selectStuff("SELECT id FROM " + SEAT_DATA +
                    " WHERE lobby = " + lobby + " AND user = " + player);
            r.last();
            if(r.getRow() > 0)
                return false;
            DBInterface.getConnection().executeStuff("UPDATE " + SEAT_DATA +
                    " SET user = " + player +
                    " WHERE id IN (SELECT id FROM (SELECT id FROM " + SEAT_DATA +
                    " WHERE lobby = " + lobby +
                    " AND user IS NULL LIMIT 1) temp)");
            r = DBInterface.getConnection().selectStuff("SELECT id FROM " + SEAT_DATA +
                    " WHERE lobby = " + lobby + " AND user = " + player);
            r.last();
            return r.getRow() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(LobbyConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static void removePlayerFromLobby(long player, long lobby){
        DBInterface.getConnection().executeStuff("UPDATE " + SEAT_DATA +
                " SET user = NULL, charac = NULL WHERE lobby = " + lobby + " AND user = " + player);
    }
    
    public static boolean assignCharacterToPlayer(long player, long character, long lobby){
        try {
            ResultSet r = DBInterface.getConnection().selectStuff("SELECT id FROM seat WHERE lobby = " + lobby +
                    " AND charac = " + character);
            r.last();
            if(r.getRow() > 0)
                return false;
            DBInterface.getConnection().executeStuff("UPDATE " + SEAT_DATA +
                    " SET charac = " + character + " WHERE user = " + player + " AND lobby = " + lobby);
            r = DBInterface.getConnection().selectStuff("SELECT id FROM seat WHERE user = " + player +
                    " AND lobby = " + lobby +
                    " AND charac = " + character);
            r.last();
            return r.getRow() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(LobbyConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
