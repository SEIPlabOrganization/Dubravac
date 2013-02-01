package project1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class EmailSettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String team = request.getParameter("team");
		String lead = request.getParameter("lead");
		String prof = request.getParameter("prof");
		HttpSession session = request.getSession(true);
		MySQLcon db = new MySQLcon();
		ResultSet r;
		if(request.getParameter("is")==null){
			try{
				PrintWriter out = response.getWriter();
				String sel="";
				r = db.Quer("SELECT Opt FROM Users WHERE idUsers='"+(String) session.getAttribute("userid")+"';");
				r.first();
				if(r.getString("Opt").charAt(3)=='0')
					sel="checked";
				out.println("<tr><td style='text-align: left;'>Professor or Assistant notifications</td><td><input type='checkbox' name='prof' value='yes' "+sel+" onchange=\"document.forms[\'form\'].submit();\" ></td></tr>");
				sel="";
				
				if(r.getString("Opt").charAt(2)=='0')
					sel="checked";
				out.println("<tr><td style='text-align: left;'>Team leader notifications</td><td><input type='checkbox' name='lead' value='yes' "+sel+" onchange=\"document.forms[\'form\'].submit();\" ></td></tr>");
				sel="";
				
				if(r.getString("Opt").charAt(1)=='0')
					sel="checked";
				out.println("<tr><td style='text-align: left;'>Team members posts</td><td><input type='checkbox' name='team' value='yes' "+sel+" onchange=\"document.forms[\'form\'].submit();\" ></td></tr>");
			}catch (Exception e){
				e.printStackTrace();
			}
		}else{
			try{
				r=db.Quer("SELECT Opt FROM Users WHERE idUsers='"+(String) session.getAttribute("userid")+"';");
				r.first();
				char Opt[]=r.getString("Opt").toCharArray();
				if(team!=null)
					Opt[1]='0';
				else
					Opt[1]='1';
				if(lead!=null)
					Opt[2]='0';
				else
					Opt[2]='1';
				if(prof!=null)
					Opt[3]='0';
				else
					Opt[3]='1';
				String Optt=new String(Opt);
				db.Upd("UPDATE Users SET Opt='"+Optt+"' WHERE idUsers='"+(String) session.getAttribute("userid")+"' ;");
			}catch (Exception e){
				e.printStackTrace();
			}
			request.removeAttribute("is");
			response.sendRedirect("EmailSettings.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

