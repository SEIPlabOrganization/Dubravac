package project1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession(true);
			String userid = (String) session.getAttribute("userid");
			PrintWriter out = response.getWriter();
			MySQLcon db = new MySQLcon();
			ResultSet r = db.Quer("SELECT * FROM Users WHERE idUsers='"+ userid +"';");
			r.first();
			out.println("User info <br/><br/>");
			out.println("Name: "+r.getString("Name")+" "+r.getString("Surname")+"<br/><br/>");
			out.print("Status: ");
			if(r.getString("Role").equalsIgnoreCase("Stud")){
				out.println("Student<br/><br/>");
				if((String) session.getAttribute("teamid")!=null){
					r = db.Quer("SELECT * FROM Team WHERE idTeam='"+(String) session.getAttribute("teamid")+"';");
					r.first();
					out.println("Team id: "+r.getString("idTeam")+"<br/><br/>");
					out.println("Team name: "+r.getString("Name")+"<br/><br/>");
					
					r = db.Quer("SELECT * FROM Responsibility WHERE idResponsibility='"+(String) session.getAttribute("teamrole")+"';");
					r.first();
					out.println("Responsibility in team: "+r.getString("Name")+"<br/><br/>");
					out.println("<button onclick=\"window.location='TeamChoice.jsp';\">Change Team</button>");
				}
				else{
					out.println("<button onclick=\"window.location='TeamChoice.jsp';\">Select Team</button>");
				}
			}
			else if(r.getString("Role").equalsIgnoreCase("Prof"))
				out.println("Professor<br/><br/>");
			else if(r.getString("Role").equalsIgnoreCase("Assi"))
				out.println("Assistant<br/><br/>");
			
			if((String) session.getAttribute("teamid")!=null){
				
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
