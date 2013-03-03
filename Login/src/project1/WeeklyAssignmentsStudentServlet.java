/*
 * 
 * Servlet koji pruza mogucnost studentu da oznaci tjedni zadatak kao rijesen, te upload datoteke ukoliko je zadatak tipa "document"
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

public class WeeklyAssignmentsStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WeeklyAssignmentsStudentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			PrintWriter out = response.getWriter();
			int idWeeklyAssignments = Integer.parseInt(request.getParameter("id"));
			
			MySQLcon database = new MySQLcon();
			
			// upit prema bazi za product format odabranog tjednog zadatka
			ResultSet rs = database.Quer("select productFormat from WeeklyAssignments where idWeeklyAssignments=" + idWeeklyAssignments);
			rs.first();
			
			out.println("<!DOCTYPE html>");
			out.println("<html><head>");
			out.println("<link rel='stylesheet' href='style.css' type='text/css'>");
			out.println("<title>Weekly Assignments</title>");
			
			out.println("</head>");
			out.println("<body>");
			out.println("<form id='form' name='form1'>");
			out.println("<fieldset>");
			out.println("<ol><li>");			
			
			// ako je product format = research
			if(rs.getString(1).equals("research"))
			{
				out.println("<a href='WeeklyAssignmentsStudentProcessingServlet?id=" + idWeeklyAssignments + "'>Mark as completed</a><br/>");
				out.println("<a href='javascript:javascript:history.go(-1)'>Back</a>");
			}
			// ako je product format = document
			else
			{
				// tu bi jos trebalo ubaciti upload field
				out.println("<a href='WeeklyAssignmentsStudentProcessingServlet?id=" + idWeeklyAssignments + "'>Mark as completed</a><br/>");
				out.println("<a href='javascript:javascript:history.go(-1)'>Back</a>");
			}
			
			out.println("</li></ol>");
			out.println("</fieldset>");
			out.println("</form>");
	
			out.println("</body>");
			out.println("</html>");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}