<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="UserPage.css" />

<jsp:include page="/RegisterServlet" flush="false" />
<jsp:include page="/SecServlet" flush="false" />

<script type="text/javascript">
	function Def() {
		document.getElementById("des").innerHTML="Tool Description";
	}
    function Createteam() {
    	document.getElementById("des").innerHTML="Tool for creating a team";
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
							<a href="CreateTeam.jsp" target="tools" onmouseover="Createteam()" onmouseout="Def()">Create new team</a>
						</td>
					</tr>
					
					
					
					<tr height="100px" >
						<td style="text-align: center;">
							<textarea rows="5" cols="22" id="des" onfocus="blur();"></textarea>
						</td>
					</tr>
				</table>
			</td>
			<td id="user_info">
			<jsp:include page="/UserInfoServlet" flush="true" />
			</td>
			<td id="msg_app">
			<iframe height="100%" width="100%" frameborder="0"></iframe>
			</td>
		</tr>
	</table>
</body>
</html>