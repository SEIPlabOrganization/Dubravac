package project1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CreateAssignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CreateAssignServlet() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
		
		HttpSession session = request.getSession(true);

		MySQLcon db = new MySQLcon();
		
		String proposalName = request.getParameter("ImeZadatka");
		String description = request.getParameter("DescriptionZadatka");
		String suggReading = request.getParameter("DescriptionZadatkaZaPripremu");
		
		
		
		if (db.Upd("INSERT INTO project_assignements SET name='" + proposalName + "', author='" + (String) session.getAttribute("userid") + "', description='" + description + "', prep_sugg_reading='" + suggReading + "', version=1;")) {
			
			response.sendRedirect("/Login/SuccessNewProjectAssignements.jsp");
		}
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
	}
}
