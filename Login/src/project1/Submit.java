package project1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Submit
 */
public class Submit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Submit() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String connectionURL = "jdbc:mysql://localhost/mydb";
		 Connection connection=null;	  
		  response.setContentType("text/html");
		  PrintWriter out = response.getWriter();  
		  String radio = request.getParameter("radio");
		  String[] checkbox = request.getParameterValues("checkbox");
		  String survey_id = request.getParameter("id");
		  int id = Integer.parseInt(survey_id);
		  int acounter = 0;
		  int scounter = 0;
			Statement stmt;
			Statement stmt2;
			ResultSet rs;
			ResultSet rs2;
			
int aid=0;
		  try 
		  {
			  
			  Class.forName("com.mysql.jdbc.Driver");
			  
			  connection = DriverManager.getConnection
			  (connectionURL, "root", "root"); 
			  stmt = connection.createStatement();
			  stmt2 = connection.createStatement();
			  
			if (radio != null)
			  {
			  rs = stmt.executeQuery("SELECT idAnswers,AnswerCounter FROM answers where Answer= '"+ radio +"' AND idSurvey = "+ id +";");
			  rs2 = stmt2.executeQuery("SELECT SubmitCounter FROM survey where idSurvey = "+ id +";");
			  
				 while (rs.next())
				 {
					 acounter = rs.getInt("AnswerCounter");
					 aid = rs.getInt("idAnswers");
				 }
				 while (rs2.next())
				 {
					 scounter=rs2.getInt(1);
				 }
				 acounter++;
				 scounter++;
				 String sql = "UPDATE answers SET AnswerCounter = "+ acounter + " WHERE Answer = '"+ radio +"' AND idSurvey ="+ id +" AND idAnswers = "+aid+";";
				 PreparedStatement pst = connection.prepareStatement(sql);
				 pst.executeUpdate();
				 pst.close();	
				 String sql2 = "UPDATE survey SET SubmitCounter = "+ scounter + " WHERE idSurvey ="+ id +";";
				 PreparedStatement pst2 = connection.prepareStatement(sql2);
				 pst2.executeUpdate();
				 pst2.close();		
				 out.println("You have successfully voted for answer: "+ radio +"!");
				 out.println("<br/>");
				 out.println("Thank You");
				 out.println("<a href=\"http://localhost:8080/Login/survey_index.jsp\">Click here to get back</a>");	
			  }
			  else {
				  for(int i=0; i<checkbox.length; i++)
				  {
				  rs = stmt.executeQuery("SELECT idAnswers,AnswerCounter FROM answers where Answer= '"+ checkbox[i] +"' AND idSurvey = "+ id +";");
				  rs2 = stmt2.executeQuery("SELECT SubmitCounter FROM survey where idSurvey = "+ id +";");
							  while (rs.next())
							  {
								 acounter = rs.getInt("AnswerCounter");
								 aid = rs.getInt("idAnswers");
							  }
							  while (rs2.next())
							  {
								 scounter=rs2.getInt(1);
							  }
						 acounter++;
						 scounter++;
						 String sql = "UPDATE answers SET AnswerCounter = "+ acounter + " WHERE Answer = '"+ checkbox[i] +"' AND idSurvey ="+ id +" AND idAnswers ="+ aid +";";
						 PreparedStatement pst = connection.prepareStatement(sql);
						 pst.executeUpdate();
						 pst.close();	
						 out.println("You have successfully voted for answer: "+ checkbox[i] +"!");
						 out.println("<br>");
				  	
				  		 
						 String sql2 = "UPDATE survey SET SubmitCounter = "+ scounter + " WHERE idSurvey ="+ id +";";
						 PreparedStatement pst2 = connection.prepareStatement(sql2);
						 pst2.executeUpdate();
						 pst2.close();		
				  }
						 out.println("<br/>");
						 out.println("Thank You");
						 out.println("<a href=\"http://localhost:8080/Login/survey_index.jsp\">Click here to get back</a>");	
				  			  
			  }
		  }
		  
  catch(ClassNotFoundException e)
  {
	  out.println("Couldn't load database driver: " 
	  + e.getMessage());
  }
  catch(SQLException e)
  {
	  out.println("SQLException caught: " 
	  + e.getMessage());
  }
  catch (Exception e)
  {
	  out.println("Please select at least one answer");
	  out.println("<a href=\"http://localhost:8080/Login/survey_index.jsp\">Click here to get back</a>");	
	  out.println("<br>");
	  out.println(e);
	  
  }
  finally {

	  try 
	  {
		  	if (connection != null) connection.close();
	  }
	  catch (SQLException ignored)
	  {
	  out.println(ignored);
	  }
  	}
  }
}
	




