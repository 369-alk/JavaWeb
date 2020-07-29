<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>登录界面</title>
    
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
  		String name = request.getParameter("username");
  		String message = (String)request.getAttribute("message");
  		if(name == null)
  		{
  			name = "";
  		}
  	%>
  
    <form action="check" method="post">
    	<table>
    		<caption>用户登录</caption>
    		<% 
    			if(message != null)
    			{
 			%>
    			<tr align="center">
    				<td colspan="2" style="color: red"><%=message %></td>
    			</tr>
    		<%
    			}
    		%>
    		
    		
    		<tr>
    			<td>用户名：</td>
    			<td><input type="text" name="username" value="<%=name %>"></td>
    		</tr>
    		<tr>
    			<td>密码：</td>
    			<td><input type="password" name="password"></td>
    		</tr>
    		<tr align="center">
    			<td colspan="2"><input type="submit" value="登录"></td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
