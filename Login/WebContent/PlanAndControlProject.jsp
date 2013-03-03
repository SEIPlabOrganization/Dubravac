	
<!-- Pocetni jsp koji se poziva nakon klika na odgovarajuci link -->
<!-- za profesora -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' href='style.css' type='text/css'>
<title>Plan and control project</title>
</head>
<body>

	<!-- ukljucivanje servleta koji mora biti u slucaju kada student nemora biti u timu, tj. ispitivanje odredenih uvjeta -->
	<jsp:include page="/SecServlet" flush="false" />
	
	<!-- ukljucivanje servleta koji izvrsava ono sto se trazi -->
	<jsp:include page="/PlanAndControlProjectBasicSearchServlet" />
</body>
</html>