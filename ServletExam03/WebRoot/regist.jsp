<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>注册状态</title>
    
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
  <%request.setCharacterEncoding("utf-8"); %>

    <jsp:useBean id="user" class="cn.sdcet.web.User" scope="session"></jsp:useBean>
    <jsp:setProperty property="*" name="user"/>
    <h1>注册状态</h1>
    <hr>
    <h5>注册成功！</h5>
    <h5>点击<a href="userInfo.jsp">这里</a>查看用户信息</h5>
  </body>
</html>
