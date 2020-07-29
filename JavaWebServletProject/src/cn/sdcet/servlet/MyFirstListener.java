package cn.sdcet.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class MyFirstListener
 */
@WebServlet("/MyFirstListener")
public class MyFirstListener implements ServletContextListener {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyFirstListener() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	ServletContext sc = sce.getServletContext();
    	sc.log("[INFO]Web应用程序正在关闭！");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sec)  { 
    	ServletContext sc = sec.getServletContext();
    	sc.log("[INFO]Web应用程序正在启动！");
    }

}
