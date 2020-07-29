<%@page import="cn.sdcet.news.utils.Constants"%>
<%@page import="cn.sdcet.news.domain.Catalog"%>
<%@page import="cn.sdcet.news.utils.PageBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta http-equiv="Content-Language" content="zh-CN" />

<title>新闻类别管理</title>
<link href="../Style/Css.css" rel="stylesheet" type="text/css" />
</head>

<body>
<jsp:useBean id="catalogDao" class="cn.sdcet.news.dao.jdbc.CatalogDaoJDBCImpl" scope="application"></jsp:useBean>
<%
	String strId = request.getParameter("id");
	if(strId != null) {
		int id = Integer.parseInt(strId);
		catalogDao.delete(id);
	}
	
%>

	<h3 class="subTitle">新闻类别</h3>

	<ul class="button">
		<li><a href="CategoryAdd.jsp">添加类别</a></li>
	</ul>

		<table width="90%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="table">
			<tr>
				<th width="8%" nowrap="nowrap">序号</th>
				<th nowrap="nowrap">类别名称</th>
				<th width="12%" nowrap="nowrap">修改</th>
				<th width="12%" nowrap="nowrap">删除</th>
			</tr>
			
			<%
				int pageNum = 1;
				String strPage = request.getParameter("page");
				if(strPage != null) {
					pageNum = Integer.parseInt(strPage);
				}
				
				PageBean<Catalog> pageBean = catalogDao.findAll2(pageNum);
				int idx = (pageNum - 1) * Constants.PAGE_SIZE + 1;
				for(Catalog catalog : pageBean.getRecordList()) {
			%>
			<tr>
				<td align="center"><%=idx++%></td>
				<td align="center"><%=catalog.getName()%></td>
				<td align="center"><a href="CategoryModify.jsp?id=<%=catalog.getId()%>">修改</a></td>
				<td align="center">
					<a href="?id=<%=catalog.getId()%>" onclick="return confirm('确认删除此分类吗？')">删除</a>
				</td>
			</tr>
			<%
				}
			%>
		</table>
		<form action="<%=request.getRequestURI()%>" method="post">
		<table width="90%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="page">
			<tr>
				<td width="50%" align="left">
					共有<%=pageBean.getRecordCount() %>条记录，
					<span style="font-family:宋体; font-size:9.0pt; color:black; ">第</span>
					<span style="font-family:Tahoma; font-size:9.0pt; color:black; "><%=pageBean.getCurrentPage() %>/<%=pageBean.getPageCount() %> </span>
					<span style="font-family:宋体; font-size:9.0pt; color:black; ">页</span>
				</td>
				<td width="50%" align="right">
					<a href="?page=1">首页</a> 
					<a href="?page=<%=pageBean.prevPage()%>">上一页</a>
					<a href="?page=<%=pageBean.nextPage()%>">下一页</a> 
					<a href="?page=<%=pageBean.getPageCount()%>">末页</a> 
					跳转到 
					<select name="page" onchange="document.forms[0].submit()">
						<%
							for(int i=1; i<=pageBean.getPageCount(); i++) {
						%>
						<option value="<%=i%>" <%=i==pageBean.getCurrentPage()?"selected":"" %>><%=i %></option>
						<%
							}
						%>
					</select>
				</td>
			</tr>
		</table>
		</form>
</body>
</html>
