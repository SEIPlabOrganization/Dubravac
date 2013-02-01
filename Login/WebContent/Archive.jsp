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
        <li><a href="#fragment-1">Posts</a></li>
        <li><a href="#fragment-2">Notifications</a></li>
    </ul>
    <div id="fragment-1" style="width: 100%; height: 100%; -moz-box-sizing: border-box; box-sizing: border-box; margin-top: -27px; padding-top: 27px;">
        <iframe src='Archive_msg.jsp' style="width: 100%; height: 100%;" frameborder="0"></iframe>
    </div>
    <div id="fragment-2" style="width: 100%; height: 100%; -moz-box-sizing: border-box; box-sizing: border-box; margin-top: -27px; padding-top: 27px;">
        <iframe src='NotificationArchive_msg.jsp' height="100%" width="100%" frameborder="0"></iframe>
    </div>
</div>
</body>
</html>