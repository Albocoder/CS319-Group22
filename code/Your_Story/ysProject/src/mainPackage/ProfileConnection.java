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
 *
 * @author Asus
 */
public class ProfileConnection {
    
    private static final String LOBBY_DATA = "lobby";
    private static final String PLAYER_DATA = "user";
    private static final String PROFILE_DATA = "profile";
    private static final String SEAT_DATA = "seat";
    private static final String STORY_DATA = "story";
    
    public static boolean updateProfileInClient(Profile profile){
        long userID = profile.getID();
        long profileID = DBInterface.selectIntArray(PROFILE_DATA, "id", "user", userID)[0];
        profile.setDescription(DBInterface.selectString(PROFILE_DATA, "description", profileID));
        profile.setName(DBInterface.selectString(PLAYER_DATA, "username", userID));
        profile.setFinishedGames(getFinishedGames(userID));
        return true;
    }
    
    public static boolean updateProfileInDatabase(Profile profile){
        long userID = DBInterface.selectIntArray(PLAYER_DATA, "id", "username", profile.getName())[0];
        long profileID = DBInterface.selectIntArray(PROFILE_DATA, "id", "user", userID)[0];
        DBInterface.update(PROFILE_DATA, "description", profileID, profile.getDescription());
        return true;
    }
    
    public static ArrayList<Lobby> getFinishedGames(long player){
        ResultSet r = DBInterface.getConnection().selectStuff("SELECT " + LOBBY_DATA +
                ".*, " + STORY_DATA +
                ".quota - COUNT(b.id) AS quota FROM " + LOBBY_DATA +
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
