<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

	<!-- Pocetni jsp koji se poziva nakon klika na odgovarajuci link -->
	
	<title>Projects Overview</title>
	<link rel='stylesheet' href='ProjectsOverview.css' />
	<link href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css' rel='stylesheet' type='text/css'/>
	<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js'></script>
	<script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js'></script>
</head>
<body style='font-size:62.5%;'>

	<!-- ukljucivanje servleta koji mora biti u slucaju kada student mora biti u timu, tj. ispitivanje odredenih uvjeta, npr. postoji li u sesiji id tima i studenta itd. -->
	<jsp:include page="/SecTeamServlet" flush="false" />
	
	<!-- ukljucivanje servleta koji izvrsava ono sto se trazi -->
	<jsp:include page="/ProjectsOverviewServlet" />
</body>
</html>