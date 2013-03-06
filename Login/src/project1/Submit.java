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


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //Request - response method -> GET method.
		 String connectionURL = "jdbc:mysql://localhost/mydb"; //path for mysql connection.
		 Connection connection=null;	  
		  response.setContentType("text/html"); //response to the client is sent by text.
		  PrintWriter out = response.getWriter();  
		  String radio = request.getParameter("radio"); //requests parameter "radio", and saves it to string. If user selects single choice survey (upon creation) value radio will be filled.
		  String[] checkbox = request.getParameterValues("checkbox"); //requests parameter "checkbox". Multiple choice answers. Saves all values that are checked in array.
		  String survey_id = request.getParameter("id"); //requests parameter "id". Gets the id of survey, and saves it to string.
		  int id = Integer.parseInt(survey_id); //converts string above to integer.
		  int acounter = 0; //initialization of answer counter -> counts "clicks" on answers.
		  int scounter = 0; //initialization of submit counter -> counts submits when survey is filled.
			Statement stmt; //initialization of statements.
			Statement stmt2;
			ResultSet rs; //initialization of result sets.
			ResultSet rs2;
			
		  try 
		  {
			  
			  Class.forName("com.mysql.jdbc.Driver");  // load the database driver.
			  
			  connection = DriverManager.getConnection
			  (connectionURL, "root", "root");  //connectionURL declared before and username/password for mysql.
			  stmt = connection.createStatement();  //statement for query
			  stmt2 = connection.createStatement();
			  
			if (radio != null) //execution only if survey type is single choice
			  {
			  rs = stmt.executeQuery("SELECT AnswerCounter FROM answers where Answer= '"+ radio +"' AND idSurvey = "+ id +";"); //query for database, returns result set by jdbc. Gets status of Answer counter
			  rs2 = stmt2.executeQuery("SELECT SubmitCounter FROM survey where idSurvey = "+ id +";"); //query for database, returns result set by jdbc. Gets status of Submit counter.
			  
				 while (rs.next()) //get data from results set returned by jdbc
				 {
					 acounter = rs.getInt("AnswerCounter"); //answer counter value
					 
				 }
				 while (rs2.next()) //get data from results set returned by jdbc
				 {
					 scounter=rs2.getInt(1); //submit counter value
				 }
				 acounter++; //increment of answer counter
				 scounter++; //increment of submit counter
				 String sql = "UPDATE answers SET AnswerCounter = "+ acounter + " WHERE Answer = '"+ radio +"' AND idSurvey ="+ id + ";"; //updates the answer counter in database
				 PreparedStatement pst = connection.prepareStatement(sql); 
				 pst.executeUpdate(); //executes updates to database
				 pst.close();	 //closes connection
				 String sql2 = "UPDATE survey SET SubmitCounter = "+ scounter + " WHERE idSurvey ="+ id +";"; //updates the submit counter in database 
				 PreparedStatement pst2 = connection.prepareStatement(sql2);
				 pst2.executeUpdate();//executes updates to database
				 pst2.close(); //closes connection		
				 out.println("You have successfully voted for answer: "+ radio +"!"); //message for voting with answer
				 out.println("<br/>");
				 out.println("Thank You");
				 out.println("<a href=\"http://localhost:8080/Login/survey_index.jsp\">Click here to get back</a>");	
			  }
			  else { //execution if survey type is other than single choice (multiple choice)
				  for(int i=0; i<checkbox.length; i++) //for loop. Counts the length of array "checkbox", and executes query until all of checked answers are submitted.
				  {
				  rs = stmt.executeQuery("SELECT AnswerCounter FROM answers where Answer= '"+ checkbox[i] +"' AND idSurvey = "+ id +";");  //query for database, returns result set by jdbc. Gets status of Answer counter
				  rs2 = stmt2.executeQuery("SELECT SubmitCounter FROM survey where idSurvey = "+ id +";"); //query for database, returns result set by jdbc. Gets status of Submit counter.
							  while (rs.next())  //get data from results set returned by jdbc
							  {
								 acounter = rs.getInt("AnswerCounter"); //answer counter value
								
							  }
							  while (rs2.next())  //get data from results set returned by jdbc
							  {
								 scounter=rs2.getInt(1); //submit counter value
							  }
						 acounter++;  //increment of answer counter
						 scounter++; //increment of submit counter
						 String sql = "UPDATE answers SET AnswerCounter = "+ acounter + " WHERE Answer = '"+ checkbox[i] +"' AND idSurvey ="+ id +";";  //updates the answer counter in database
						 PreparedStatement pst = connection.prepareStatement(sql);
						 pst.executeUpdate(); //executes updates to database
						 pst.close();	//closes connection
						 out.println("You have successfully voted for answer: "+ checkbox[i] +"!"); //message for voting with answer
						 out.println("<br>");
				  	
				  		 
						 String sql2 = "UPDATE survey SET SubmitCounter = "+ scounter + " WHERE idSurvey ="+ id +";"; //updates the submit counter in database 
						 PreparedStatement pst2 = connection.prepareStatement(sql2);
						 pst2.executeUpdate(); //executes updates
						 pst2.close();	//closes connection		
				  }
						 out.println("<br/>");
						 out.println("Thank You");
						 out.println("<a href=\"http://localhost:8080/Login/survey_index.jsp\">Click here to get back</a>");	
				  			  
			  }
		  }
		  
  catch(ClassNotFoundException e) //SQL recovery
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
	




