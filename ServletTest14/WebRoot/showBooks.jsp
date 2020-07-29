<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>查询数据</title>

  </head>
  
  <body>
    <%
		final String DBDRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		final String DBURL = "jdbc:sqlserver://localhost:1433;databaseName=BOOKSTORE";
		final String USER = "sa";
		final String PASSWORD = "123456";

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

				ps = connection.prepareStatement("select * from BOOKS order by price");

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
		rs.close();
		ps.close();
		connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("执行查询操作失败：" + e.getMessage());
		} 
		
	%>
  </body>
</html>
