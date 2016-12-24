package mainPackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import thirdparty.*;

/**
 * This is the  main class for other connection classes to use
 * @author Cevat
 */
public class DBInterface {
	private static final String type = "mysql";
        private static final String adress = "160.153.75.104";
        private static final String port = "3306";
        private static final String database = "YourStoryDB";
        private static final String username = "albocoder";
        private static final String password = "@fI@6U!yMGRMv^]";
        
        private static final String VOTING_DATA = "voting";
        
	private static DBConn c = new DBConn(type, adress, port, database, username, password);
        private static Connection con = c.ConnectToDB();
        private static DBInter db = new DBInter(con);

    /**
     * Gets the connection object
     * @return Connection
     */
    public static DBInter getConnection(){
            return db;
        }

    /**
     * Protects the given string for SQL injections
     * @param term The string to be escaped
     * @return The escaped string
     */
    public static String escapeString(String term){
            term = term.replaceAll("'","''");
            return term;
        }
        
    /**
     * Converts a resultset to array of characters
     * @param r Target resultset
     * @return Array of characters
     */
    public static Character[] resultSetToCharacterArray(ResultSet r){
            try {
                r.last();
                int size = r.getRow();
                Character[] result = new Character[size];
                int index = 0;
                Character c;
                for(boolean go = r.first(); go; go = r.next()){
                    c = new Character(r.getString("description"), r.getString("name"), r.getLong("id"), r.getInt("occupied")== 1);
                    result[index++] = c;
                }
                return result;
            } catch (SQLException ex) {
                Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        
    /**
     * Converts a resultset to array of messages
     * @param r Target resultset
     * @return Array of messages
     */
    public static Message[] resultSetToMessageArray(ResultSet r){
            try {
                r.last();
                int size = r.getRow();
                Message[] result = new Message[size];
                int index = 0;
                Message m;
                for(boolean go = r.first(); go; go = r.next()){
                    m = new Message(r.getString("user"), r.getString("charac"), r.getString("body"));
                    result[index++] = m;
                }
                return result;
            } catch (SQLException ex) {
                Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        
    /**
     * Converts a resultset to array of seats
     * @param r Target resultset
     * @return Array of seats
     */
    public static Seat[] resultSetToSeatArray(ResultSet r){
            System.out.println("Middle2");
            try {
                r.last();
                int size = r.getRow();
                Seat[] result = new Seat[size];
                int index = 0;
                Seat s;
                for(boolean go = r.first(); go; go = r.next()){
                    s = new Seat(r.getLong("id"), r.getLong("lobby"), r.getLong("charac"));
                    System.out.println("Middle3");
                    if(r.getLong("user") > 0)
                        s.addPlayer(r.getByte("user"));
                    System.out.println("Middle4");
                    result[index++] = s;
                }
                System.out.println("Out2");
                return result;
            } catch (SQLException ex) {
                Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        
    /**
     * Converts a resultset to array of players
     * @param r Target resultset
     * @return Array of players
     */
    public static Player[] resultSetToPlayerArray(ResultSet r){
            try {
                r.last();
                int size = r.getRow();
                Player[] result = new Player[size];
                int index = 0;
                Player p;
                for(boolean go = r.first(); go; go = r.next()){
                    p = new Player(r.getLong("id"));
                    result[index++] = p;
                }
                return result;
            } catch (SQLException ex) {
                Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        
    /**
     * Converts a resultset to array of lobbies
     * @param r Target resultset
     * @return Array of lobbies
     */
    public static Lobby[] resultSetToLobbyArray(ResultSet r){
            try {
                r.last();
                int size = r.getRow();
                Lobby[] result = new Lobby[size];
                int index = 0;
                Lobby l;
                for(boolean go = r.first(); go; go = r.next()){
                    l = new Lobby(r.getString("name"), r.getLong("id"), r.getInt("quota"), r.getInt("state"), null, r.getLong("storyid"));
                    long[] votings = DBInterface.selectIntArray(VOTING_DATA, "id", "lobby", l.getID());
                    ArrayList<Long> temp = new ArrayList<Long>();
                    for(int i = 0; i < votings.length; i++){
                        temp.add(new Long(votings[i]));
                    }
                    l.setVoteID(temp);
                    result[index++] = l;
                }
                return result;
            } catch (SQLException ex) {
                Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        
    /**
     * Converts a resultset to array of stories
     * @param r Target resultset
     * @return Array of stories
     */
    public static Story[] resultSetToStoryArray(ResultSet r){
            try {
                r.last();
                int size = r.getRow();
                Story[] result = new Story[size];
                int index = 0;
                Story s;
                for(boolean go = r.first(); go; go = r.next()){
                    s = new Story(r.getString("description"), r.getString("timeline"), r.getLong("id"));
                    result[index++] = s;
                }
                return result;
            } catch (SQLException ex) {
                Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }

    /**
     * Converts a resultset to array of long objects
     * @param r Target resultset
     * @return Array of long objects
     */
    public static long[] resultSetToIntArray(ResultSet r){
            try {
                r.last();
                int size = r.getRow();
                long[] result = new long[size];
                int index = 0;
                for(boolean go = r.first(); go; go = r.next()){
                    result[index++] = r.getLong(1);
                }
                return result;
            } catch (SQLException ex) {
                Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }

    /**
     * Converts a resultset to array of strings
     * @param r Target resultset
     * @return Array of strings
     */
    public static String[] resultSetToStringArray(ResultSet r){
            try {
                r.last();
                int size = r.getRow();
                String[] result = new String[size];
                int index = 0;
                for(boolean go = r.first(); go; go = r.next()){
                    result[index++] = r.getString(1);
                }
                return result;
            } catch (SQLException ex) {
                Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }

    /**
     * Executes a 'Update' query in database
     * @param table Table to update
     * @param column Column to update
     * @param id ID of row to update
     * @param value New value of column
     * @return Boolean that shows if query was successful
     */
    public static boolean update(String table, String column, long id, String value){
            value = escapeString(value);
            return db.executeStuff("UPDATE " + table + " SET " + column + " = '" + value + "' WHERE id = " + id);
        }

    /**
     * Executes a 'Update' query in database
     * @param table Table to update
     * @param column Column to update
     * @param id ID of row to update
     * @param value New value of column
     * @return Boolean that shows if query was successful
     */
    public static boolean update(String table, String column, long id, int value){
            return db.executeStuff("UPDATE " + table + " SET " + column + " = " + value + " WHERE id = " + id);
        }

    /**
     * Executes a 'Select' query in database
     * @param table Table to select
     * @param column Column to select
     * @param id ID of row
     * @return Returns the integer
     */
    public static long selectInt(String table, String column, long id){
            try {
                ResultSet r = db.selectStuff("SELECT " + column + " FROM " + table + " WHERE id = " + id);
                r.first();
                return r.getLong(1);
            } catch (SQLException ex) {
                Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        }

    /**
     * Executes a 'Select' query in database
     * @param table Table to select
     * @param column Column to select
     * @param id ID of row
     * @return Returns the string
     */
    public static String selectString(String table, String column, long id){
            try {
                ResultSet r = db.selectStuff("SELECT " + column + " FROM " + table + " WHERE id = " + id);
                r.first();
                return r.getString(1);
            } catch (SQLException ex) {
                Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }

    /**
     * Executes a 'Select' query in database
     * @param table Table select
     * @param targetColumn Column to select
     * @param conditionColumn Condition column that will be used in WHERE clause
     * @param conditionValue Value that will be used in WHERE clause
     * @return Array of integers that satisfies condition
     */
    public static long[] selectIntArray(String table, String targetColumn, String conditionColumn, String conditionValue){
            conditionValue = escapeString(conditionValue);
            ResultSet r = db.selectStuff("SELECT " + targetColumn + " FROM " + table + " WHERE " + conditionColumn + " = '" + conditionValue + "'");
            return resultSetToIntArray(r);
        }

    /**
     * Executes a 'Select' query in database
     * @param table Table select
     * @param targetColumn Column to select
     * @param conditionColumn Condition column that will be used in WHERE clause
     * @param conditionValue Value that will be used in WHERE clause
     * @return Array of integers that satisfies condition
     */
    public static long[] selectIntArray(String table, String targetColumn, String conditionColumn, long conditionValue){
            ResultSet r = db.selectStuff("SELECT " + targetColumn + " FROM " + table + " WHERE " + conditionColumn + " = " + conditionValue);
            return resultSetToIntArray(r);
        }

    /**
     * Executes a 'Select' query in database
     * @param table Table select
     * @param targetColumn Column to select
     * @param conditionColumn Condition column that will be used in WHERE clause
     * @param conditionValue Value that will be used in WHERE clause
     * @return Array of strings that satisfies condition
     */
    public static String[] selectStringArray(String table, String targetColumn, String conditionColumn, String conditionValue){
            conditionValue = escapeString(conditionValue);
            ResultSet r = db.selectStuff("SELECT " + targetColumn + " FROM " + table + " WHERE " + conditionColumn + " = '" + conditionValue + "'");
            return resultSetToStringArray(r);
        }

    /**
     * Executes a 'Select' query in database
     * @param table Table select
     * @param targetColumn Column to select
     * @param conditionColumn Condition column that will be used in WHERE clause
     * @param conditionValue Value that will be used in WHERE clause
     * @return Array of strings that satisfies condition
     */
    public static String[] selectStringArray(String table, String targetColumn, String conditionColumn, long conditionValue){
            ResultSet r = db.selectStuff("SELECT " + targetColumn + " FROM " + table + " WHERE " + conditionColumn + " = " + conditionValue);
            return resultSetToStringArray(r);
        }
}
