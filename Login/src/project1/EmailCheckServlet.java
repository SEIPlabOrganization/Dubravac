package project1;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmailCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			MySQLcon db = new MySQLcon();
			ResultSet r = db.Quer("SELECT Opt FROM Users WHERE idUsers='"+ (String) request.getParameter("id") +"';");
			String pass=(String) request.getParameter("pw");
			String name=(String) request.getParameter("nm");
			if(r.first()){
				if(((String) request.getParameter("check")).equalsIgnoreCase(r.getString("Opt"))){
					if(db.Upd("UPDATE Users SET User_password='"+pass+"', User_name='"+name+"', Opt='2000000000' WHERE idUsers='"+ (String) request.getParameter("id") +"' ;")){
						ServletContext sc = this.getServletContext();
						RequestDispatcher rd = sc.getRequestDispatcher("/LogIn.jsp?ret=Successfully Registered");
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
		}
	}
}
