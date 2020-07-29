package cn.sdcet.news.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sdcet.news.dao.NewsDao;
import cn.sdcet.news.dao.jdbc.NewsDaoJDBCImpl;
import cn.sdcet.news.domain.Catalog;
import cn.sdcet.news.domain.News;

public class AddNewsServlet extends HttpServlet {

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
		int catalogId = Integer.parseInt(request.getParameter("catalogId"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String author = request.getParameter("author");
		String source = request.getParameter("source");
		
		//2.
		ServletContext sc = request.getServletContext();
		NewsDao dao = (NewsDao) sc.getAttribute("newsDao");
		if(dao == null) {
			dao = new NewsDaoJDBCImpl();
			sc.setAttribute("newsDao", dao);
		}
		
		News news = new News(null, title, content, author, new Date(), source);
		Catalog catalog = new Catalog();
		catalog.setId(catalogId);
		news.setCatalog(catalog);
		dao.save(news);
		
		//3.
		response.sendRedirect(request.getContextPath() + "/manage/News/NewsList.jsp");
	}

}
