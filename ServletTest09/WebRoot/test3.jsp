<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>第三题</title>
    
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
    <table border="1">
    	<tr align="center">
    		<td>编号</td>
    		<td>商品名称</td>
    		<td>操作</td>
    	</tr>
    	<%
    	
    		for(int i = 1; i <= 10; i++){
    	%>
    		<tr align="center">
    			<td><%=i %></td>
    			<td></td>
    			<td>修改    删除</td>
    		</tr>
		<%
    		}
    	%>
    	
    </table>
  </body>
</html>
