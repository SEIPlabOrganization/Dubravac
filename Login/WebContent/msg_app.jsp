<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh" content="60; URL=http://localhost:8080/Login/msg_app.jsp">
<link rel="stylesheet" href="msg_app.css" />
<jsp:include page="/SecServlet" flush="false" />
</head>
<body>
<div style="text-align:center;">
<h2>Unread Messages</h2>
</div>
<br/>
<div id="main">
<jsp:include page="/MsgAppServlet" flush="true" />
</div>
</body>
</html>