<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<jsp:include page="/SecTeamServlet" flush="false" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Create New Project</title>




		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js" type="text/javascript"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/jquery-ui.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function()
			{
				$("#date_picker").datepicker();
			});
			$(function()
			{
				$("#date_picker2").datepicker();
			});
		</script>




</head>
<body>
	

		<form action="NewProjectServlet" method="POST">
			<table id="tableID" border="0" align="center">
				<tr><td colspan="2"><h2>Create New Project:</h2></td></tr>
				
				<tr><td align="right">Project name:</td><td><input type="text" size="67" name="ProjectName"></td></tr>
				<tr><td align="right">Project Description:</td><td><textarea rows="4" cols="50" name="ProjectDescription"></textarea></td></tr>
				<tr><td align="right">Project start date(format: yyyy-mm-dd)</td><td><input type="text" id="date_picker" name="DateBegin"></td></tr>
				<tr><td align="right">Project end date(format: yyyy-mm-dd)</td><td><input type="text" id="date_picker2" name="DateEnd"></td></tr>
				<tr><td align="right">Subject</td><td><input type="text" size="67" value="Software Engineering" name="Subject"></td></tr>
				
				
				<tr><td align="right"><input type="submit" value="Create!" /></td></tr>
			</table>
			
		
		</form>
		
	
</body>
</html>