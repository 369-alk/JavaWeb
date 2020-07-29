<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb18030" />
<title>左侧菜单</title>
<link href="Style/Left.css" rel="stylesheet" type="text/css" />
<script language="JavaScript">
//菜单点击用方法--隐藏
function hideAll() 
{
    if (document.all.list.length==null)
    {
        list.style.display="none";
    }
    else
    {
        for(i=0;i<list.length;i++) 
        {
            list[i].style.display="none";
        }
    }
}

//菜单点击用方法--显示
function showobj(num) 
{
    if (document.all.list.length==null)
    {
        if (list.style.display=="none") 
        {
            hideAll();
            list.style.display="inline";
        }
        else 
        {
            list.style.display="none";
        }
    }
    else
    {
        if (list[num].style.display=="none") 
        {
            hideAll();
            list[num].style.display="inline";
        }
        else 
        {
            list[num].style.display="none";
        }
    }
}
</script>
<style type="text/css">
/*<![CDATA[*/
.yincang {
	display:none;
}
/*]]>*/
</style>
</head>
<body>
<div>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="leftMenu">	
		<tr onclick="showobj(0)">
			<td><a target="mainFrame">管理中心</a></td>
		</tr>
		<tr id="list" style="display:none;">
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td><a href="News/CategoryList.jsp" target="mainFrame">类别管理</a></td>
					</tr>
					<tr>
						<td><a href="News/NewsList.jsp" target="mainFrame">新闻管理</a></td>
					</tr>
				</table>
			</td>
		</tr>	
		<tr onclick="showobj(1)">
			<td><a href="<%=request.getContextPath()%>/logout" target="_parent">退出</a></td>
		</tr>
	</table>
</div>
</body>
</html>
