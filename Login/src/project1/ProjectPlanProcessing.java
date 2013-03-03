/*
 * 
 * Servlet koji je namijenjen obradi informacija unesenih preko projekt plan forme te unosu u bazu
 * 
 * */

package project1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProjectPlanProcessing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProjectPlanProcessing() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//obrada rezultata
		//ako je uspjesno
		response.sendRedirect("/Login/PlanAndControlProjectServlet?message=success");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
