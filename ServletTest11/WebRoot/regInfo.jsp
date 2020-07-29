<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>用户信息显示页面</title>
    
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
    <h2>用户信息</h2>
    <hr>
    <jsp:useBean id="user" class="cn.sdcet.web.User" scope="session"></jsp:useBean>
    电子邮箱：<jsp:getProperty property="mail" name="user"/><br>
    微博账号：<jsp:getProperty property="blog" name="user"/><br>
    姓名：<jsp:getProperty property="name" name="user"/><br>
    性别：<jsp:getProperty property="gender" name="user"/><br>
    家庭住址：<jsp:getProperty property="address" name="user"/><br>
  </body>
</html>
