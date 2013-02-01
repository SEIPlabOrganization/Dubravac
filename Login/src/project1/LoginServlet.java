package project1;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LogIn log = new LogIn(username, password);
		userid=log.sucess();
		
		if(userid != null){
			HttpSession session = request.getSession(true);
			session.setAttribute( "userid", userid);
			session.setAttribute( "userrole", log.role()); 
		}
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/Frame.jsp");
		rd.forward(request, response);
	}

}
