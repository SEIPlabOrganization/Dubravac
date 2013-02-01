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
public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		Connection con;
		Statement stmt;
		Statement stmt2;
		Statement stmt3;
		Statement stmt4;
		
		ResultSet rs;
		ResultSet rs2;
		ResultSet rs3;
		ResultSet rs4;
	
	
	
				pw.println("<HTML>");
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
				
				
	String type = null;
	String id = req.getParameter("id");
	String questionid = null;
	String question = null;
					
			try
			{
				String connectionURL = "jdbc:mysql://localhost:3306/mydb";
				  
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection (connectionURL, "root", "root"); 
				stmt = con.createStatement();
				stmt2 = con.createStatement();
				stmt3 = con.createStatement();
				stmt4 = con.createStatement();
				pw.println("<h3>Avaliable questions: </h3>");
				pw.println("<select name='id' form='question'>");
				rs4  = stmt4.executeQuery("SELECT idSurvey,Question from Survey;");
				pw.println("<option selected='" + questionid + "'>");
				while(rs4.next())
				{
					 question = rs4.getString("Question");
					 questionid = rs4.getString("idSurvey");
					pw.println("<option value='" + questionid + "'>Question: " + questionid + " - "+ question + "</option>");
				}

				pw.println("</select>");
				pw.println("<br >");
				pw.println("<form action=\"Survey\" id=\"question\">");
				pw.println("<br >");
				pw.println( "<input type=\"submit\" value=\"Open question\">");
				pw.println("</form>");
				
				if (id !="")
				
				{
					rs4.close();
					rs = stmt.executeQuery("Select Answer from answers where idSurvey = "+ id + "");
					rs2=stmt2.executeQuery("Select Question from survey where idSurvey = "+ id + "");
				    rs3=stmt3.executeQuery("Select Type from survey where idSurvey = "+ id + "");
				    
		
				    	while (rs3.next())
				    	{
				    		type = rs3.getString(1);
				    	}
			
					
					pw.println("<h3>Question:</h3>");
					String value;
					while(rs2.next())
					{
						pw.println("<h4>");
						value = rs2.getString(1);
						pw.println(value);
						pw.println("</h4>");
					}
					String columnValue;
					ResultSetMetaData r = (ResultSetMetaData) rs.getMetaData();
					int numofcol = r.getColumnCount();
					
					
					pw.println("<table  width=\"300\">");
					pw.println("<form name\"myform\" action=\"Submit\" method=\"get\">");
					while(rs.next())
						{			
								for (int j = 1 ; j <= numofcol; j++)
								{
									columnValue = rs.getString(j);
									pw.println("<tr>");
									pw.println("<td>");				
									pw.println(columnValue);
									if (type.equalsIgnoreCase("single"))
										{
											pw.println("<td>");
											pw.println("<input type=\"radio\" name=\"radio\" value=\""+ columnValue +"\">");
											pw.println("</td>");
										}
									else if (type.equalsIgnoreCase("multi")) 
										{
											pw.println("<td>");
											pw.println("<input type=\"checkbox\" name=\"checkbox\" value=\""+ columnValue +"\">");
											pw.println("</td>");	
										}	
								}
						
								pw.println("</td>");
								pw.println("</tr>");
						}				
						pw.println("<tr>");
						pw.println("<td>");
						pw.println("<input type=\"hidden\" name=\"id\" value=\"" + id + "\"/>");
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

