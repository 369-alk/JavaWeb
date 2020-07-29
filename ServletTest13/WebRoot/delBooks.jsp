<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page import="cn.sdcet.web.Book"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>图书删除</title>
    
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
    	request.setCharacterEncoding("utf-8");
    		String file = application.getRealPath("config.properties");
    		
    		String[] ibname = request.getParameterValues("ibname");
    		String isdelete = request.getParameter("isdelete");
    %>
    <jsp:useBean id="bookBean" class="cn.sdcet.web.BookDB"></jsp:useBean>
  	<jsp:setProperty property="file" name="bookBean" value="<%=file %>"/>
  	<%
  		
  		if("action".equals(isdelete)){
  			bookBean.deleteBook(ibname);
  		}
  		List<Book> bookList = bookBean.getAllBook(); 
  	%>
    <h1>图书列表</h1>
    <hr>
    <form action="delBooks.jsp?isdelete=action" method="post">
    	<table>
		<tr>
			<td>&nbsp;</td>
			<td>书名</td>
			<td>作者</td>
			<td>出版社</td>
			<td>刊号</td>
			<td>价格</td>
			<td>出版日期</td>
		</tr>
	<%for(Book book : bookList){ %>
		<tr>
			<td><input type="checkbox" name="ibname" value="<%=book.getName().trim()%>"></td>
			<td><%=book.getName() %></td>
			<td><%=book.getAuthor() %></td>
			<td><%=book.getPublisher() %></td>
			<td><%=book.getIsbn() %></td>
			<td><%=book.getPrice() %></td>
			<td><%=book.getDate() %></td>
		</tr>
	<%} %>
		<tr><td colspan="7"><input type="submit" value="删除选中图书"></td></tr>
	</table>
    </form>
  </body>
</html>
