package project1;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	   
	  
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try{
				HttpSession session = request.getSession(true);
				MySQLcon db = new MySQLcon();
				ResultSet r = db.Quer("SELECT Opt FROM Users WHERE idUsers='"+ session.getAttribute("userid") +"';");
				r.first();
				if(r.getString("Opt").charAt(0)=='0'){
					request.getRequestDispatcher("/Register.jsp").forward(request, response);
				}
				if(r.getString("Opt").charAt(0)=='1'){
					request.getRequestDispatcher("/Register.jsp").forward(request, response);
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}
}
