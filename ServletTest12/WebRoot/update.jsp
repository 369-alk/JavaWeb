<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="cn.sdcet.pojo.Book"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>修改页面</title>

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
		final String DBDRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		final String DBURL = "jdbc:sqlserver://localhost:1433;DatabaseName=BookStore";
		final String USER = "sa";
		final String PASSWORD = "4210";

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			Class.forName(DBDRIVER);
			connection = DriverManager.getConnection(DBURL, USER, PASSWORD);

			ps = connection.prepareStatement("update book set price = ? where name = ?");
			ps.setInt(1, 20);
			ps.setString(2, "XML实用教程");

			ps.executeUpdate();

			%>修改成功<%
		} catch (Exception e) {
			%>修改失败<%
			e.printStackTrace();
			throw new RuntimeException("执行修改操作失败：" + e.getMessage());
		} finally {

			try {
				if (ps != null)
					ps.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("关闭ps失败：" + e.getMessage());
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("关闭数据库连接失败：" + e.getMessage());
				}
			}

		}
	%>

</body>
</html>
