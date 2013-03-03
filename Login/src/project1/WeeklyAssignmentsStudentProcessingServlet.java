/*
 * 
 * Pomocni servlet koji sluzi za promjenu statusa zadatka u bazi sa "zadano" u "ispunjeno" te vremena u kojem je predan
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

public class WeeklyAssignmentsStudentProcessingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     public WeeklyAssignmentsStudentProcessingServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();
			MySQLcon database = new MySQLcon();
			int idWeeklyAssignments = Integer.parseInt(request.getParameter("id"));
			
			// dohvacanje podataka iz sesije o trenutno ulogiranom studentu i odabranom timu
			HttpSession session = request.getSession(true);
			String userid = (String) session.getAttribute("userid");
			String teamid = (String) session.getAttribute("teamid");

			// promjena statusa i unos datuma izvrsavanja tjednog zadatka
			database.Upd("update WeeklyAssignments set status=2, DeliveryDate=(select curdate()) where idWeeklyAssignments=" + idWeeklyAssignments);
			
			
			// trenutno vrijeme i datum u formatu za mysql
			ResultSet curdate = database.Quer("select curdate();");
			ResultSet curtime = database.Quer("select curtime();");
			curdate.first();
			curtime.first();
			
			ResultSet rs = database.Quer("select idUsers_team from Users_team, Responsibility where Responsibility.idResponsibility=Users_team.Responsibility_idResponsibility and Responsibility.idResponsibility=1 and Team_idTeam=" + teamid);
			rs.first();
			
			// slanje poruke project manageru o ispunjenom tjednom zadatku
			database.Upd("insert into Messages (Users_idUsers, Users_team_idUsers_team, subject, content, date, time) values ('" + userid + "', '" + rs.getString(1) + "', 'Rate my weekly assignment!', 'Weekly assignment completed. Please rate my weekly assignment', '" + curdate.getString(1) + "', '" + curtime.getString(1) + "');");
			curdate.close();
			curtime.close();
			rs.close();
			
			// redirekcija na prethodnu stranicu s porukom o uspjesnosti
			response.sendRedirect("/Login/WeeklyAssignmentsStudentFirstServlet?message=success");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
