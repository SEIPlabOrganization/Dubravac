package project1;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateTeamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CreateTeamServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Catching team leader jmbag
		HttpSession session = request.getSession(true);
		String JMBAGTeamLeader = (String) session.getAttribute("userid");
		
		
		
		String TeamName = request.getParameter("TeamName");		
		String TeamId = "50";
		
		try {
		
			MySQLcon db = new MySQLcon();
			
		
			ResultSet resCheckTeamName = db.Quer("SELECT Team.Name FROM Team WHERE Team.Name='" + TeamName + "';");
			ResultSet resCheckLeader = db.Quer("SELECT Users_idUsers FROM Users_team WHERE Users_idUsers='" + JMBAGTeamLeader + "' AND Responsibility_idResponsibility=1;");
			
			if (resCheckTeamName.first() || resCheckLeader.first()) {
				response.sendRedirect("/Login/Error.jsp");

			} else {
			
			
				db.Upd("INSERT INTO Team SET Name='" + TeamName + "';");
				
				ResultSet res1 = db.Quer("SELECT Team.idTeam FROM Team WHERE Team.Name='" + TeamName + "';");
				
				while (res1.next()) {
					TeamId = res1.getString(1);
				}
				
				if (db.Upd("INSERT INTO Users_team SET Team_idTeam='" + TeamId + "', Users_idUsers='" + JMBAGTeamLeader + "', Responsibility_idResponsibility='1';")) {
					ResultSet r = db.Quer("SELECT Team.idTeam FROM Team WHERE Team.Name='" + TeamName + "';");
					r.first();
					session.setAttribute("teamid", r.getString("idTeam"));
					session.setAttribute("teamrole", "1");
					response.sendRedirect("/Login/SuccessNewTeam.jsp");
	
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
