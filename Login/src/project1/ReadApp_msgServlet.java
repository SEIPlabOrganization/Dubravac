package project1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadApp_msgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		MySQLcon db = new MySQLcon();
		db.Upd("UPDATE Messages SET Messages.Read=1 WHERE idMessages='"+(String) request.getParameter("id")+"';");
		ResultSet r=db.Quer("SELECT Name, Surname FROM Users WHERE idUsers='"+(String) request.getParameter("idu")+"';");
		try {
			r.first();
			out.print("<tr><td style=\"width: 80px; height: 30px;\">From:</td><td>"+r.getString("Name")+" "+r.getString("Surname")+"</td>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		r=db.Quer("SELECT * FROM Messages WHERE idMessages='"+(String) request.getParameter("id")+"';");
		try {
			r.first();
			out.print("<td style=\"width: 100px; height: 30px;\">Recived on:</td><td style=\"width: 100px;\">"+r.getString("Date")+"</td>");
			out.println("<td style=\"width: 40px; height: 30px; text-align: right;\">At:</td><td style=\"width: 100px;\">"+r.getString("time")+"</td></tr>");
			out.print("<tr><td style=\"width: 80px; height: 30px;\">Subject:</td><td colspan=\"5\"><textarea rows=\"1\" cols=\"1\" name=\"content\" style=\"resize: none; width: 100%; height: 25px; font-size: 16px; vertical-align: middle;\" onfocus=\"blur();\" spellcheck='false'>");
			out.println(r.getString("Subject")+"</textarea></td></tr>");
			out.println("<tr><td style=\"height: 40px; text-align: center;\" colspan=\"6\">Message content:</td></tr>");
			out.print("<tr><td colspan=\"6\"><textarea rows=\"1\" cols=\"1\" name=\"content\" style=\"resize: none; width: 100%; height: 100%; font-size: 16px; vertical-align: middle;\" onfocus=\"blur();\" spellcheck='false'>");
			out.println(r.getString("Content")+"</textarea></td></tr>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);	
	}

}
