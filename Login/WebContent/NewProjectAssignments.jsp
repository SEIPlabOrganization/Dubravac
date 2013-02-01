<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 	<jsp:include page="/SecTeamServlet" flush="false" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Create New Assignment</title>
</head>
<body>

	
	<h1>Create New Assignments</h1>
	
	
		<form action="CreateAssignServlet" method="POST">
			<table border="0" align="center">
				<tr><td align="right">Proposal Name:</td><td><input type="text" size="67" name="ImeZadatka"></td></tr>
				<tr><td align="right">Assignment Description:</td><td><textarea rows="4" cols="50" name="DescriptionZadatka"></textarea></td></tr>
				<tr><td align="right">Preparation for assignment and suggested readings:</td><td><textarea rows="4" cols="50" name="DescriptionZadatkaZaPripremu"></textarea></td></tr>
				<tr><td align="right"><input type="submit" value="Create!" /></td></tr>
			</table>
		</form>
	
	
</body>
</html>