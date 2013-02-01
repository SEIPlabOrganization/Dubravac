<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
content = new Array();
subject = new Array();
temp;
tempcontent;
tempsubject;
function readcontent(i){
	temp=document.getElementById("main").innerHTML;
	tempcontent=content[i];
	tempsubject=subject[i];
	document.getElementById("main").innerHTML="<style type='text/css'>*{ margin: 0px; padding: 0px}html, body{width: 100%; height: 100%;}table{width: 100%; height: 100%;}</style><table><tr><td style='width: 80px; height: 30px;'>Subject:</td><td><textarea rows='1' cols='1' name='content' style='resize: none; width: 100%; height: 25px; font-size: 16px; vertical-align: middle;' onfocus='blur();' spellcheck='false'>"+tempsubject+"</textarea></td></tr><tr><td style='height: 40px; text-align: center;' colspan='2'>Message content:</td></tr><tr><td colspan='2'><textarea rows='1' cols='1' name='content' style='resize: none; width: 100%; height: 100%; font-size: 16px; vertical-align: middle;' onfocus='blur();' spellcheck='false'>"+tempcontent+"</textarea></td></tr><tr><td colspan='2' style='height: 20px;'><button onClick='backtolist();' style='width: 100%;''>Back</button></td></tr></table>";
}
function backtolist(){
	document.getElementById("main").innerHTML=temp;
}
</script>
<style type="text/css">
*{ margin: 0px; padding: 0px}
table{width: 780px; text-align: center;}
a{font: bold 16px Tahoma; text-decoration: none; color: black;}
th{font: bold 16px Tahoma;}
#read{width: 60px;}
#subject{width: 480px;}
#date{width: 100px;}
#time{width: 100px;}
td{padding-top: 5px;}
#con:nth-child(4n-2) {background: #F6F6F6}
#con:nth-child(4n) {background: #D7D7D7}
#sub{padding-left: 20px; padding-right: 20px; text-align: left;}
#button{background-color: white;}
</style>
</head>
<body id='main'>
<jsp:include page="/My_msgServlet" flush="true" />
</body>
</html>