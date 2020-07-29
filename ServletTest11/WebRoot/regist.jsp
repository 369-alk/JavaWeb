<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>注册成功页面</title>

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
		request.setCharacterEncoding("utf-8");
		String vehicle = request.getParameter("vehicle");
		String rewrite = (String)session.getAttribute("rewrite");
		if (vehicle == null) {
			request.setAttribute("message", "请阅读腾讯QQ用户服务条款并接受！");
	%>
		<jsp:forward page="register.jsp"></jsp:forward>
		
	<%
		}
	%>

	<jsp:useBean id="user" class="cn.sdcet.web.User" scope="session"></jsp:useBean>
	<jsp:setProperty property="*" name="user" />
	
	<h3><%=rewrite %></h3>
	
	<hr>
	点击<a href="register.jsp?rewritecheck=1">这里</a>修改用户信息<br>
	点击<a href="regInfo.jsp">这里</a>查看用户详细信息
</body>
</html>
