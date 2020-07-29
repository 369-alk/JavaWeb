<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page import="cn.sdcet.web.Book"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>图书查询</title>
    
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
    		
    		String iname = request.getParameter("iname");
    		String ipublisher = request.getParameter("ipublisher");
    		String iselect = request.getParameter("iselect");
    		if(iname == null)
    	iname = "";
    %>
    <jsp:useBean id="bookBean" class="cn.sdcet.web.BookDB"></jsp:useBean>
  	<jsp:setProperty property="file" name="bookBean" value="<%=file %>"/>
    <h1>查询图书</h1>
    <hr>
    <form action="findBooks.jsp?iselect=action" method="post">
    	<table>
    		<tr>
    			<td>书名：</td>
    			<td><input type="text" name="iname" value="<%=iname%>"></td>
    		</tr>
    		<tr>
    			<td>出版社：</td>
    			<td>
    				<select name="ipublisher" size="1">
    				<%
    					Set<String> publisherList = bookBean.getAllBookPublisher();
    					for(String publisher : publisherList){ 
    				%>
    					<option value="<%=publisher%>"><%=publisher%></option>
    				<%} %>
    				</select>
    			</td>
    		</tr>
    		<tr>
    			<td colspan="2"><input type="submit" value="查询"></td>
    		</tr>
    	</table>
    </form>
    <%
    	if("action".equals(iselect)){ 
    	List<Book> bookList = bookBean.getAllBook(iname, ipublisher);
    %>
    <p>查询结果</p>
    <p>共有<%=bookList.size() %>条符合条件的记录</p>
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
	<%for(Book book : bookList){ %>
		<tr>
			<td><%=book.getName() %></td>
			<td><%=book.getAuthor() %></td>
			<td><%=book.getPublisher() %></td>
			<td><%=book.getIsbn() %></td>
			<td><%=book.getPrice() %></td>
			<td><%=book.getDate() %></td>
		</tr>
	<%} %>
	</table>
    <%} %>
  </body>
</html>
