/*
 * 
 * Servlet koji sluzi za ispis cijele stranice Projects Overview. Ispisuje tabove za akademske godine te unutar svake akademske godine ispisuje sve pripadne projekte te njihove informacije kao i grafove napredaka projekata
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

public class ProjectsOverviewServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    public ProjectsOverviewServlet()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			PrintWriter out = response.getWriter();
			MySQLcon database = new MySQLcon();
			
			// inicijalizacija jquery tabova
			out.println("<script type=\"text/javascript\">");
			out.println("$(document).ready(function() {");
			out.println("$(\"#tabs\").tabs();");
			out.println("});");
			
			// javascript funkcija expand/collapse za otvaranje odredenih elemenata stranice
			out.println("function toggleMe(a) {");
			out.println("var button = document.getElementById('button' + a).innerHTML;");
			out.println("var content = document.getElementById('content' + a);");
			
			out.println("if(content.style.display==\"none\")");
			out.println("{");
			out.println("button=button.replace(\"+\", \"-\");");
			out.println("document.getElementById(\"button\" + a).innerHTML = button;");
			out.println("content.style.display=\"block\";");
			out.println("}");
			out.println("else {");
			out.println("button=button.replace(\"-\", \"+\");");
			out.println("document.getElementById(\"button\" + a).innerHTML = button;");
			out.println("content.style.display=\"none\";");
			out.println("}");
			  
			out.println("return true;");
			out.println("}");
			
			out.println("</script>");
			
			out.println("<div id='tabs'>");
			out.println("<ul>");
			
			// uzimanje iz baze svih akademskih godina koji ce predstavljat tabove
			ResultSet acad_years = database.Quer("select distinct Acad_year from Project order by Acad_year DESC");
			
			if (acad_years.next())
			{
				acad_years.beforeFirst();
				while(acad_years.next())
				{
					// ispis tabova
					out.println("<li><a href=\"#" + acad_years.getString("Acad_year") + "\">" + acad_years.getString("Acad_year") + "</a></li>");
				}
				out.println("</ul>");
				
				acad_years.beforeFirst();

				// za svaku akademsku godinu ispisi projekte
				while(acad_years.next())
				{
					out.println("<div id=\"" + acad_years.getString("Acad_year") + "\">");
					
					ResultSet projects = database.Quer("select * from Project, Team where (Project.Team_idTeam=Team.idTeam) and Project.Acad_year=\"" + acad_years.getString("Acad_year") + "\"");

					while(projects.next())
					{
						// za svaki projekt ispisi informacije o projektu i team leaderu
						ResultSet project_manager = database.Quer("select Users.Name, Users.Surname from Users, Users_team, Team, Project, Responsibility where Users_team.Team_idTeam = Team.idTeam and Users_team.Users_idUsers = Users.idUsers and Users_team.Responsibility_idResponsibility=Responsibility.idResponsibility and Project.Team_idTeam=Team.idTeam and Responsibility.idResponsibility=1 and Project.Name=\"" + projects.getString("Project.Name") + "\" and Team.Name=\"" + projects.getString("Team.Name") + "\"");
						out.println("<table border=\"0\">");
						out.println("<tr id=\"buttons\">");
						out.println("<td onclick=\"return toggleMe(" + projects.getString("idProject") + ")\"> <big id=\"button" + projects.getString("idProject") + "\"> <b>+ " + projects.getString("project.Name") + "</b> </big> </td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td>Project no: " + projects.getString("idProject") + " | Team name: " + projects.getString("team.Name"));
						if (project_manager.next()) out.println(" | Project Manager: " + project_manager.getString("Name") + " " + project_manager.getString("Surname"));
						out.println("</td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td id=\"content" + projects.getString("idProject") + "\" style=\"display:none\">");
						//out.println("<canvas id=\"graph" + projects.getString("idProject") + "\" width=\"450\" height=\"250\">");
				        //out.println("</canvas>");
						//out.println("<script type=\"text/javascript\">");
						
				        //out.println("drawGraph();");
				        
						//out.println("</script>");
						
						// iscrtavanje grafova, gore navedena ideja kako dinamiski koristit grafove
						// za sada za svaki tim prikazuje isti graf
						out.println("<iframe id=\"iframe" + projects.getString("idProject") + "\" src=\"canvas_graph.jsp\" frameborder=\"0\" width=\"472\" height=\"272\"></iframe>");
						out.println("</td>");
						out.println("</tr>");
						out.println("</table>");
						project_manager.close();
					}
					out.println("</div>");
					projects.close();
				}
			}
			else
			{
				out.println("Project database is empty!<br/><br/><hr/><hr/><br/>");
				out.println("<a href='javascript:javascript:history.go(-1)'>Back</a><br/>");
			}
			
			out.println("</div>");
			acad_years.close();
			
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	}

}