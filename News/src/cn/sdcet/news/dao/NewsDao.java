package cn.sdcet.news.dao;

import java.util.List;

import cn.sdcet.news.domain.News;
import cn.sdcet.news.utils.PageBean;

public interface NewsDao {

	/**
	 * ��ȡÿ�������µ�ͷ5������
	 */
	public List<News> getTopNewsList();
	
	/**
	 * ���ݱ�������ݲ�������
	 */
	public List<News> findByCondition(String title, String content);
	
	/**
	 * ����Id��������
	 */
	public News findById(int id);
	
	/**
	 * ��ȡָ�������µ���������
	 */
	public List<News> findByCatalogId(int catalogId);
	
	/**
	 * ��ȡָ�������µ���������
	 */
	public PageBean<News> findByCatalogId2(int catalogId, int page);
	
	/**
	 * ��ȡ��������
	 */
	public List<News> findAll();
	
	/**
	 * ��������
	 */
	public void update(News news);
	
	/**
	 * ɾ������
	 */
	public void delete(int id);
	
	/**
	 * ��������
	 */
	public void save(News news);
}
