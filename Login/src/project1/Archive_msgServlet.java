package project1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Archive_msgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		MySQLcon db = new MySQLcon();
		ResultSet r1;
		ResultSet r=db.Quer("SELECT idUsers_team FROM Users_team WHERE Team_idTeam='"+(String) session.getAttribute("teamid")+"' AND Users_idUsers!='"+(String) session.getAttribute("userid")+"';");
		ArrayList<String> Wall = new ArrayList<String>();
		String Query="SELECT * FROM Messages WHERE ( "; 
		try{
			while(r.next()){
				Wall.add(r.getString("idUsers_team"));
			}
			for (int i = 0; i < Wall.size(); i++) {
				
				Query+="( Users_team_idUsers_team='"+Wall.get(i)+"' AND ";
				r1=db.Quer("SELECT Users_idUsers FROM Users_team WHERE idUsers_team='"+Wall.get(i)+"';");
				r1.first();
				Query+="Users_idUsers='"+r1.getString("Users_idUsers")+"' )";
				if(i < (Wall.size()-1))
					Query+=" OR ";
				
			}
		} catch (Exception e) {
	        e.printStackTrace();
		}
		Query+=" ) AND Subject LIKE '%";
		String filtersub;
		String filterauthor;
		String Aranger;
		
		if(request.getParameter("sub")!=null)
			 filtersub = (String) request.getParameter("sub");
		else 
			filtersub = "";
		Query+=filtersub+"%' ";
		
		
		Query+=" AND Users_idUsers LIKE '%";
		
		
		if(request.getParameter("author")!=null)
			 filterauthor = (String) request.getParameter("author");
		else 
			filterauthor = "";
		Query+=filterauthor+"%' ";
		
		Aranger="datedown";
		if(request.getParameter("aranger")!=null)
			Aranger = (String) request.getParameter("aranger");
		if(Aranger.equalsIgnoreCase("dateup"))
			Query+="ORDER BY Date ASC";
		if(Aranger.equalsIgnoreCase("datedown"))
			Query+="ORDER BY Date DESC";
		if(request.getParameter("aranger")!=null)
			Aranger = (String) request.getParameter("aranger");
		if(Aranger.equalsIgnoreCase("authorup"))
			Query+="ORDER BY Users_idUsers ASC";
		if(Aranger.equalsIgnoreCase("authordown"))
			Query+="ORDER BY Users_idUsers DESC";
	
		Query+=";";
		r=db.Quer(Query);
		
		out.println("<form action=\"Archive_msg.jsp\" method=\"get\">");
		out.println("  Subject:<input type=\"text\" value=\""+filtersub+"\" maxlength=\"50\" size=\"30\" name=\"sub\"/>");
		out.println("Author:");
		out.println("<select name='author'>");
		out.println("<option value='%'>");
		out.println("All");
		out.println("</option>");
		try{
			for (int i = 0; i < Wall.size(); i++) {
				r1=db.Quer("SELECT Users_idUsers FROM Users_team WHERE idUsers_team='"+Wall.get(i)+"';");
				r1.first();
				if(filterauthor.equalsIgnoreCase(r1.getString("Users_idUsers"))){
					out.println("<option value='"+r1.getString("Users_idUsers")+"' selected>");
				}else{
					out.println("<option value='"+r1.getString("Users_idUsers")+"'>");
				}
				r1=db.Quer("SELECT Name, Surname FROM Users WHERE idUsers='"+r1.getString("Users_idUsers")+"';");
				r1.first();
				out.println(r1.getString("Name")+" "+r1.getString("Surname"));
				out.println("</option>");
			}
		} catch (Exception e) {
	        e.printStackTrace();
		}
		out.println("</select>");
		out.println("<input type=\"hidden\" value=\""+Aranger+"\" name=\"datear\" \"Send\"/>");
		out.println("<input type=\"submit\" value=\"Filter\"/>");
		out.println("</form>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<th id='read'>");
		out.println("</th>");
		out.println("<th id='author'>");
		if(Aranger.equalsIgnoreCase("authorup")){
			out.println("<a href='Archive_msg.jsp?aranger=authordown&sub="+filtersub+"&author="+filterauthor+"'>");
			out.println("Author ");
			out.println("▲");
			}
		else if(Aranger.equalsIgnoreCase("authordown")){
			out.println("<a href='Archive_msg.jsp?aranger=authorup&sub="+filtersub+"&author="+filterauthor+"'>");
			out.println("Author ");
			out.println("▼");
			}
		else{
			out.println("<a href='Archive_msg.jsp?aranger=authordown&sub="+filtersub+"&author="+filterauthor+"'>");
			out.println("Author •");
			}
		out.println("</a>");
		out.println("</th>");
		out.println("<th id='subject'>");
		out.println("Subject ");
		out.println("</th>");
		out.println("<th id='date'>");
		if(Aranger.equalsIgnoreCase("dateup")){
			out.println("<a href='Archive_msg.jsp?aranger=datedown&sub="+filtersub+"&author="+filterauthor+"'>");
			out.println("Date ");
			out.println("▲");
			}
		else if(Aranger.equalsIgnoreCase("datedown")){
			out.println("<a href='Archive_msg.jsp?aranger=dateup&sub="+filtersub+"&author="+filterauthor+"'>");
			out.println("Date ");
			out.println("▼");
			}
		else{
			out.println("<a href='Archive_msg.jsp?aranger=datedown&sub="+filtersub+"&author="+filterauthor+"'>");
			out.println("Date •");
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
				r1=db.Quer("SELECT Name, Surname FROM Users WHERE idUsers='"+r.getString("Users_idUsers")+"';");
				r1.first();
				out.println("<tr id='con'>");
				out.println("<td id='button'>");
				out.println("<form action='ReadArchive_msg.jsp?aranger="+Aranger+"&sub="+filtersub+"&author="+filterauthor+"' method='post'>");
				out.println("<input type='hidden' value='"+r.getString("idMessages")+"' name='id'/>");
				out.println("<input type='hidden' value='"+r1.getString("Name")+" "+r1.getString("Surname")+"' name='from'/>");
				out.println("<input type='hidden' value='"+r.getString("Subject")+"' name='subject'/>");
				out.println("<input type='hidden' value='"+r.getString("Content")+"' name='content'/>");
				out.println("<input type='hidden' value='"+r.getString("Time")+"' name='time'/>");
				out.println("<input type='hidden' value='"+r.getString("Date")+"' name='date'/>");
				out.println("<input type='submit' value='Read'/>");
				out.println("</form>");
				out.println("</td>");
				out.println("<td id='aut'>");
				out.println(r1.getString("Name")+" "+r1.getString("Surname"));
				out.println("</td>");
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
