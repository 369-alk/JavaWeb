package cn.sdcet.news.dao;

import cn.sdcet.news.domain.User;

public interface UserDao {

	/**
	 * 根据用户名和密码查找用户
	 */
	public boolean hasMatchUser(String name, String password);
	
	/**
	 * 根据登陆名查找用户
	 */
	public User findByLoginName(String name);
}
