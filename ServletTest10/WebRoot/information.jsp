<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" errorPage="error.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>信息显示页面</title>

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
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");

		int number = Integer.valueOf(age);
		if (number < 18) {
			number = number / 0;
		}
	%>
	姓名：<%=name%><br>
	性别：<%=gender%><br>
	年龄：<%=age%><br>
	
</body>
</html>
