<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.sdcet.web.*" %>
<jsp:useBean id="cart" class="cn.sdcet.web.ShopCart" scope="session"></jsp:useBean>
<% 
	HashMap<Integer,Book> books = (HashMap<Integer,Book>)application.getAttribute("books");
	String id = request.getParameter("id");
	if(id != null){
		int bookId = Integer.parseInt(id);
		Book book = books.get(bookId);
		cart.add(bookId, book);
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>M购买页面</title>
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
    <h1 align="center">图书列表</h1>
    <hr>
    <table border="1px" align="center" width="90%">
    	<tr>
    		<th>编号</th>
    		<th>书名</th>
    		<th>作者</th>
    		<th>出版社</th>
    		<th>价格</th>
    		<th>简介</th>
    		<th>&nbsp;</th>
    	</tr>
    	
    	<% 
    		List<Book> bookList = new ArrayList<Book>(books.values());
    		for(Book book : bookList){
    	%>	
    		<tr>
    			<td><%=book.getId() %></td>
    			<td><%=book.getName() %></td>
    			<td><%=book.getAuthor() %></td>
    			<td><%=book.getPublisher() %></td>
    			<td><%=book.getPrice() %>元</td>
    			<td><%=book.getDescription() %></td>
    			<td><a href="?id=<%=book.getId() %>">加入购物车</a></td>
    		</tr>
    	<%	} %>	
    </table>
    <table width="90%" align="center">
    	<tr>
    		<td align="left">当前购物车共有<%=cart.getItemNumbers() %>件商品</td>
    		<td align="right"><a href="showCart.jsp">查看购物车</a></td>
    	</tr>
    </table>
  </body>
</html>
