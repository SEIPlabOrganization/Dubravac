package project1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FrameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		
		//Student is team leader in team 
		if(("Stud".equalsIgnoreCase((String) session.getAttribute("userrole"))) && ((String) session.getAttribute("teamid")!=null) && ("1".equalsIgnoreCase((String) session.getAttribute("teamrole")))){
			
			//Navigation menu
			out.println("<div id='nav'>");
			out.println("<a href='UserPage_TeamLeader.jsp' target='tools'>Home</a>");
			out.println("<a href='Msg.jsp' target='tools'>Messages</a>");
			out.println("<a href='AccSetings.jsp' target='tools'>Account management</a> ");
			out.println("<a onClick='myPopup()' style='cursor: pointer;'>Find JMBAG</a> ");
			out.println("<a href='LogIn.jsp?ret=Successfully logged out.'>Log Out</a>");
			out.println("</div>");
		
			//in frame
			out.println("<iframe name='tools' src='UserPage_TeamLeader.jsp' id='tools' ></iframe>");
		}
		//Student in team
		else if("Stud".equalsIgnoreCase((String) session.getAttribute("userrole")) && ((String) session.getAttribute("teamid")!=null) ){
			
			//Navigation menu
			out.println("<div id='nav'>");
			out.println("<a href='UserPage_Student.jsp' target='tools'>Home</a>");
			out.println("<a href='Msg.jsp' target='tools'>Messages</a>");
			out.println("<a href='AccSetings.jsp' target='tools'>Account management</a> ");
			out.println("<a onClick='myPopup()' style='cursor: pointer;'>Find JMBAG</a> ");
			out.println("<a href='LogIn.jsp?ret=Successfully logged out.'>Log Out</a>");
			out.println("</div>");
		
			//in frame
			out.println("<iframe name='tools' src='UserPage_Student.jsp' id='tools' ></iframe>");
		}
		
		
		//Student without team
		if(("Stud".equalsIgnoreCase((String) session.getAttribute("userrole"))) && ((String) session.getAttribute("teamid")==null)){
			
			//Navigation menu
			out.println("<div id='nav'>");
			out.println("<a href='UserPage_NoTeam.jsp' target='tools'>Home</a>");
			out.println("<a href='AccSetings.jsp' target='tools'>Account management</a> ");
			out.println("<a onClick='myPopup()' style='cursor: pointer;'>Find JMBAG</a> ");
			out.println("<a href='LogIn.jsp?ret=Successfully logged out.'>Log Out</a>");
			out.println("</div>");
		
			//in frame
			out.println("<iframe name='tools' src='UserPage_NoTeam.jsp' id='tools' ></iframe>");
		}
		
		//Professor or Assistant
		if(("Prof".equalsIgnoreCase((String) session.getAttribute("userrole"))) || ("Assi".equalsIgnoreCase((String) session.getAttribute("userrole")))){
			
			//Navigation menu
			out.println("<div id='nav'>");
			out.println("<a href='UserPage_ProfAssi.jsp' target='tools'>Home</a>");
			out.println("<a href='ProfAssi_msg.jsp' target='tools'>Messages</a>");
			out.println("<a href='AccSetings.jsp' target='tools'>Account management</a> ");
			out.println("<a onClick='myPopup()' style='cursor: pointer;'>Find JMBAG</a> ");
			out.println("<a href='LogIn.jsp?ret=Successfully logged out.'>Log Out</a>");
			out.println("</div>");
		
			//in frame
			out.println("<iframe name='tools' src='UserPage_ProfAssi.jsp' id='tools' ></iframe>");
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
