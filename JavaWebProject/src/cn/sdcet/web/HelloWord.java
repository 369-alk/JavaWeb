package cn.sdcet.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/hello")
public class HelloWord extends HttpServlet {
/**
 * doGet��doPost������
 * 		������������servlet����Ҫ������doGet�ĵ��÷�ʽ�����֣�1.�������ֱ������url�����ʣ�2.����������ӻ�ת����3.�����ύ��ʽΪget��
 * 			doPost�ĵ��÷�ʽֻ��һ�֣������ύ��ʽΪpost��
 * 		������������������Ҫ�Ĳ����ֱ���request��response(�������Ӧ)
 * 			request�������ͻ��������������������
 * 			response��������������Ӧ���ͻ��˵����ݣ�������ʾ������������ݡ�
 * 		request���÷�����
 * 			getParameter()  ��ȡָ��������һ��������ȡ���е�����
 * 			setAttribute()  �洢����
 * 			getAttribute()	��ȡ����
 * 		response���÷�����
 * 			getWriter()  ��ͻ��˷����ַ�����  PrintWriter out = response.getWriter();
 *			setContentType()  ������Ӧ���ݵ�MIME����  response.setContentType("text/html;charset=UTF-8");
 *
 * Web3.1�����ԣ�
 * 		@WebServlet("/hello")�˾��൱��web.xml�ļ��е�<servlet>�������������ô�servlet�ļ�
 * 			""���������servlet�ļ�ӳ�䣬�൱��<url-pattern>  ע�⣺/  ����ʡ��
 * 
 * 		���ó�ʼ��������
 * 		@WebServlet(value = "/InitParamServlet", initParams={
 * 			@WebInitParam(name = "username", value = "javaweb"),
 * 			@WebInitParam(name = "password", value = "123456")
 * 		})
 * 
 * 		���ù�������
 * 		@WebFilter("/*")
 * 
 * 		���ü�����
 * 		@WebListener
 * 
 * Servlet������
 * 		���û���һ�������ʱ��Web�����ᴴ��Servletʵ����
 * 		����web�����У�servletʵ����Ψһ�ġ�
 * 		����servlet��������ֻҪһ��ʵ�������Բ�Ҫ��ʵ�������б����ض��û���ص���Ϣ��
 * 		�������request��session��servletContext (���󡢻Ự��������)
 * 			���з�����setAttribute() getAttribute()  �ֱ��Ǵ洢���ݺͻ�ȡ����
 * 			
 * 			�������Χ��С���ӿͻ��˷�������ʼ����Ӧ��Ϣ�ص��ͻ���(����Ӧ��Ϣ�����Ժ��������ͻᱻ����)
 * 			�Ự����һ�����һ���û�
 * 			����webӦ���������Ķ�����Ψһ�ģ��ʺ��ڱ�����ض�Ӧ����ص���Ϣ(�統ǰ��������)
 * 
 * 		ServletConfig����
 * 			ͨ������web.xml�ļ�������Servlet��ʼ������
 * 			��Ҫ�÷���
 * 				ServletConfig config = getServletConfig();
 * 				getInitParameter()  ��ȡ��ʼ���Ĳ���
 * 
 * 		Session����
 * 			��Ҫ�÷���
 * 				HttpSession session = request.getSession();
 * 				setAttribute()  �洢����
 * 				getAttribute()	��ȡ����
 *				invalidate()    ���ٻỰ
 *
 *		ServletContext����
 *			��Ҫ�÷���
 *				ServletContext context = getServletContext();
 *				setAttribute()  �洢����
 * 				getAttribute()	��ȡ����
 * 
 * ����ת�����ض���
 * 		��һ������û����ɣ���Ҫ����Web�����������Ļ�����ת����(��ѯҳ��->��ѯ�������)
 * 		��һ��������ɣ���������һ�����ܵ�ʱ���������ض���(��¼�ɹ�->������)
 * 		ע�⣺
 * 			����������Դ������ܹر������
 * 			ת���Ժ��ַ���е�·������
 * 			�ض������������󣬵�ַ������ʾ�����ض�����Դ��URL
 * 			ת��ֻ��ת����ͬһWeb��Դ����·����������������·��
 * 			�ض�����Դﵽ������Դ
 * 		ת����
 * 			RequestDispatcher dispatcher = request.getRequestDispatcher("Ҫת�����ļ������·��");
 *			dispatcher.forward(request, response);  �Ƚϳ���
 *			dispatcher.include(request, response);  �������̣�һ���ò���
 *		�ض���
 *			response.sendRedirect("Ҫ�ض�����ļ������·�������·��")
 *
 * ��������
 * 		����Ϊ����Web��Դ�ṩ�����ܹ����ؿͻ�����Ŀ����Դ֮����������Ӧ��Ϣ��
 * 		������Щ��Ϣ���й��ˡ�
 * 		ʹ�ã�
 * 			ʵ��Filter�ӿڣ���web.xml������Filter��
 * 			ʵ��init()��destory()��doFilter()����
 * 			init()��������ʼ��������servlet�����ڴ���Filterʵ���󽫵������������
 * 			destory()���������ٷ�����servlet����������Filterʵ��ǰ���ø÷�����ͨ���ڸ÷������ͷŹ�����ռ�õ���Դ��
 *			doFilter()�������÷������ʵ�ʵĹ��˹�����  chain.doFilter(request, response);  ����
 *
 * ��������
 * 		����ʵ��ServletContextListener�ӿڡ�HttpSessionListener�ӿڡ�ServletRequestListener�ӿڣ�����д�䷽����
 * 		ServletContextListener�ӿڣ�
 * 			contextDestroyed()�������ر�webӦ��ʱ��web���������������
 * 			contextInitialized()����������webӦ��ʱ��web���������������
 * 		HttpSessionListener�ӿڣ�
 * 			sessionCreated()������ÿ��һ��HttpSession���󴴽�ʱ��web�����������������
 * 			sessionDestroyed()������ÿ��һ��HttpSession��������ʱ��web�����������������
 * 		ServletRequestListener�ӿڣ�
 * 			requestInitialized()��������web���󼴽�������������еĵ�һ��������ʱ��web�����������������
 * 			requestDestroyed()��������web����ӵ�һ������������ʱ��web�����������������

 * 
 */


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext context = getServletContext();
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html><head><title>");
		out.print("");
		out.print("</title></head><body>");
		out.print("");
		out.print("</body></html>");
		out.flush();
		out.close();
	}

}
