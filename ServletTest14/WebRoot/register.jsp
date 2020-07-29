<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>用户注册</title>

  </head>
  
  <body>
    <form action="information.jsp" method="post">
    	<table>
    		<tr>
    			<td>用户名</td>
    			<td><input type="text" name="username"></td>
    		</tr>
    		<tr>
    			<td>性别</td>
    			<td>
    				<input type="radio" name="gender" value="男">男
    				<input type="radio" name="gender" value="女">女
    			</td>
    		</tr>
    		<tr>
    			<td>爱好</td>
    			<td>
    				<input type="checkbox" name="aihao" value="吃饭">吃饭
    				<input type="checkbox" name="aihao" value="睡觉">睡觉
    				<input type="checkbox" name="aihao" value="打豆豆">打豆豆
    			</td>
    		</tr>
    		<tr>
    			<td><input type="submit" value="注册"></td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
