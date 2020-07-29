package cn.sdcet.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionTestServlet
 */
@WebServlet("/SessionTestServlet")
public class SessionTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionTestServlet() {
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
		request.setCharacterEncoding("UTF-8");
		String user = request.getParameter("username");
		HttpSession session = request.getSession();
		session.setAttribute("username", user);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html><head><title>");
		out.print("Session����ҳ��");
		out.print("</title></head><body>");
		out.print("�Ự״̬��");
		if (session.isNew()) {
			out.print("�µĻỰ<br>");
		} else {
			out.print("�ɵĻỰ<br>");
		}
		out.print("�ỰID:" + session.getId() + "<br>");
		out.print("����ʱ�䣺" + new Date(session.getCreationTime()) + "<br><br>");
		String username = (String) session.getAttribute("username");
		if (username != null) {
			out.print("��ӭ����" + username + "!<br><br>");
			out.print("<a href = LogoutServlet>ע��</a>");
		} else {
			out.print("<form method = post action = SessionTestServlet>");
			out.print("<table>");
			out.print("<tr><td>�û�����</td><td><input type = text name = username></td></tr>");
			out.print("<tr><td>���룺</td><td><input type = password name = password></td></tr>");
			out.print("<tr><td><input type = submit value = ��¼></td>");
			out.print("<td><input type = reset value = ����></td></tr>");
			out.print("</table></form>");
		}
		out.print("</body></html>");
		out.close();
	}

}
