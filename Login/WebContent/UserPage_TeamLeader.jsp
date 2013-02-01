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
    function ProjectsOverview() {
   		document.getElementById("des").innerHTML="Review of projects progress";
   	}
    function WeeklyAssignments() {
   		document.getElementById("des").innerHTML="Weekly Assignments form";
   	}
    function PlanAndControlProject() {
   		document.getElementById("des").innerHTML="Project details, project plan form";
   	}
    function Survey() {
   		document.getElementById("des").innerHTML="Surveys for students, professors";
   	}
    function NewProjectAssignments() {
    	document.getElementById("des").innerHTML="Form for creating a new project assignment";
    }
    function ProjectAssignmentOverview() {
    	document.getElementById("des").innerHTML="List of all project assignments";
    }
    function CreateNewProject() {
    	document.getElementById("des").innerHTML="Create New Project for your team";
    }
    function PickAssignments() {
    	document.getElementById("des").innerHTML="Pick assignments for your project";
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
							<a href="ProjectsOverview.jsp" target="tools" onmouseover="ProjectsOverview()" onmouseout="Def()">Projects Overview</a><br/><br/>
							<a href="WeeklyAssignments.jsp" target="tools" onmouseover="WeeklyAssignments()" onmouseout="Def()">Weekly Assignments</a><br/><br/>
							<a href="PlanAndControlProjectStudent.jsp" target="tools" onmouseover="PlanAndControlProject()" onmouseout="Def()">Plan Project</a><br/><br/>
							<a href="survey_index.jsp" target="tools" onmouseover="Survey()" onmouseout="Def()">Survey</a><br/><br/>
							<a href="NewProjectAssignments.jsp" target="tools" onmouseover="NewProjectAssignments()" onmouseout="Def()">New assignment</a><br/><br/>
							<a href="ProjectAssignmentOverview.jsp" target="tools" onmouseover="ProjectAssignmentOverview()" onmouseout="Def()">View assignments</a><br/><br/>
							<a href="CreateNewProject.jsp" target="tools" onmouseover="CreateNewProject()" onmouseout="Def()">New project</a><br/><br/>
							<a href="PickAssignments.jsp" target="tools" onmouseover="PickAssignments()" onmouseout="Def()">Pick assignment</a>
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
			<iframe src="msg_app.jsp" height="100%" width="100%" frameborder="0"></iframe>
			</td>
		</tr>
	</table>
</body>
</html>