package mainPackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import thirdparty.*;

public class DBInterface {
	private static String type = "mysql";
        private static String adress = "sql7.freemysqlhosting.net";
        private static String port = "3306";
        private static String database = "sql7149730";
        private static String username = "sql7149730";
        private static String password = "g55vGMRzBq";
        
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
        
        public static Lobby[] resultSetToLobbyArray(ResultSet r){
            try {
                r.last();
                int size = r.getRow();
                Lobby[] result = new Lobby[size];
                int index = 0;
                Lobby l;
                for(boolean go = r.first(); go; go = r.next()){
                    //l = new Lobby(r.getString("name"), r.getLong("id"), r.getInt("quota"), r.getInt("state"), null);
                    //result[index++] = l;
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