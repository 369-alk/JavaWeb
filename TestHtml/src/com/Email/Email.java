package com.Email;

import java.util.Random;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class Email { 
	
	//生成6位数  验证码
	public static String random1(){
		String code = "";
		Random rd=new Random();
		for (int i = 0; i < 6; i++) {
			int r = rd.nextInt(10); //每次随机出一个数字（0-9）
			code = code + r;  //把每次随机出的数字拼在一起
		}
		System.out.println(code);
		return code;
	} 
	
	//发送					这里的参数为   qq   和验证码   
	public static void test(String email,String yzm){ 
		HtmlEmail send = new HtmlEmail();//创建一个HtmlEmail实例对象
		// 获取随机验证码   
		String resultCode = yzm;       
		try {    
			send.setHostName("smtp.qq.com");	  		
			send.setAuthentication("1457569976@qq.com", "gvywdrzpymezhdac"); //第一个参数是发送者的QQEamil邮箱   第二个参数是刚刚获取的授权码
   
			send.setFrom("1457569976@qq.com", "张彬");//发送人的邮箱为自己的，用户名可以随便填  记得是自己的邮箱不是qq
//			send.setSmtpPort(465); 	//端口号 可以不开       
			send.setSSLOnConnect(true); //开启SSL加密  
			send.setCharset("utf-8");      
			send.addTo("2664373242"+"@qq.com");  //设置收件人    email为你要发送给谁的邮箱账户   上方参数
			send.setSubject("测试测试"); //邮箱标题  
			send.setMsg("HelloWorld!欢迎大大光临，特此送上验证:   " + resultCode + "   请大大签收"); //Eamil发送的内容
			send.send();  //发送 
		} catch (EmailException e) { 
			e.printStackTrace();    
		} 
	} 
}
