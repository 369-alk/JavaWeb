<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="header">
	<div id="logo">
		<a href="Index.jsp" title="新闻发布系统">新闻发布系统</a>
	</div>
	<!--[if !IE]>logo 结束<![endif]-->
	<div class="search">
		<form id="form1" method="post" action="searchNews">
			<input type="text" name="key" id="key" class="iText" />
			<select name="type">
				<option value="title" selected="selected">标题</option>
				<option value="content">内容</option>
			</select> 
			<input type="submit" name="Submit" class="btn" value="搜索" />
		</form>
	</div>
	<!--[if !IE]>search 结束<![endif]-->
</div>
<!--[if !IE]>header 结束<![endif]-->