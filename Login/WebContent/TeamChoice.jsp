<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	table{ margin: auto; position: absolute; top: 100px; bottom: 0px; left: 0px; right: 0px; }
	td{ padding-left: 20px;}
	a{ font: bold 16px Tahoma; text-decoration: none;}
	a:link, a:visited{ color: Blue;}
	a:hover{ color: Navy;}
	h2{font: Tahoma;}
</style>
</head>
<body>
	<h2>Chose team:</h2>
	<jsp:include page="/TeamChoiceServlet" flush="false" />
</body>
</html>