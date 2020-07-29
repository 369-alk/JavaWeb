<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page errorPage=""%>
<%-- 
	Jsp的三种注释：
		前端语言注释：<!--  -->
			会被转译，也会被发送，但是不会被浏览器执行；
		Java语言注释：
			会被转译，但是不会被servlet执行；
		Jsp注释：
			不会被转译。
			
	Jsp的page指令：
		<%@page 属性名="属性值" 属性名="属性值"... %>
			language：声明Jsp要被转译的语言。
			import：声明转译的java文件要导入的包，不同的包使用逗号隔开。
			pageEncoding：设置jsp文件的数据的编码格式。
			contentType="text/html; charset=UTF-8"：设置jsp数据响应给浏览器时，浏览器的解析和编码格式。
			session：设置转移的servlet中是否开启session支持，默认开启，true表示开启，false表示关闭。
			errorPage：设置jsp运行错误跳转的页面。
			extends：设置jsp转译的java文件要继承的父类(包名+类名)。
		作用：
			配置jsp文件的转译相关的参数。
	
	Jsp的局部代码块：
		特点：
			局部代码块中声明的java代码会被原样转译到jsp对应的servlet文件的_JspService方法中
			代码块声明的变量都是局部变量。
		使用：
			<% java局部代码  %>
		缺点：
			使用局部代码块在jsp中进行逻辑判断，书写麻烦，阅读困难。
		开发：
			servlet进行请求逻辑处理，使用jsp进行页面展现。
			
	Jsp的全局代码块：
		特点：
			声明的java代码作为全局代码块转译到对应的servlet类中。
		使用：
			<%！ java局部代码   %>
		注意：
			全局代码块声明的代码，需要使用局部代码块调用。
	
	Jsp的脚本段语句：
		特点：
			帮助我们快速的获取变量或者方法的返回值作为数据响应给浏览器。
		使用：
			<%= 变量名或方法 %>
		注意：
			不要在变量名或方法后使用分号。
		位置：
			除了jsp语法要求以外的任意位置。
			
	Jsp的静态引入和动态引入：
		静态引入：
			特点：
				会将引入的jsp文件的当前jsp文件转译成一个java(Servlet)文件使用，
				在网页中也就显示了合并后的显示效果。
			使用：
				<%@include file="要引入的jsp文件的相对路径" %>
			注意：
				静态引入的jsp文件不会单独转译成java(Servlet)文件。
				当前文件和静态引入的jsp文件中不能够使用java代码块声明同名变量。
		动态引入：
			特点：
				会将引入的jsp文件单独转译，在当前文件转译好的java文件中调用引入的jsp文件的转译文件。
				在网页中显示合并后的显示效果。
			使用：
				<jsp:include page="要引入的jsp文件的相对路径"></jsp:include>
			注意：
				动态引入允许文件中声明同名变量。
			优点：
				降低jsp代码的冗余，便于维护升级。
				
	Jsp的转发标签forward：
		特点：
			一次请求，地址栏信息不变。
		使用：
			<jsp:forward page="要转发的jsp文件的相对路径"></jsp:forward>
		注意：
			在转发标签的两个标签中间除了写<jsp:param value="a" name="str"/>子标签不会报错，其它任意字符都会报错。
			name属性为附带的数据的键名；
			value为附带的数据内容。
			注意：会将数据以？的形式拼接在转发路径的后面。
	
	Jsp的九大内置对象：
		内置对象：
			jsp文件在转译成其对应的servlet文件的时候自动生成的并声明的对象，我们在jsp页面中直接使用即可。
		注意：
			内置对象在jsp页面中使用，使用局部代码块或脚本段语句来使用，不能够在全局代码块中使用。
		内容：九个对象
			pageContext：页面上下文对象，封存了其它内置对象，也就是封存了当前jsp运行信息。
				注意：每个jsp文件单独拥有一个pageContext对象。
				作用域：当前页面。
			request：封存当前请求数据对象，由tomcat服务器创建，一次请求。
			session：此对象用来存储用户的不同请求的共享数据的，一次会话。
			application：也就是ServletContext对象，一个项目只有一个，存储用户共享数据的对象，以及完成其它操作。项目内
			response：响应对象，用来响应请求处理结果给浏览器的对象。设置响应头，重定向。
			out：响应对象，Jsp内部使用，带有缓冲区的响应对象，效率高于response对象。
			page：代表当前jsp的对象，相当于java中的this。
			exception：异常对象，存储了当前运行的异常信息。
				注意：使用此对象需要在page指定中使用属性isErrorPage = "true"开启。
			config：也就是ServletConfig，主要是用来获取web.xml中的配置数据。完成一些初始化数据的读取。
			
	四个作用域对象：
		pageContext：当前页面，解决了在当前页面内的数据共享问题。
		request：一次请求，一次请求的servlet的共享数据。通过请求转发，将数据流转给下一个servlet。
		session：一次会话，一个用户的不同请求的数据共享。将数据从一次请求流转给其它请求。
		application：项目内，不同用户的数据共享问题，将数据从一个用户流转给其他用户。
		作用：
			数据流转。
	
	Jsp的路径：
		在jsp中资源路径可以使用相对路径完成跳转，但是：
			问题一：资源的位置不可随意更改。
			问题二：需要使用../进行文件夹的跳出，使用比较麻烦。
		使用绝对路径：(必须会)
			/虚拟项目名/项目资源路径
			注意：
				在jsp中资源的第一个/表示的是服务器根目录，相当于：localhost:8080
		使用jsp自带的全局路径声明：
			<%
				String path = request.getContextPath();
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			%>
			<base href="<%=basePath%>">
			作用：
				给资源前面添加项目路径： http://localhost:8080/虚拟项目名
				
				
--%>

<html>
	<head>
		<title>Jsp基本语法</title>
		<meta charset="utf-8">
	</head>
	<body>
		<h2>Jsp基本语法学习</h2>
		<hr>
		<!-- 局部代码块 -->
		<% 
			//声明java代码域--局部代码块声明
			String str = "jsp使用逻辑校验很难受";
			int a = 2;
			if(a>3){
		 %>
		 	<b>jsp学习很简单</b>
		 <% } else{ %>
		 	<i><%= str %></i>
		 <% }%>
		 
		 <!-- 全局代码块 -->
		 <%!
		 	//声明java代码域--全局代码块声明
		 	public void test(){
		 		System.out.println("我是全局代码块声明");
		 	}
		 %>
		 
		 <!-- jsp的静态引入 -->
		 <%@include file="includeStatic.jsp" %>
		 
		 <!-- jsp的动态引入 -->
		 <jsp:include page="includeActive.jsp"></jsp:include>
		 
		 <!-- jsp的转发forward标签 -->
		 <%--
		 <jsp:forward page="forward.jsp">
		 	<jsp:param value="a" name="str"/>
		 </jsp:forward>
		 --%>
		 
		 <!-- jsp的九大对象 -->
		 <%
		 	String s = request.getParameter("str");
		 	request.getParameter("str"); 
		 %>
		 <%=s %>
		 <%
		 	//response.sendRedirect("forward.jsp");
		 %>
		 
		 <!-- jsp的路径 -->
		 <br>
		 <a href="a/a.jsp">a.jsp</a><br>
		 <a href="a/b/b.jsp">b.jsp</a><br>
		 
		 
		 
	</body>
</html>


