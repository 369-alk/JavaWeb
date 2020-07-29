package cn.sdcet.news.dao;

import java.util.List;

import cn.sdcet.news.domain.Catalog;
import cn.sdcet.news.utils.PageBean;

public interface CatalogDao {

	/**
	 * ��ȡ�������ŷ���
	 */
	public List<Catalog> findAll();
	
	/**
	 * ��ȡ�������ŷ���
	 */
	public PageBean<Catalog> findAll2(int page);
	
	/**
	 * ���·�����Ϣ
	 */
	public void upate(Catalog catalog);
	
	/**
	 * ɾ������
	 */
	public void delete(int id);
	
	/**
	 * �������
	 */
	public void save(Catalog catalog);
	
	/**
	 * ����Id���ҷ���
	 */
	public Catalog findById(int id);
}
