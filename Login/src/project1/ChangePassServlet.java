package project1;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ChangePassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String oldpass = request.getParameter("oldpassword");
		String newpass = request.getParameter("newpassword");
		String reppass = request.getParameter("repeatpassword");
		int check=1;
		String ret="Error ocured.";
		HttpSession session = request.getSession(true);
		try{
			MySQLcon db = new MySQLcon();
			ResultSet r = db.Quer("SELECT User_password FROM Users WHERE idUsers='"+(String) session.getAttribute("userid")+"';");
			if(r.first()){
				if(!db.toMD5(oldpass).equalsIgnoreCase(r.getString("User_password"))){
					check=0;
					ret="Incorrect old password.";
				}
			}else
				check=0;
			if(!newpass.equalsIgnoreCase(reppass))
				check=0;
			if(check==1){
				if(db.Upd("UPDATE Users SET User_password='"+db.toMD5(newpass)+"' WHERE idUsers='"+(String) session.getAttribute("userid")+"' ;")){
					ServletContext sc = this.getServletContext();
					RequestDispatcher rd = sc.getRequestDispatcher("/ChangePass.jsp?ret=Success.");
					rd.forward(request, response);
				}else{
					ServletContext sc = this.getServletContext();
					RequestDispatcher rd = sc.getRequestDispatcher("/ChangePass.jsp?ret="+ret+"");
					rd.forward(request, response);
				}
			}else{
				ServletContext sc = this.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/ChangePass.jsp?ret="+ret+"");
				rd.forward(request, response);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
