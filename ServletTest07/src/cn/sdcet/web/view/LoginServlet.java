package cn.sdcet.web.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message = (String) request.getAttribute("message");
		String name = request.getParameter("username");
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html><head><title>");
		out.print("��ҳ��");
		out.print("</title></head><body>");
		out.print("<form action=\"check\" method = \"post\">");
		out.print("	<table>");
		out.print("   <caption>�û���¼</caption>");
		
		if (message != null) {
			out.print("		<tr align=\"center\">");
			out.print("			<td colspan=\"2\" style=\"color: red\">" + message + "</td>");
			out.print("		</tr>");
		}
		
		out.print("		<tr>");
		out.print("			<td>�û���:</td>");
		out.print("			<td><input type = \"text\" name = \"username\" value=" + (name == null?"":name) + "></td>");
		out.print("		</tr>");
		out.print("		<tr>");
		out.print("			<td>��  ��:</td>");
		out.print("			<td><input type = \"password\" name = \"password\"></td>");
		out.print("		</tr>");
		out.print("		<tr>");
		out.print("			<th colspan=\"2\"><input type = \"submit\" value = \"��¼\"></th>");
		out.print("		</tr>");
		out.print("	</table>");
		out.print("</form>");
		out.print("</body></html>");
		out.flush();
		out.close();
	}

}
