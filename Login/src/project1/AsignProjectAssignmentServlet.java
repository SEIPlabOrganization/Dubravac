package project1;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AsignProjectAssignmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AsignProjectAssignmentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String TeamId = (String) session.getAttribute("teamid");
		
		String ProjectName = request.getParameter("ProjectName");
		int ProjectAssignmentId = Integer.parseInt(request.getParameter("ProjectAssignmentId"));
		String ProjectID = "1";
		
		try {
			
			MySQLcon db = new MySQLcon();
			
			ResultSet r = db.Quer("SELECT idProject FROM Project WHERE Team_idTeam=" + TeamId + " AND Name='" + ProjectName + "';");
			
			while (r.next()) {
				ProjectID = r.getString(1);
			}
			System.out.println(ProjectID);
			
			//Is the assignment already taken?
			ResultSet r1 = db.Quer("SELECT Project_idProject FROM project_assignements WHERE id_as=" + ProjectAssignmentId + ";");
			
			//ResultSet r1 = db.Quer("SELECT Project_idProject FROM project_assignements WHERE id_as=" + ProjectAssignmentId + ";");
			System.out.println("SELECT Project_idProject FROM project_assignements WHERE id_as=" + ProjectAssignmentId + ";");
			r1.first();
			String tst = r1.getString(1);
		
			boolean check = true;
			
			//if (tst == 0) check = false;
			
			if (tst == null) check = false;
			System.out.println(check);
			
			if (check) {
				System.out.println("Uslo u true. Greska");
				response.sendRedirect("/Login/ErrorPickAssignments.jsp");
				
			} else {
				System.out.println("Uslo u false. Dobro je");
					if (db.Upd("UPDATE project_assignements SET Project_idProject='" + ProjectID + "' WHERE id_as=" + ProjectAssignmentId + ";")) {
						
						response.sendRedirect("/Login/PickAssignments.jsp");
		
					}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}