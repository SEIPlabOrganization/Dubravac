package project1;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InputUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int check=1;
		
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String role = request.getParameter("type");
		String jmbag = null;
		try{
			if(role.equalsIgnoreCase("Stud")){
				jmbag = request.getParameter("jmbag");
				if(jmbag.length()!=10)
					check=0;
				for(int i=0; i<jmbag.length(); i++){
					if((jmbag.charAt(i)<'0') || (jmbag.charAt(i)>'9'))
						check=0;
				}
			}
			if((role.equalsIgnoreCase("Stud")) || (role.equalsIgnoreCase("Prof")) || (role.equalsIgnoreCase("Assi"))){
			}else
				check=0;
			if((name.length()>50) || (surname.length()>50))
				check=0;
			name=name.toLowerCase();
			for(int i=0; i<name.length(); i++){
				if(((name.charAt(i)<'a') || (name.charAt(i)>'z')) && ((name.charAt(i)!='è') && (name.charAt(i)!='æ') && (name.charAt(i)!='ž') && (name.charAt(i)!='š') && (name.charAt(i)!='ð')))
					check=0;
			}
			String buffer=name.toUpperCase();
			char cn[]=name.toCharArray();
			cn[0]=buffer.charAt(0);
			name=new String(cn);
			
			surname=surname.toLowerCase();
			for(int i=0; i<surname.length(); i++){
				if(((surname.charAt(i)<'a') || (surname.charAt(i)>'z')) && ((surname.charAt(i)!='è') && (surname.charAt(i)!='æ') && (surname.charAt(i)!='ž') && (surname.charAt(i)!='š') && (surname.charAt(i)!='ð')))
					check=0;
			}
			buffer=surname.toUpperCase();
			char cs[]=surname.toCharArray();
			cs[0]=buffer.charAt(0);
			surname=new String(cs);
			if(check==1){
				MySQLcon db = new MySQLcon();
				if(role.equalsIgnoreCase("Stud")){
					if(!db.Upd("INSERT INTO Users SET idUsers='"+jmbag+"', Name='"+name+"', Surname='"+surname+"', User_name='"+jmbag+"', User_password='"+db.toMD5(name+"."+surname)+"', Role='"+role+"';"))
						check=0;
				}
				if(role.equalsIgnoreCase("Prof") || role.equalsIgnoreCase("Assi")){
					String password="privilegije";
					MessageDigest m;
					m=MessageDigest.getInstance("MD5");
					m.update(password.getBytes(),0,password.length());
					password = new BigInteger(1,m.digest()).toString(16);
					while((32-password.length())!=0){
						password="0"+password;
					}
					int bid=0;
					String id=null;
					ResultSet r;
					while(id==null){
						bid++;
						id=new Integer(bid).toString();
						while((10-id.length())!=0){
							id="0"+id;
						}
						r = db.Quer("SELECT * FROM Users WHERE idUsers='"+ id +"';");
						if(r.first())
							id=null;
					}
					if(!db.Upd("INSERT INTO Users SET idUsers='"+id+"', Name='"+name+"', Surname='"+surname+"', User_name='"+name+"."+surname+".prof', User_password='"+password+"', Role='"+role+"';"))
						check=0;
				}
				
			}
			if(check==1){
				ServletContext sc = this.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/InputUsers.jsp?ret=User successfully added.");
				rd.forward(request, response);
			}
		}catch (Exception e){
			e.printStackTrace();
			check=0;
		}
		if(check==0){
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/InputUsers.jsp?ret=Error ocured. Input unsuccessful.");
			rd.forward(request, response);
		}
		
		
	}

}
