package cn.sdcet.web.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html><head><title>");
		out.print("");
		out.print("</title></head><body>");
		out.print("<h2>主界面</h2><br>");
		
		if (name == null) {
			out.print("欢迎你,游客【<a href = login>登录</a>】");
		} else {
			out.print("欢迎你，"+ name +"【<a href = logout>退出</a>】");
		}
		
		out.print("</body></html>");
		out.flush();
		out.close();
	}

}
