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
			
			
			/*out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");*/
			//
			/*out.println("<link rel='stylesheet' href='ProjectsOverview.css' />");
			out.println("<link href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css' rel='stylesheet' type='text/css'/>");
			out.println("<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js'></script>");
			out.println("<script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js'></script>");
			out.println("</head>");*/
			/*out.println("<body style='font-size:62.5%;'>");*/
			
			
			
			out.println("<script type=\"text/javascript\">");
			out.println("$(document).ready(function() {");
			out.println("$(\"#tabs\").tabs();");
			out.println("});");
			/*
			out.println("function getMaxY() {");
			out.println("var max = 0;");
			out.println("for(var i = 0; i < data.values.length; i ++) {");
			out.println("if(data.values[i].Y > max) {");
			out.println("max = data.values[i].Y; } }");
                out.println("max += 10 - max % 10;");
                out.println("return max; }");
                
            out.println("function getXPixel(val) {");
                out.println("return ((graph.width - xPadding-15) / data.values.length) * val + (xPadding * 1.5); }");
                
            out.println("function getYPixel(val) {");
                out.println("return graph.height - (((graph.height - yPadding-22) / getMaxY()) * val) - yPadding; }");
			
			
			
			
			
			out.println("function drawGraph() {");
				out.println("xPadding = 30;");
				out.println("yPadding = 20;");
				out.println("data = { values:[");
					out.println("{ X: \"1\", Y: 6.25 },");
					out.println("{ X: \"2\", Y: 6.25*2 },");
					out.println("{ X: \"3\", Y: 6.25*3 },");
					out.println("{ X: \"4\", Y: 6.25*4 },");
					out.println("{ X: \"5\", Y: 6.25*5 },");
					out.println("{ X: \"6\", Y: 6.25*6 },");
					out.println("{ X: \"7\", Y: 6.25*7 },");
					out.println("{ X: \"8\", Y: 6.25*8 },");
					out.println("{ X: \"9\", Y: 6.25*9 },");
					out.println("{ X: \"10\", Y: 6.25*10 },");
					out.println("{ X: \"11\", Y: 6.25*11 },");
					out.println("{ X: \"12\", Y: 6.25*12 },");
					out.println("{ X: \"13\", Y: 6.25*13 },");
					out.println("{ X: \"14\", Y: 6.25*14 },");
					out.println("{ X: \"15\", Y: 6.25*15 },");
					out.println("{ X: \"16\", Y: 6.25*16 },");
				out.println("]};");
				
				out.println("graph = document.getElementById('graph10');");
				out.println("c = graph.getContext(\"2d\");  ");          
				out.println("c.lineWidth = 2;");
				out.println("c.strokeStyle = '#333';");
				out.println("c.font = 'italic 8pt sans-serif';");
				out.println("c.textAlign = \"center\";");
				
				out.println("c.beginPath();");
				out.println("c.moveTo(xPadding-5,12);");
				out.println("c.lineTo(xPadding, 0);");
				out.println("c.lineTo(xPadding+5, 12);");
				out.println("c.stroke();");
				out.println("c.beginPath();");
				out.println("c.moveTo(xPadding, 0);");
				out.println("c.lineTo(xPadding, graph.height - yPadding);");
				out.println("c.lineTo(graph.width, graph.height - yPadding);");
				out.println("c.lineTo(graph.width-12, graph.height - yPadding-5);");
				out.println("c.stroke();");
				out.println("c.beginPath();");
				out.println("c.moveTo(graph.width, graph.height - yPadding);");
				out.println("c.lineTo(graph.width-12, graph.height - yPadding+5);");
				out.println("c.stroke();");
				
				out.println("for(var i = 0; i < data.values.length; i ++) {");
					out.println("c.fillText(data.values[i].X, getXPixel(i), graph.height - yPadding + 20);");
				out.println("}");
				out.println("c.font = 'bold italic 12pt times-new-roman';");
				out.println("c.fillText(\"WEEK\", getXPixel(data.values.length-1), graph.height - yPadding - 10);");
				out.println("c.font = 'italic 8pt sans-serif';");
				
				out.println("c.textAlign = \"right\";");
				out.println("c.textBaseline = \"middle\";");
				
				out.println("for(var i = 0; i < getMaxY(); i += 10) {");
					out.println("c.fillText(i, xPadding - 10, getYPixel(i));");
				out.println("}");
				out.println("c.font = 'bold italic 13pt times-new-roman';");
				out.println("c.fillText(\"%\", xPadding -10, getYPixel(getMaxY())-10);");
				out.println("c.font = 'italic 8pt sans-serif';");
				
				out.println("c.strokeStyle = '#f00';");
				
				out.println("c.beginPath();");
				out.println("c.moveTo(getXPixel(0), getYPixel(data.values[0].Y));");
				out.println("for(var i = 1; i < data.values.length; i ++) {");
					out.println("c.lineTo(getXPixel(i), getYPixel(data.values[i].Y));");
				out.println("}");
				out.println("c.stroke();");
				
				out.println("c.fillStyle = '#333';");
				
				out.println("for(var i = 0; i < data.values.length; i ++) { ");
					out.println("c.beginPath();");
					out.println("c.arc(getXPixel(i), getYPixel(data.values[i].Y), 3, 0, Math.PI * 2, true);");
					out.println("c.fill();");
				out.println("}");
			out.println("}");
			*/
			
			
			
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
			
			ResultSet acad_years = database.Quer("select distinct Acad_year from Project order by Acad_year DESC");
			while(acad_years.next())
			{
				out.println("<li><a href=\"#" + acad_years.getString("Acad_year") + "\">" + acad_years.getString("Acad_year") + "</a></li>");
			}
			out.println("</ul>");
			
			acad_years.beforeFirst();

			while(acad_years.next())
			{
				out.println("<div id=\"" + acad_years.getString("Acad_year") + "\">");
				
				ResultSet projects = database.Quer("select * from Project, Team where (Project.Team_idTeam=Team.idTeam) and Project.Acad_year=\"" + acad_years.getString("Acad_year") + "\"");

				while(projects.next())
				{
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
					out.println("<iframe id=\"iframe" + projects.getString("idProject") + "\" src=\"canvas_graph.jsp\" frameborder=\"0\" width=\"472\" height=\"272\"></iframe>");
					out.println("</td>");
					out.println("</tr>");
					out.println("</table>");
					project_manager.close();
				}
				out.println("</div>");
				projects.close();
			}
			out.println("</div>");
			/*out.println("</body>");
			out.println("</html>");*/
			acad_years.close();
			
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	}

}