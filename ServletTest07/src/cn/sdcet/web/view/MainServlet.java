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
		out.print("<h2>������</h2><br>");
		
		if (name == null) {
			out.print("��ӭ��,�ο͡�<a href = login>��¼</a>��");
		} else {
			out.print("��ӭ�㣬"+ name +"��<a href = logout>�˳�</a>��");
		}
		
		out.print("</body></html>");
		out.flush();
		out.close();
	}

}
