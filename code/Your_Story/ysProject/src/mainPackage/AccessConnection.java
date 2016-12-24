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
 *
 * @author Cevat
 */
public class AccessConnection {
    
    /**
     *
     */
    public final static String AUTH_DATA = "user";

    /**
     *
     */
    public final static String PROFILE_DATA = "profile";
    
    /**
     *
     * @param username
     * @param password
     * @return
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
     *
     * @param username
     * @return
     */
    public static boolean isAvailable(String username){
        return DBInterface.selectIntArray(AUTH_DATA, "id", "username", username).length == 0;
    }

    /**
     *
     * @param username
     * @param password
     * @return
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
     *
     * @param player
     */
    public static void logout(long player){
        DBInterface.getConnection().executeStuff("UPDATE " + AUTH_DATA +
                    " SET online = 0 WHERE id = " + player);
    }
}