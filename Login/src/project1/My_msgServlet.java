package project1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class My_msgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		MySQLcon db = new MySQLcon();
		
		ResultSet r=db.Quer("SELECT idUsers_team FROM Users_team WHERE Team_idTeam='"+(String) session.getAttribute("teamid")+"' AND Users_idUsers='"+(String) session.getAttribute("userid")+"';");
		
		String Wall;
		try{
		if(r.first()) //Is result set not empty?
			Wall= r.getString("idUsers_team");
		else
			Wall= "error";
		}catch(Exception e){
			Wall= "error";
		}
		
		String Query="SELECT * FROM Messages WHERE Users_idUsers='"+(String) session.getAttribute("userid")+"' AND Users_team_idUsers_team='"+Wall+"' AND Subject LIKE '%";
		String filter;
		String dateAr;
		
		if(request.getParameter("sub")!=null)
			 filter = (String) request.getParameter("sub");
		else 
			filter = "";
		Query+=filter+"%' ";
		dateAr="down";
		if(request.getParameter("datear")!=null)
		 dateAr = (String) request.getParameter("datear");
		if(dateAr.equalsIgnoreCase("up"))
			Query+="ORDER BY Date ASC";
		if(dateAr.equalsIgnoreCase("down"))
			Query+="ORDER BY Date DESC";
		Query+=";";
		r=db.Quer(Query);
		out.println("<form action=\"My_msg.jsp\" method=\"get\">");
		out.println("Subject:<input type=\"text\" value=\""+filter+"\" maxlength=\"50\" size=\"50\" name=\"sub\"/>");
		out.println("<input type=\"hidden\" value=\""+dateAr+"\" name=\"datear\" \"Send\"/>");
		out.println("<input type=\"submit\" value=\"Filter\"/>");
		out.println("</form>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<th id='read'>");
		out.println("</th>");
		out.println("<th id='subject'>");
		out.println("Subject ");
		out.println("</th>");
		out.println("<th id='date'>");
		
		if(dateAr.equalsIgnoreCase("up")){
			out.println("<a href='My_msg.jsp?datear=down&sub="+filter+"'>");
			out.println("Date ");
			out.println("▲");
			}
		if(dateAr.equalsIgnoreCase("down")){
			out.print("<a href='My_msg.jsp?datear=up&sub="+filter+"'>");
			out.println("Date ");
			out.println("▼");
			}
		out.println("</a>");
		out.println("</th>");
		out.println("<th id='time'>");
		out.println("Time");
		out.println("</th>");
		out.println("</tr>");
		try{
			int i=0;
			while(r.next()){
				out.println("<tr id='con'>");
				out.println("<td id='button'>");
				out.println("<button onClick='readcontent("+i+");'>Read</button>");
				out.println("<td id='sub'>");
				out.println(r.getString("Subject"));
				out.println("</td>");
				out.println("<td>");
				out.println(r.getString("Date"));
				out.println("</td>");
				out.println("<td>");
				out.println(r.getString("Time"));
				out.println("</td>");
				out.println("</tr>");
				out.println("<script type='text/javascript'>");
				out.println("content ["+i+"]='"+r.getString("Content")+"';");
				out.println("subject ["+i+"]='"+r.getString("Subject")+"';");
				out.println("</script>");
				i++;
			}
			out.println("</table>");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);	
	}

}
