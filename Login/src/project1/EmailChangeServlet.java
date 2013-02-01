package project1;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class EmailChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession(true);
		MySQLcon db = new MySQLcon();
		try{	
			if(((String) request.getParameter("newemail")).equalsIgnoreCase((String) request.getParameter("repeatemail"))&& (!((String) request.getParameter("newemail")).equalsIgnoreCase(""))){
					
				ResultSet r=db.Quer("SELECT User_password FROM Users WHERE Email='"+(String) request.getParameter("newemail")+"';");
				if(r.first()){
					request.getRequestDispatcher("/EmailSettings.jsp?ret=E-mail address not changed. E-mail alredy used by some one.").forward(request, response);
				}else{
					r=db.Quer("SELECT User_password FROM Users WHERE idUsers='"+(String) session.getAttribute("userid")+"';");
					r.first();
					String pass=r.getString("User_password");
					String sub="SPtool E-mail change confirmation";
					String cont="Go to folowing link to compleate the e-mail change:<br/><br/><a target='_blank' href='http://localhost:8080/Login/EmailChangeConfirmationServlet?id="+(String) session.getAttribute("userid")+"&pw="+pass+"&em="+(String) request.getParameter("newemail")+"'>http://localhost:8080/Login/EmailChangeConfirmationServlet?id="+(String) session.getAttribute("userid")+"&pw="+pass+"&em="+(String) request.getParameter("newemail")+"</a> ";
					SendEmail em=new SendEmail();
					if(em.send((String) request.getParameter("newemail"), sub, cont))
						request.getRequestDispatcher("/EmailSettings.jsp?ret=Confirmation sent to new address. After confirming changes will take affect.").forward(request, response);
				}
			}else{
				request.getRequestDispatcher("/EmailSettings.jsp?ret=Error. E-mail address not changed.").forward(request, response);
			}
		}catch (Exception e){
			e.printStackTrace();
			request.getRequestDispatcher("/EmailSettings.jsp?ret=Error. E-mail address not changed.").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

