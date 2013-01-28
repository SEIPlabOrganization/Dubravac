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

				String[] projectResults;
				String query = "select distinct team.Name, project.Subject, project.acad_year, project.name, project.description from project, team ";
				
				if (request.getParameterValues("student") != null || request.getParameterValues("projectManager") != null)
				{
					query = query + ", responsibility, users, users_team ";
				}
				
				query= query +  "where project.Team_idTeam=team.idTeam and (";
				
				if (request.getParameterValues("student") != null || request.getParameterValues("projectManager") != null)
				{
					query = query + "responsibility.idResponsibility=users_team.Responsibility_idResponsibility and users.idusers=users_team.users_idusers and users_team.team_idteam=team.idteam) and (";
				}
				
				if (request.getParameterValues("projectName") != null)
				{
					projectResults = request.getParameterValues("projectName");
					for(int i=0; i<projectResults.length; i++)
				    {
				        query = query + "project.name='" + projectResults[i] + "' or ";
				    }
				}
				if (request.getParameterValues("teamName") != null)
				{
					projectResults = request.getParameterValues("teamName");
					for(int i=0; i<projectResults.length; i++)
				    {
				        query = query + "team.name='" + projectResults[i] + "' or ";
				    }
				}
				if (request.getParameterValues("academicYear") != null)
				{
					projectResults = request.getParameterValues("academicYear");
					for(int i=0; i<projectResults.length; i++)
				    {
				        query = query + "project.acad_year='" + projectResults[i] + "' or ";
				    }
				}
				if (request.getParameterValues("projectManager") != null)
				{
					projectResults = request.getParameterValues("projectManager");
					for(int i=0; i<projectResults.length; i++)
				    {
						String name=projectResults[i];
						String surname;
						byte index = (byte)name.indexOf(";");
						surname = name.substring(index+1);
						name = name.substring(0, index);
						
						query = query + "(users.name='" + name + "' and users.surname='" + surname + "' and responsibility.idResponsibility=1) or ";
				    }
				}
				if (request.getParameterValues("student") != null)
				{
					projectResults = request.getParameterValues("student");
					for(int i=0; i<projectResults.length; i++)
				    {
						String name=projectResults[i];
						String surname;
						byte index = (byte)name.indexOf(";");
						surname = name.substring(index+1);
						name = name.substring(0, index);
						
						query = query + "(users.name='" + name + "' and users.surname='" + surname + "') or ";
				    }
				}
				query = query.substring(0, query.length()-4);
				query = query + ") order by project.acad_year DESC;";
				plan = database.Quer(query);
			}
			
			//ako je resultset prazan
			if (!plan.next())
			{
				//ulogiran student
				//redirekcija na pocetnu stranicu
				//response.sendRedirect("/SPtool/pocetna stranica");
			}
			//ako resultset nije prazan
			else
			{
				byte count=0;
				plan.beforeFirst();
				while (plan.next()) count++;
				
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<link rel='stylesheet' href='style.css' type='text/css'/>");
				out.println("<title>Plan and control project</title>");
				
				if (count == 1)
				{
					plan.first();
					
					out.println("</head>");
					out.println("<body>");

					out.println("<form id='form'>");
						
					out.println("<fieldset>");
					out.println("<legend>Project details:</legend>");
					out.println("<ol>");
					out.println("<li>");
					out.println("<span>Subject:</span>");
					out.println("<i>" + plan.getString("project.Subject") + "</i><br/>");
					out.println("<hr/>");
					out.println("<span>Team name:</span>");
					out.println("<i>" + plan.getString("team.Name") + "</i><br/>");
					out.println("<hr/>");
					out.println("<span>Project name:</span>");
					out.println("<i>" + plan.getString("project.Name") + "</i><br/>");
					out.println("<hr/>");
					out.println("<span>Academic year:</span>");
					out.println("<i>" + plan.getString("project.acad_year") + "</i><br/>");
					out.println("<hr/>");
					out.println("<span>Description:</span>");
					out.println("<i>" + plan.getString("project.Description") + "</i>");
					out.println("</li>");
					out.println("</ol>");
					out.println("</fieldset>");
							
					ResultSet planDetails = database.Quer("select users.name, users.surname, responsibility.name from users, users_team, team, project, responsibility where users_team.Team_idTeam=team.idTeam and users_team.Users_idUsers=users.idUsers and users_team.responsibility_idResponsibility=responsibility.idresponsibility and team.idTeam=project.Team_idTeam and team.name='" + plan.getString("team.Name") + "' and project.name='" + plan.getString("project.Name") + "';");
					out.println("<fieldset>");
					out.println("<legend>Team members:</legend>");
					out.println("<ol>");
					out.println("<li>");
					
					while(planDetails.next())
					{
						out.println("<span>" + planDetails.getString("responsibility.name") + ":</span>");
						out.println("<i>" + planDetails.getString("users.name") + " " + planDetails.getString("users.surname") + "</i><br/>");
						if(planDetails.next()) out.println("<hr/>");
						planDetails.previous();
					}
					planDetails.close();

					out.println("</li>");
					out.println("</ol>");
					out.println("</fieldset>");
							
					out.println("<fieldset>");
					out.println("<legend>Project plan:</legend>");
					out.println("<ol>");
					out.println("<li>");
					
					//ako je ulogiran project manager:
					out.println("<a href='ProjectPlan.jsp'><i>Create new Project Plan</i></a>");
					//
					
					
					out.println("</li>");
					out.println("</ol>");
					out.println("</fieldset>");

					out.println("</form>");
					out.println("</body>");
					out.println("</html>");
				}
				//vise rezultata (advanced search)
				else
				{
					out.println("<link href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css' rel='stylesheet' type='text/css'/>");
					out.println("<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js'></script>");
					out.println("<script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js'></script>");
					
					out.println("<script type=\"text/javascript\">");
					out.println("$(document).ready(function() {");
					out.println("$(\"#tabs\").tabs();");
					out.println("});");
					out.println("</script>");
					
					out.println("</head>");
					
					out.println("<body style=\"font-size:62.5%;\">");
					
					out.println("<div id='tabs'>");

					out.println("<ul>");
					
					plan.beforeFirst();
					while(plan.next())
					{
						out.println("<li><a href='#" + plan.getString("project.name") + "'>" + plan.getString("project.name") + "</a></li>");
					}
					out.println("</ul>");
					
					plan.beforeFirst();
					while(plan.next())
					{
					    out.println("<div id='" + plan.getString("project.Name") + "'>");
					    
					    out.println("<form id='form'>");
						out.println("<fieldset>");
						out.println("<legend>Project details:</legend>");
						out.println("<ol>");
						out.println("<li>");
						out.println("<span>Subject:</span>");
						out.println("<i>" + plan.getString("project.Subject") + "</i><br/>");
						out.println("<hr/>");
						out.println("<span>Team name:</span>");
						out.println("<i>" + plan.getString("team.Name") + "</i><br/>");
						out.println("<hr/>");
						out.println("<span>Project name:</span>");
						out.println("<i>" + plan.getString("project.Name") + "</i><br/>");
						out.println("<hr/>");
						out.println("<span>Academic year:</span>");
						out.println("<i>" + plan.getString("project.acad_year") + "</i><br/>");
						out.println("<hr/>");
						out.println("<span>Description:</span>");
						out.println("<i>" + plan.getString("project.Description") + "</i>");
						out.println("</li>");
						out.println("</ol>");
						out.println("</fieldset>");
						
						ResultSet planDetails = database.Quer("select users.name, users.surname, responsibility.name from users, users_team, team, project, responsibility where users_team.Team_idTeam=team.idTeam and users_team.Users_idUsers=users.idUsers and users_team.responsibility_idResponsibility=responsibility.idresponsibility and team.idTeam=project.Team_idTeam and team.name='" + plan.getString("team.Name") + "' and project.name='" + plan.getString("project.Name") + "';");
						out.println("<fieldset>");
						out.println("<legend>Team members:</legend>");
						out.println("<ol>");
						out.println("<li>");
						
						while(planDetails.next())
						{
							out.println("<span>" + planDetails.getString("responsibility.name") + ":</span>");
							out.println("<i>" + planDetails.getString("users.name") + " " + planDetails.getString("users.surname") + "</i><br/>");
							if(planDetails.next()) out.println("<hr/>");
							planDetails.previous();
						}
						planDetails.close();

						out.println("</li>");
						out.println("</ol>");
						out.println("</fieldset>");
								
						out.println("<fieldset>");
						out.println("<legend>Project plan:</legend>");
						out.println("<ol>");
						out.println("<li>");
						
						out.println("</li>");
						out.println("</ol>");
						out.println("</fieldset>");
						
						out.println("</form>");
					    
					    out.println("</div>");
					}
					
					out.println("</div>");
					
					out.println("</body>");
					out.println("</html>");
				}
			}
			plan.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
