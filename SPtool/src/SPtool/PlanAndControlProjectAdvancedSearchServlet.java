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
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
			out.println("<head>");
				out.println("<link rel='stylesheet' href='style.css' type='text/css'>");
				out.println("<title>Plan and control project</title>");
			out.println("</head>");
			out.println("<body>");
				out.println("<form id='form' method='get' action='PlanAndControlProjectServlet'>");
					out.println("<fieldset>");
						out.println("<legend>Search by:</legend>");
						out.println("<ol>");
							out.println("<li>");
								out.println("<label>Project name</label>");
								out.println("<select name='projectName' multiple>");
								
								projects = database.Quer("select project.name, project.acad_year from project, team where (project.Team_idTeam=team.idTeam) order by project.acad_year DESC, project.name ASC;");
								while(projects.next())
								{
									out.println("<option value='" + projects.getString("Project.Name") + "'>" + projects.getString("Project.Name") + "</option>");
								}
								projects.close();
								
								out.println("</select>");
							out.println("</li>");
						out.println("</ol>");
						out.println("<ol>");
							out.println("<li>");
								out.println("<label>Team name</label>");
								out.println("<select name='teamName' multiple>");
								
								projects = database.Quer("select Team.Name, Project.acad_year from Project, Team where (Project.Team_idTeam=Team.idTeam) order by Project.Acad_year DESC, Team.Name ASC;");
								while(projects.next())
								{
									out.println("<option value='" + projects.getString("Team.Name") + "'>" + projects.getString("Team.Name") + "</option>");
								}
								projects.close();
								
								out.println("</select>");
							out.println("</li>");
						out.println("</ol>");
						out.println("<ol>");
							out.println("<li>");
								out.println("<label>Academic year</label>");
								out.println("<select name='academicYear' multiple>");
								
								projects = database.Quer("select distinct acad_year from project order by acad_year DESC");
								while(projects.next())
								{
									out.println("<option value='" + projects.getString("project.acad_year") + "'>" + projects.getString("project.acad_year") + "</option>");
								}
								projects.close();
								
								out.println("</select>");
							out.println("</li>");
						out.println("</ol>");
						out.println("<ol>");
							out.println("<li>");
								out.println("<label>Project Manager</label>");
								out.println("<select name='projectManager' multiple>");
								
								projects = database.Quer("select distinct users.name, users.surname from users, users_team, responsibility where (users.idusers=users_team.users_idusers) and (responsibility.idresponsibility=users_team.responsibility_idresponsibility) and responsibility.idResponsibility=1 order by users.surname ASC;");
								while(projects.next())
								{
									out.println("<option value='" + projects.getString("users.name") + ";" + projects.getString("users.surname") + "'>" + projects.getString("users.name") + " " + projects.getString("users.surname") + "</option>");
								}
								projects.close();

								out.println("</select>");
							out.println("</li>");
						out.println("</ol>");
						out.println("<ol>");
							out.println("<li>");
								out.println("<label>Student</label>");
								out.println("<select name='student' multiple>");

								projects = database.Quer("select distinct users.name, users.surname from users, users_team, responsibility where (users.idusers=users_team.users_idusers) and (responsibility.idresponsibility=users_team.responsibility_idresponsibility) order by users.surname ASC;");
								while(projects.next())
								{
									out.println("<option value='" + projects.getString("users.name") + ";" + projects.getString("users.surname") + "'>" + projects.getString("users.name") + " " + projects.getString("users.surname") + "</option>");
								}
								projects.close();

								out.println("</select>");
							out.println("</li>");
						out.println("</ol>");
					out.println("</fieldset>");
					
					out.println("<fieldset>");
					//out.println("<span>");
						out.println("<input type='reset' value='Reset'/>");
						//out.println("</span>");
						out.println("<input type='submit' value='Submit'/>");
					out.println("</fieldset>");
				out.println("</form>");
				
				out.println("<script type='text/javascript'>");
				out.println("var form = document.getElementById('form');");
				out.println("form.onsubmit = function(event) {");
				
				out.println("if (form['projectName'].value == '' & form['teamName'].value == '' & form['academicYear'].value == '' & form['projectManager'].value == '' & form['student'].value == '')");
				out.println("{");
				out.println("event.preventDefault();");
				out.println("alert('At least one field must be selected')");
				out.println("}");
				
				out.println("};");
		        out.println("</script>");
		        
			out.println("</body>");
		out.println("</html>");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}