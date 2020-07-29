<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>添加数据</title>

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
		Statement stmt = null;
		try {
			Class.forName(DBDRIVER);
			connection = DriverManager.getConnection(DBURL, USER, PASSWORD);
			stmt = connection.createStatement();
			stmt.addBatch(
					"insert into book (name,author,publisher,isbn,price,date) "
	    			+ "values('Tomcat与Java Web开发技术详解','孙卫琴','电子工业出版社','7-5053-9392-8','45','2004-04-01 00:00:00.000')");
	    	stmt.addBatch(
					"insert into book (name,author,publisher,isbn,price,date) "
	    			+ "values('Java Web开发详解','孙鑫','电子工业出版社','7-121-02396-2','99','2006-04-01 00:00:00.000')");
	    	stmt.addBatch(
					"insert into book (name,author,publisher,isbn,price,date) "
	    			+ "values('XML实用教程','丁跃潮','北京大学出版社','7-301-10462-6','26','2006-01-01 00:00:00.000')");
	    	stmt.addBatch(
					"insert into book (name,author,publisher,isbn,price,date) "
	    			+ "values('HTML编程指南','武焰','电子工业出版社','7-5053-5186-9','28','1999-06-01 00:00:00.000')");
	    	stmt.executeBatch();
	    	%>数据添加成功<%
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("执行查询操作失败：" + e.getMessage());
		}finally{
			try {
			  	 if(stmt != null)
		  		 	stmt.close();
  			  } catch(Exception e) {
  				 e.printStackTrace();
  	  			 throw new RuntimeException("关闭stmt失败：" + e.getMessage());
  			  } finally {
  				  try {
				  	 if(connection != null)
				  	 	connection.close();
  				  } catch(Exception e) {
  					 e.printStackTrace();
  	  	  			 throw new RuntimeException("关闭数据库连接失败：" + e.getMessage());
  				  }
  			  }
		}
	%>
</body>
</html>
