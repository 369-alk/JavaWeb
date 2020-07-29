<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>用户注册页面</title>
    
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
    <form action="regist.jsp" method="post">
    	<table>
    		<caption>邮箱注册</caption>
    		
    		<% 
    			String message = (String)request.getAttribute("message");
    			String rewritecheck = request.getParameter("rewritecheck");
    			if(rewritecheck != null){
    				session.setAttribute("rewrite", "恭喜你，修改成功！");
    			}else{
    				session.setAttribute("rewrite", "恭喜你，注册成功！");
    			}
    			if(message != null)
    			{
    		%>
    			<tr align="center">
    				<td colspan="2" style="color: red"><%=message %></td>
    			</tr>
    		<%
    			}
    		%>
    		
    		
    		<tr align="right">
    			<td>电子邮箱：</td>
    			<td><input type="text" name="mail"></td>
    		</tr>
    		<tr align="right">
    			<td>微博账号：</td>
    			<td><input type="text" name="blog"></td>
    		</tr>
    		<tr align="right">
    			<td>姓名：</td>
    			<td><input type="text" name="name"></td>
    		</tr>
    		<tr align="right">
    			<td>密码：</td>
    			<td><input type="password" name="password1"></td>
    		</tr>
    		<tr align="right">
    			<td>确认密码：</td>
    			<td><input type="password" name="password2"></td>
    		</tr>
    		<tr align="right">
    			<td>性别：</td>
    			<td align="left">
    				<input type="radio" name="gender" value="男">男
					<input type="radio" name="gender" value="女">女
				</td>
    		</tr>
    		<tr align="right">
    			<td>家庭住址：</td>
    			<td><input type="text" name="address"></td>
    		</tr>
    		<tr>
    			<td></td>
    			<td><input type="submit" value="确定并同意一下条款"></td>
    		</tr>
    		<tr>
    			<td></td>
    			<td><input type="checkbox" name="vehicle" value="yes">我接受<br></td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
