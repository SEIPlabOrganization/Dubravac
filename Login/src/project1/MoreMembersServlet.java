package project1;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class MoreMembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MoreMembersServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		
		
		String responsibility = request.getParameter("zaduzenja");
		String JMBAGMember = request.getParameter("JMBAGMember");
		String responsibId = "1";
		
		try {
			
			MySQLcon db = new MySQLcon();
			
			
			boolean b = false;
			ResultSet res3 = db.Quer("SELECT idUsers FROM Users WHERE idUsers='" + JMBAGMember + "';");
			
			if (res3.first()) b = true;
			System.out.println(b);
			

			if (b) {
				System.out.println("Usao u true");
				ResultSet res2 = db.Quer("SELECT idResponsibility FROM responsibility WHERE Name='" + responsibility + "';");
				
				while (res2.next()) {
					responsibId = res2.getString(1);
				}
				
				if (db.Upd("INSERT INTO users_team SET Users_idUsers='" + JMBAGMember + "', Responsibility_idResponsibility='" + responsibId + "', Team_idTeam=" + (String) session.getAttribute("teamid") + ";")) {
					
					response.sendRedirect("/Login/SuccessNewMember.jsp");
	
				}
			} else {
				System.out.println("Usao u false");
				response.sendRedirect("/Login/SuccessNewTeam.jsp?ret=Nepostojeci_user");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}