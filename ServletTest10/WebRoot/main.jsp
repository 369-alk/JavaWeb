<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>主界面</title>
    
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
  	<% 
  		String name = (String)session.getAttribute("name");
  	%>
    <h1>主界面</h1>
    <% 
    	if(name == null)
    	{
    %>
    		欢迎你，游客【<a href = "login.jsp">登录</a>】
    <%
    	}else{
    %>
    		欢迎你，<%=name %>【<a href = "logout">退出</a>】
    <%
    	}
    %>
  </body>
</html>
