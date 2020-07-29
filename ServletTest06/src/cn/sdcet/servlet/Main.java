package cn.sdcet.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Main
 */
@WebServlet("/main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		ServletContext context = getServletContext();
		String index = null;
		String message = null;
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html><head><title>");
		out.print("��ҳ��");
		out.print("</title></head><body>");
		if (username == null && password == null) {
			index = "<font size=\"4\">��ҳ</font>";
			message = "��ӭ��,�ο͡�<a href = login>��¼</a>��";
			context.setAttribute("index", index);
			context.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("index");
			rd.include(request, response);
			
		} else if ("admin".equals(username) && "123456".equals(password)) {
			index = "<font size=\"4\">��ҳ</font>";
			message = "ף����,��½�ɹ���<br>��<a href = logout>�˳�</a>��";
			context.setAttribute("index", index);
			context.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("index");
			rd.forward(request, response);
		} else{
			out.print("<font color=\"red\" size=\"4\">��¼ʧ�ܣ����������룡</font><br>");
			RequestDispatcher rd = request.getRequestDispatcher("login");
			rd.include(request, response);
			
		}
		out.print("</body></html>");
		out.flush();
		out.close();
	}

}
