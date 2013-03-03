/*
 * 
 * Servlet koji sluzi za omogucavanje profesoru/asistentu osnovno pretrazivanje. Profesor odabere kombinaciju team-projekt i te informacije mu se prikazu pomocu PlanAndControlBasicSearchServlet
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
			MySQLcon database = new MySQLcon();

			out.println("<form id='form' method='get' action='PlanAndControlProjectServlet'>");
			
			out.println("<fieldset>");
			out.println("<legend>Plan and control project</legend>");
			out.println("<ol>");
					
			out.println("<li>");
			out.println("<label for='projectBasicSearch'>Choose project</label>");
			
			out.println("<select id='projectBasicSearch' name='projectBasicSearch'>");
			
			// prikaz svih projekata i timova
			// radi preglednosti se moze odkomentirat dio koda u resultsetu ispod, tako da profesor u osnovnom pretrazivanju vidi samo kombinacije team-projekt za tekucu godinu
			ResultSet projects = database.Quer("select Project.Name, Team.Name, Project.acad_year from Project, Team where (Project.Team_idTeam=Team.idTeam) order by Project.Acad_year DESC, Project.Name ASC"/* and acad_year=(select max(acad_year) from project)*/);

			// prikaz svih timova s pripadnim projektima u padajucem izborniku
			while(projects.next())
			{
				out.println("<option value='" + projects.getString("Project.Name") + ";" + projects.getString("Team.Name") + "'>" + projects.getString("Project.Name") + " - " + projects.getString("Team.Name") +"</option>");
			}
			projects.close();
			out.println("</select>");
			
			out.println("<small>&nbsp&nbsp&nbsp<a href='PlanAndControlProjectAdvancedSearchServlet'>Advanced Search</a></small>");
			out.println("</li>");
						
			out.println("</ol>");
			out.println("</fieldset>");
			
			out.println("<fieldset>");
			
			out.println("<input type='submit' value='Submit'/>");
			
			out.println("</fieldset>");
			out.println("</form>");
			
			// onemoguci submit s praznim svim poljima
			out.println("<script type='text/javascript'>");
			out.println("var form = document.getElementById('form');");
			out.println("form.onsubmit = function(event) {");
			out.println("if (form['projectBasicSearch'].value == '')");
			out.println("{");
			out.println("event.preventDefault();");
			out.println("alert('One project must be selected')");
			out.println("}");
			out.println("};");
	        out.println("</script>");
			/*
			out.println("</body>");
			out.println("</html>");*/
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}