package project1;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmailChangeConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			MySQLcon db = new MySQLcon();
			ResultSet r = db.Quer("SELECT * FROM Users WHERE idUsers='"+ (String) request.getParameter("id") +"';");
			String pass=(String) request.getParameter("pw");
			String email=(String) request.getParameter("em");
			if(r.first()){
				if(pass.equalsIgnoreCase(r.getString("User_password"))){
					if(db.Upd("UPDATE Users SET Email='"+email+"' WHERE idUsers='"+ (String) request.getParameter("id") +"';")){
						ServletContext sc = this.getServletContext();
						RequestDispatcher rd = sc.getRequestDispatcher("/ChangeEmail.html");
						rd.forward(request, response);
					}else{
						ServletContext sc = this.getServletContext();
						RequestDispatcher rd = sc.getRequestDispatcher("/LogIn.jsp?ret=Error ocured");
						rd.forward(request, response);
					}
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/LogIn.jsp?ret=Error ocured");
			rd.forward(request, response);
		}
	}
}
