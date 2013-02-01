<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
*{ margin: 0px; padding: 0px}
html, body{width: 100%; height: 100%;}
table{width: 100%; height: 100%;}
</style>
</head>
<body>
<table>
<jsp:include page="/ReadApp_msgServlet" flush="true" />
<tr>
	<td colspan="6" style="height: 20px;">
		<form action='Frame.jsp' method='get'>
		<input type="submit" value="Back" style="width: 100%;"/>
		</form>
	</td>
</tr>
</table>
</body>
</html>