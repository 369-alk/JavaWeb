package com.zhang;

/**
 * 
 *@class_name��htmlText
 *@comments:�����ʼ�����
 *@param:
 *@return: 
 *@author:����/Zoutao
 *@createtime:2019��2��23��
 */
public class htmlText {
		//  ����ҳ��HtmlЯ����6λ�����
		public static String html(String code) {
			
			String html = "Email��ַ��֤<br/>"+ 
			"����ʼ����ɡ�����������Ƽ������͵ġ�<br/>"+
			"���յ�����ʼ��ǡ�����������Ƽ����������û�ע������û��޸�Emailʹ�������ַ��<br/>"+
			"�˺ż�������<br/>"+
			"�뽫�������֤�����뵽��ʾ�򼴿ɣ�<h3 style='color:red;'>" + code + "</h3><br/>";
			return html;
		}
	}
