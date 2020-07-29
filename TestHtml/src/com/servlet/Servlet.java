
package com.servlet;
 
import java.io.File;
import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class Servlet extends HttpServlet{
	
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		System.out.println("chapterId=====//===="+request.getParameter("chapterId"));
		if(request.getParameter("chapterId")!= null){
	        String chapterFileName = "bookChapterRead_"+request.getParameter("chapterId")+".html";  
	        String chapterFilePath = getServletContext().getRealPath("/") + chapterFileName; 
	        System.out.println("chapterFilePath===//============="+chapterFilePath);
	        File chapterFile = new File(chapterFilePath);
	        System.out.println("chapterFile===//============="+chapterFile);
	        if(chapterFile.exists()){//如果有这个文件就告诉浏览器转向
	        	System.out.println("有此静态页面＝＝＝＝＝＝");
	        	response.sendRedirect(chapterFileName);
	        	return;
	          }
	        System.out.println("没有此静态页面＝＝＝＝＝＝");
	        //======存放业务处理后得到的信息
	        request.setAttribute("novelChapter", "测试单节信息");
	        request.setAttribute("lastPageId", "lastPageId111");
	        request.setAttribute("nextPageId", "nextPageId222");
	        //============
	        System.out.println("getServletContext()==========//========="+getServletContext());
	        new CreateStaticHTMLPage().createStaticHTMLPage(request, response, getServletContext(),
	                chapterFileName, chapterFilePath, "/bookRead.jsp");
	    }
		
	}
 
}
