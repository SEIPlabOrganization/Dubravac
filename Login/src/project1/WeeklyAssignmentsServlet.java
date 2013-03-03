/*
 * 
 * Servlet koji sluzi za spremanje podataka o zadanom tjednom zadatku u bazu i slanje pripadne poruke studentu kojemu je namijenjen zadatak
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

public class WeeklyAssignmentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WeeklyAssignmentsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			PrintWriter out = response.getWriter();
			MySQLcon database = new MySQLcon();
			
			String WR_deadline;
			int difference;
			
			// pretvaranje formata vrijednosti dobivene datePickerom u vrijednost potrebne za unos u bazu
			// Detaljnije opisano u Conversion.java
			Conversion c = new Conversion();
			String WA_deadline = c.DateConversion(request.getParameter("deadline"));
			String[] NameSurname = c.NameConversion(request.getParameter("nameSurname"));
			
			ResultSet rs = database.Quer("select datediff('" + WA_deadline + "', curdate())/2");
			rs.first();
			difference = Math.round(Float.parseFloat(rs.getString(1)));
			rs = database.Quer("select adddate(curdate(), " + difference + ")");
			rs.first();
			WR_deadline = rs.getString(1);
			rs.close();
			
			// dohvacanje podataka iz sesije
			HttpSession session = request.getSession(true);
			String teamid = (String) session.getAttribute("teamid");

			// upit koji vraca id trenutnog usera u timu, tako da se moze povezat na bazu
			rs = database.Quer("select Users_team.idUsers_team from Users_team, Users, Team where Users.idUsers=Users_team.Users_idUsers and Team.idTeam=Users_team.team_idTeam and Users.Name='" + NameSurname[0] + "' and Users.Surname='" + NameSurname[1] + "' and Team.idTeam="+teamid+";");
			rs.first();
			
			//status = 1, zadatak zadan a nije jos rijesen, ubacivanje zadatka u bazu
			database.Upd("insert into weeklyassignments (name, description, difficulty, deadline, productformat, status, Users_team_idUsers_team) values ('" + request.getParameter("name") + "', '" + request.getParameter("description") + "', " + Integer.parseInt(request.getParameter("difficulty")) + ", '" + WA_deadline + "', '" + request.getParameter("productFormat") + "', 1, " + rs.getString(1) + ");");
			
			// nakon omogucavanja week reporta treba se odkomentirati linija koda za ubacivanje podataka u tablicu WeekReport
			// database.Upd("insert into weekreport (weeknumber, deadline, weeklyassignments_idweeklyassignments) values (" + Integer.parseInt(request.getParameter("week")) + ", '" + WR_deadline +"')");
			
			String userid = (String) session.getAttribute("userid");
			
			// odabir kome se salje poruka 
			rs = database.Quer("select idUsers_team from Users_team where Team_idTeam='" + teamid + "' and Users_idUsers=(select idUsers from Users where name='" + NameSurname[0] + "' and surname='" + NameSurname[1] + "')");
			rs.first();
			
			// trenutno vrijeme i datum u formatu za mysql
			ResultSet curdate = database.Quer("select curdate();");
			ResultSet curtime = database.Quer("select curtime();");
			curdate.first();
			curtime.first();
			
			// slanje poruke o zadanom novom tjednom zadatku
			database.Upd("insert into Messages (Users_idUsers, Users_team_idUsers_team, subject, content, date, time) values ('" + userid + "', '" + rs.getString(1) + "', 'You have new weekly assignment', 'Name:\"" + request.getParameter("name") + "\" short description:\"" + request.getParameter("description") + "\" difficulty:\"" + request.getParameter("difficulty") + "\" weekly assignment deadline:\"" + request.getParameter("deadline") + "\" week report deadline:\"" + WR_deadline + "\" product format:\"" + request.getParameter("productFormat") + "\"', '" + curdate.getString(1) + "', '" + curtime.getString(1) + "');");
			
			curdate.close();
			curtime.close();
			rs.close();
			
			// povratak na prijasnju stranicu s porukom da je uspjesno zadan tjedni zadatak
			// ako se nesto od prethodnih naredbi ne izvrsi kako treba, poruka se nece ispisati
			response.sendRedirect("/Login/WeeklyAssignmentsFirstServlet?message=success");
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}