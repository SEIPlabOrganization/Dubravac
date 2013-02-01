<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
*{ margin: 0px; padding: 0px}
</style>
</head>
<body style="height: 100%; width: 100%;">
<div style="margin-right:10px; margin-left:10px;">
<table style="width: 100%; padding-top: 50px;">
	<tr>
		<td align="center">
			<form action="ChangePassServlet" method="post">
			<table>
				<tr>
					<td style="text-align: left;">Old password:</td><td><input type="password" name="oldpassword"/></td>
				</tr>
				<tr>
					<td style="text-align: left;">New password:</td><td><input type="password" name="newpassword"/></td>
				</tr>	
				<tr>
					<td style="text-align: left;">Repeat new password:</td><td><input type="password" name="repeatpassword"/></td>
				</tr>
				<tr>	
					<td colspan="2" style="height: 20px;"><input type="submit" value="Change password" style="width: 100%;"/></td>	
				</tr>
				<%
			   	if((request.getParameter("ret"))!=null){
			   		%>
			   		<tr>
			    	<td colspan="2" style="text-align: center; height: 50px;">
			    		<%out.print(request.getParameter("ret"));%>
			    	</td>
			    	</tr>
			    	<%
			   	}
				%>
			</table>
			</form>
		</td>
	</tr>
</table>

</div>
</body>
</html>