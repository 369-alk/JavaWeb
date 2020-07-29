package com.zout.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 *@class_name：RegistServlet
 *@comments: 注册请求处理
 *@param:验证码校验 
 *@return: jsp跳转
 *@author:邹涛/Zoutao
 *@createtime:2019年2月22日
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// 获取session中的验证码
		String sessionCode = (String) req.getSession().getAttribute("code");
		System.out.println(sessionCode);
		
		if(sessionCode != null) {
			//  获取页面提交的验证码
			String inputCode = req.getParameter("code");
			System.out.println("页面提交的验证码:" + inputCode);
			if (sessionCode.toLowerCase().equals(inputCode.toLowerCase())) {
				String username = req.getParameter("username");
				String password = req.getParameter("password");
				System.out.println("页面提交:" + username+password);
				//  验证成功，跳转成功页面
				req.setAttribute("username", username);
				req.getRequestDispatcher("/success.jsp").forward(req, resp);
			}else {
				//  验证失败
				req.getRequestDispatcher("fail.jsp").forward(req, resp);
			}
		}else {
			//  验证失败
			req.getRequestDispatcher("fail.jsp").forward(req, resp);
		}
		//  移除session中的验证码
		req.removeAttribute("code");
	}
}
