package cn.sdcet.news.dao;

import java.util.List;

import cn.sdcet.news.domain.Reply;

public interface ReplyDao {

	/**
	 * ��ȡָ�����ŵ����лظ�
	 */
	public List<Reply> findByNewsId(int newsId);
	
	/**
	 * ����ظ�
	 */
	public void save(int newsId, Reply reply);
	
	/**
	 * ɾ��ָ�����ŵ����лظ�
	 */
	public void deleteByNewsId(int newsId);
}
