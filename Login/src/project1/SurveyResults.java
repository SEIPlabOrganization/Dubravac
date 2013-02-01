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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Connection con;
		Statement stmt;
		Statement stmt2;
		Statement stmt4;
		
		ResultSet rs;
		ResultSet rs2;
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
				
				float scounter= 0;
				float acounter=0;
				
				String id = request.getParameter("id");
				String questionid = null;
				String question = null;
				String question2 = null;				
						try
						{
							String connectionURL = "jdbc:mysql://localhost/mydb";
							  
							Class.forName("com.mysql.jdbc.Driver");
							con = DriverManager.getConnection (connectionURL, "root", "root"); 
							stmt = con.createStatement();
							stmt2 = con.createStatement();
							stmt4 = con.createStatement();
							pw.println("<h3>See results for question: </h3>");
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
							pw.println("<form action=\"SurveyResults\" id=\"question\">");
							pw.println("<br >");
							pw.println( "<input type=\"submit\" value=\"Open question\">");
							pw.println("</form>");
							
							
							if (id !="")
							
							{
								rs = stmt.executeQuery("Select Question,SubmitCounter from Survey where idSurvey = "+ id + "");
								rs2=stmt2.executeQuery("Select Answer,AnswerCounter from Answers where idSurvey = "+ id + "");
								
								while (rs.next())
						    	{
									 question2 = rs.getString("Question");
						    		 scounter = rs.getInt("SubmitCounter");
						    	}
								String columnValue;
								
								
								pw.println("<h3>Results for question:</h3>");
								pw.println("<h4>"+ question2 +"</h4>");
								
								pw.println("Number of total votes: " +(int) scounter +"");
								
								pw.println("<br/><br/>");
								pw.println("<table  width=\"300\" border='1'>");
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
								while(rs2.next())
									{			columnValue = rs2.getString("Answer");
												acounter = rs2.getInt("AnswerCounter");
											   if (scounter == 0)
											   {
												   float percent = 0;
												   pw.println("<tr>");																						
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
											   else{
												float percent =  (acounter / scounter)*100;
												pw.println("<tr>");																						
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
