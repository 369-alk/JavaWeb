package cn.sdcet.news.dao;

import cn.sdcet.news.domain.User;

public interface UserDao {

	/**
	 * �����û�������������û�
	 */
	public boolean hasMatchUser(String name, String password);
	
	/**
	 * ���ݵ�½�������û�
	 */
	public User findByLoginName(String name);
}
