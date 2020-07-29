<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>查询页面</title>

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
		ResultSet rs = null;
	%>
	<table>
		<tr>
			<td>书名</td>
			<td>作者</td>
			<td>出版社</td>
			<td>刊号</td>
			<td>价格</td>
			<td>出版日期</td>
		</tr>
		<%
			try {
				Class.forName(DBDRIVER);
				connection = DriverManager.getConnection(DBURL, USER, PASSWORD);

				ps = connection.prepareStatement("select * from book order by price");

				rs = ps.executeQuery();
				while (rs.next()) {
		%>

		<tr>
			<td><%=rs.getString(1)%></td>
			<td><%=rs.getString(2)%></td>
			<td><%=rs.getString(3)%></td>
			<td><%=rs.getString(4)%></td>
			<td><%=rs.getInt(5)%>元</td>
			<td><%=rs.getDate(6)%></td>
		</tr>


		<%
			}
		%>

	</table>
	<%
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("执行查询操作失败：" + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("关闭结果集失败：" + e.getMessage());
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
		}
	%>

</body>
</html>
