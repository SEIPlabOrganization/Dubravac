package SPtool;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * Class used for working with the data base.
 * For it to work, the mysql-connector-java-5.1.22-bin.jar library needs to be accessible to the app.
 * To make it accessible download "http://cdn.mysql.com/Downloads/Connector-J/mysql-connector-java-5.1.22.tar.gz".
 * From the downloaded file extract the .jar file and put it in the lib directory of the server.
 * The lib directory is located in the directory that the server was installed.
 */

public class MySQLcon {
	
	//Constructor for connecting to DB, 1st argument it receives is the database access address
	//2nd and 3rd arguments are the user name and password for accessing in to the database
	//In my case : "jdbc:mysql://localhost/test", "root", "a".
	private String DBinfo[] = {null, null, null};
	private Connection con;
	public  MySQLcon(String DBconnadr, String DBusername, String DBpass){
		try{
			DBinfo[0]=DBconnadr;
			DBinfo[1]=DBusername;
			DBinfo[2]=DBpass;
			
			//Accessing driver from JAR file
			Class.forName("com.mysql.jdbc.Driver");
			
			//Create var for connection
			con = DriverManager.getConnection(DBinfo[0], DBinfo[1], DBinfo[2]);

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Method for queries that return Result sets (queries as SELECT)
	//1st and only argument it receives is a string in the form of a MySQL query 
	//Returns ResultSet, if operation successful returns full ResultSet, in case of an error returns null 
	public ResultSet Quer(String query) {
		try{
			//Pass query to DB
			PreparedStatement statement = con.prepareStatement(query);
			
			//Execute query
			ResultSet r = statement.executeQuery();
			
			
			return r;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//Method for DB Alteration (queries as INSERT INTO)
	//1st and only argument it receives is a string in the form of a MySQL query 
	//Returns boolean, if operation successful returns true, in case of an error returns false 
	public boolean Upd(String query) {
		try{
			
			//Pass query to DB
			PreparedStatement statement = con.prepareStatement(query);
			
			//Execute query
			statement.executeUpdate();
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
