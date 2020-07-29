<%@page import="cn.sdcet.news.domain.Catalog"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb18030" />
<meta http-equiv="Content-Language" content="zh-CN" />
<!-- 兼容IE9	-->
<meta http-equiv="X-UA-Compatible" content="IE=9" /> 

<title>添加新闻</title>
<link href="../Style/Css.css" rel="stylesheet" type="text/css" />

<script language="javascript">
	function check() {
		if (document.all.form1.title.value.Trim() == "") {
			alert("请填写标题!");
			return false;
		}

		return true;
	}

	function back() {
		document.all.form1.action = "NewsList.htm";
		document.form1.submit();
	}
</script>

	<!-- KindEditor	-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/kindeditor/themes/default/default.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/kindeditor/kindeditor-all.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/kindeditor/lang/zh-CN.js"></script>
	
	<script type="text/javascript">
		$(function() {
			var editor = KindEditor.create("textarea[name='content']",  {
                uploadJson : '<%=request.getContextPath()%>/manage/upload',
        	});
		});
	</script>
</head>
<body>
	<jsp:useBean id="catalogDao" class="cn.sdcet.news.dao.jdbc.CatalogDaoJDBCImpl" scope="application"></jsp:useBean>
	
	<h3 class="subTitle">添加新闻</h3>

	<form id="form1" name="form1" action="<%=request.getContextPath()%>/manage/addNews">
		<table width="90%" border="0" align="center" cellpadding="0"
			class="table2" cellspacing="0">
			<tr>
				<th width="15%" align="right">新闻类别</th>
				<td>
					<select name="catalogId">
					<%
						List<Catalog> catalogs = catalogDao.findAll();
						for(Catalog catalog : catalogs) {
					%>
						<option value="<%=catalog.getId()%>"><%=catalog.getName()%></option>
					<%
						}
					%>
					</select>
				</td>
			</tr>
			<tr>
				<th align="right">文章标题</th>
				<td><input name="title" type="text" class="input1" id="title" /></td>
			</tr>
			<tr>
				<th align="right">作者</th>
				<td><input name="author" type="text" class="input1" id="author" /></td>
			</tr>
			<tr>
				<th align="right">来源</th>
				<td><input name="source" type="text" class="input1" id="source" /></td>
			</tr>
			<tr>
				<th align="right" valign="top">文章内容</th>
				<td>
					<textarea name="content">
					</textarea>
				</td>
			</tr>
		</table>
		<table width="90%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="yesno">
			<tr>
				<td height="50" align="center">
					<input type="submit" name="Submit" value="添加" /> 
					<input type="reset" name="Submit2" value="重置" /> 
					<input type="button" name="Submit" value="取消" onclick="back()" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
