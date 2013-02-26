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
			ResultSet rs = database.Quer("select productFormat from WeeklyAssignments where idWeeklyAssignments=" + idWeeklyAssignments);
			rs.first();
			
			if(rs.getString(1).equals("research"))
			{
			}
			else
			{
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}