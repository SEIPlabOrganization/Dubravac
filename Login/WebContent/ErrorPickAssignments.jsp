<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/SecTeamServlet" flush="false" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error! Pick Assignments</title>
</head>
<body>
	<input type="button" value="Pick Assignment" onclick="window.location='/Login/PickAssignments.jsp';" />
	
	
	
	<table border="0">
	
	
		<tr><td><b>Error!!!<br/>Assignment already part of some project!<br/><b>Please pick another available project assignment</b></b></td></tr>
		
		
	</table>
</body>
</html>