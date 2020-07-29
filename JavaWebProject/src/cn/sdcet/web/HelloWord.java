package cn.sdcet.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/hello")
public class HelloWord extends HttpServlet {
/**
 * doGet和doPost方法：
 * 		这两个方法是servlet的重要方法，doGet的调用方式有三种：1.在浏览器直接输入url并访问；2.点击超级链接或转发；3.表单的提交方式为get。
 * 			doPost的调用方式只有一种：表单的提交方式为post。
 * 		这两个方法有两个重要的参数分别是request和response(请求和响应)
 * 			request方法：客户端向服务器发出的请求。
 * 			response方法：服务器响应给客户端的内容，就是显示在浏览器的内容。
 * 		request常用方法：
 * 			getParameter()  获取指定参数，一般用来获取表单中的数据
 * 			setAttribute()  存储数据
 * 			getAttribute()	获取数据
 * 		response常用方法：
 * 			getWriter()  向客户端发送字符数据  PrintWriter out = response.getWriter();
 *			setContentType()  设置响应数据的MIME类型  response.setContentType("text/html;charset=UTF-8");
 *
 * Web3.1新特性：
 * 		@WebServlet("/hello")此句相当于web.xml文件中的<servlet>，就是用来配置此servlet文件
 * 			""里的内容是servlet文件映射，相当于<url-pattern>  注意：/  不可省略
 * 
 * 		配置初始化参数：
 * 		@WebServlet(value = "/InitParamServlet", initParams={
 * 			@WebInitParam(name = "username", value = "javaweb"),
 * 			@WebInitParam(name = "password", value = "123456")
 * 		})
 * 
 * 		配置过滤器：
 * 		@WebFilter("/*")
 * 
 * 		配置监听器
 * 		@WebListener
 * 
 * Servlet基础：
 * 		当用户第一次请求的时候，Web容器会创建Servlet实例。
 * 		整个web容器中，servlet实例是唯一的。
 * 		由于servlet在容器中只要一个实例，所以不要在实例变量中保存特定用户相关的信息。
 * 		三大对象：request、session、servletContext (请求、会话、上下文)
 * 			共有方法：setAttribute() getAttribute()  分别是存储数据和获取数据
 * 			
 * 			请求对象范围最小，从客户端发送请求开始到响应信息回到客户端(当响应信息回来以后，请求对象就会被销毁)
 * 			会话对象一般代表一个用户
 * 			整个web应用中上下文对象是唯一的，适合于保存和特定应用相关的信息(如当前在线人数)
 * 
 * 		ServletConfig对象：
 * 			通过配置web.xml文件，来给Servlet初始化参数
 * 			主要用法：
 * 				ServletConfig config = getServletConfig();
 * 				getInitParameter()  获取初始化的参数
 * 
 * 		Session对象：
 * 			主要用法：
 * 				HttpSession session = request.getSession();
 * 				setAttribute()  存储数据
 * 				getAttribute()	获取数据
 *				invalidate()    销毁会话
 *
 *		ServletContext对象：
 *			主要用法：
 *				ServletContext context = getServletContext();
 *				setAttribute()  存储数据
 * 				getAttribute()	获取数据
 * 
 * 请求转发与重定向：
 * 		当一个任务没有完成，需要其它Web组件继续处理的话，用转发。(查询页面->查询结果界面)
 * 		当一个功能完成，进入另外一个功能的时候，用请求重定向。(登录成功->主界面)
 * 		注意：
 * 			被包含的资源输出后不能关闭输出流
 * 			转发以后地址栏中的路径不变
 * 			重定向是两次请求，地址栏中显示的是重定向资源的URL
 * 			转发只能转发给同一Web资源，根路径里面隐含上下文路径
 * 			重定向可以达到任意资源
 * 		转发：
 * 			RequestDispatcher dispatcher = request.getRequestDispatcher("要转发的文件的相对路径");
 *			dispatcher.forward(request, response);  比较常用
 *			dispatcher.include(request, response);  条件苛刻，一般用不到
 *		重定向：
 *			response.sendRedirect("要重定向的文件的相对路径或绝对路径")
 *
 * 过滤器：
 * 		可以为任意Web资源提供服务，能够拦截客户端与目标资源之间的请求和响应信息，
 * 		并对这些信息进行过滤。
 * 		使用：
 * 			实现Filter接口，在web.xml中配置Filter。
 * 			实现init()、destory()、doFilter()方法
 * 			init()方法：初始化方法，servlet容器在创建Filter实例后将调用这个方法。
 * 			destory()方法：销毁方法，servlet容器在销毁Filter实例前调用该方法，通常在该方法中释放过滤器占用的资源。
 *			doFilter()方法：该方法完成实际的过滤工作。  chain.doFilter(request, response);  放行
 *
 * 监听器：
 * 		可以实现ServletContextListener接口、HttpSessionListener接口、ServletRequestListener接口，并重写其方法。
 * 		ServletContextListener接口：
 * 			contextDestroyed()方法：关闭web应用时，web容器用这个方法。
 * 			contextInitialized()方法：启动web应用时，web容器用这个方法。
 * 		HttpSessionListener接口：
 * 			sessionCreated()方法：每当一个HttpSession对象创建时，web容器调用这个方法。
 * 			sessionDestroyed()方法：每当一个HttpSession对象销毁时，web容器调用这个方法。
 * 		ServletRequestListener接口：
 * 			requestInitialized()方法：当web请求即将进入过滤器链中的第一个过滤器时，web容器调用这个方法。
 * 			requestDestroyed()方法：当web请求从第一个过滤器返回时，web容器调用这个方法。

 * 
 */


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext context = getServletContext();
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html><head><title>");
		out.print("");
		out.print("</title></head><body>");
		out.print("");
		out.print("</body></html>");
		out.flush();
		out.close();
	}

}
