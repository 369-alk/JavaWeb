package cn.sdcet.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<form action=\"main\" method = \"post\">");
		out.print("	<table>");
		out.print("		<tr>");
		out.print("			<td>Login</td>");
		out.print("			<td>用户登录</td>");
		out.print("		</tr>");
		out.print("		<tr>");
		out.print("			<td>用户名</td>");
		out.print("			<td><input type = \"text\" name = \"username\"></td>");
		out.print("		</tr>");
		out.print("		<tr>");
		out.print("				<td>密码</td>");
		out.print("				<td><input type = \"password\" name = \"password\"></td>");
		out.print("			</tr>");
		out.print("			<tr>");
		out.print("			<td><input type = \"submit\" value = \"登录\"></td>");
		out.print("				<td><input type = \"reset\" value = \"重置\"></td>");
		out.print("		</tr>");
		out.print("	</table>");
		out.print("</form>");
	}

}
