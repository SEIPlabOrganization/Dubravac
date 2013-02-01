<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/SecTeamServlet" flush="false" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
</head>
<body>
	
	
	<input type="button" value="Create Team" onclick="window.location='/Login/CreateTeam.jsp';" />
	
	
	
	<table border="0">
	
	
		<tr><td><b>Error!!!<br/>Two posible errors!<br/>Team name already exists (change your team name)!<br/>You can't create another team!  (One TeamLeader can't have more then one team)</b></td></tr>
		
		
	</table>
</body>
</html>