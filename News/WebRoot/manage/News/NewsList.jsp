<%@page import="java.text.SimpleDateFormat"%>
<%@page import="cn.sdcet.news.domain.News"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb18030" />
<meta http-equiv="Content-Language" content="zh-CN" />

<title>新闻列表</title>
<link href="../Style/Css.css" rel="stylesheet" type="text/css" />
</head>

<body>
<jsp:useBean id="newsDao" class="cn.sdcet.news.dao.jdbc.NewsDaoJDBCImpl" scope="application"></jsp:useBean>

<h3 class="subTitle">新闻资讯</h3>
<ul class="button">
	<li><a href="NewsAdd.jsp">添加新闻</a></li>
</ul>

<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" class="table">
	<tr>
	    <th width="8%" nowrap="nowrap">序号</th>
	    <th nowrap="nowrap">标题</th>
	    <th width="15%" nowrap="nowrap">日期</th>
	    <th width="12%">修改</th>
	    <th width="12%">删除</th>
	</tr>

	<%
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int idx = 1;
		List<News> newsList = newsDao.findAll();
		for(News news : newsList) {
	%>
	<tr>
	    <td align="center"><%=idx++%></td>
	    <td align="center"><%=news.getTitle()%></td>
	    <td align="center"><%=sdf.format(news.getPubDate())%></td>
	    <td align="center"><a href="NewsModify.jsp?id=<%=news.getId()%>">修改</a></td>
	    <td align="center">
	    	<a href="<%=request.getContextPath()%>/manage/deleteNews?id=<%=news.getId()%>" onclick="return confirm('是否要删除这条新闻？')">删除</a>
	    </td>
	</tr>
	<%
		}
	%>
</table>

<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" class="page">
	<tr>
		<td width="50%" align="left">共有25条记录，<span style="font-family:宋体; font-size:9.0pt; color:black; ">第</span><span style="font-family:Tahoma; font-size:9.0pt; color:black; "> 1/3 </span><span style="font-family:宋体; font-size:9.0pt; color:black; ">页</span></td>
		<td width="50%" align="right"><a href="#">首页</a> <a href="#">上一页</a> <a href="#">下一页</a> <a href="#">末页</a>跳转到
	    <select name="select2">
	      <option>1</option>
	      <option>2</option>
	      <option>3</option>
	      <option>4</option>
	      <option>5</option>
	      <option>6</option>
	    </select>
		</td>
	</tr>
</table>

</body>
</html>
