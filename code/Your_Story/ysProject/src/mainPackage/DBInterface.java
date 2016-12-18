package mainPackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import thirdparty.*;

public class DBInterface {
	private static final String type = "mysql";
        private static final String adress = "sql7.freemysqlhosting.net";
        private static final String port = "3306";
        private static final String database = "sql7149730";
        private static final String username = "sql7149730";
        private static final String password = "g55vGMRzBq";
        
        private static final String VOTING_DATA = "voting";
        
	private static DBConn c = new DBConn(type, adress, port, database, username, password);
        private static Connection con = c.ConnectToDB();
        private static DBInter db = new DBInter(con);

        public static DBInter getConnection(){
            return db;
        }

        public static String escapeString(String term){
            term = term.replaceAll("'","''");
            return term;
        }
        
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
        
        public static Seat[] resultSetToSeatArray(ResultSet r){
            try {
                r.last();
                int size = r.getRow();
                Seat[] result = new Seat[size];
                int index = 0;
                Seat s;
                for(boolean go = r.first(); go; go = r.next()){
                    s = new Seat(r.getLong("id"), r.getLong("lobby"), r.getLong("charac"));
                    if(r.getLong("user") > 0)
                        s.addPlayer(r.getByte("user"));
                    result[index++] = s;
                }
                return result;
            } catch (SQLException ex) {
                Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        
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

        public static boolean update(String table, String column, long id, String value){
            value = escapeString(value);
            return db.executeStuff("UPDATE " + table + " SET " + column + " = '" + value + "' WHERE id = " + id);
        }

	public static boolean update(String table, String column, long id, int value){
            return db.executeStuff("UPDATE " + table + " SET " + column + " = " + value + " WHERE id = " + id);
        }

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

        public static long[] selectIntArray(String table, String targetColumn, String conditionColumn, String conditionValue){
            conditionValue = escapeString(conditionValue);
            ResultSet r = db.selectStuff("SELECT " + targetColumn + " FROM " + table + " WHERE " + conditionColumn + " = '" + conditionValue + "'");
            return resultSetToIntArray(r);
        }

        public static long[] selectIntArray(String table, String targetColumn, String conditionColumn, long conditionValue){
            ResultSet r = db.selectStuff("SELECT " + targetColumn + " FROM " + table + " WHERE " + conditionColumn + " = " + conditionValue);
            return resultSetToIntArray(r);
        }

        public static String[] selectStringArray(String table, String targetColumn, String conditionColumn, String conditionValue){
            conditionValue = escapeString(conditionValue);
            ResultSet r = db.selectStuff("SELECT " + targetColumn + " FROM " + table + " WHERE " + conditionColumn + " = '" + conditionValue + "'");
            return resultSetToStringArray(r);
        }

        public static String[] selectStringArray(String table, String targetColumn, String conditionColumn, long conditionValue){
            ResultSet r = db.selectStuff("SELECT " + targetColumn + " FROM " + table + " WHERE " + conditionColumn + " = " + conditionValue);
            return resultSetToStringArray(r);
        }
}