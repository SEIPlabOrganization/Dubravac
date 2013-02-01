<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>

<html>
<head>
<link rel="stylesheet" href="LogIn.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
*{ margin: 0px; padding: 0px}
body, html{ width: 100%; height: 100%; background-color:#5BD4FF; text-align: center; }
</style>
<script type="text/javascript">
	<!--
		if (top.location!= self.location) {
			top.location = self.location.href
		}
		if(document.URL!="http://localhost:8080/Login/LogIn.jsp"){
			temp=document.getElementById("ret").innerHTML;
			location.href="http://localhost:8080/Login/LogIn.jsp"+"ret="+temp;
		}
	//-->
	</script>
<jsp:include page="/UnsetSessionServlet" flush="true" />
</head>
<body>
<div style="position:absolute; margin: 0px auto; text-align: center; border: 0px solid black; width: 100%; padding-top: 50px;">
	<h1 style="font: bold 30px Tahoma;">SPtool LogIn</h1> 
</div>
<form method="POST" action="LoginServlet">
<table style="width:100% ; padding-top: 200px;">
<tr><td align="center">
	
	<div style="background-color: white; width: 400px; margine-top: -100px; padding-top: 10px; padding-bottom: 10px; box-shadow: black 4px 4px 5px;">
	<table style="text-align: right;">
		
		<tr>
			<td style="height: 20px; text-align: left; ">User Name:</td>
			<td style="height: 20px; text-align: left; "><input type="text" name="username" /></td>
		</tr> 
		<tr>
			<td style="height: 20px; text-align: left; ">Password:</td>
			<td style="height: 20px; text-align: left; "><input type="password" name="password" /></td>
		</tr>
		<tr>
			<td colspan="2" style="height: 20px; text-align: right; "><input type="submit" value="Login" style="width: 100%;"/></td>
		</tr> 	
    </table>
    </div>
    <%
   	if((request.getParameter("ret"))!=null){
   		%>
   		<table style="text-align: center;">
   		<tr>
    	<td colspan="3" style="text-align: center; height: 50px;" id="ret">
    		<%out.print(request.getParameter("ret"));%>
    	</td>
    	</tr>
    	</table>
    	<%
   	}
	%>
</td></tr>
</table>
</form>
</body>
</html>