<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
*{ margin: 0px; padding: 0px}
table{width: 740px; text-align: center;}
#read{width: 60px;}
#author{width: 160px;}
#subject{width: 340px;}
#date{width: 100px;}
#time{width: 100px;}
a{font: bold 16px Tahoma; text-decoration: none; color: black;}
th{font: bold 16px Tahoma;}
td{padding-top: 5px;}
#con:nth-child(4n-2) {background: #F6F6F6}
#con:nth-child(4n) {background: #D7D7D7}
#sub{padding-left: 20px; padding-right: 20px; text-align: left;}
#aut{padding-left: 10px; padding-right: 10px; text-align: left;}
#button{background-color: white;}
</style>
</head>
<body id='main'>
<jsp:include page="/NotificationServlet" flush="true" />
</body>
</html>