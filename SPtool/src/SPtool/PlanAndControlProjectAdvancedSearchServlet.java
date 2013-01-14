package SPtool;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlanAndControlProjectAdvancedSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PlanAndControlProjectAdvancedSearchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
		PrintWriter out = response.getWriter();
		MySQLcon database = new MySQLcon("jdbc:mysql://localhost:3306/mydb", "root", "root");
		ResultSet projects;
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
			out.print("<head>");
				out.print("<link rel='stylesheet' href='PlanAndControlProjectAdvancedSearch.css' type='text/css'>");
				out.print("<title>Plan and control project</title>");
			out.print("</head>");
			out.print("<body>");
				out.print("<form id='advancedSearch' method='get' action='PlanAndControlProjectServlet'>");
					out.print("<fieldset>");
						out.print("<legend>Search by:</legend>");
						out.print("<ol>");
							out.print("<li>");
								out.print("<label>Project name</label>");
								out.print("<select name='projectName' multiple>");
								
								projects = database.Quer("select project.name, project.acad_year from project, team where (project.Team_idTeam=team.idTeam) order by project.acad_year DESC, project.name ASC;");
								while(projects.next())
								{
									out.print("<option value='" + projects.getString("Project.Name") + "'>" + projects.getString("Project.Name") + "</option>");
								}
								projects.close();
								
								out.print("</select>");
							out.print("</li>");
						out.print("</ol>");
						out.print("<ol>");
							out.print("<li>");
								out.print("<label>Team name</label>");
								out.print("<select name='teamName' multiple>");
								
								projects = database.Quer("select Team.Name, Project.acad_year from Project, Team where (Project.Team_idTeam=Team.idTeam) order by Project.Acad_year DESC, Team.Name ASC;");
								while(projects.next())
								{
									out.print("<option value='" + projects.getString("Team.Name") + "'>" + projects.getString("Team.Name") + "</option>");
								}
								projects.close();
								
								out.print("</select>");
							out.print("</li>");
						out.print("</ol>");
						out.print("<ol>");
							out.print("<li>");
								out.print("<label>Academic year</label>");
								out.print("<select name='academicYear' multiple>");
								
								projects = database.Quer("select distinct acad_year from project order by acad_year DESC");
								while(projects.next())
								{
									out.print("<option value='" + projects.getString("project.acad_year") + "'>" + projects.getString("project.acad_year") + "</option>");
								}
								projects.close();
								
								out.print("</select>");
							out.print("</li>");
						out.print("</ol>");
						out.print("<ol>");
							out.print("<li>");
								out.print("<label>Team leader</label>");
								out.print("<select name='teamLeader' multiple>");
								
								projects = database.Quer("select distinct users.name, users.surname from users, users_team, responsibility where (users.idusers=users_team.users_idusers) and (responsibility.idresponsibility=users_team.responsibility_idresponsibility) and responsibility.name='team leader' order by users.surname ASC;");
								while(projects.next())
								{
									out.print("<option value='" + projects.getString("users.name") + ";" + projects.getString("users.surname") + "'>" + projects.getString("users.name") + " " + projects.getString("users.surname") + "</option>");
								}
								projects.close();

								out.print("</select>");
							out.print("</li>");
						out.print("</ol>");
						out.print("<ol>");
							out.print("<li>");
								out.print("<label>Student</label>");
								out.print("<select name='student' multiple>");

								projects = database.Quer("select distinct users.name, users.surname from users, users_team, responsibility where (users.idusers=users_team.users_idusers) and (responsibility.idresponsibility=users_team.responsibility_idresponsibility) order by users.surname ASC;");
								while(projects.next())
								{
									out.print("<option value='" + projects.getString("users.name") + ";" + projects.getString("users.surname") + "'>" + projects.getString("users.name") + " " + projects.getString("users.surname") + "</option>");
								}
								projects.close();

								out.print("</select>");
							out.print("</li>");
						out.print("</ol>");
					out.print("</fieldset>");
					
					out.print("<fieldset>");
					out.print("<span>");
						out.print("<input type='reset' value='Reset'/>");
						out.print("<input type='submit' value='Submit'/>");
						out.print("</span>");
					out.print("</fieldset>");
				out.print("</form>");
			out.print("</body>");
		out.print("</html>");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
