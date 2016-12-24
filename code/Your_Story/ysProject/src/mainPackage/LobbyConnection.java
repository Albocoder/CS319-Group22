/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class LobbyConnection {
    
    private static final String LOBBY_DATA = "lobby";
    private static final String PLAYER_DATA = "user";
    private static final String SEAT_DATA = "seat";
    private static final String STORY_DATA = "story";
    private static final String VOTING_DATA = "vote";
    private static final String CHARACTER_DATA = "charac";
    
    /**
     *
     * @param player
     * @return
     */
    public static ArrayList<Lobby> getOngoingGamesOfPlayer(long player){
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
                ".id GROUP BY " + LOBBY_DATA + ".id HAVING " + LOBBY_DATA +
                ".state = 1");
        return new ArrayList<Lobby>(Arrays.asList(DBInterface.resultSetToLobbyArray(r)));
    }
    
    /**
     *
     * @param id
     * @return
     */
    public static Seat getSeat(long id){
        ResultSet r = DBInterface.getConnection().selectStuff("SELECT * FROM " + SEAT_DATA +
                " WHERE id = " + id);
        return DBInterface.resultSetToSeatArray(r)[0];
    }
    
    /**
     *
     * @param id
     * @return
     */
    public static Lobby getLobby(long id){
        ResultSet r = DBInterface.getConnection().selectStuff("SELECT " + LOBBY_DATA +
                ".*, " + STORY_DATA + 
                ".quota - COUNT(" + SEAT_DATA +
                ".id) AS quota, " + STORY_DATA +
                ".id AS storyid FROM " + LOBBY_DATA +
                " LEFT JOIN " + SEAT_DATA + 
                " ON " + SEAT_DATA +
                ".lobby = " + LOBBY_DATA +
                ".id AND " + SEAT_DATA +
                ".user IS NOT NULL INNER JOIN " + STORY_DATA +
                " ON " + LOBBY_DATA +
                ".story = " + STORY_DATA +
                ".ID WHERE " + LOBBY_DATA +
                ".id = " + id + " GROUP BY " + LOBBY_DATA + 
                ".id");
        return DBInterface.resultSetToLobbyArray(r)[0];
    }
    
    /**
     *
     * @param id
     * @return
     */
    public static Player getPlayer(long id){
        return new Player(id);
    }
    
    /**
     *
     * @param lobbyID
     * @return
     */
    public static ArrayList<Seat> getSeats(long lobbyID){
        System.out.println("In2");
        ResultSet r = DBInterface.getConnection().selectStuff("SELECT * FROM " + SEAT_DATA +
                " WHERE lobby = " + lobbyID);
        return new ArrayList<Seat>(Arrays.asList(DBInterface.resultSetToSeatArray(r)));
    }
    
    /**
     *
     * @param storyID
     * @return
     */
    public static Story getStory(long storyID){
        return new Story(DBInterface.selectString(STORY_DATA, "description", storyID),
        DBInterface.selectString(STORY_DATA, "timeline", storyID), storyID);
    }
    
    /**
     *
     * @return
     */
    public static ArrayList<Lobby> getWaitingLobbies(){
        ResultSet r = DBInterface.getConnection().selectStuff("SELECT " + LOBBY_DATA +
                ".*, " + STORY_DATA + 
                ".quota - COUNT(" + SEAT_DATA +
                ".id) AS quota, " + STORY_DATA +
                ".id AS storyid FROM " + LOBBY_DATA +
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
        return new ArrayList<Lobby>(Arrays.asList(DBInterface.resultSetToLobbyArray(r)));
    }
    
    /**
     *
     * @param player
     * @return
     */
    public static ArrayList<Lobby> getLobbies(long player){
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
        return new ArrayList<Lobby>(Arrays.asList(DBInterface.resultSetToLobbyArray(r)));
    }
    
    /**
     *
     * @return
     */
    public static int getOnlineUsers(){
        return DBInterface.selectIntArray(PLAYER_DATA, "id", "online", 1).length;
    }
    
    /**
     *
     * @param lobby
     * @return
     */
    public static ArrayList<Player> getOnlineUsersOfLobby(long lobby){
        ResultSet r = DBInterface.getConnection().selectStuff("SELECT " + PLAYER_DATA +
                ".* FROM " + PLAYER_DATA +
                " INNER JOIN " + SEAT_DATA +
                " ON " + PLAYER_DATA +
                ".online = 1 AND " + SEAT_DATA +
                ".user = " + PLAYER_DATA +
                ".id AND " + SEAT_DATA +
                ".lobby = " + lobby);
        return new ArrayList<Player>(Arrays.asList(DBInterface.resultSetToPlayerArray(r)));
    }
    
    /**
     *
     * @param lobby
     * @return
     */
    public static ArrayList<Player> getOfflineUsersOfLobby(long lobby){
        ResultSet r = DBInterface.getConnection().selectStuff("SELECT " + PLAYER_DATA +
                ".* FROM " + PLAYER_DATA +
                " INNER JOIN " + SEAT_DATA +
                " ON " + PLAYER_DATA +
                ".online = 0 AND " + SEAT_DATA +
                ".user = " + PLAYER_DATA +
                ".id AND " + SEAT_DATA +
                ".lobby = " + lobby);
        return new ArrayList<Player>(Arrays.asList(DBInterface.resultSetToPlayerArray(r)));
    }
    
    /**
     *
     * @param player
     * @return
     */
    public static ArrayList<Story> getStories(long player){
        ResultSet r = DBInterface.getConnection().selectStuff("SELECT " + STORY_DATA +
                ".* FROM " + STORY_DATA + 
                " INNER JOIN " + PLAYER_DATA +
                " ON " + PLAYER_DATA + ".id = " + player);
        return new ArrayList<Story>(Arrays.asList(DBInterface.resultSetToStoryArray(r)));
    }
    
    /**
     *
     * @param name
     * @param story
     * @return
     */
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
    
    /**
     *
     * @param lobby
     * @return
     */
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
    
    /**
     *
     * @param player
     * @param lobby
     * @return
     */
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
    
    /**
     *
     * @param player
     * @param lobby
     */
    public static void removePlayerFromLobby(long player, long lobby){
        DBInterface.getConnection().executeStuff("UPDATE " + SEAT_DATA +
                " SET user = NULL, charac = NULL WHERE lobby = " + lobby + " AND user = " + player);
    }
    
    /**
     *
     * @param player
     * @param character
     * @param lobby
     * @return
     */
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
    
    /**
     *
     * @param lobby
     * @return
     */
    public static ArrayList<Character> getCharacters(long lobby){
        ResultSet r = DBInterface.getConnection().selectStuff("SELECT " + CHARACTER_DATA +
                ".*, COUNT(" + SEAT_DATA +
                ".id) AS occupied FROM " + CHARACTER_DATA +
                " LEFT JOIN " + SEAT_DATA +
                " ON " + SEAT_DATA +
                ".charac = " + CHARACTER_DATA +
                ".id INNER JOIN " + LOBBY_DATA +
                " ON " + LOBBY_DATA +
                ".story = " + CHARACTER_DATA +
                ".story AND " + LOBBY_DATA + 
                ".id = " + lobby + " GROUP BY " + CHARACTER_DATA +".id");
        return new ArrayList<Character>(Arrays.asList(DBInterface.resultSetToCharacterArray(r)));
    }

    /**
     *
     * @param story
     * @return
     */
    public static ArrayList<Character> getCharactersByStory(long story){
        ResultSet r = DBInterface.getConnection().selectStuff("SELECT " + CHARACTER_DATA +
                ".*, 0 AS occupied FROM " + CHARACTER_DATA +
                " WHERE story = " + story);
        return new ArrayList<Character>(Arrays.asList(DBInterface.resultSetToCharacterArray(r)));
    }
    
    /**
     *
     * @param lobby
     * @param state
     */
    public static void setLobbyState(long lobby, int state){
        DBInterface.getConnection().executeStuff("UPDATE " + LOBBY_DATA +
                " SET state = " + state + " WHERE id = " + lobby);
    }
    
    /**
     *
     * @param id
     * @return
     */
    public static Character getCharacter(long id){
        ResultSet r = DBInterface.getConnection().selectStuff("SELECT " + CHARACTER_DATA +
                ".*, 0 AS occupied FROM " + CHARACTER_DATA +
                " WHERE id = " + id);
        return (DBInterface.resultSetToCharacterArray(r)[0]);
    }
}
