<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.sdcet.web.*" %>
<jsp:useBean id="cart" class="cn.sdcet.web.ShopCart" scope="session"></jsp:useBean>
<% 
	String action = request.getParameter("action");
	if(action != null){
		if ("delete".equals(action)) {
			int bookId = Integer.parseInt(request.getParameter("id"));
			cart.remove(bookId);
		} else if("update".equals(action)){
			int number = Integer.parseInt(request.getParameter("itemNum"));
			for(int i=1;i<=number;i++){
				int id = Integer.parseInt(request.getParameter("book_"+i));
				int quantity = Integer.parseInt(request.getParameter("quantity_"+i));
				cart.setQuantity(id, quantity);
			}
		}else {
			cart.clear();
		}
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>购物车</title>
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
    <h1 align="center">购物车</h1>
    <hr>
    <form action="?action=update" method="post">
    	<table border="1px" align="center" width="90%">
    		<tr>
    			<th>书名</th>
    			<th>单价</th>
    			<th>数量</th>
    			<th>总价</th>
    			<th>操作</th>
    		</tr>
    		<% 
    			List<ShopCartItem> items = cart.getItrms();
    			int total = 0;
    			int num = 0;
    			for(ShopCartItem item : items){
    				Book book = item.getBook();
    				int itemPrice = book.getPrice() * item.getQuantity();
    				total += itemPrice;
    				num++;
    		%>
    		<tr>
    			<td><%=book.getName() %></td>
    			<td><%=book.getPrice() %>元</td>
    			<td><input type="text" name="quantity_<%=num %>" value="<%=item.getQuantity() %>"></td>
    			<td><input type="hidden" name="book_<%=num %>" value="<%=book.getId() %>"></td>
    			<td><%=itemPrice %>元</td>
    			<td><a href="?id=<%=book.getId() %>&action=delete">删除</a></td>
    		</tr>
    		<%	} %>
    		<tr><td colspan="5" align="right">合计：&nbsp;&nbsp;<%=total %>元<td></tr>
    	</table>
    	<hr>
    	<table width="90%" align="center">
    		<tr>
    			<td align="left"><input type="submit" value="保存修改"></td>
    			<td align="right"><a href="index.jsp">继续购物</a></td>
    			<td align="right"><a href="?action=clear">清空购物车</a></td>
    		</tr>
    	</table>
    	<input type="hidden" name="itemNum" value="<%=num %>">
    </form>
  </body>
</html>
