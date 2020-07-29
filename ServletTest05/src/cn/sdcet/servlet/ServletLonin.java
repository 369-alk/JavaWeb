package cn.sdcet.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletLonin
 */
@WebServlet("/login")
public class ServletLonin extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String message = "";
		if ("admin".equals(username) && "123456".equals(password)) {
			message = "你好，管理员！";
		} else {
			message = "对不起，您没有访问的权限！请点击<a href=\"login.html\">这里</a>登陆";
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
