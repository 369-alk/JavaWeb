package com.xiaochangwei.html;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * �����û��������ɻ���ֱ�ӷ���html
 * 
 * @author xiaochangwei
 *
 */
@WebServlet("/JspStatic")
public class JspStatic extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public JspStatic() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") != null) {
            String fileName = "article_" + request.getParameter("id") + ".html";
            String filePath = getServletContext().getRealPath("/") + fileName;
            File chapterFile = new File(filePath);
            if (chapterFile.exists()) {
                System.out.println("htmlҳ����ڣ�ֱ����ת");
                response.sendRedirect(fileName);
                return;
            }
            
            
            System.out.println("������htmlҳ��");
            //TODO ����ɵ���service��ѯҳ������Ҫ�����ݣ�Ȼ���װ��request����
            request.setAttribute("time", new java.util.Date());
            request.setAttribute("id", request.getParameter("id"));
            new CreateStaticHTMLPage().create(request, response, getServletContext(), fileName, filePath,
                    "/articleTemplate.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}