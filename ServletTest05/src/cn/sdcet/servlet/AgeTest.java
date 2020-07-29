package cn.sdcet.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AgeTest
 */
@WebServlet(urlPatterns = "/age", initParams ={
		@WebInitParam(name="name", value="Java"),
		@WebInitParam(name="age", value="12")
})
public class AgeTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgeTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletConfig sc = getServletConfig();
		String name = sc.getInitParameter("name");
		String age = sc.getInitParameter("age");
		String message = "";
		int ages = -1;
		try {
			ages = Integer.valueOf(age);
		} catch (Exception  e) {
			message = "�������䲻��ȷ";
		}
		if (ages == -1) {
			message = "�������䲻��ȷ";
		}
		else if (ages < 10) {
			message = "��������̫С��";
		}
		else{
			message = "������" + name + "<br>" + "���䣺" + ages;
		}
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html><head><title>");
		out.print("��ӭҳ��");
		out.print("</title></head><body>");
		out.print(message);
		out.print("</body></html>");
		out.close();
	}

}
