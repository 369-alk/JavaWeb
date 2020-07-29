package cn.sdcet.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class URLtest
 */
@WebServlet(urlPatterns = "*.action", loadOnStartup = 1)
public class URLtest extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = request.getRequestURI();
		String message = "";
		
		if (url.contains("/manage")) {
			message = "请以管理员身份登录";
		} else {
			message = "欢迎访问我的网站";
		}
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html><head><title>");
		out.print("欢迎页面");
		out.print("</title></head><body>");
		out.print(message);
		out.print("</body></html>");
		out.close();
	}

}
