<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<!doctype html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home Page</title>
	<script type="text/javascript">
	<!--
		if (top.location!= self.location) {
			top.location = self.location.href
		}
		if(document.URL!="http://localhost:8080/Login/Frame.jsp")
			location.href="http://localhost:8080/Login/Frame.jsp";
		
		function myPopup() {
		window.open( "JMBAGapp.jsp", "Find JMBAG", 
				"height = 300, width = 300, location=no,menubar=no,directories=no,toolbar=no,status=no,personalbar=no,titlebar=no,resizeable=ye s,scrollbars=yes,dependant=yes,dialog=yes")
		}
	//-->
	</script>
	<link rel="stylesheet" href="Frame.css" />
	<jsp:include page="/SecServlet" flush="false" />
	<jsp:include page="/RegisterServlet" flush="false" />
</head>
<body>
	<div id="main_wrapper">
		
		<header id="top_header">
		<img src="http://www.riteh.uniri.hr/images/logo1.gif" />
		<img src="http://www.riteh.uniri.hr/images/logo2.gif" />
		<h1>SPtool</h1>
		</header>
		
		<jsp:include page="/FrameServlet" flush="true" />

		<footer id="bottom_footer">
		info
		</footer>
		
	</div>
</body>
</html>
