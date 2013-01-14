package SPtool;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlanAndControlProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public PlanAndControlProjectServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			PrintWriter out = response.getWriter();
			MySQLcon database = new MySQLcon("jdbc:mysql://localhost:3306/mydb", "root", "root");
			ResultSet plan;
			//provjeri je li ulogiran student ili profesor i spremi zaduzenje i 
			//ako je ulogiran student cini slijedece:
			
			/*
			 ...
			 */
			
			
			//inace (ako je ulogiran profesor):
			if (request.getParameter("projectBasicSearch") != null)
			{ 
			    // basic search
				String project=request.getParameter("projectBasicSearch");
				String team;
				byte index = (byte)project.indexOf(";");
				team = project.substring(index+1);
				project = project.substring(0, index);
				
				plan = database.Quer("select team.Name, project.Subject, project.acad_year, project.name, project.description from project,team where project.Team_idTeam=team.idTeam and project.name='"+project+"' and team.name='"+team+"';");
			}
			else
			{
				// advanced search
				plan = database.Quer("select team.Name, project.Subject, project.acad_year, project.name, project.description from project,team where project.Team_idTeam=team.idTeam and project.name='fdasfsdaf';");
			}
			
			//ako je resultset prazan
			if (!plan.next())
			{
				//ako je ulogiran profesor/asistent redirektat na advancedSearch i prikazat poruku da nema rezultata za trazeni upit
				
				//else (ako je ulogiran student)
				out.print("<script>");
				out.print("alert(\"Project doesn't exist in database yet!\");");
				out.print("</script>");
				//redirekcija na pocetnu stranicu
			}
			//ako resultset nije prazan
			else
			{
				byte count=0;
				plan.beforeFirst();
				while (plan.next()) count++;
				
				if (count == 1)
				{
					plan.first();
					
					out.print("<!DOCTYPE html>");
					out.print("<html>");
					out.print("<head>");
					out.print("<link rel='stylesheet' href='PlanAndControlProject.css' type='text/css'/>");
					out.print("<title>Plan and control project</title>");
					out.print("</head>");
					out.print("<body>");

					out.print("<form id='Show'>");
						
					out.print("<fieldset>");
					out.print("<legend>Project details:</legend>");
					out.print("<ol>");
					out.print("<li>");
					out.print("<span>Subject:</span>");
					out.print("<i>" + plan.getString("project.Subject") + "</i><br/>");
					out.print("<hr/>");
					out.print("<span>Team name:</span>");
					out.print("<i>" + plan.getString("team.Name") + "</i><br/>");
					out.print("<hr/>");
					out.print("<span>Project name:</span>");
					out.print("<i>" + plan.getString("project.Name") + "</i><br/>");
					out.print("<hr/>");
					out.print("<span>Academic year:</span>");
					out.print("<i>" + plan.getString("project.acad_year") + "</i><br/>");
					out.print("<hr/>");
					out.print("<span>Description:</span>");
					out.print("<i>" + plan.getString("project.Description") + "</i>");
					out.print("</li>");
					out.print("</ol>");
					out.print("</fieldset>");
							
					plan = database.Quer("select users.name, users.surname, responsibility.name from users, users_team, team, project, responsibility where users_team.Team_idTeam=team.idTeam and users_team.Users_idUsers=users.idUsers and users_team.responsibility_idResponsibility=responsibility.idresponsibility and team.idTeam=project.Team_idTeam and team.name='" + plan.getString("team.Name") + "' and project.name='" + plan.getString("project.Name") + "';");
					out.print("<fieldset>");
					out.print("<legend>Team members:</legend>");
					out.print("<ol>");
					out.print("<li>");
					
					while(plan.next())
					{
						out.print("<span>" + plan.getString("responsibility.name") + ":</span>");
						out.print("<i>" + plan.getString("users.name") + " " + plan.getString("users.surname") + "</i><br/>");
						if(plan.next()) out.print("<hr/>");
						plan.previous();
					}

					out.print("</li>");
					out.print("</ol>");
					out.print("</fieldset>");
							
							
					out.print("<fieldset>");
					out.print("<legend>Project plan:</legend>");
					out.print("<ol>");
					out.print("<li>");
					out.print("</li>");
					out.print("</ol>");
					out.print("</fieldset>");

							
					out.print("</form>");
					out.print("</body>");
					out.print("</html>");
				}
				//vise rezultata (advanced search)
				else
				{
					//prikazi rezultate u petlji
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
