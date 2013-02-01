<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="/SecTeamServlet" flush="false" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>New Project Assignments - Success!</title>
</head>
<body>
	
	
	<input type="button" value="Create New Project Assignment" onclick="window.location='/Login/NewProjectAssignments.jsp';" />
	
	
	
	<table border="0">
	
	
		<tr><td><b>You have successfully inserted new assignment into table!!</b></td></tr>
		
		
	</table>
</body>
</html>