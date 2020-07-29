package cn.sdcet.news.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sdcet.news.dao.NewsDao;
import cn.sdcet.news.domain.News;

public class SearchNewsServlet extends HttpServlet {

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
		//1. ��ȡ�û��ύ����Ϣ
		String type = request.getParameter("type");
		String key = request.getParameter("key");
		
		//2. ������Ϣ�������ݿ�
		ServletContext sc = request.getServletContext();
		NewsDao dao = (NewsDao) sc.getAttribute("newsDao");
		List<News> newsList = new ArrayList<News>();
		if("title".equals(type)) {
			newsList = dao.findByCondition(key, "");
		} else if("content".equals(type)) {
			newsList = dao.findByCondition("", key);
		}
		
		//3. ��Search.jsp����ʾ���������ת����
		request.setAttribute("newsList", newsList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Search.jsp");
		dispatcher.forward(request, response);
	}

}
