package project1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Survey extends HttpServlet{
private static final long serialVersionUID = 1L;

public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException //Request - response method -> GET method.
	{
		res.setContentType("text/html");  //response to the client is sent by text.
		PrintWriter pw = res.getWriter();
		Connection con; 
		Statement stmt; //initialization of statements.
		Statement stmt2;
		Statement stmt3;
		Statement stmt4;
		
		ResultSet rs; //initialization of result sets.
		ResultSet rs2;
		ResultSet rs3;
		ResultSet rs4;
	
	
	
				pw.println("<HTML>"); //page generation
				pw.println("<BODY>");
				pw.println("<HEAD>");
				
				
				pw.println("</HEAD>");
				pw.println("<table>");
				pw.println("<tr>");
				pw.println("<td style=\"background-color:#0066FF; width:1000px; height:10px\">");
				pw.println("<h1 style=\"color:white\">");
				pw.println("Survey");
				pw.println("</h1>");				
				pw.println("</td>");
				pw.println("</tr>");
				pw.println("</table>");
				
				
	String type = null; 
	String id = req.getParameter("id"); //requests parameter "id", and saves it to string. 
	String questionid = null; 
	String question = null;
					
			try
			{
				String connectionURL = "jdbc:mysql://localhost/mydb"; //path for mysql connection.
				  
				Class.forName("com.mysql.jdbc.Driver"); // Load the database driver
				con = DriverManager.getConnection (connectionURL, "root", "root"); //connectionURL declared before and username/password for mysql.
				stmt = con.createStatement(); //statement creation
				stmt2 = con.createStatement();
				stmt3 = con.createStatement();
				stmt4 = con.createStatement();
				pw.println("<h3>Avaliable questions: </h3>"); 
				pw.println("<select name='id' form='question'>");
				rs4  = stmt4.executeQuery("SELECT idSurvey,Question from Survey;"); //query for all questions in database
				pw.println("<option selected='" + questionid + "'>"); //list of questions in database
				while(rs4.next()) //get data from results set returned by jdbc
				{
					 question = rs4.getString("Question");
					 questionid = rs4.getString("idSurvey");
					pw.println("<option value='" + questionid + "'>Question: " + questionid + " - "+ question + "</option>"); //drop down menu list 
				}

				pw.println("</select>");
				pw.println("<br >");
				pw.println("<form action=\"Survey\" id=\"question\">");
				pw.println("<br >");
				pw.println( "<input type=\"submit\" value=\"Open question\">"); //button for opening a question from list
				pw.println("</form>");
				
				if (id !="") //checks if id is set. Opens a question if id is set.
				
				{
					rs4.close();
					rs = stmt.executeQuery("Select Answer from answers where idSurvey = "+ id + ""); //query for listing all answers
					rs2=stmt2.executeQuery("Select Question from survey where idSurvey = "+ id + ""); //query for listing questions
				    rs3=stmt3.executeQuery("Select Type from survey where idSurvey = "+ id + ""); //query for selecting type
				    
		
				    	while (rs3.next()) //get data from results set returned by jdbc
				    	{
				    		type = rs3.getString(1); //type value
				    	}
			
					
					pw.println("<h3>Pitanje:</h3>"); 
					String value;
					while(rs2.next())  //get data from results set returned by jdbc
					{
						pw.println("<h4>"); 
						value = rs2.getString(1); //question value 
						pw.println(value);
						pw.println("</h4>");
					}
					String columnValue;
					ResultSetMetaData r = (ResultSetMetaData) rs.getMetaData();
					int numofcol = r.getColumnCount(); 
					
					
					pw.println("<table  width=\"300\">");
					pw.println("<form name\"myform\" action=\"Submit\" method=\"get\">");
					while(rs.next()) //get data from results set returned by jdbc
						{			
								for (int j = 1 ; j <= numofcol; j++) //generating survey (rows and columns)
								{
									columnValue = rs.getString(j); //answers
									pw.println("<tr>");
									pw.println("<td>");				
									pw.println(columnValue); //answer value generated and displayed to user
									if (type.equalsIgnoreCase("single")) //if survey is single choice ...
										{
											pw.println("<td>");
											pw.println("<input type=\"radio\" name=\"radio\" value=\""+ columnValue +"\">"); //... input type will be radio
											pw.println("</td>");
										}
									else if (type.equalsIgnoreCase("multi")) //if survey is multiple choice ...
										{
											pw.println("<td>");
											pw.println("<input type=\"checkbox\" name=\"checkbox\" value=\""+ columnValue +"\">");//... input type is checkbox
											pw.println("</td>");	
										}	
								}
						
								pw.println("</td>");
								pw.println("</tr>");
						}				
						pw.println("<tr>");
						pw.println("<td>");
						pw.println("<input type=\"hidden\" name=\"id\" value=\"" + id + "\"/>"); //submits id if user want to vote for some other question
						pw.println("<input type=\"submit\" value=\"Submit\"/>");
						pw.println("<input type=button onClick=\"location.href='http://localhost:8080/Login/survey_index.jsp'\" value='Go back'>");
						pw.println("</form>");
						pw.println("</tr>");
						pw.println("</td>");
						pw.println("</table>");
						pw.println("</br>");
						pw.println("</BODY>");
						pw.println("</HTML>");
					}
			
			}
		catch (Exception e)
				{
				pw.println(e);
				}
			pw.println("</BODY>");
			pw.println("</HTML>");
		}
		
		}

