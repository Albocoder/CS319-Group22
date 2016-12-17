package thirdparty;

/*==================================================================
 * Author: Erin Avllazagaj AKA "Albocoder"
 * Website: http://erin.avllazagaj.ug.bilkent.edu.tr
 * Latest Changed date: Dec/08/2016
 * Version: 2.3.1
 *==================================================================
 * Description:
 * This class is an interface to ease communication with DB
 * */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class DBInter {
	private Connection c;

	public DBInter(Connection con){
		c = con;
	}
	
	public static void writeLog(String toWrite){
		Date curDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat();
		String dateToStr = format.format(curDate);
		try {
			PrintWriter writer = new PrintWriter(new FileOutputStream(new File("logs.txt"), true /* append = true */));
			writer.println("[ "+dateToStr+" ]\t"+toWrite+"\n");
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	//for selections
	public ResultSet selectStuff(String query) {
            int timeout = 0;
		Statement stat;
		ResultSet rs = null;
		try {
			stat = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			if(timeout!=0)
				stat.setQueryTimeout(timeout);
			rs = stat.executeQuery(query);
		} catch (SQLException e) {
			if(e.getMessage().equalsIgnoreCase("terminating connection due to administrator command"))
				try {
                                    Thread.sleep(300000);
				} catch (InterruptedException ex) {
                                    Logger.getLogger(DBInter.class.getName()).log(Level.SEVERE, null, ex);
				}
			//writeLog( "Selecting data ==> "+ e.getClass().getName()+": "+ e.getMessage() );
                        else
                            serverError();
		}
		return rs;
	}


	
	
	//Stuff below needs improvement since they are not used 

	public void printData(ResultSet r){
		if(r == null)
			return;
		try {
			ResultSetMetaData metaData = r.getMetaData();
			int count;
			count = metaData.getColumnCount();
			String[] colNames = new String[count];
			for (int i = 1; i <= count; i++)
				colNames[i-1] = metaData.getColumnName(i);
			for (int i = 1; i <= count; i++)
				System.out.print(colNames[i-1]+"\t|");
			while ( r.next() ) {
				String query = "\n";
				for (int i = 0; i <= count-1; i++){
					try{
						query += r.getString(colNames[i]).toString() + "\t|";
					}
					catch(SQLException ex){
						//System.out.println("Error in loop: " + ex);
						//query +=  "NULL\t|";
						continue;
					}
				}
				//System.out.println(query);
			}
		} catch (SQLException e) {
			//writeLog("Printing data ==> "+ e.getClass().getName()+": "+ e.getMessage() );
		}
	}
	
	//writes resultset to file output.txt
	public void writeData(ResultSet r){
		try {
			PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
			ResultSetMetaData metaData = r.getMetaData();
			int count;
			count = metaData.getColumnCount();
			String[] colNames = new String[count];
			for (int i = 1; i <= count; i++)
				colNames[i-1] = metaData.getColumnName(i);
			for (int i = 1; i <= count; i++){
				writer.println(colNames[i-1]+"\t|");
				writer.flush();
			}
			while ( r.next() ) {
				String query = "\n";
				for (int i = 0; i <= count-1; i++){
					try{
						query += r.getString(colNames[i]).toString() + "\t|";
					}
					catch(SQLException ex){
						//System.out.println("Error in writing: " + ex);
						//query +=  "NULL\t|";
						continue;
					}
				}
				//write in file instead of printing!!!
				writer.println(query);
			}
			writer.close();
			//System.out.println("Wrote to output.txt");
		} catch (SQLException | FileNotFoundException | UnsupportedEncodingException e) {
			//System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		}
	}
	
	// for updates and insertions
	public boolean executeStuff(String query){
		Statement stat = null;
		try {
			stat = c.createStatement();
			stat.executeUpdate(query);
			//c.commit();    //can change this to commit the change
			stat.close();
		} catch (SQLException e) {
                        serverError();
			return false;
		}
		return true;
	}
        private void serverError(){
            JOptionPane.showMessageDialog(null, 
                "Well this is embarrasing! Server experienced and error!",
                "Oops...", JOptionPane.PLAIN_MESSAGE,
                new ImageIcon("./img/serverError.png"));
        }
}