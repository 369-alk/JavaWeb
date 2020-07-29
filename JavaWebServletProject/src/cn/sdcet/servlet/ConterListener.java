package cn.sdcet.servlet;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Servlet implementation class ConterListener
 */
@WebServlet("/ConterListener")
@WebListener()
public class ConterListener implements HttpSessionListener {
	private static final long serialVersionUID = 1L;
	private static int count = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConterListener() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         count++;
         ServletContext context = se.getSession().getServletContext();
         context.setAttribute("count", new Integer(count));
         context.log("当前在线人数:" + count + "人");
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	count--;
        ServletContext context = se.getSession().getServletContext();
        context.setAttribute("count", new Integer(count));
        context.log("当前在线人数:" + count + "人");
    }

}
