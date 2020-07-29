package com.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/send")
public class MailResponse extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public MailResponse() {
        super();
    }
    
    private void process(HttpServletRequest request, HttpServletResponse response) {
        try{
            String pwd = "gvywdrzpymezhdac";//发件人邮箱密码
             String mailfrom = request.getParameter("14575469976"); //网易的邮箱
             String wangyiFrom = mailfrom.substring(0, mailfrom.indexOf('@'));//网易邮箱的用户名
            String tu = "qq.com"; //你邮箱的后缀域名
            String tto= request.getParameter("14575469976"); //接收邮件的邮箱
            String ttitle= "在线软件工程管理员回复！";
            String tcontent = request.getParameter("messageTo");
    
            Properties props=new Properties();
    
            props.put("mail.smtp.host","smtp."+tu);//邮箱SMTP服务器地址端口
    
            props.put("mail.smtp.auth","true");//这样才能通过验证
    
            Session s=Session.getInstance(props);
    
            s.setDebug(true);
    
            MimeMessage message=new MimeMessage(s);
    
            //给消息对象设置发件人/收件人/主题/发信时间
    
            InternetAddress from=new InternetAddress(mailfrom); //发件人的qq邮箱
    
            message.setFrom(from);
    
            InternetAddress to=new InternetAddress(tto);//收件人的邮箱
    
            message.setRecipient(Message.RecipientType.TO,to);
    
            message.setSubject(ttitle);
    
            message.setSentDate(new Date());
    
            //给消息对象设置内容
    
            BodyPart mdp=new MimeBodyPart();//新建一个存放信件内容的BodyPart对象
    
            mdp.setContent(tcontent,"text/html;charset=gb2312");//给BodyPart对象设置内容和格式/编码方式
    
            Multipart mm=new MimeMultipart();//新建一个MimeMultipart对象用来存放BodyPart对
    
            //象(事实上可以存放多个)
    
            mm.addBodyPart(mdp);//将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)
    
            message.setContent(mm);//把mm作为消息对象的内容
    
            message.saveChanges();
    
            Transport transport=s.getTransport("smtp");
    
            transport.connect("smtp."+tu, wangyiFrom, pwd); //这里的wangyiFrom为发件人网易账号
    
            transport.sendMessage(message,message.getAllRecipients());
    
            transport.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        process(request, response);
    }

}