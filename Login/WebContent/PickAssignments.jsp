<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/SecTeamServlet" flush="false" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pick Assignment</title>
</head>
<body>
	<div align="center">
		<jsp:include page="/AssignmentsViewServlet" />
		
		<h2>Pick Assignment for your project:</h2>
		
		
		<form action="AsignProjectAssignmentServlet" method="POST">
			<table id="tableID" border="0" align="center">
				<tr><td align="right">Project Name:</td><td><input type="text" size="67" name="ProjectName"></td></tr>
				<tr><td align="right">Select Assignment for your project(input Project Assignment id):</td><td><input type="text" size="67" name="ProjectAssignmentId"></td></tr>
				<tr><td align="right"><input type="submit" value="Pick!" /></td></tr>
			</table>
		</form>
		
		
		
		
	</div>
</body>
</html>