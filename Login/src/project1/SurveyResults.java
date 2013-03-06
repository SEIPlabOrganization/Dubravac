package project1;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SurveyResults
 */
public class SurveyResults extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SurveyResults() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //Request - response method -> GET method.
		response.setContentType("text/html"); //response to the client is sent by text
		PrintWriter pw = response.getWriter(); 
		Connection con;
		Statement stmt; //initialization of statements.
		Statement stmt2;
		Statement stmt4;
		
		ResultSet rs; //initialization of result sets.
		ResultSet rs2;
		ResultSet rs4;
	
	
	
				pw.println("<HTML>"); //generating page
				pw.println("<BODY>");
				pw.println("<table>");
				pw.println("<tr>");
				pw.println("<td style=\"background-color:#0066FF; width:1000px; height:10px\">");
				pw.println("<h1 style=\"color:white\">");
				pw.println("Survey");
				pw.println("</h1>");				
				pw.println("</td>");
				pw.println("</tr>");
				pw.println("</table>");	
				
				float scounter= 0; //initialization of counters
				float acounter=0;
				
				String id = request.getParameter("id"); //requests parameter "id". Gets the id of survey, and saves it to string.
				String questionid = null; 
				String question = null; //initialization for question picker 
				String question2 = null; //initialization for question for results			
						try
						{
							String connectionURL = "jdbc:mysql://localhost/mydb"; //path for mysql connection.
							  
							Class.forName("com.mysql.jdbc.Driver"); // load the database driver.
							con = DriverManager.getConnection (connectionURL, "root", "root"); //connectionURL declared before and username/password for mysql.
							stmt = con.createStatement(); //initialization of statements
							stmt2 = con.createStatement();
							stmt4 = con.createStatement();
							pw.println("<h3>See results for question: </h3>"); //page generation 
							pw.println("<select name='id' form='question'>"); //drop down menu list
							rs4  = stmt4.executeQuery("SELECT idSurvey,Question from Survey;"); //query for drop down menu list
							pw.println("<option selected='" + questionid + "'>"); 
							while(rs4.next()) //get data from results set returned by jdbc
							{
								 question = rs4.getString("Question");  // question value from database
								 questionid = rs4.getString("idSurvey"); // question id value from database
								pw.println("<option value='" + questionid + "'>Question: " + questionid + " - "+ question + "</option>"); 
							}
							pw.println("</select>");
							pw.println("<br >");
							pw.println("<form action=\"SurveyResults\" id=\"question\">");
							pw.println("<br >");
							pw.println( "<input type=\"submit\" value=\"Open question\">");
							pw.println("</form>");
							
							
							if (id !="")  //checks if id is set. Opens a question if id is set.
							
							{
								rs = stmt.executeQuery("Select Question,SubmitCounter from Survey where idSurvey = "+ id + ""); //query for listing question and submit counter
								rs2=stmt2.executeQuery("Select Answer,AnswerCounter from Answers where idSurvey = "+ id + ""); //query for listing answer and answer counter
								
								while (rs.next()) //get data from results set returned by jdbc
						    	{
									 question2 = rs.getString("Question"); //value of selected question
						    		 scounter = rs.getInt("SubmitCounter"); //value of submit counter
						    	}
								String columnValue; //initialization for generating a table
								
								
								pw.println("<h3>Results for question:</h3>"); //generating a result report
								pw.println("<h4>"+ question2 +"</h4>"); //name of question
								
								pw.println("Number of total votes: " +(int) scounter +""); //total votes of that survey.Submit counter as integer value
								
								pw.println("<br/><br/>"); 
								pw.println("<table  width=\"300\" border='1'>"); //table generation
								pw.println("<tr>");
								pw.println("<th>");
								pw.println("Answer");
								pw.println("</th>");
								pw.println("<th>");
								pw.println("Number of votes");
								pw.println("</th>");
								pw.println("<th>");
								pw.println("Percent %");
								pw.println("</th>");
								pw.println("</tr>");
								while(rs2.next()) //get data from results set returned by jdbc
									{			columnValue = rs2.getString("Answer");
												acounter = rs2.getInt("AnswerCounter");
											   if (scounter == 0) // if scounter is 0 , percent value will be 0 instead of NaN
											   {
												   float percent = 0; 
												   pw.println("<tr>");	//table generation																			
													pw.println("<td align=\"center\">");
													pw.println(columnValue);
													pw.println("</td>");
													pw.println("<td align=\"center\">");
													pw.println(acounter);
													pw.println("</td>");
													pw.println("<td align=\"center\">");
													pw.println(percent);
													pw.println("</td>");
													pw.println("</tr>");	
											   }
											   else{ //for every other value of scounter, percent value will be calculated
												float percent =  (acounter / scounter)*100; //formula for calculation
												pw.println("<tr>");	  //table generation																				
												pw.println("<td align=\"center\">");
												pw.println(columnValue);
												pw.println("</td>");
												pw.println("<td align=\"center\">");
												pw.println(acounter);
												pw.println("</td>");
												pw.println("<td align=\"center\">");
												pw.println(percent);
												pw.println("</td>");
												pw.println("</tr>");	
											   }												
												
											
									}				
									
									pw.println("</table>");
							}
							pw.println("<input type=button onClick=\"location.href='http://localhost:8080/Login/survey_index.jsp'\" value='Go back'>");
							pw.println("</BODY>");
							pw.println("</HTML>");
							
							
						}
						catch (Exception e)
						{
						pw.println(e);
						}
					pw.println("</BODY>");
					pw.println("</HTML>");
				}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
