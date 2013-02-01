package project1;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ProjSec {
	
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
