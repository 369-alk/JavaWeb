package cn.sdcet.servlet;

import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet implementation class LogFilter
 */
@WebServlet("/LogFilter")
//@WebFilter("/*")
public class LogFilter extends HttpServlet implements Filter {
	private static final long serialVersionUID = 1L;
	private FilterConfig config;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig config) throws javax.servlet.ServletException { 
         this.config = config;
    }

	/**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws java.io.IOException, javax.servlet.ServletException { 
         HttpServletRequest req = (HttpServletRequest) request;
         String username = req.getParameter("username");
         if (username == null || !username.equalsIgnoreCase("admin")) {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<html><head><title>");
			out.print("禁止访问");
			out.print("</title></head><body>");
			out.print("对不起，" + username + "您没有足够的权限!");
			out.print("</body></html>");
			out.close();
		} else {
			ServletContext context = config.getServletContext();
			context.log("--------开始过滤请求--------");
			context.log("客户端地址：" + req.getRemoteAddr());
			context.log("请求URL：" + req.getRequestURI());
			chain.doFilter(request, response);
			context.log("--------过滤请求结束--------");
		}
    }

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		this.config = null;
	}

}
