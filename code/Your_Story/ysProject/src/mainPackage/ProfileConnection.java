/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Connection class that handles database relation for profile class
 * @author Cevat
 */
public class ProfileConnection {
    
    private static final String LOBBY_DATA = "lobby";
    private static final String PLAYER_DATA = "user";
    private static final String PROFILE_DATA = "profile";
    private static final String SEAT_DATA = "seat";
    private static final String STORY_DATA = "story";
    
    /**
     * Updates the profile table in database
     * @param profile Profile object to be uploaded
     * @return Boolean that show if it was successful or not
     */
    public static boolean updateProfileInClient(Profile profile){
        long userID = profile.getID();
        long profileID = DBInterface.selectIntArray(PROFILE_DATA, "id", "user", userID)[0];
        profile.setName(DBInterface.selectString(PLAYER_DATA, "username", userID));
        profile.setFinishedGames(getFinishedGames(userID));
        profile.setDescription(DBInterface.selectString(PROFILE_DATA, "description", profileID));
        return true;
    }
    
    /**
     * Updates the profile object with the info at database
     * @param profile Profile object to be updated
     * @return Boolean that show if it was successful or not
     */
    public static boolean updateProfileInDatabase(Profile profile){
        long userID = DBInterface.selectIntArray(PLAYER_DATA, "id", "username", profile.getName())[0];
        long profileID = DBInterface.selectIntArray(PROFILE_DATA, "id", "user", userID)[0];
        DBInterface.update(PROFILE_DATA, "description", profileID, profile.getDescription());
        return true;
    }
    
    /**
     * Gets finished gamed of a player
     * @param player ID of player
     * @return Arraylist of lobbies
     */
    public static ArrayList<Lobby> getFinishedGames(long player){
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
                ".state = 2");
        return new ArrayList<Lobby>(Arrays.asList(DBInterface.resultSetToLobbyArray(r)));
    }
    
}
