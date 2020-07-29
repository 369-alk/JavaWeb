<%@page import="cn.sdcet.news.domain.Catalog"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta http-equiv="Content-Language" content="zh-CN" />
<title>添加新闻类别</title>
<link href="../Style/Css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
  String.prototype.Trim  =  function()
  {
		return  this.replace(/(^\s*)|(\s*$)/g,"");  
	}
	function back()
	{
		document.all.form1.action="CategoryList.htm";
		document.form1.submit();
	}
</script>
</head>

<body>
<jsp:useBean id="catalogDao" class="cn.sdcet.news.dao.jdbc.CatalogDaoJDBCImpl" scope="application"></jsp:useBean>

<%
	int catalogId = Integer.parseInt(request.getParameter("id"));
	Catalog catalog = catalogDao.findById(catalogId);
%>
<h3 class="subTitle">新闻类别</h3>

<form id="form1" name="form1" action="<%=request.getContextPath()%>/manage/updateCatalog">	
	<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" class="table2">			
  		<tr>
          <th width="15%" align="right">类别名称</th>
          <td><input name="name" type="text" class="input1" value="<%=catalog.getName() %>" /></td>
      </tr>
  		<tr>
          <th width="15%" align="right">类别描述</th>
          <td><input name="description" type="text" class="input1"  value="<%=catalog.getDescription()%>" /></td>
      </tr>
    </table>
    <table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" class="yesno">
        <tr>
            <td height="50" align="center">
            	<input type="submit" name="Submit1" value="修改"/> 
           		<input type="reset" name="Submit2" value="重置" />
           		<input type="button" name="Submit3" value="取消" onclick="back()"/>
           	</td>
        </tr>
    </table>
    <input type="hidden" name="id" value="<%=catalog.getId()%>" />
</form>
</body>
</html>
