<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html style="width:100%; height: 100%">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
*{ margin: 0px; padding: 0px}
</style>
<script type="text/javascript">
tempmulti;
tempsingle;
tempstud;
tempprof;
tempassi;
function clear(){
	tempmulti=document.getElementById("multi").innerHTML;
	tempstud=document.getElementById("stud").innerHTML;
	tempprof=document.getElementById("prof").innerHTML;
	tempassi=document.getElementById("assi").innerHTML;
	tempsingle=document.getElementById("single").innerHTML;
	document.getElementById("main").innerHTML="";
}
function multisingle(){
	var radios = document.getElementsByName('rb');
	document.getElementById("ret").innerHTML="";
	for (var i = 0, length = radios.length; i < length; i++) {
	    if (radios[i].checked) {
	        val=radios[i].value;
	    }
	}
	if(val=="multi")
		document.getElementById("main").innerHTML=tempmulti;
	if(val=="single")
		document.getElementById("main").innerHTML=tempsingle;
}
function role(){
	val=document.getElementById("role").value;
	if(val=="no")
		document.getElementById("temp").innerHTML="";
	if(val=="stud")
		document.getElementById("temp").innerHTML=tempstud;
	if(val=="prof")
		document.getElementById("temp").innerHTML=tempprof;
	if(val=="assi")
		document.getElementById("temp").innerHTML=tempassi;
		
}
</script>
<body onload="clear();" style="width:100%; height: 100%">
<table style="width:100%;"><tr><td align="center">
<table><tr><td align="left">
<form>
<input type="radio" name="rb" value="multi" onChange="multisingle();">Input multiple students using a .xls file.<br>
<input type="radio" name="rb" value="single" onChange="multisingle();">Input a single user.
</form>
</td></tr></table>
<br/><br/>
<div id="main">
	<div id="multi">
	<form action="InputUsersServlet" method="post" enctype="multipart/form-data">
	    <input type="file" name="file" />
	    <input type="submit" value="Submit Users"/>
	</form>
	</div>
	
	<div id="stud">
	<form action="InputUserServlet" method="post">
	    <input type="hidden" name="type" value="Stud" />
	<table>
	<tr>
	<td>JMBAG:</td>
	    <td><input type="text" name="jmbag" /></td>
	</tr>
	<tr>
	<td>Name:</td>
	    <td><input type="text" name="name" /></td>
	</tr>
	<tr>
	<td>Surname:</td>
	    <td><input type="text" name="surname" /></td>
	</tr>
	<tr>
	    <td colspan="2" align="right"><input type="submit" value="Submit User" style="width:100%;"/></td>
	</tr>
	</table>
	</form>
	</div>
	
	<div id="prof">
	<form action="InputUserServlet" method="post">
		<input type="hidden" name="type" value="Prof" />
	<table>
	<tr>
	<td>Name:</td>
	    <td><input type="text" name="name" /></td>
	</tr>
	<tr>
	<td>Surname:</td>
	    <td><input type="text" name="surname" /></td>
	</tr>
	<tr>
	    <td colspan="2" align="right"><input type="submit" value="Submit User" style="width:100%;"/></td>
	</tr>
	</table>
	</form>
	</div>
	
	<div id="assi">
	<form action="InputUserServlet" method="post">
		<input type="hidden" name="type" value="Assi" />
	<table>
	<tr>
	<td>Name:</td>
	    <td><input type="text" name="name" /></td>
	</tr>
	<tr>
	<td>Surname:</td>
	    <td><input type="text" name="surname" /></td>
	</tr>
	<tr>
	    <td colspan="2" align="right"><input type="submit" value="Submit User" style="width:100%;"/></td>
	</tr>
	</table>
	</form>
	</div>
	
	<div id="single">
	Select users role:
	<select id="role" onChange="role();">
		<option value="no">-Select-</option>
		<option value="stud" >Student</option>
		<option value="prof" >Professor</option>
		<option value="assi" >Assistant</option>
	</select>
	<br/><br/>
	<div id="temp">
	</div>
	</div>
	
</div>
<div id="ret">
<%
   	if((request.getParameter("ret"))!=null){
   		out.print("<br/>");
		out.print(request.getParameter("ret"));
   	}
%>
</div>

</td></tr></table>
</body>
</html>