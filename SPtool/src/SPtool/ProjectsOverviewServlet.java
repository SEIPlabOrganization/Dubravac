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
			MySQLcon database = new MySQLcon("jdbc:mysql://localhost:3306/mydb", "root", "root");
			ResultSet acad_years = database.Quer("select distinct Acad_year from Project order by Acad_year DESC");
			
			
			out.print("<!DOCTYPE html>");
			out.print("<html>");
			out.print("<head>");
			//
			out.print("<link rel=\"stylesheet\" href=\"ProjectsOverview.css\" />");
			out.print("<link href=\"http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css\" rel=\"stylesheet\" type=\"text/css\"/>");
			out.print("<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js\"></script>");
			out.print("<script src=\"http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js\"></script>");
			out.print("</head>");
			out.print("<body style=\"font-size:62.5%;\">");
			
			
			/*
			out.print("<script type=\"text/javascript\">");
			out.print("$(document).ready(function() {");
			out.print("$(\"#tabs\").tabs();");
			out.print("});");
			
			out.print("function getMaxY() {");
			out.print("var max = 0;");
			out.print("for(var i = 0; i < data.values.length; i ++) {");
			out.print("if(data.values[i].Y > max) {");
			out.print("max = data.values[i].Y; } }");
                out.print("max += 10 - max % 10;");
                out.print("return max; }");
                
            out.print("function getXPixel(val) {");
                out.print("return ((graph.width - xPadding-15) / data.values.length) * val + (xPadding * 1.5); }");
                
            out.print("function getYPixel(val) {");
                out.print("return graph.height - (((graph.height - yPadding-22) / getMaxY()) * val) - yPadding; }");
			
			
			
			
			
			out.print("function drawGraph() {");
				out.print("xPadding = 30;");
				out.print("yPadding = 20;");
				out.print("data = { values:[");
					out.print("{ X: \"1\", Y: 6.25 },");
					out.print("{ X: \"2\", Y: 6.25*2 },");
					out.print("{ X: \"3\", Y: 6.25*3 },");
					out.print("{ X: \"4\", Y: 6.25*4 },");
					out.print("{ X: \"5\", Y: 6.25*5 },");
					out.print("{ X: \"6\", Y: 6.25*6 },");
					out.print("{ X: \"7\", Y: 6.25*7 },");
					out.print("{ X: \"8\", Y: 6.25*8 },");
					out.print("{ X: \"9\", Y: 6.25*9 },");
					out.print("{ X: \"10\", Y: 6.25*10 },");
					out.print("{ X: \"11\", Y: 6.25*11 },");
					out.print("{ X: \"12\", Y: 6.25*12 },");
					out.print("{ X: \"13\", Y: 6.25*13 },");
					out.print("{ X: \"14\", Y: 6.25*14 },");
					out.print("{ X: \"15\", Y: 6.25*15 },");
					out.print("{ X: \"16\", Y: 6.25*16 },");
				out.print("]};");
				
				out.print("graph = document.getElementById('graph10');");
				out.print("c = graph.getContext(\"2d\");  ");          
				out.print("c.lineWidth = 2;");
				out.print("c.strokeStyle = '#333';");
				out.print("c.font = 'italic 8pt sans-serif';");
				out.print("c.textAlign = \"center\";");
				
				out.print("c.beginPath();");
				out.print("c.moveTo(xPadding-5,12);");
				out.print("c.lineTo(xPadding, 0);");
				out.print("c.lineTo(xPadding+5, 12);");
				out.print("c.stroke();");
				out.print("c.beginPath();");
				out.print("c.moveTo(xPadding, 0);");
				out.print("c.lineTo(xPadding, graph.height - yPadding);");
				out.print("c.lineTo(graph.width, graph.height - yPadding);");
				out.print("c.lineTo(graph.width-12, graph.height - yPadding-5);");
				out.print("c.stroke();");
				out.print("c.beginPath();");
				out.print("c.moveTo(graph.width, graph.height - yPadding);");
				out.print("c.lineTo(graph.width-12, graph.height - yPadding+5);");
				out.print("c.stroke();");
				
				out.print("for(var i = 0; i < data.values.length; i ++) {");
					out.print("c.fillText(data.values[i].X, getXPixel(i), graph.height - yPadding + 20);");
				out.print("}");
				out.print("c.font = 'bold italic 12pt times-new-roman';");
				out.print("c.fillText(\"WEEK\", getXPixel(data.values.length-1), graph.height - yPadding - 10);");
				out.print("c.font = 'italic 8pt sans-serif';");
				
				out.print("c.textAlign = \"right\";");
				out.print("c.textBaseline = \"middle\";");
				
				out.print("for(var i = 0; i < getMaxY(); i += 10) {");
					out.print("c.fillText(i, xPadding - 10, getYPixel(i));");
				out.print("}");
				out.print("c.font = 'bold italic 13pt times-new-roman';");
				out.print("c.fillText(\"%\", xPadding -10, getYPixel(getMaxY())-10);");
				out.print("c.font = 'italic 8pt sans-serif';");
				
				out.print("c.strokeStyle = '#f00';");
				
				out.print("c.beginPath();");
				out.print("c.moveTo(getXPixel(0), getYPixel(data.values[0].Y));");
				out.print("for(var i = 1; i < data.values.length; i ++) {");
					out.print("c.lineTo(getXPixel(i), getYPixel(data.values[i].Y));");
				out.print("}");
				out.print("c.stroke();");
				
				out.print("c.fillStyle = '#333';");
				
				out.print("for(var i = 0; i < data.values.length; i ++) { ");
					out.print("c.beginPath();");
					out.print("c.arc(getXPixel(i), getYPixel(data.values[i].Y), 3, 0, Math.PI * 2, true);");
					out.print("c.fill();");
				out.print("}");
			out.print("}");
			*/
			
			
			
			out.print("function toggleMe(a) {");
			out.print("var button = document.getElementById(\"button\" + a).innerHTML;");
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
			
			acad_years.beforeFirst();

			while(acad_years.next())
			{
				out.print("<div id=\"" + acad_years.getString("Acad_year") + "\">");
				
				ResultSet projects = database.Quer("select * from Project, Team where (Project.Team_idTeam=Team.idTeam) and Project.Acad_year=\"" + acad_years.getString("Acad_year") + "\"");

				while(projects.next())
				{
					ResultSet team_leader = database.Quer("select Users.Name, Users.Surname from Users, Users_team, Team, Project, Responsibility where Users_team.Team_idTeam = Team.idTeam and Users_team.Users_idUsers = Users.idUsers and Users_team.Responsibility_idResponsibility=Responsibility.idResponsibility and Project.Team_idTeam=Team.idTeam and Responsibility.Name='Team Leader' and Project.Name=\"" + projects.getString("Project.Name") + "\" and Team.Name=\"" + projects.getString("Team.Name") + "\"");
					out.print("<table border=\"0\">");
					out.print("<tr id=\"buttons\">");
					out.print("<td onclick=\"return toggleMe(" + projects.getString("idProject") + ")\"> <big id=\"button" + projects.getString("idProject") + "\"> <b>+ " + projects.getString("project.Name") + "</b> </big> </td>");
					out.print("</tr>");
					out.print("<tr>");
					out.print("<td>Project no: " + projects.getString("idProject") + " | Team name: " + projects.getString("team.Name"));
					if (team_leader.next()) out.print(" | Team Leader: " + team_leader.getString("Name") + " " + team_leader.getString("Surname"));
					out.print("</td>");
					out.print("</tr>");
					out.print("<tr>");
					out.print("<td id=\"content" + projects.getString("idProject") + "\" style=\"display:none\">");
					//out.print("<canvas id=\"graph" + projects.getString("idProject") + "\" width=\"450\" height=\"250\">");
			        //out.print("</canvas>");
					//out.print("<script type=\"text/javascript\">");
					
			        //out.print("drawGraph();");
			        
					//out.print("</script>");
					out.print("<iframe id=\"iframe" + projects.getString("idProject") + "\" src=\"canvas_graph.jsp\" frameborder=\"0\" width=\"472\" height=\"272\"></iframe>");
					out.print("</td>");
					out.print("</tr>");
					out.print("</table>");
					team_leader.close();
				}
				out.print("</div>");
				projects.close();
			}
			out.print("</div>");
			out.print("</body>");
			out.print("</html>");
			acad_years.close();
			
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	}

}