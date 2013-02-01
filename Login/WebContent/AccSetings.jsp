<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="MsgPage.css" />
<jsp:include page="/SecServlet" flush="false" />
<script type="text/javascript">
	function Def() {
		document.getElementById("des").innerHTML="Tool Description";
	}
    function Tool1() {
    	document.getElementById("des").innerHTML="Tool1 info";
    }
    function Tool2() {
   		document.getElementById("des").innerHTML="Tool2 info";
   	}
</script>
</head>
<body onload="Def()">
	<table>
		<tr>
			<td id="tool_list">
				<table>
					<tr>
						<td>
							<a href="ChangePass.jsp" target="setwin" onmouseover="Tool1()" onmouseout="Def()">Change password</a><br/><br/>
							<a href="EmailSettings.jsp" target="setwin" onmouseover="Tool2()" onmouseout="Def()">E-mail settings</a><br/><br/>
							<%
							if(((String) session.getAttribute("userrole")).equalsIgnoreCase("Stud")){
								%><%
							}
							%>
							
						</td>
					</tr>
					
					<tr height="100px" >
						<td style="text-align: center;">
							<textarea rows="5" cols="22" id="des" onfocus="blur();"></textarea>
						</td>
					</tr>
				</table>
			</td>
			
			<td id="msg_app">
			<iframe name="setwin" height="100%" width="100%" frameborder="0"></iframe>
			</td>
		</tr>
	</table>
</body>
</html>