<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改数据</title>
  </head>
  
  <body>
    <%
		final String DBDRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		final String DBURL = "jdbc:sqlserver://localhost:1433;databaseName=BOOKSTORE";
		final String USER = "sa";
		final String PASSWORD = "123456";

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			Class.forName(DBDRIVER);
			connection = DriverManager.getConnection(DBURL, USER, PASSWORD);

			ps = connection.prepareStatement("update BOOKS set price = ? where name = ?");
			ps.setInt(1, 20);
			ps.setString(2, "《XML实用教程》");

			ps.executeUpdate();
			
			ps.close();
			connection.close();

			%>修改成功<%
		} catch (Exception e) {
			%>修改失败<%
			e.printStackTrace();
			throw new RuntimeException("执行修改操作失败：" + e.getMessage());
		} 
	%>
  </body>
</html>
