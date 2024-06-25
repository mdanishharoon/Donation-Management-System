import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import com.mysql.cj.jdbc.MysqlDataSource;

public class My_CNX extends JFrame {
	
	private static String servername = "localhost";
	private static String username = "root";
	private static String dbname = "users_db";
	private static Integer portnumber = 3306;
	private static String passsword = "";
	
	
	
	
	
		public static Connection getConnection() {
			Connection  cnx = null;
			
			MysqlDataSource datasource = new MysqlDataSource();
			
			datasource.setServerName(servername);
			datasource.setUser(username);
			datasource.setPassword(passsword);
			datasource.setDatabaseName(dbname);
			datasource.setPortNumber(portnumber);
			
			
			
			 try {
				cnx= datasource.getConnection();
			} catch (SQLException ex) {
				
				Logger.getLogger(" Get Connection -> " + My_CNX.class.getName()).log(Level.SEVERE, null, ex);
				
			}
			
			
			return cnx;
			
		
			
		}
}