package SPtool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WeeklyAssignmentsFirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public WeeklyAssignmentsFirstServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//saznat tko je ulogiran
		//ako je ulogiran obican student skociti na stranicu
		//response.sendRedirect("/SPtool/WeeklyAssignmentsStudentServlet");
		
		//ako je ulogiran team leader
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html><head>");
		out.println("<link rel='stylesheet' href='style.css' type='text/css'>");
		out.println("<title>Weekly Assignments</title>");				
		out.println("</head>");
		out.println("<body>");
		out.println("<form id='form' name='form1'>");
		out.println("<fieldset>");
		out.println("<ol><li>");
		
		out.println("<a href='WeeklyAssignmentsForm.jsp'>Create new weekly assignment</a><br/>");
		//out.println("<a href='WeeklyAssignmentsRate.jsp'>Rate completed weekly assignments</a>");
		
		out.println("</li></ol>");
		out.println("</fieldset>");
		out.println("</form>");
		if (request.getParameter("message") != null)
		{
			if ((request.getParameter("message")).compareTo("success") == 0)
			{
				out.println("<script type='text/javascript'>");
				out.println("alert('You have successfully set weekly assignment');");
				out.println("</script>");
			}
		}
		out.println("</body>");
		out.println("</html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}