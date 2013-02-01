<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html style="height: 100%; width: 100%;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
*{ margin: 0px; padding: 0px}
</style>
</head>
<body style="height: 100%; width: 100%;">
<div style="margin-right:10px; margin-left:10px;">
<form action="New_ProfAssimsgServlet" method="post" style="height: 100%; width: 100%;">
<table style="text-align: left; height: 100%; width: 100%;">
	<tr>
		<td rowspan="2">To:</td>
		<td style="height: 14px; width: 70px;">JMBAG:</td>
		<td style="height: 14px;"><input type="text" maxlength="10" size="50" name="jmbag" style="width: 100%;"/></td>
		<td rowspan="2" style="text-align: left;"><textarea rows="1" cols="240" style="resize: none; height: 100%; width: 100%; font-size: 14px;" onfocus="blur();">Chose the student on who's wall to post a message. For aid in finding the students JMBAG and team id use the find student application in the menu above.</textarea></td>
	</tr>
	<tr>
		<td style="height: 14px; width: 70px;">Team id:</td><td style="height: 14px;"><input type="text" maxlength="10" size="50" name="team" style="width: 100%;"/></td>
	</tr>
	<tr>
    	<td style="height: 50px; width: 30px; text-align: left;">Subject:</td><td colspan="3" style="height: 14px;"><input type="text" maxlength="50" size="50" name="subject" style="width: 100%;"/></td>
    </tr>
  
    <tr>
    	<td colspan="4" style="text-align: center; height: 14px;">Message content:</td>
    </tr>
    <tr>
    	<td colspan="4" style="text-align: center;"><textarea rows="1" cols="1" name="content" style="resize: none; height: 100%; width: 100%;"></textarea></td>
    </tr>
    <tr>
    	<td colspan="4" style="height: 20px;"><input type="submit" value="Send" style="width: 100%;"/></td>
    </tr>
    <%
   	if((request.getParameter("ret"))!=null){
   		%>
   		<tr>
    	<td colspan="4" style="text-align: center; height: 50px;">
    		<%out.print(request.getParameter("ret"));%>
    	</td>
    	</tr>
    	<%
   	}
	%>
</table>
</form>
</div>
</body>
</html>