
<HTML>
<HEAD>
    <jsp:include page="/SecServlet" flush="false" />
</HEAD>

<BODY>
<table>
	<tr>
		<td style="background-color:#0066FF; width:1000px; height:10px">
			<h1 style="color:white">Survey</h1>
		</td>
	</tr>
</table>

<h2>Survey index page</h2>
<div align="left">
<form action="Survey" method="get">
<input type="hidden" name="id"/>
<input type="submit" value="My surveys" />
</form>



<input type=button onClick="location.href='../Login/survey_management.jsp'" value='Create survey'>
<br >
<br >
<form action="SurveyResults" method="get">
<input type="hidden" name="id"/>
<input type="submit" value="Survey results" />
</form>
</div>
</BODY>
</HTML>
