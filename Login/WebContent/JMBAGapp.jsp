<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JMBAG App</title>
<jsp:include page="/SecServlet" flush="false" />
<style type="text/css">
td{padding-left: 15px; padding-right: 15px;}
</style>
</head>
<body>
	<form action="JMBAGapp.jsp" method="post">
	<table style="text-align:left">
	<tr>
		<td>Name:</td><td><input type="text" name="name"/></td>
	</tr>
	<tr>
		<td>Surname:</td><td><input type="text" name="surname"/></td>
	</tr>
	<tr><td colspan="2">
		<input type="submit" value="Search" style="width:100%"/>
	</td></tr>
	</table>
	</form>
<jsp:include page="/JMBAGappServlet" flush="true" />
</body>
</html>