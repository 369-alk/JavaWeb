<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>新闻发布后台管理系统</title>
<script language="JavaScript">
function OC(){
	if(main_frm.cols=="0,*")
	{
    	main_frm.cols="177,*";
	}
	else
	{
    	main_frm.cols="0,*";
	}
}
</script>
</head>
<frameset rows="100,*,23" cols="*" frameborder="no" border="0" framespacing="0">
	<frame src="Top.jsp" name="topFrame" noresize="noresize" id="topFrame" title="topFrame" />
	<frameset name="main_frm" id="main_frm" cols="177,*" frameborder="no" border="0" framespacing="0">
		<frame src="Left.jsp" name="leftFrame" id="leftFrame" noresize="noresize" title="leftFrame" />
		<frameset name="mainFrame" id="mainFrame" cols="8,*" frameborder="no" border="0" framespacing="0">
			<frame src="Border.jsp" name="oc" frameborder="no" scrolling="no" noresize="noresize" id="oc" title="mainFrame" />
			<frame src="Main.jsp" name="mainFrame" frameborder="no" scrolling="yes" id="mainFrame" title="mainFrame" />
		</frameset>
	</frameset>
	<frame src="Copyright.jsp" name="copyrightFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>
