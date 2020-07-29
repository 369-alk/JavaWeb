package cn.sdcet.news.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import cn.sdcet.news.dao.CatalogDao;
import cn.sdcet.news.domain.Catalog;
import cn.sdcet.news.utils.Constants;
import cn.sdcet.news.utils.PageBean;

public class CatalogDaoJDBCImpl implements CatalogDao {
	private DataSource dataSource;
	
	public CatalogDaoJDBCImpl() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/News");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("��������Դʧ�ܣ�" + e.getMessage());
		}
	}
	
	@Override
	public List<Catalog> findAll() {
		List<Catalog> catalogs = new ArrayList<Catalog>();
		
	  	  Connection connection = null;
	  	  PreparedStatement ps = null;
	  	  ResultSet rs = null;
	  	  
	  	  try {
		  	  connection = dataSource.getConnection();
		  	  ps = connection.prepareStatement("select * from catalog");
		  	  rs = ps.executeQuery();
		  	  while(rs.next()) {
	  		     //��������
		  		 int id = rs.getInt(1);
		  		 String name = rs.getString(2);
		  		 String description = rs.getString(3);
		  		 
		  		 Catalog catalog = new Catalog(id, name, description);
		  		 catalogs.add(catalog);
		  	  }
	  	  } catch(Exception e) {
	  		  e.printStackTrace();
	                  throw new RuntimeException("��ȡ���з������ʧ�ܣ�" + e.getMessage());
	  	  } finally {
	  		  try {
		  		 if(rs != null)
		  		 	rs.close();
	  		  } catch(Exception e) {
	  			  e.printStackTrace();
	  			  throw new RuntimeException("�رս����ʧ�ܣ�" + e.getMessage());
	  		  } finally {
	  			  try {
				  	 if(ps != null)
			  		 	ps.close();
	  			  } catch(Exception e) {
	  				 e.printStackTrace();
	  	  			 throw new RuntimeException("�ر�psʧ�ܣ�" + e.getMessage());
	  			  } finally {
	  				  try {
					  	 if(connection != null)
					  	 	connection.close();
	  				  } catch(Exception e) {
	  					 e.printStackTrace();
	  	  	  			 throw new RuntimeException("�ر����ݿ�����ʧ�ܣ�" + e.getMessage());
	  				  }
	  			  }
	  			  
	  		 }
	  	  }
	  	  
		return catalogs;
	}

	@Override
	public void upate(Catalog catalog) {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = dataSource.getConnection();
			ps = connection.prepareStatement("update catalog set name=? where id = ?");
			ps.setString(1, catalog.getName());
			ps.setInt(2, catalog.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("���·������ʧ�ܣ�" + e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("�ر�psʧ�ܣ�" + e.getMessage());
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("�ر����ݿ�����ʧ�ܣ�" + e.getMessage());
				}
			}

		}		
	}

	@Override
	public void delete(int id) {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = dataSource.getConnection();
			ps = connection.prepareStatement("delete from catalog where id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ɾ���������ʧ�ܣ�" + e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("�ر�psʧ�ܣ�" + e.getMessage());
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("�ر����ݿ�����ʧ�ܣ�" + e.getMessage());
				}
			}

		}
	}

	@Override
	public void save(Catalog catalog) {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = dataSource.getConnection();
			ps = connection.prepareStatement("insert into catalog(name,description) values(?,?)");
			ps.setString(1, catalog.getName());
			ps.setString(2, catalog.getDescription());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("����������ʧ�ܣ�" + e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("�ر�psʧ�ܣ�" + e.getMessage());
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("�ر����ݿ�����ʧ�ܣ�" + e.getMessage());
				}
			}

		}
	}

	@Override
	public Catalog findById(int id) {
		Catalog catalog = new Catalog();
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();
			ps = connection.prepareStatement("select * from catalog where id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				// ��������
				int catalogId = rs.getInt(1);
				String name = rs.getString(2);
				String description = rs.getString(3);

				catalog.setId(catalogId);
				catalog.setName(name);
				catalog.setDescription(description);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("����Id���ҷ������ʧ�ܣ�" + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("�رս����ʧ�ܣ�" + e.getMessage());
			} finally {
				try {
					if (ps != null)
						ps.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("�ر�psʧ�ܣ�" + e.getMessage());
				} finally {
					try {
						if (connection != null)
							connection.close();
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("�ر����ݿ�����ʧ�ܣ�"
								+ e.getMessage());
					}
				}

			}
		}
		
		return catalog;
	}

	@Override
	public PageBean<Catalog> findAll2(int page) {
		PageBean<Catalog> pageBean = null;
		
	  	  Connection connection = null;
	  	  PreparedStatement ps = null;
	  	  ResultSet rs = null;
	  	  
	  	  try {
		  	  connection = dataSource.getConnection();
		  	  
		  	  //��ѯ�ܼ�¼��
		  	  int recordCount = -1;
		  	  String sql = "select count(*) from catalog";
		  	  ps = connection.prepareStatement(sql);
		  	  rs = ps.executeQuery();
		  	  if(rs.next()) {
		  		  recordCount = rs.getInt(1);
		  	  }
		  	  
		  	  //��ѯ��ǰҳ��ʾ�ļ�¼�б�
		  	  List<Catalog> catalogs = new ArrayList<Catalog>();
		  	  sql = "select top " + Constants.PAGE_SIZE +" * from catalog " +
		  	  		"where id not in (select top " + Constants.PAGE_SIZE * (page - 1) + " id from catalog)";
		  	  ps = connection.prepareStatement(sql);
		  	  rs = ps.executeQuery();
		  	  while(rs.next()) {
	  		     //��������
		  		 int id = rs.getInt(1);
		  		 String name = rs.getString(2);
		  		 String description = rs.getString(3);
		  		 
		  		 Catalog catalog = new Catalog(id, name, description);
		  		 catalogs.add(catalog);
		  	  }
		  	  
		  	  pageBean = new PageBean<Catalog>(page, Constants.PAGE_SIZE, recordCount, catalogs);
	  	  } catch(Exception e) {
	  		  e.printStackTrace();
	                  throw new RuntimeException("��ȡ���з������ʧ�ܣ�" + e.getMessage());
	  	  } finally {
	  		  try {
		  		 if(rs != null)
		  		 	rs.close();
	  		  } catch(Exception e) {
	  			  e.printStackTrace();
	  			  throw new RuntimeException("�رս����ʧ�ܣ�" + e.getMessage());
	  		  } finally {
	  			  try {
				  	 if(ps != null)
			  		 	ps.close();
	  			  } catch(Exception e) {
	  				 e.printStackTrace();
	  	  			 throw new RuntimeException("�ر�psʧ�ܣ�" + e.getMessage());
	  			  } finally {
	  				  try {
					  	 if(connection != null)
					  	 	connection.close();
	  				  } catch(Exception e) {
	  					 e.printStackTrace();
	  	  	  			 throw new RuntimeException("�ر����ݿ�����ʧ�ܣ�" + e.getMessage());
	  				  }
	  			  }
	  			  
	  		 }
	  	  }
	  	  
		return pageBean;
	}

}
