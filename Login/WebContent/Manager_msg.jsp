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
<form action="New_ManagermsgServlet" method="post" style="height: 100%; width: 100%;">
<table style="text-align: left; height: 100%; width: 100%;">
	<tr>
		<td style="height: 14px; ">To:</td><td style="height: 14px;" align="left"><jsp:include page="/ManagerMsgSelectServlet" flush="true" /></td>
	</tr>
	<tr>
    	<td style="height: 50px; width: 30px; text-align: left;">Subject:</td><td style="height: 14px;"><input type="text" maxlength="50" size="50" name="subject" style="width: 100%;"/></td>
    </tr>
  
    <tr>
    	<td colspan="2" style="text-align: center; height: 14px;">Message content:</td>
    </tr>
    <tr>
    	<td colspan="2" style="text-align: center;"><textarea rows="1" cols="1" name="content" style="resize: none; height: 100%; width: 100%;"></textarea></td>
    </tr>
    <tr>
    	<td colspan="2" style="height: 20px;"><input type="submit" value="Send" style="width: 100%;"/></td>
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
</div>
</body>
</html>