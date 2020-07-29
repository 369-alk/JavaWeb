<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page import="cn.sdcet.web.Book"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'Testbook.jsp' starting page</title>
    
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
  	String file = application.getRealPath("config.properties");
  %>
  	<jsp:useBean id="bookBean" class="cn.sdcet.web.BookDB"></jsp:useBean>
  	<jsp:setProperty property="file" name="bookBean" value="<%=file %>"/>
    <p>查询结果</p>
    <hr>
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
		List<Book> bookList = bookBean.getAllBook();
		for(Book book : bookList){ 
	%>
		<tr>
			<td><%=book.getName() %></td>
			<td><%=book.getAuthor() %></td>
			<td><%=book.getPublisher() %></td>
			<td><%=book.getIsbn() %></td>
			<td><%=book.getPrice() %></td>
			<td><%=book.getDate() %></td>
		</tr>
	<%} %>
	<%
		Set<String> publisherList = bookBean.getAllBookPublisher();
		for(String publisher : publisherList){ 
	%>
		<tr>
			<td><%=publisher %></td>

		</tr>
	<%} %>
	</table>
  </body>
</html>
