package com.Email;

import java.util.Random;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class Email { 
	
	//����6λ��  ��֤��
	public static String random1(){
		String code = "";
		Random rd=new Random();
		for (int i = 0; i < 6; i++) {
			int r = rd.nextInt(10); //ÿ�������һ�����֣�0-9��
			code = code + r;  //��ÿ�������������ƴ��һ��
		}
		System.out.println(code);
		return code;
	} 
	
	//����					����Ĳ���Ϊ   qq   ����֤��   
	public static void test(String email,String yzm){ 
		HtmlEmail send = new HtmlEmail();//����һ��HtmlEmailʵ������
		// ��ȡ�����֤��   
		String resultCode = yzm;       
		try {    
			send.setHostName("smtp.qq.com");	  		
			send.setAuthentication("1457569976@qq.com", "gvywdrzpymezhdac"); //��һ�������Ƿ����ߵ�QQEamil����   �ڶ��������Ǹոջ�ȡ����Ȩ��
   
			send.setFrom("1457569976@qq.com", "�ű�");//�����˵�����Ϊ�Լ��ģ��û������������  �ǵ����Լ������䲻��qq
//			send.setSmtpPort(465); 	//�˿ں� ���Բ���       
			send.setSSLOnConnect(true); //����SSL����  
			send.setCharset("utf-8");      
			send.addTo("2664373242"+"@qq.com");  //�����ռ���    emailΪ��Ҫ���͸�˭�������˻�   �Ϸ�����
			send.setSubject("���Բ���"); //�������  
			send.setMsg("HelloWorld!��ӭ�����٣��ش�������֤:   " + resultCode + "   ����ǩ��"); //Eamil���͵�����
			send.send();  //���� 
		} catch (EmailException e) { 
			e.printStackTrace();    
		} 
	} 
}
