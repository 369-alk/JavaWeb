<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>top</title>
<link href="Style/Top.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="top">
    <div id="time">您好，今天是<span id="date">2007年7月5日&nbsp;星期四</span></div>
    <script type="text/javascript">
			var d=new Date()
			document.getElementById("date").innerHTML=d.getFullYear()+"年"+(d.getMonth()+1)+"月"+d.getDate()+"日"+" 星期"+ "天一二三四五六 ".charAt(d.getDay());
		</script>
</div>
<div id="menuBg">
    <div id="name">管理员</div>
    <div id="menu">
		<a href="Main.htm" target="mainFrame" id="home">首页</a>
		<a href="Login.htm" target="_parent" id="quit">退出</a>	</div>
</div>
</body>
</html>
