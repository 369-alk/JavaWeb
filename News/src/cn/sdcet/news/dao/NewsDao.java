package cn.sdcet.news.dao;

import java.util.List;

import cn.sdcet.news.domain.News;
import cn.sdcet.news.utils.PageBean;

public interface NewsDao {

	/**
	 * 获取每个分类下的头5条新闻
	 */
	public List<News> getTopNewsList();
	
	/**
	 * 根据标题和内容查找新闻
	 */
	public List<News> findByCondition(String title, String content);
	
	/**
	 * 根据Id查找新闻
	 */
	public News findById(int id);
	
	/**
	 * 获取指定分类下的所有新闻
	 */
	public List<News> findByCatalogId(int catalogId);
	
	/**
	 * 获取指定分类下的所有新闻
	 */
	public PageBean<News> findByCatalogId2(int catalogId, int page);
	
	/**
	 * 获取所有新闻
	 */
	public List<News> findAll();
	
	/**
	 * 更新新闻
	 */
	public void update(News news);
	
	/**
	 * 删除新闻
	 */
	public void delete(int id);
	
	/**
	 * 保存新闻
	 */
	public void save(News news);
}
