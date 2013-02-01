<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
*{ margin: 0px; padding: 0px}
html, body{width: 100%; height: 100%;}
table{width: 100%; height: 100%;}
</style>
<jsp:include page="/ReadArchive_msgServlet" flush="true" />
</head>
<body>
<table>
<tr>
	<td style="width: 80px; height: 30px;">From:</td><td><%=(String) request.getParameter("from")%></td><td style="width: 100px; height: 30px;">Recived on:</td><td style="width: 100px;"><%=(String) request.getParameter("date")%></td><td style="width: 40px; height: 30px; text-align: right;">At:</td><td style="width: 100px;"><%=(String) request.getParameter("time")%></td>
</tr>
<tr>
	<td style="width: 80px; height: 30px;">Subject:</td><td colspan="5"><textarea rows="1" cols="1" name="content" style="resize: none; width: 100%; height: 25px; font-size: 16px; vertical-align: middle;" onfocus="blur();" spellcheck='false'><%=(String) request.getParameter("subject")%></textarea></td>
</tr>
<tr>
<td style="height: 40px; text-align: center;" colspan="6">Message content:</td>
</tr>
<tr>
	<td colspan="6"><textarea rows="1" cols="1" name="content" style="resize: none; width: 100%; height: 100%; font-size: 16px; vertical-align: middle;" onfocus="blur();" spellcheck='false'><%=(String) request.getParameter("content")%></textarea></td>
</tr>
<tr>
	<td colspan="6" style="height: 20px;">
		<form action='NotificationArchive_msg.jsp' method='get'>
		<input type='hidden' value='<%=(String) request.getParameter("aranger")%>' name='aranger'/>
		<input type='hidden' value='<%=(String) request.getParameter("sub")%>' name='sub'/>
		<input type='hidden' value='<%=(String) request.getParameter("author")%>' name='author'/>
		<input type="submit" value="Back" style="width: 100%;"/>
		</form>
	</td>
</tr>
</table>
</body>
</html>