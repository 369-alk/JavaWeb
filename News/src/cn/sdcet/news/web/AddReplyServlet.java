package cn.sdcet.news.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sdcet.news.dao.ReplyDao;
import cn.sdcet.news.domain.Reply;

/**
 * ����ظ�
 */
public class AddReplyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1. ��ȡ�û��ύ����Ϣ
		String content = request.getParameter("content");
		String name = request.getParameter("username");
		int newsId = Integer.parseInt(request.getParameter("newsId"));
		
		//2.���浽���ݿ�
		ServletContext sc = request.getServletContext();
		ReplyDao dao = (ReplyDao) sc.getAttribute("replyDao");
		
		Reply reply = new Reply();
		reply.setAuthor(name);
		reply.setContent(content);
		reply.setPubDate(new Date());
		dao.save(newsId, reply);
		
		//3. ��������ҳ��
		response.sendRedirect("Info.jsp?id=" + newsId);
	}

}
