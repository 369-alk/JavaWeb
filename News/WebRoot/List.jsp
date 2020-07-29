<%@page import="cn.sdcet.news.utils.PageBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="cn.sdcet.news.domain.News"%>
<%@page import="cn.sdcet.news.domain.Catalog"%>
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
<jsp:useBean id="catalogDao" class="cn.sdcet.news.dao.jdbc.CatalogDaoJDBCImpl" scope="application"></jsp:useBean>
<jsp:useBean id="newsDao" class="cn.sdcet.news.dao.jdbc.NewsDaoJDBCImpl" scope="application"></jsp:useBean>

<div id="wrapper">
	<jsp:include page="common/header.jsp" />
	
	<div id="main" class="wrapfix">
		<div id="mostlyCon">
			<div class="newslist">
				<%
					String catalogName = request.getParameter("name");
				%>
				<div class="hd"><h3><%=catalogName%></h3></div>
				<div class="bd">
					<ul class="list">
					
						<%
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							int catalogId = Integer.parseInt(request.getParameter("id"));
							//List<News> newsList = newsDao.findByCatalogId(catalogId);
							
							int pageNum = 1;
							String strPage = request.getParameter("page");
							if(strPage != null) {
								pageNum = Integer.parseInt(strPage);
							}
							PageBean<News> pageBean = newsDao.findByCatalogId2(catalogId, pageNum);
							for(News news : pageBean.getRecordList()) {
						%>
						<li>
							<a href="Info.jsp?id=<%=news.getId()%>" target="_blank"><%=news.getTitle() %></a>
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
				<div class="total">共有<%=pageBean.getRecordCount() %>条记录&nbsp;当前<%=pageBean.getCurrentPage()%>/<%=pageBean.getPageCount() %>页</div>
				<div class="pn">
					<a href="?page=<%=pageBean.prevPage()%>&id=<%=catalogId%>&name=<%=catalogName%>" title="上一页" class="nobar">上一页</a>
					
					<%
						for(int i=pageBean.getBeginPageIndex(); i<=pageBean.getEndPageIndex();i++) {
					%>
					<a href="?page=<%=i%>&id=<%=catalogId%>&name=<%=catalogName%>" <%=i==pageBean.getCurrentPage()?"class=\"nonce\"":"" %>><%=i%></a>
					<%
						}
					%>
					
					<a href="?page=<%=pageBean.nextPage()%>&id=<%=catalogId%>&name=<%=catalogName%>" title="下一页" class="nobar">下一页</a>
				</div>
			</div>
			<!--[if !IE]>paging 结束<![endif]-->
		</div>
		<!--[if !IE]>mostlyCon 结束<![endif]-->
		<div id="sideCon">
			<div id="nav">
				<h3>新闻类别</h3>
				<ul>
					<%
						List<Catalog> catalogs = catalogDao.findAll();
						for(Catalog catalog : catalogs) {
					%>
					<li><a href="List.jsp?id=<%=catalog.getId()%>&name=<%=catalog.getName()%>"><%=catalog.getName()%></a></li>
					<%
						}
					%>
				</ul>
			</div>
		</div>
		<!--[if !IE]>sideCon 结束<![endif]-->
	</div>
	<!--[if !IE]>main 结束<![endif]-->
	
	<jsp:include page="common/footer.jsp" />
</div>
</body>
</html>