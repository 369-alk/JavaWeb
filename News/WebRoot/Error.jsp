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
		<div class="error">
			<p>出错啦。。。</p>
			<p><a href="index.htm">返回首页，查看其它的文章</a></p>
		</div>
	</div>
	<!--[if !IE]>main 结束<![endif]-->
	
	<jsp:include page="common/footer.jsp" />
</div>
</body>
</html>