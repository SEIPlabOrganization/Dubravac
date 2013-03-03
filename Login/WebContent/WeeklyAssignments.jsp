<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<!-- Pocetni jsp koji se poziva nakon klika na odgovarajuci link -->
	
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Weekly Assignments</title>
</head>
<body>
	<!-- ukljucivanje servleta koji mora biti u slucaju kada student mora biti u timu, tj. ispitivanje odredenih uvjeta, npr. postoji li u sesiji id tima i studenta itd. -->
	<jsp:include page="/SecTeamServlet" flush="false" />
	
	<!-- ukljucivanje servleta koji izvrsava ono sto se trazi -->
	<jsp:include page="/WeeklyAssignmentsFirstServlet" />
</body>
</html>