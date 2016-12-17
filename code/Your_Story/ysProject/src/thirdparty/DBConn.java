package thirdparty;
/*==================================================================
 * Author: Erin Avllazagaj AKA "Albocoder"
 * Website: http://erin.avllazagaj.ug.bilkent.edu.tr
 * Latest Changed date: Dec/08/2016
 * Version: 1.5.0
 *==================================================================
 * Description:
 * This class is used to connect to the database
 * */
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class DBConn {
	//class properties
	private String URI, UID, pw, port,database, type;
	//class constructors///////////////////////////////////////////////////////////
	public DBConn(String p){
		this("mysql","localhost","3306","mysql","mysqlUSER",p);
	}
	public DBConn(String id,String p){
		this("mysql","localhost","3306","mysql",id,p);
	}
	public DBConn(String id,String p,String db){
		this("mysql","localhost","3306",db,id,p);
	}
	public DBConn(String id,String p,String db,String port){
		this("mysql","localhost",port,db,id,p);
	}
	
	//class master constructor
	public DBConn(String type,String URI, String pt, String db, String id,String p){
		this.URI = URI;
		this.type = type;
		port = pt;
		UID = id;
		pw = p;
		database = db;
	}
	//class constructors end here /////////////////////////////////////////////////
	
	
	
	//This does the connection according to the data in the object
	public Connection ConnectToDB() {
		Connection c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager
			.getConnection("jdbc:"+ type +"://"+URI+":"+port+"/"+database,UID, pw);
			c.setAutoCommit(true);
		} catch (Exception e) {
			//System.err.println(e.getClass().getName()+": "+e.getMessage());
			JOptionPane.showMessageDialog(null, 
                        "Tch... There is no internet connection! Exiting...",
                        "Uhh... :S", JOptionPane.PLAIN_MESSAGE,
                        new ImageIcon("./img/noInternet.png"));
                        System.exit(0);
		}
		//System.out.println("Connected!");
		return c;
	}
}