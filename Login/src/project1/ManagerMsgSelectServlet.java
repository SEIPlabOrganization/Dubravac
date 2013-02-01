package project1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ManagerMsgSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession(true);
			PrintWriter out = response.getWriter();
			MySQLcon db= new MySQLcon();
			ResultSet r1;
			ResultSet r=db.Quer("SELECT Users_idUsers, idUsers_team FROM Users_team WHERE Team_idTeam='"+(String) session.getAttribute("teamid")+"';");
			out.println("<select name='to'>");
			out.println("<option>-Select-</option>");
			while(r.next()){
				if(!((String) session.getAttribute("userid")).equalsIgnoreCase(r.getString("Users_idUsers"))){
					r1=db.Quer("SELECT Name, Surname FROM Users WHERE idUsers='"+r.getString("Users_idUsers")+"';");
					r1.first();
					out.println("<option value='"+r.getString("idUsers_team")+"'>"+r1.getString("Name")+" "+r1.getString("Surname")+"</option>");
				}
			}
			out.println("</select>");
		} catch (Exception e) {
	        e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
