package cn.sdcet.news.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import cn.sdcet.news.dao.UserDao;
import cn.sdcet.news.dao.jdbc.UserDaoJDBCImpl;
import cn.sdcet.news.domain.User;
import cn.sdcet.news.utils.Constants;

public class LoginCheckServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("username");
		String passwd = request.getParameter("password");
		passwd = DigestUtils.md5Hex(passwd + Constants.SALT);
		System.out.println("password = " + passwd);
		
		HttpSession session = request.getSession();
		UserDao dao = new UserDaoJDBCImpl();
		if(dao.hasMatchUser(name, passwd)) {
			//用户登陆成功
			User user = dao.findByLoginName(name);
			session.setAttribute("user", user);
			
			response.sendRedirect("manage/Index.jsp");
		} else {
			//用户登陆失败
			request.setAttribute("message", "用户名或密码不正确");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
			dispatcher.forward(request, response);
		}
	}

}
