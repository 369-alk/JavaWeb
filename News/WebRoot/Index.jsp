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
			
			<%
				List<News> newsList = newsDao.getTopNewsList();
			
				HashMap<Integer, List<News>> newsMap = new HashMap<Integer, List<News>>();
				for(News news : newsList) {
					int catalogId = news.getCatalog().getId();
					if(newsMap.containsKey(catalogId)) {
						List<News> value = newsMap.get(catalogId);
						value.add(news);
					} else {
						List<News> value = new ArrayList<News>();
						value.add(news);
						
						newsMap.put(catalogId, value);
					}
				}
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				for(List<News> list : newsMap.values()) {
			%>
			<div class="newslist">
				<div class="hd"><h3><%=list.get(0).getCatalog().getName()%></h3></div>
				<div class="bd">
					<ul class="list">
						<%
							for(News n : list) {
						%>
						<li>
							<a href="Info.jsp?id=<%=n.getId()%>" target="_blank"><%=n.getTitle()%></a>
							<span class="date"><%=sdf.format(n.getPubDate())%></span>
						</li>
						<%
							}
						%>
					</ul>
				</div>
			</div>
			<!--[if !IE]>newslist 结束<![endif]-->
			<%
				}
			%>
			
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