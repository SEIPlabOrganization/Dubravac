package project1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class NewProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public NewProjectServlet() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		
		HttpSession session = request.getSession(true);
		String TeamId = (String) session.getAttribute("teamid");
		
		
		String ProjectName = request.getParameter("ProjectName");
		String ProjectDescription = request.getParameter("ProjectDescription");
		String Subject = request.getParameter("Subject");
		String AcadYear = "2000-2001";
		
		Conversion c1 = new Conversion();
		String noviDateEnd = c1.DateConversion(request.getParameter("DateEnd"));
		
		Conversion c = new Conversion();
		String noviDateBegin = c.DateConversion(request.getParameter("DateBegin"));
		
		
		String[] token = noviDateBegin.split("-");
		int a = Integer.parseInt(token[1]);
		int godina = Integer.parseInt(token[0]);
		
		try {
			
			MySQLcon db = new MySQLcon();
			
			
			if (a <= 9) {
				AcadYear = (godina-1) + "-" + godina;
			} else {
				AcadYear = godina + "-" + (godina+1);
			}
			
			
			if (db.Upd("INSERT INTO `mydb`.`project` SET Subject='" + Subject + "', Description='" + ProjectDescription + "', DateBegin='" + noviDateBegin + "', DateEnd='" + noviDateEnd + "', Name='" + ProjectName + "', Team_idTeam=" + TeamId + ", Acad_year='" + AcadYear + "';")) {
				
				response.sendRedirect("/Login/PickAssignments.jsp");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}