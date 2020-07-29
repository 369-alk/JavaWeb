package cn.sdcet.news.dao;

import java.util.List;

import cn.sdcet.news.domain.Catalog;
import cn.sdcet.news.utils.PageBean;

public interface CatalogDao {

	/**
	 * 获取所有新闻分类
	 */
	public List<Catalog> findAll();
	
	/**
	 * 获取所有新闻分类
	 */
	public PageBean<Catalog> findAll2(int page);
	
	/**
	 * 更新分类信息
	 */
	public void upate(Catalog catalog);
	
	/**
	 * 删除分类
	 */
	public void delete(int id);
	
	/**
	 * 保存分类
	 */
	public void save(Catalog catalog);
	
	/**
	 * 根据Id查找分类
	 */
	public Catalog findById(int id);
}
