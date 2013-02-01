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
    function Tool1() {
    	document.getElementById("des").innerHTML="Add one or more students to the data base";
    }
    function ProjectsOverview() {
   		document.getElementById("des").innerHTML="Review of projects progress";
   	}
    function PlanAndControlProject() {
   		document.getElementById("des").innerHTML="Project details, project plan form";
   	}
    function Survey() {
   		document.getElementById("des").innerHTML="Surveys for students, professors";
   	}
    function ProjectAssignmentOverview() {
    	document.getElementById("des").innerHTML="List of all project assignments";
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
							<a href="InputUsers.jsp" target="tools" onmouseover="Tool1()" onmouseout="Def()">Add Students</a><br/><br/>
							<a href="ProjectsOverviewProf.jsp" target="tools" onmouseover="ProjectsOverview()" onmouseout="Def()">Projects Overview</a><br/><br/>
							<a href="PlanAndControlProject.jsp" target="tools" onmouseover="PlanAndControlProject()" onmouseout="Def()">Plan project</a><br/><br/>
							<a href="survey_index.jsp" target="tools" onmouseover="Survey()" onmouseout="Def()">Survey</a><br/><br/>
							<a href="ProjectAssignmentOverviewProf.jsp" target="tools" onmouseover="ProjectAssignmentOverview()" onmouseout="Def()">View assignments</a>
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