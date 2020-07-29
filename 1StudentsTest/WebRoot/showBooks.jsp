<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>查询数据</title>

  </head>
  
  <body>
    <%
		String url = "jdbc:mysql://localhost:3306/students?serverTimezone=UTC";
    	String name = "com.mysql.jdbc.Driver";
    	String username = "root";
    	String password = "ROOT";

		Connection connection = null;
    	PreparedStatement preparedStatement = null;
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
			try{
            Class.forName(name);
            connection = DriverManager.getConnection(url, username, password);
            preparedStatement = connection.prepareStatement("SELECT * FROM j17001");
            
            ResultSet rs = preparedStatement.executeQuery();


				while (rs.next()) {
		%>

		<tr>
			<td><%=rs.getString(1)%></td>
			<td><%=rs.getString(2)%></td>
		</tr>


		<%
			}
		%>

	</table>
	<%

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("执行查询操作失败：" + e.getMessage());
		} 
		
	%>
  </body>
</html>
