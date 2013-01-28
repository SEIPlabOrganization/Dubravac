package SPtool;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			MySQLcon database = new MySQLcon("jdbc:mysql://localhost:3306/mydb", "root", "root");
			
			//zamijeniti team.idteam=10 sa id-om trenutno ulogiranog tima
			ResultSet name = database.Quer("select users.name, users.surname from users, users_team, responsibility, team where users.idusers=users_team.users_idusers and responsibility.idresponsibility=users_team.responsibility_idresponsibility and users_team.team_idteam=team.idteam and responsibility.idresponsibility!=1 and team.idteam=10");

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
