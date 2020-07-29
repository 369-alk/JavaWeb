<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>创建表</title>

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
					"create table book "
	    			+ "("
	    			+ "		name nchar(50),"
	    			+ "		author nchar(10),"
	    			+ "		publisher nchar(20),"
	    			+ "		isbn nchar(30),"
	    			+ "		price int,"
	    			+ "		date datetime"
	    			+ ")");

	    	stmt.executeBatch();
	    	%>数据表创建成功<%
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
