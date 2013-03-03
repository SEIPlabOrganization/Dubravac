/*
 * 
 * Pomocni servlet koji sluzi za iscrtavanje padajuceg izbornika "<select>" za imena i prezimena studenata u bazi koji je izvan .jsp fajla jer povlaci informacije iz baze
 * 
 * */

package project1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WeeklyAssignmentsSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WeeklyAssignmentsSelectServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try 
		{
			PrintWriter out = response.getWriter();
			MySQLcon database = new MySQLcon();
			
			HttpSession session = request.getSession(true);
			String teamid = (String) session.getAttribute("teamid");
			
			// povlacenje informacija o imenu i prezimenu svih studenata
			ResultSet name = database.Quer("select users.name, users.surname from users, users_team, responsibility, team where users.idusers=users_team.users_idusers and responsibility.idresponsibility=users_team.responsibility_idresponsibility and users_team.team_idteam=team.idteam and responsibility.idresponsibility!=1 and team.idteam="+teamid+"");

			out.println("<select name='nameSurname'>");
				
			while(name.next())
			{
				out.println("<option value='" + name.getString("users.name") + ";" + name.getString("users.surname") + "'>" + name.getString("users.name") + " " + name.getString("users.surname") + "</option>");
			}
				
			out.println("</select>");
	
			name.close();
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
