package project1;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeAccServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	   
	  
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try{
				String newusername = request.getParameter("newusername");
				String oldpassword = request.getParameter("oldpassword");
				String newpassword = request.getParameter("newpassword");
				String repeatpassword = request.getParameter("repeatpassword");
				String email = request.getParameter("email");
				String repeatemail = request.getParameter("repeatemail");
				HttpSession session = request.getSession(true);
				int check=1;
				MySQLcon db = new MySQLcon();
				ResultSet r;
				String ret="Error ocured.";
				try{
					r = db.Quer("SELECT User_password FROM Users WHERE idUsers='"+(String) session.getAttribute("userid")+"';");
					if(r.first()){
						if(!db.toMD5(oldpassword).equalsIgnoreCase(r.getString("User_password"))){
							check=0;
							ret="Incorrect old password.";
						}
					}else
						check=0;
					if(!newpassword.equalsIgnoreCase(repeatpassword)){
						ret="New passwords did not match";
						check=0;
					}
					r = db.Quer("SELECT User_name FROM Users WHERE User_name='"+newusername+"';");
					if(r.first()){
							check=0;
							ret="Username already exists.";
					}
					if(!email.equalsIgnoreCase(repeatemail)){
						check=0;
						ret="E-mails did not match.";
					}
					r = db.Quer("SELECT Email FROM Users WHERE Email='"+email+"' and Opt LIKE '2%';");
					if(r.first()){
							check=0;
							ret="The E-mail is already asigned to a user.";
					}
				}catch (Exception e){
					e.printStackTrace();
					check=0;
				}
				r = db.Quer("SELECT * FROM Users WHERE idUsers='"+ session.getAttribute("userid") +"';");
				r.first();
				
				
				
				System.out.println(check);
				if(check==1){
					char c[]={'1',(char) ('0'+(int)(Math.random()*9)),(char) ('0'+(int)(Math.random()*9)),(char) ('0'+(int)(Math.random()*9)),(char) ('0'+(int)(Math.random()*9)),(char) ('0'+(int)(Math.random()*9)),(char) ('0'+(int)(Math.random()*9)),(char) ('0'+(int)(Math.random()*9)),(char) ('0'+(int)(Math.random()*9)),(char) ('0'+(int)(Math.random()*9))};
					String opt=new String(c);
					String sub="SPtool registration confirmation";
					String cont="Go to folowing link to compleate registration:<br/><br/><a href='http://localhost:8080/Login/EmailCheckServlet?id="+(String) session.getAttribute("userid")+"&nm="+newusername+"&pw="+db.toMD5(newpassword)+"&check="+opt+"'>http://localhost:8080/Login/EmailCheckServlet?id="+(String) session.getAttribute("userid")+"&nm="+newusername+"&pw="+db.toMD5(newpassword)+"&check="+opt+"</a> ";
					if(db.Upd("UPDATE Users SET Email='"+email+"', Opt='"+opt+"' WHERE idUsers='"+ (String) session.getAttribute("userid") +"' ;")){
						SendEmail em=new SendEmail();
						if(em.send(email, sub, cont)){
							ServletContext sc = this.getServletContext();
							RequestDispatcher rd = sc.getRequestDispatcher("/EmailSent.html");
							rd.forward(request, response);
						}
					}
				}
				ServletContext sc = this.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/Register.jsp?ret="+ret);
				rd.forward(request, response);
				
			}catch (Exception e){
				e.printStackTrace();
			}
	}

}
