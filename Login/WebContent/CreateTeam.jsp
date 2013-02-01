<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/SecServlet" flush="false" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Team</title>
</head>
<body>
	<div id="idDiv">
	
	
	<form action="CreateTeamServlet" method="POST">
	<table id="tableID" border="0" align="center">
	
		<tr><td colspan='2'><h2>Create new team:</h2></td></tr>
		<tr><td>Team name:</td><td><input type="text" size="20" name="TeamName"></td></tr>
		
		<tr><td colspan='2'><input style='width:100%' type="submit" value="Create!" /></td></tr>
	</table>
	</form>
	
	
	</div>
</body>
</html>