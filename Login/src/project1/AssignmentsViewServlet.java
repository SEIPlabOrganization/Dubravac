package project1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.ResultSetMetaData;


public class AssignmentsViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AssignmentsViewServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		try{
			
			MySQLcon db = new MySQLcon();
			
			ResultSet r = db.Quer("SELECT * FROM project_assignements");
			
			ResultSetMetaData rsmd = (ResultSetMetaData) r.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			String columnValue;
			
			out.print("<h1>Project Assignments Overview</h1>");
			out.print("<table border=\"1\">");
			out.print("<tr><td>Assignment Id</td><td>Assignment name</td><td>Author</td><td>Prefix</td><td>Description</td><td>Sugested reading</td><td>Project ID</td><td>Version</td></tr>");
			while (r.next()) {
				out.print("<tr>");
				for (int i = 1; i <= numberOfColumns; i++)
				{
					columnValue = r.getString(i);
					if (i == 4) {
						if (columnValue.equalsIgnoreCase("Pending")) {
							out.print("<td style=\"background:yellow\">");
						} else if (columnValue.equalsIgnoreCase("Approved")) {
							out.print("<td style=\"background:green\">");
						} else if (columnValue.equalsIgnoreCase("Discard")) {
							out.print("<td style=\"background:red\">");
						}
					} else {
						out.print("<td>");
					}
					out.print(columnValue);
					out.print("</td>");
				}
				out.print("</tr>");
			}
			out.print("</table>");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
