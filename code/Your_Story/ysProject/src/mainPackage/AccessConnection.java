package mainPackage;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import thirdparty.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class is used for connections for accessing or creating a new account
 * @author Cevat
 */
public class AccessConnection {
    
    /**
    * Table name for user in database
     */
    public final static String AUTH_DATA = "user";

    /**
    * Table name for profile in database
     */
    public final static String PROFILE_DATA = "profile";
    
    /**
     * Adds user to database
     * @param username Nickname of user
     * @param password Password of user
     * @return ID of new user
     */
    public static long record(String username, String password){
        if(!isAvailable(username))
            return -1;
        username = DBInterface.escapeString(username);
        password = DBInterface.escapeString(password);
        if(!DBInterface.getConnection().executeStuff("INSERT INTO " + AUTH_DATA + "(username, password) VALUES('" + username + "', '" + password + "')"))
            return -1;
        long id = DBInterface.selectIntArray(AUTH_DATA, "id", "username", username)[0];
        DBInterface.getConnection().executeStuff("INSERT INTO " + PROFILE_DATA + "(user, description) VALUES(" + id + ", '')");
        return id;
    }

    /**
     * Checks if given username is available
     * @param username Username to check
     * @return A boolean that if it is available or not
     */
    public static boolean isAvailable(String username){
        return DBInterface.selectIntArray(AUTH_DATA, "id", "username", username).length == 0;
    }

    /**
     * Checks the given credentials
     * @param username Username of user
     * @param password Password of user
     * @return ID of player on success, otherwise it will be equal to -1
     */
    public static long authenticate(String username, String password){
        try {
            username = DBInterface.escapeString(username);
            password = DBInterface.escapeString(password);
            ResultSet r;
            r = DBInterface.getConnection().selectStuff("SELECT id FROM " + AUTH_DATA + " WHERE username = '" + username + "' AND password = '" + password + "'");
            if(!r.first())
                return -1;
            DBInterface.getConnection().executeStuff("UPDATE " + AUTH_DATA +
                    " SET online = 1 WHERE username = '" + username +
                    "'");
            return r.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessConnection.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    
    /**
     * Sets offline a user in database
     * @param player ID of user
     */
    public static void logout(long player){
        DBInterface.getConnection().executeStuff("UPDATE " + AUTH_DATA +
                    " SET online = 0 WHERE id = " + player);
    }
}