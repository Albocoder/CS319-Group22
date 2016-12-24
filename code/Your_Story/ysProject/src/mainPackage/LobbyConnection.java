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
 * This class is used by Lobby class to handle database relation
 * @author Cevat
 */
public class LobbyConnection {
    
    private static final String LOBBY_DATA = "lobby";
    private static final String PLAYER_DATA = "user";
    private static final String SEAT_DATA = "seat";
    private static final String STORY_DATA = "story";
    private static final String VOTING_DATA = "vote";
    private static final String CHARACTER_DATA = "charac";
    
    /**
     * Gets ongoing gamed of a player
     * @param player ID of player
     * @return Arraylist of lobbied
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
     * Gets a seat object feom databade
     * @param id ID of seat to get
     * @return The seat object
     */
    public static Seat getSeat(long id){
        ResultSet r = DBInterface.getConnection().selectStuff("SELECT * FROM " + SEAT_DATA +
                " WHERE id = " + id);
        return DBInterface.resultSetToSeatArray(r)[0];
    }
    
    /**
     * Gets a lobby object from database
     * @param id ID of lobby to get
     * @return Lobby Object
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
     * Creates a player object
     * @param id ID of player
     * @return Player object
     */
    public static Player getPlayer(long id){
        return new Player(id);
    }
    
    /**
     * Get seats of a lobby
     * @param lobbyID ID of lobby
     * @return Arraylist of lobbies
     */
    public static ArrayList<Seat> getSeats(long lobbyID){
        System.out.println("In2");
        ResultSet r = DBInterface.getConnection().selectStuff("SELECT * FROM " + SEAT_DATA +
                " WHERE lobby = " + lobbyID);
        return new ArrayList<Seat>(Arrays.asList(DBInterface.resultSetToSeatArray(r)));
    }
    
    /**
     * Gets a story object from database
     * @param storyID ID of story
     * @return The story object
     */
    public static Story getStory(long storyID){
        return new Story(DBInterface.selectString(STORY_DATA, "description", storyID),
        DBInterface.selectString(STORY_DATA, "timeline", storyID), storyID);
    }
    
    /**
     * Gets the waiting lobbies
     * @return Arraylist of lobbies
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
     * Gets the lobbies that player in
     * @param player ID of player
     * @return Arraylist of lobbies
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
     * Gets the number of online users
     * @return Number of online users
     */
    public static int getOnlineUsers(){
        return DBInterface.selectIntArray(PLAYER_DATA, "id", "online", 1).length;
    }
    
    /**
     * Gets online players in a lobby
     * @param lobby ID of lobby
     * @return Arraylist of online players in that lobby
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
     * Gets offline users of a lobby
     * @param lobby ID of lobby
     * @return Arraylist of players that offline in that lobby
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
     * Gets available stories to choose
     * @param player ID of a player to authenticate access to stories
     * @return Arraylist of stories available
     */
    public static ArrayList<Story> getStories(long player){
        ResultSet r = DBInterface.getConnection().selectStuff("SELECT " + STORY_DATA +
                ".* FROM " + STORY_DATA + 
                " INNER JOIN " + PLAYER_DATA +
                " ON " + PLAYER_DATA + ".id = " + player);
        return new ArrayList<Story>(Arrays.asList(DBInterface.resultSetToStoryArray(r)));
    }
    
    /**
     * Creates a lobby in database
     * @param name Name of lobby
     * @param story Chosen story
     * @return ID of lobby
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
     * Gets the remaining quota of a lobby 
     * @param lobby ID of lobby
     * @return Number of quota
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
     * Adds a player to lobby
     * @param player ID of player to add
     * @param lobby ID of lobby to be used
     * @return Boolean that show if it was successful or not
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
     * Removes a player from lobby
     * @param player ID of player
     * @param lobby ID of lobby
     */
    public static void removePlayerFromLobby(long player, long lobby){
        DBInterface.getConnection().executeStuff("UPDATE " + SEAT_DATA +
                " SET user = NULL, charac = NULL WHERE lobby = " + lobby + " AND user = " + player);
    }
    
    /**
     * Assigns a character to player
     * @param player ID of player
     * @param character ID of character
     * @param lobby ID of lobby
     * @return Boolean that if it was successsful or not
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
     * Gets character of a lobby
     * @param lobby ID Of lobby
     * @return Arraylist of characters
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
     * Get characters of a story
     * @param story ID of story
     * @return Arraylist of characters
     */
    public static ArrayList<Character> getCharactersByStory(long story){
        ResultSet r = DBInterface.getConnection().selectStuff("SELECT " + CHARACTER_DATA +
                ".*, 0 AS occupied FROM " + CHARACTER_DATA +
                " WHERE story = " + story);
        return new ArrayList<Character>(Arrays.asList(DBInterface.resultSetToCharacterArray(r)));
    }
    
    /**
     * Sets a new state for lobby
     * @param lobby ID of lobby
     * @param state State as integer
     */
    public static void setLobbyState(long lobby, int state){
        DBInterface.getConnection().executeStuff("UPDATE " + LOBBY_DATA +
                " SET state = " + state + " WHERE id = " + lobby);
    }
    
    /**
     * Gets a character object from database
     * @param id ID of character
     * @return Character object
     */
    public static Character getCharacter(long id){
        ResultSet r = DBInterface.getConnection().selectStuff("SELECT " + CHARACTER_DATA +
                ".*, 0 AS occupied FROM " + CHARACTER_DATA +
                " WHERE id = " + id);
        return (DBInterface.resultSetToCharacterArray(r)[0]);
    }
    
    /**
     * Gets the state of a lobby
     * @param id ID of lobby
     * @return Integer that represents the state
     */
    public static int getState(long id){
        return (int)DBInterface.selectInt(LOBBY_DATA, "state", id);
    }
}
