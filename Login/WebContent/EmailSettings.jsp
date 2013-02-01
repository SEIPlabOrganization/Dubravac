<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="width: 100%; height: 100%; -moz-box-sizing: border-box; margin-top: -8px; padding-top: 8px; margin-left: -7px; padding-left: 7px;">
<head>
<style type="text/css">
*{ margin: 0px; padding: 0px}
</style>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
  
  <script>
  $(document).ready(function() {
    $("#tabs").tabs();
  });
  </script>
</head>
<body style="font-size:62.5%; width: 100%; height: 100%;">
  
<div id="tabs" style="width: 100%; height: 100%;">
    <ul>
        <li><a href="#fragment-1">Settings</a></li>
        <li><a href="#fragment-2">Change E-mail address</a></li>
    </ul>
    <div id="fragment-1" style="width: 100%; font-size:17px; -moz-box-sizing: border-box; margin-left: -50px; padding-left: 50px;">
	<table style="width: 100%; padding-top: 50px;">
		<tr>
			<td align="center">
				<form id="form" action="EmailSettingsServlet" method="post">
				<table style="padding-top: 50px;">
					<tr>
				    	<td colspan="2" style="text-align: center; height: 50px;">
				    		Recive E-mail notification on:
				    	</td>
				    </tr>
					<jsp:include page="/EmailSettingsServlet" flush="true" />
					<%if((request.getParameter("ret"))!=null){
				   		%>
				   		<tr>
				    	<td colspan="2" style="text-align: center; height: 50px;">
				    		<%out.print(request.getParameter("ret"));%>
				    	</td>
				    	</tr>
						<%
					}%>
				</table>
				<input type="hidden" value="yes" name="is"/>
				</form>
			</td>
		</tr>
	</table>
    </div>
    <div id="fragment-2" style="width: 100%; font-size:17px; -moz-box-sizing: border-box; margin-left: -50px; padding-left: 50px;">
        <table style="width: 100%; padding-top: 50px;">
		<tr>
			<td align="center">
				<form action="EmailChangeServlet" method="post">
				<table style="padding-top: 50px;">
					<tr>
						<td style="text-align: left;">New E-mail address:</td><td><input type="text" name="newemail" autocomplete="off"/></td>
					</tr>	
					<tr>
						<td style="text-align: left;">Repeat new address:</td><td><input type="text" name="repeatemail" autocomplete="off"/></td>
					</tr>
					<tr>	
						<td colspan="2" style="height: 20px;"><input type="submit" value="Change E-mail address" style="width: 100%;"/></td>	
					</tr>
					<%if((request.getParameter("ret"))!=null){
				   		%>
				   		<tr>
				    	<td colspan="2" style="text-align: center; height: 50px;">
				    		<%out.print(request.getParameter("ret"));%>
				    	</td>
				    	</tr>
						<%
					}%>
				</table>
				</form>
			</td>
		</tr>
	</table>
    </div>
</div>
</body>
</html>