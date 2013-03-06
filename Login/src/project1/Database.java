package project1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Database extends HttpServlet
{
	
	public void init(ServletConfig config) throws ServletException
	  {
		  super.init(config);
	  }
 
	  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException //Request - response method -> GET method.
  		{
		  String connectionURL = "jdbc:mysql://localhost/mydb"; //path for mysql connection.
		  Connection connection=null;	  
		  
		  res.setContentType("text/html"); //response to the client is sent by text.
		  PrintWriter out = res.getWriter();  
		  String Question = req.getParameter("question"); //requests parameter "question", and saves it to string. Text box field where user enters question.
		  Conversion c = new Conversion(); 	
		  String Deadline = c.DateConversion(req.getParameter("deadline"));  //requests parameter "deadline", and saves it to string. User picks a deadline for survey, also date picker conversion for right deadline date format.	
		  String[] answer = req.getParameterValues("answer"); //requests parameter "answer", and saves all answers to array (usually more than one answer).
		  String Type = req.getParameter("type"); //requests parameter "type", and saves it to string. Type of survey can be single choice or multiple choice.

		  try 
		  {
			  
			  Class.forName("com.mysql.jdbc.Driver"); // Load the database driver
			  
			  connection = DriverManager.getConnection  (connectionURL, "root", "root"); //connectionURL declared before and username/password for mysql.
			  String sql = "insert into survey (Type,Deadline,Question) values (?,?,?)"; //query for insertion into database. Inserting type, deadline and question values.
			  PreparedStatement pst = connection.prepareStatement(sql); //preparedstatement for database submit
		
			  	  
				  pst.setString(1, Type); //sets value from string Type 
				  pst.setString(2, Deadline); //sets value from string Deadline 
				  pst.setString(3, Question); //sets value from string Question	 	 
				  pst.executeUpdate(); //execute query using preparedstatement object
		 
				  pst.close(); //closes the connection
				  
				  String sql2 = "insert into answers (Answer,idSurvey) values (? ,(SELECT max(idSurvey) FROM survey));"; //query for insertion into database. Inserting Answer,idSurvey. Reading idSurvey value from last insertion.
				PreparedStatement pst2 = connection.prepareStatement(sql2); //preparedstatement for database submit
			  for(int i=0; i<answer.length; i++)  //for loop. Counts the length of array "answer", and executes query until all of answers are submitted.
			  	{
			  
				  pst2.setString(1, answer[i]);
				  pst2.executeUpdate();
			  	}
			  pst2.close(); //closes the connection
					
			  out.println("Survey successfully created!"); //message for successful execution
			  out.println("<a href=\"http://localhost:8080/Login/survey_index.jsp\">Click here to get back</a>"); //button to get back on main page  	

		  
		  }
		  
		  
  catch(ClassNotFoundException e) //recovery if database driver cannot be loaded.
  {
	  out.println("<br>");
	  out.println("Couldn't load database driver: " 
	  + e.getMessage());
  }
  catch(SQLException e) //recovery if deadline is empty or there is an SQL problem.
  { 
	 
	  out.println("<br>");
	  out.println("SQLException caught: " 
	  + e.getMessage());
	  out.println("<br>");
	  out.println("<a href=\"http://localhost:8080/Login/survey_management.jsp\">Click here to get back</a>");	
  }
  catch (Exception e)
  {
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