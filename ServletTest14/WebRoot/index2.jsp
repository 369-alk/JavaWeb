<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.servlet.web.test.JDBCtest" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>富文本编辑器</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="${request.contextPath }/kindeditor/themes/default/default.css">
	<script type="text/javascript" src="${request.contextPath }/script/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="${request.contextPath }/kindeditor/kindeditor-all.js"></script>
	<script type="text/javascript" src="${request.contextPath }/kindeditor/lang/zh-CN.js"></script>
	
	<script type="text/javascript">
		$(function() {
			var editor = KindEditor.create("textarea[name='content']",  {
                uploadJson : '${pageContext.request.contextPath }/upload',
        	});
		});
	</script>

  </head>
  
  <body>
  		<form action="info.jsp">
  			<textarea name="content"></textarea>
  			<input type="submit" value="提交">
  		</form>


  </body>
</html>
