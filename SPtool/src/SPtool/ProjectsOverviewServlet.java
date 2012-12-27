package SPtool;

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
			MySQLcon database = new MySQLcon("jdbc:mysql://localhost:3306/sptool", "root", "root");
			ResultSet acad_years = database.Quer("select distinct Acad_year from Project order by Acad_year DESC");
			
			
			out.print("<!DOCTYPE html>");
			out.print("<html>");
			
			out.print("<body style=\"font-size:62.5%;\">");
			
			out.print("<link href=\"http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css\" rel=\"stylesheet\" type=\"text/css\"/>");
			out.print("<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js\"></script>");
			out.print("<script src=\"http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js\"></script>");

			out.print("<script type=\"text/javascript\">");
			out.print("$(document).ready(function() {");
			out.print("$(\"#tabs\").tabs();");
			out.print("});");
			
			out.print("function toggleMe(a) {");
			out.print("var button = document.getElementById(\"button\" + a).innerHTML;");//vrijf
			out.print("var content = document.getElementById(\"content\" + a);");
			
			out.print("if(content.style.display==\"none\")");
			out.print("{");
			out.print("button=button.replace(\"+\", \"-\");");
			out.print("document.getElementById(\"button\" + a).innerHTML = button;");
			out.print("content.style.display=\"block\";");
			out.print("}");
			out.print("else {");
			out.print("button=button.replace(\"-\", \"+\");");
			out.print("document.getElementById(\"button\" + a).innerHTML = button;");
			out.print("content.style.display=\"none\";");
			out.print("}");
			  
			out.print("return true;");
			out.print("}");
			
			out.print("</script>");
			
			out.print("<div id=\"tabs\">");
			out.print("<ul>");
			
			while(acad_years.next())
			{
				out.print("<li><a href=\"#" + acad_years.getString("Acad_year") + "\">" + acad_years.getString("Acad_year") + "</a></li>");
			}
			out.print("</ul>");
			
			acad_years.first();
			acad_years.previous();
			while(acad_years.next())
			{
				out.print("<div id=\"" + acad_years.getString("Acad_year") + "\">");
				
				ResultSet projects = database.Quer("select * from Project, Team where (Project.Team_idTeam=Team.idTeam) and Project.Acad_year=\"" + acad_years.getString("Acad_year") + "\"");
				
				while(projects.next())
				{
					out.print("<table border=\"0\">");
					out.print("<tr>");
					out.print("<td onclick=\"return toggleMe(" + projects.getString("idProject") + ")\"> <big id=\"button" + projects.getString("idProject") + "\"> <b>+ " + projects.getString("projectName") + "</b> </big> </td>");
					out.print("</tr>");
					out.print("<tr>");
					out.print("<td>Project no: " + projects.getString("idProject") + " | Team name: " + projects.getString("TeamName") + "</td>");
					out.print("</tr>");
					out.print("<tr rowspan=\"20\" width=\"100\">");
					out.print("<td id=\"content" + projects.getString("idProject") + "\" style=\"display:none\">");
					out.print("<iframe name=\"iframe" + projects.getString("idProject") + "\" src=\"canvas-graph.html\" frameborder=\"0\" width=\"310\" height=\"250\"></iframe>");
					out.print("</td>");
					out.print("</tr>");
					out.print("</table>");
				}
				out.print("</div>");
			}
			
			out.print("</div>");
			out.print("</body>");
			out.print("</html>");
			
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	}

}