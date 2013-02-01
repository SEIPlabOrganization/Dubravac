package project1;

import java.util.Date;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class New_msgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String Date=dateFormat.format(date);
		dateFormat = new SimpleDateFormat("HH:mm:ss");
		date = new Date();
		String Time=dateFormat.format(date);
		
		HttpSession session = request.getSession(true);
		String Author=(String) session.getAttribute("userid");
		String Wall;
		String ret="error.";
		
		MySQLcon db= new MySQLcon();
		System.out.println((String) request.getParameter("subject"));
		String Subject = db.Sec((String) request.getParameter("subject"));
		String Content = db.Sec((String) request.getParameter("content"));
		
		
		ResultSet r=db.Quer("SELECT idUsers_team FROM Users_team WHERE Team_idTeam='"+(String) session.getAttribute("teamid")+"' AND Users_idUsers='"+Author+"';");
	
		try{
		if(r.first()) //Is result set not empty?
			Wall= r.getString("idUsers_team");
		else
			Wall= "error";
		}catch(Exception e){
			Wall= "error";
		}
		if(db.Upd("INSERT INTO Messages SET Users_idUsers='"+Author+"', Users_team_idUsers_team='"+Wall+"', Subject='"+Subject+"', Content='"+Content+"', Date='"+Date+"', Time='"+Time+"';")){
    		ret="Sucess.";
    		try{
	    		r=db.Quer("SELECT Name, Surname, Opt FROM Users WHERE idUsers='"+(String) session.getAttribute("userid")+"';");
	    		r.first();
	    		if(r.getString("Opt").charAt(1)=='0'){
		    		String name=r.getString("Name")+" "+r.getString("Surname");
		    		r=db.Quer("SELECT Name FROM Team WHERE idTeam="+(String) session.getAttribute("teamid")+";");
		    		r.first();
		    		String group=r.getString("Name");
		    		r=db.Quer("SELECT Users_idUsers FROM Users_team WHERE Team_idTeam="+(String) session.getAttribute("teamid")+" AND Users_idUsers!='"+Author+"';");
		    		
	    			SendEmail em=new SendEmail();
	    			String sub=name+" made a new post in the "+group+" team.";
	    			String cont="From: "+name+"<br/>Team: "+group+"<br/><br/>"+"Subject: "+Subject+"<br/><br/>"+Content;
		    		while(r.next()){
		    			ResultSet r1=db.Quer("SELECT Email FROM Users WHERE idUsers='"+r.getString("Users_idUsers")+"';");
		    			r1.first();
		    			if(r1.getString("Email")!=null){
							em.send(r1.getString("Email"), sub, cont);
						}
		    		}
	    		}
    		}catch(Exception e){
    		}
    	}
		
    	ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/New_msg.jsp?ret="+ret);
		rd.forward(request, response);
	}

}
