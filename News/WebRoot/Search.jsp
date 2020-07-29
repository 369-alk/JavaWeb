<%@page import="java.text.SimpleDateFormat"%>
<%@page import="cn.sdcet.news.domain.News"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新闻发布系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb18030" />
<meta http-equiv="Content-Language" content="zh-CN" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta name="Keywords" content="关键字" />
<meta name="Description" content="描述" />
<link rel="stylesheet" href="Style/Main.css" type="text/css" media="screen, projection"/>
</head>
<body>
<div id="wrapper">
	<jsp:include page="common/header.jsp" />
	
	<div id="main">
		<div class="newslist">
			<div class="hd"><h3>搜索结果</h3></div>
			<div class="bd">
				<ul class="list">
					<%
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						List<News> newsList = (List<News>)request.getAttribute("newsList");
						for(News news : newsList) {
					%>
					<li>
						<a href="Info.jsp?id=<%=news.getId()%>" target="_blank"><%=news.getTitle()%></a>
						<span class="date"><%=sdf.format(news.getPubDate())%></span>
					</li>
					<%
						}
					%>
				</ul>
			</div>
		</div>
		<!--[if !IE]>newslist 结束<![endif]-->
		<div class="paging wrapfix">
			<div class="total">共有200条记录&nbsp;当前1/10页</div>
			<div class="pn">
				<a href="search.htm" title="上一页" class="nobar">上一页</a>
				<a href="search.htm" class="nonce">1</a>
				<a href="search.htm">2</a>
				<a href="search.htm">3</a>
				<a href="search.htm">4</a>
				<a href="search.htm">5</a>
				<a href="search.htm">6</a>
				<a href="search.htm">7</a>
				<a href="search.htm">8</a>
				<a href="search.htm">9</a>
				<a href="search.htm">10</a>
				<a href="search.htm" title="下一页" class="nobar">下一页</a>
			</div>
		</div>
		<!--[if !IE]>paging 结束<![endif]-->

	</div>
	<!--[if !IE]>main 结束<![endif]-->
	
	<jsp:include page="common/footer.jsp" />
</div>
</body>
</html>