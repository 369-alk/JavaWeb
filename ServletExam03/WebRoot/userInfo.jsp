<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>用户信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <jsp:useBean id="user" class="cn.sdcet.web.User" scope="session"></jsp:useBean>
    	姓名：<jsp:getProperty property="name" name="user"/><br>
    	性别：<jsp:getProperty property="gender" name="user"/><br>
    	学历：<jsp:getProperty property="education" name="user"/><br>
    	爱好：<jsp:getProperty property="hobbyInput" name="user"/><br>
    	
  </body>
</html>
