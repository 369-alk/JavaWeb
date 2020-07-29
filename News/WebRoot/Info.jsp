<%@page import="cn.sdcet.news.domain.Reply"%>
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
<jsp:useBean id="newsDao" class="cn.sdcet.news.dao.jdbc.NewsDaoJDBCImpl" scope="application"></jsp:useBean>
<jsp:useBean id="replyDao" class="cn.sdcet.news.dao.jdbc.ReplyDaoJDBCImpl" scope="application"></jsp:useBean>

<div id="wrapper">
	<jsp:include page="common/header.jsp" />
	
	<%
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int id = Integer.parseInt(request.getParameter("id"));
		News news = newsDao.findById(id);
	%>
	<div id="main">
		<div class="doc-info-view">
			<div class="hd"><h1><%=news.getTitle()%></h1></div>
			<hr class="double" />
			<!--[if !IE]>文章属性<![endif]-->
			<div class="doc-parameter">
				<div>作者：<%=news.getAuthor() %></div>
				<div><%=sdf.format(news.getPubDate())%></div>
				<div>来源：<%=news.getSource()%></div>
			</div>
			<!--[if !IE]>正文<![endif]-->
			<div class="doc-text">
				<%=news.getContent()%>
			</div>
			<!--[if !IE]>评论<![endif]-->
			<hr class="double" />
			<div class="comment">
				<div class="hd"><h3>最新评论</h3></div>
				<!--[if !IE]>评论列表 开始<![endif]-->
				<ol class="com-list">
				
					<%
						int idx = 1;
						List<Reply> replies = replyDao.findByNewsId(id);
						for(Reply reply : replies) {
					%>
					<li>
						<p class="title wrapfix">
							<span class="num"><%=idx++%>.</span>
							<span class="name"><%=reply.getAuthor()%></span>
							<span class="time"><%=sdf.format(reply.getPubDate())%></span>
						</p>
						<div class="com-body">
							<%=reply.getContent()%>
						</div>
					</li>
					<%
						}
					%>
				</ol>
				<!--[if !IE]>评论列表 结束<![endif]-->
				<!--[if !IE]>填写评论 开始<![endif]-->
				<div class="com-form">
				<hr class="double" />
					<div class="hd"><h3>发表评论</h3></div>
					<p class="tips">请自觉遵守互联网相关政策法规，评论不得超过250字。</p>
					<form id="form1" method="post" action="addReply">
						<p><textarea name="content" id="content" rows="5" class="textarea"></textarea></p>
						<p>
							<label for="username">昵称</label><input type="text" name="username" size="10" id="username" class="iText" />
							<input type="submit" name="Submit" class="btn" value="发表评论" />
						</p>
						<input type="hidden" name="newsId" value="<%=id%>"/>
					</form>
				</div>
				<!--[if !IE]>填写评论 开始<![endif]-->
			</div>
		</div>
		<!--[if !IE]>新闻详情 结束<![endif]-->
	</div>
	<!--[if !IE]>main 结束<![endif]-->
	
	<jsp:include page="common/footer.jsp" />
</div>
</body>
</html>