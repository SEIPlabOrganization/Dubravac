/*
 * 
 * Servlet koji sluzi za ispis svih zadanih tjednih zadataka trenutno ulogiranom studentu, te omogucava oznacavanje zadataka kao rijesenih.
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
import javax.servlet.http.HttpSession;

public class WeeklyAssignmentsStudentFirstServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
    
    public WeeklyAssignmentsStudentFirstServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//stranica za studenta
		try
		{
			PrintWriter out = response.getWriter();
			
			//povezivanje na bazu
			MySQLcon database = new MySQLcon();
			
			//uzimanje informacija iz sesije
			HttpSession session = request.getSession(true);
			String teamid = (String) session.getAttribute("teamid");
			String userid = (String) session.getAttribute("userid");
			
			out.println("<!DOCTYPE html>");
			out.println("<html><head>");
			out.println("<link rel='stylesheet' href='style.css' type='text/css'>");
			out.println("<title>Weekly Assignments</title>");				
			out.println("</head>");
			out.println("<body>");
			out.println("<form id='form' name='form1'>");
			out.println("<fieldset>");
			out.println("<ol><li>");
			
			// upit prema bazi za sve tjedne zadatke trenutno ulogiranog studenta
			ResultSet rs = database.Quer("select * from WeeklyAssignments where status=1 and Users_team_idUsers_team=(select idUsers_team from Users_team where Team_idTeam='" + teamid + "' and Users_idUsers='" + userid + "')");

			if(rs.next())
			{
				rs.beforeFirst();
				// ispis svih podataka vezanih uz pojedini tjedni zadatak
				while (rs.next())
				{
					out.println("<font size='3' color=black> <a href='WeeklyAssignmentsStudentServlet?id=" + rs.getString("idWeeklyAssignments") + "'>" + rs.getString("name") + "</a></font><br/>");
					out.println("<font size='2'>Description: " + rs.getString("description") + " | ");
					out.println("Difficulty: " + rs.getString("difficulty") + " | ");
					out.println("Deadline: " + rs.getString("deadline") + " | ");
					out.println("Product format: " + rs.getString("productFormat") + "</font>");
					if (rs.next())
					{
						out.println("<br/><br/>");
						rs.previous();
					}
				}
			}
			else
			{
				out.println("You don't have any weekly assignment!");
			}
			
			out.println("</li></ol>");
			out.println("</fieldset>");
			out.println("</form>");
	
			if (request.getParameter("message") != null)
			{
				// javascript funkcija koja ispisuje poruku ako su uspjesno rijeseni
				if ((request.getParameter("message")).compareTo("success") == 0)
				{
					out.println("<script type='text/javascript'>");
					out.println("alert('You have successfully mark weekly assignment as completed');");
					out.println("</script>");
				}
			}
			
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