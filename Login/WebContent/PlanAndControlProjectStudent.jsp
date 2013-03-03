
<!-- Pocetni jsp koji se poziva nakon klika na odgovarajuci link -->
<!-- za studenta -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' href='style.css' type='text/css'>
<title>Plan and control project</title>
</head>
<body>

	<!-- ukljucivanje servleta koji mora biti u slucaju kada student mora biti u timu, tj. ispitivanje odredenih uvjeta, npr. postoji li u sesiji id tima i studenta itd. -->
	<jsp:include page="/SecTeamServlet" flush="false" />
	
	<!-- ukljucivanje servleta koji izvrsava ono sto se trazi -->
	<jsp:include page="/PlanAndControlProjectServlet" />
</body>
</html>