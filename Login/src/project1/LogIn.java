package project1;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.ResultSet;




public class LogIn {
	private String protpass;
	private MySQLcon db;
	private ResultSet r;
	
	public LogIn(String u ,String s){
		try{
			MessageDigest m=MessageDigest.getInstance("MD5");
			m.update(s.getBytes(),0,s.length());
			protpass = new BigInteger(1,m.digest()).toString(16);
			while((32-protpass.length())!=0){
				protpass="0"+protpass;
			}
			db = new MySQLcon();
			r = db.Quer("SELECT * FROM Users WHERE User_name='"+ u +"' AND User_password='"+ protpass +"';");
		}catch (Exception e){
			protpass = null;
			e.printStackTrace();
		}
	}
	
	public String sucess(){
		try {
			if(r.first()){ //Is result set not empty?
				return r.getString("idUsers");
			}else{
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	public String role(){
		try {
			if(r.first()){ //Is result set not empty?
				return r.getString("Role");
			}else{
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
}
