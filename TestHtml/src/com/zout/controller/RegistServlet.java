package com.zout.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 *@class_name��RegistServlet
 *@comments: ע��������
 *@param:��֤��У�� 
 *@return: jsp��ת
 *@author:����/Zoutao
 *@createtime:2019��2��22��
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// ��ȡsession�е���֤��
		String sessionCode = (String) req.getSession().getAttribute("code");
		System.out.println(sessionCode);
		
		if(sessionCode != null) {
			//  ��ȡҳ���ύ����֤��
			String inputCode = req.getParameter("code");
			System.out.println("ҳ���ύ����֤��:" + inputCode);
			if (sessionCode.toLowerCase().equals(inputCode.toLowerCase())) {
				String username = req.getParameter("username");
				String password = req.getParameter("password");
				System.out.println("ҳ���ύ:" + username+password);
				//  ��֤�ɹ�����ת�ɹ�ҳ��
				req.setAttribute("username", username);
				req.getRequestDispatcher("/success.jsp").forward(req, resp);
			}else {
				//  ��֤ʧ��
				req.getRequestDispatcher("fail.jsp").forward(req, resp);
			}
		}else {
			//  ��֤ʧ��
			req.getRequestDispatcher("fail.jsp").forward(req, resp);
		}
		//  �Ƴ�session�е���֤��
		req.removeAttribute("code");
	}
}
