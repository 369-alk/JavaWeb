package cn.sdcet.news.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sdcet.news.dao.NewsDao;
import cn.sdcet.news.dao.ReplyDao;

public class DeleteNewsServlet extends HttpServlet {

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
		//1.
		int id = Integer.parseInt(request.getParameter("id"));
		
		//2.
		//TODO:修改
		ServletContext sc = request.getServletContext();
		NewsDao dao = (NewsDao) sc.getAttribute("newsDao");
		dao.delete(id);
		
		//删除对应的回复
		ReplyDao replyDao = (ReplyDao) sc.getAttribute("replyDao");
		replyDao.deleteByNewsId(id);
		
		//3.
		//  /manage/deleteNews
		//  /manage/News/NewsList.jsp
		response.sendRedirect(request.getContextPath() + "/manage/News/NewsList.jsp");
	}

}
