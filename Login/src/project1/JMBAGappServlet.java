package project1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JMBAGappServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if((request.getParameter("name")!=null) && (request.getParameter("surname")!=null) && (request.getParameter("name")!="") && (request.getParameter("surname")!="")){
			try{
				PrintWriter out = response.getWriter();
				MySQLcon db = new MySQLcon();
				ResultSet r;
				ResultSet r1;
				ResultSet r2;
				r=db.Quer("SELECT * FROM Users WHERE Name='"+(String) request.getParameter("name")+"' AND Surname='"+(String) request.getParameter("surname")+"';");
				out.println("<hr/>");
				out.println("<hr/>");
				if(r.first()){
					out.println("<table>");
					do{
						out.println("<tr>");
						out.println("<th>"+r.getString("idUsers")+"</th>");
						out.println("<th>"+r.getString("Name")+"</th>");
						out.println("<th>"+r.getString("Surname")+"</th>");
						out.println("</tr>");
						r1=db.Quer("SELECT * FROM Users_team WHERE Users_idUsers='"+r.getString("idUsers")+"';");
						while(r1.next()){
							r2=db.Quer("SELECT * FROM Team WHERE idTeam='"+r1.getString("Team_idTeam")+"';");
							if(r2.first()){
								out.println("<tr>");
								out.println("<td>"+r2.getString("idTeam")+"</td>");
								out.println("<td colspan='2'>"+r2.getString("Name")+"</td>");
								out.println("</tr>");
							}
						}while(r1.next());
						out.println("<tr><td colspan='3'><hr/></td></tr>");
					}while(r.next());
					out.println("</table>");
				}else{
					out.println("No user found.");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
