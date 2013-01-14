package SPtool;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlanAndControlProjectBasicSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PlanAndControlProjectBasicSearchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			PrintWriter out = response.getWriter();
			
			out.print("<!DOCTYPE html>");
			out.print("<html><head>");
			out.print("<link rel=\"stylesheet\" href=\"PlanAndControlProjectBasicSearch.css\" type=\"text/css\">");
			out.print("<title>Plan and control project</title>");				
			out.print("</head>");
			out.print("<body>");
			
			out.print("<form id=\"basicSearch\" method=\"get\" action=\"PlanAndControlProjectServlet\">");
			
			out.print("<fieldset>");
			out.print("<legend>Plan and control project</legend>");
			out.print("<ol>");
					
			out.print("<li>");
			out.print("<label for=\"projectBasicSearch\">Choose project</label>");
			
			out.print("<select id='projectBasicSearch' name='projectBasicSearch'>");
			
			MySQLcon database = new MySQLcon("jdbc:mysql://localhost:3306/mydb", "root", "root");
			ResultSet projects = database.Quer("select Project.Name, Team.Name, Project.acad_year from Project, Team where (Project.Team_idTeam=Team.idTeam) order by Project.Acad_year DESC, Project.Name ASC"/* and acad_year=(select max(acad_year) from project)*/);

			while(projects.next())
			{
				out.print("<option value=\"" + projects.getString("Project.Name") + ";" + projects.getString("Team.Name") + "\">" + projects.getString("Project.Name") + " - " + projects.getString("Team.Name") +"</option>");
			}
			projects.close();
			out.print("</select>");
			
			out.print("<small>&nbsp&nbsp&nbsp<a href=\"PlanAndControlProjectAdvancedSearchServlet\">Advanced Search</a></small>");
			out.print("</li>");
						
			out.print("</ol>");
			out.print("</fieldset>");
			
			out.print("<fieldset>");
			
			out.print("<input type='submit' value='Submit'/>");
			
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