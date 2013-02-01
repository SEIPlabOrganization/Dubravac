<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/SecTeamServlet" flush="false" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add members:</title>
</head>
<body>

	<h1>Add members into your team:</h1>
		
		
		
		<form action="MoreMembersServlet" method="POST">
			<table id="tableID" border="0" align="center">
			
				<tr><td>Member Responsibility:</td><td><jsp:include page="/ResponsibilitiesServlet" /></td></tr>
				<tr><td>Member JMBAG:</td><td><input type="text" size="67" name="JMBAGMember" /></td></tr>
			
			
		
		<% if((request.getParameter("ret"))!=null){ %>
				<tr><td colspan="2" style="text-align: center; height: 50px;" id="ret">
		<%	out.print(request.getParameter("ret"));  %>
				</td></tr>
		<% } %>
		
	
			</table>
			<input type="submit" value="Add!" />		
		</form>
		

</body>
</html>