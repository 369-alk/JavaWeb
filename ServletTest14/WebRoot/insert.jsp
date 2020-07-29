<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加数据</title>
  </head>
  <body>
    <%
		String DBDRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String DBURL = "jdbc:sqlserver://localhost:1433;databaseName=BOOKSTORE";
		String USER = "sa";
		String PASSWORD = "123456";
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(DBDRIVER);
			connection = DriverManager.getConnection(DBURL, USER, PASSWORD);
			String sql = "insert into BOOKS (name,author,price) values (?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, "《XML实用教程》");
			ps.setString(2, "丁跃潮");
			ps.setInt(3, 26);
			
			ps.executeUpdate();
			
			ps.close();
	    	connection.close();
	    	%>数据添加成功<%
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("添加数据操作失败：" + e.getMessage());
		}
	%>
  </body>
</html>
