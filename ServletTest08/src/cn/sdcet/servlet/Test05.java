package cn.sdcet.servlet;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Servlet implementation class Test05
 */
@WebListener
public class Test05 implements HttpSessionListener {

	private static int count = 0;
	
	@Override
	public void sessionCreated(HttpSessionEvent hse) {

		count++;
		ServletContext context = hse.getSession().getServletContext();
		context.setAttribute("count", new Integer(count));
		context.log("当前在线人数：" + count + "人");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent hse) {
		
		count--;
		ServletContext context = hse.getSession().getServletContext();
		context.setAttribute("count", new Integer(count));
		context.log("当前在线人数：" + count + "人");
		
	}

}
