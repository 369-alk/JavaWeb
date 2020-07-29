<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>用户信息</title>

  </head>
  
  <body>
  <% 
  		request.setCharacterEncoding("utf-8");
  		String username = request.getParameter("username");
  		String gender = request.getParameter("gender");
  		String[] aihao = request.getParameterValues("aihao");
  %>
    <h3>您的注册信息是：</h3>
    <hr>
    	用户名：<%=username %><br>
    	性别：<%=gender %><br>
    	爱好：
    	<% 
    		for(String s : aihao){
    	%>
    	<%=s %>
    	<% 
    		}
    	%>
  </body>
</html>
