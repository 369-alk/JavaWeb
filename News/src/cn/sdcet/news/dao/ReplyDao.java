package cn.sdcet.news.dao;

import java.util.List;

import cn.sdcet.news.domain.Reply;

public interface ReplyDao {

	/**
	 * 获取指定新闻的所有回复
	 */
	public List<Reply> findByNewsId(int newsId);
	
	/**
	 * 保存回复
	 */
	public void save(int newsId, Reply reply);
	
	/**
	 * 删除指定新闻的所有回复
	 */
	public void deleteByNewsId(int newsId);
}
