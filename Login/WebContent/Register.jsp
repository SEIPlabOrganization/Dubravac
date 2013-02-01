<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
table{ margin: auto; position: absolute; top: 100px; bottom: 0px; left: 0px; right: 0px; }
</style>
<script type="text/javascript">
	function name() {
		var numaric = document.BoxForm.newusername.value;
    	
		for(var j=0; j<numaric.length; j++)
    		{
			var alpha=alphaa.tolower();
    		  var a = alpha.charCodeAt(0);
    		  if(!((a.charAt(k)>='a' && a.charAt(k)<='z') || a.charAt(k)=='č' || a.charAt(k)=='ć' || a.charAt(k)=='đ' || a.charAt(k)=='š' || a.charAt(k)=='ž')){
    			  document.getElementById("nun").innerHTML="*";
    		  }else{
    			  document.getElementById("nun").innerHTML="!";
    		  }
     		}
	}
    function Tool1() {
    	document.getElementById("des").innerHTML="Tool1 info";
    }
    function Tool2() {
   		document.getElementById("des").innerHTML="Tool2 info";
   	}

</script>
</head>
<body >
	<jsp:include page="/SecServlet" flush="false" />
	<form method="POST" action="ChangeAccServlet" name="BoxForm">
	<table style="text-align: right;">
		<tr>
			<td>New User Name:</td>
			<td><input type='text' name='newusername' autocomplete="off" onchange="name();"/><b id=nun></b></td>
		</tr>
		<tr>
			<td>Old Password:</td>
			<td><input type="password" name="oldpassword"/><b id="op"></b></td>
		</tr>
		<tr>
		<tr>
			<td>New Password:</td>
			<td><input type="password" name="newpassword"/><b id="np"></b></td>
		</tr>
		<tr>
			<td>Repeat New Password:</td>
			<td><input type="password" name="repeatpassword"/><b id="rp"></b></td>
		</tr>
		<tr>
			<td>E-mail address:</td>
			<td><input type="text" name="email" autocomplete="off"/><b id="nem"></b></td>
		</tr>
		<tr>
		<tr>
			<td>Repeat E-mail address:</td>
			<td><input type="text" name="repeatemail" autocomplete="off"/><b id="oem"></b></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="Register" style="width: 100%"/></td>
		</tr> 
		<tr>
			<td colspan="2" id="return" align="center">
			<%
		   	if((request.getParameter("ret"))!=null){
		    	out.print(request.getParameter("ret"));
		   	}
			%>
			</td>
		</tr> 
    </table>
    </form>
</body>
</html>