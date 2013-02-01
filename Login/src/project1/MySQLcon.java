package project1;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;

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
	
	public  MySQLcon(){
		try{
			//Accessing driver from JAR file
			Class.forName("com.mysql.jdbc.Driver");
			
			//Create var for connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
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
	
	//Method to close the connection to the DB
	public boolean Close() {
		try{
			 //Close connection to DB
			con.close();
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//Method to convert a string of text to XSS attack and SQL injection safe text
	public String Sec(String s) {
		try{
			s=toSafeHtml(s);
			ArrayList<Character> al = new ArrayList<Character>();
			char c[]=s.toCharArray();
			for(int i=0; i<c.length; i++){
				al.add(c[i]);
			}
			for(int i=0; i<al.size(); i++){

				if(al.get(i)=='/'){
					al.add(i, '/');
					i++;
				}
				if(al.get(i)=='%'){
					al.add(i, '%');
					i++;
				}
				if(al.get(i)=='!'){
					al.add(i, '!');
					i++;
				}
				if(al.get(i)=='@'){
					al.add(i, '@');
					i++;
				}
				if(al.get(i)=='"'){
					al.set(i, '/');
				}
				if(al.get(i)=='\''){
					al.set(i, '%');
				}
				if(al.get(i)==';'){
					al.set(i, '!');
				}
				if(al.get(i)=='-'){
					al.set(i, '@');
				}
					
			}
			s="";
			for(int i=0; i<al.size(); i++){
				s+=al.get(i);
			}
			return s;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//Method to normally display SQL injection protected text from the database
	private String SecGet(String s) {
		int a;
		try{
			ArrayList<Character> al = new ArrayList<Character>();
			char c[]=s.toCharArray();
			for(int i=0; i<c.length; i++){
				al.add(c[i]);
			}
			for(int i=0; i<al.size(); i++){
				a=1;
				if(i<(al.size()-1)){
					if((al.get(i)=='/')&&(al.get(i+1)=='/')){
						al.remove(i);
						a=0;
					}
					if((al.get(i)=='%')&&(al.get(i+1)=='%')){
						al.remove(i);
						a=0;
					}
					if((al.get(i)=='!')&&(al.get(i+1)=='!')){
						al.remove(i);
						a=0;
					}
					if((al.get(i)=='@')&&(al.get(i+1)=='@')){
						al.remove(i);
						a=0;
					}
				}
				if(a==1){
					if(al.get(i)=='"'){
						al.set(i, '/');
					}
					if(al.get(i)=='%'){
						al.set(i, '\'');
					}
					if(al.get(i)=='!'){
						al.set(i, ';');
					}
					if(al.get(i)=='@'){
						al.set(i, '-');
					}
				}
					
			}
			s="";
			for(int i=0; i<al.size(); i++){
				s+=al.get(i);
			}
			return s;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	//Private method to convert a string in to XSS attack safe text
	public String toSafeHtml(String html){

		try{
		    Policy policy = Policy.getInstance("C:\\Users\\Mario\\workspace\\Login\\WebContent\\WEB-INF\\antisamy-slashdot-1.4.4.xml");
		    AntiSamy antiSamy = new AntiSamy(policy);
		    CleanResults cleanResults = antiSamy.scan(html, policy);
		    return cleanResults.getCleanHTML()/*.trim()*/;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String toMD5(String s){
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			m.update(s.getBytes(),0,s.length());
			s = new BigInteger(1,m.digest()).toString(16);
			while((32-s.length())!=0){
				s="0"+s;
			}
			return s;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
