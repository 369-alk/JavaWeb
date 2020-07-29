package cn.sdcet.news.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import cn.sdcet.news.dao.NewsDao;
import cn.sdcet.news.domain.Catalog;
import cn.sdcet.news.domain.News;
import cn.sdcet.news.utils.Constants;
import cn.sdcet.news.utils.PageBean;

public class NewsDaoJDBCImpl implements NewsDao {
	private DataSource dataSource;
	
	public NewsDaoJDBCImpl() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/News");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("查找数据源失败：" + e.getMessage());
		}
	}
	
	@Override
	public List<News> getTopNewsList() {
		List<News> newsList = new ArrayList<News>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
	  	  Connection connection = null;
	  	  PreparedStatement ps = null;
	  	  ResultSet rs = null;
	  	  
	  	  try {
		  	  connection = dataSource.getConnection();
		  	  
		  	  String sql = "select n.id, n. title, n.publishedTime,n.catalogId, c.name from News n, Catalog c ";
		  	  sql += " where n.id in (select top 5 id from News where catalogId = n.catalogId) and n.catalogId = c.id ";
		  	  sql += " order by catalogId, publishedTime desc";
		  	  ps = connection.prepareStatement(sql);
		  	  rs = ps.executeQuery();
		  	  while(rs.next()) {
	  		     //处理结果集
		  		 int id = rs.getInt(1);
		  		 String title = rs.getString(2);
		  		 String strDate = rs.getString(3);
		  		 Date pubDate = sdf.parse(strDate);
		  		 
		  		 int catalogId = rs.getInt(4);
		  		 String catalogName = rs.getString(5);
		  		 Catalog catalog = new Catalog(catalogId, catalogName, "");
		  		 
		  		 News news = new News();
		  		 news.setId(id);
		  		 news.setTitle(title);
		  		 news.setPubDate(pubDate);
		  		 news.setCatalog(catalog);
		  		 
		  		 newsList.add(news);
		  	  }
	  	  } catch(Exception e) {
	  		  e.printStackTrace();
	          throw new RuntimeException("获取头条新闻操作失败：" + e.getMessage());
	  	  } finally {
	  		  try {
		  		 if(rs != null)
		  		 	rs.close();
	  		  } catch(Exception e) {
	  			  e.printStackTrace();
	  			  throw new RuntimeException("关闭结果集失败：" + e.getMessage());
	  		  } finally {
	  			  try {
				  	 if(ps != null)
			  		 	ps.close();
	  			  } catch(Exception e) {
	  				 e.printStackTrace();
	  	  			 throw new RuntimeException("关闭ps失败：" + e.getMessage());
	  			  } finally {
	  				  try {
					  	 if(connection != null)
					  	 	connection.close();
	  				  } catch(Exception e) {
	  					 e.printStackTrace();
	  	  	  			 throw new RuntimeException("关闭数据库连接失败：" + e.getMessage());
	  				  }
	  			  }
	  			  
	  		 }
	  	  }
		
		return newsList;
	}

	@Override
	public List<News> findByCondition(String title, String content) {
		List<News> newsList = new ArrayList<News>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
	  	  Connection connection = null;
	  	  PreparedStatement ps = null;
	  	  ResultSet rs = null;
	  	  
	  	  try {
		  	  connection = dataSource.getConnection();
		  	  
		  	  String sql = "select id,title,publishedTime from News where title like ? and content like ?";
		  	  ps = connection.prepareStatement(sql);
		  	  ps.setString(1, "%" + title + "%");
		  	  ps.setString(2, "%" + content + "%");
		  	  rs = ps.executeQuery();
		  	  while(rs.next()) {
	  		     //处理结果集
		  		 News news = new News();
		  		 news.setId(rs.getInt(1));
		  		 news.setTitle(rs.getString(2));
		  		 String strDate = rs.getString(3);
		  		 Date pubDate = sdf.parse(strDate);
		  		 news.setPubDate(pubDate);
		  		 newsList.add(news);
		  	  }
	  	  } catch(Exception e) {
	  		  e.printStackTrace();
	          throw new RuntimeException("根据标题和内容查找新闻操作失败：" + e.getMessage());
	  	  } finally {
	  		  try {
		  		 if(rs != null)
		  		 	rs.close();
	  		  } catch(Exception e) {
	  			  e.printStackTrace();
	  			  throw new RuntimeException("关闭结果集失败：" + e.getMessage());
	  		  } finally {
	  			  try {
				  	 if(ps != null)
			  		 	ps.close();
	  			  } catch(Exception e) {
	  				 e.printStackTrace();
	  	  			 throw new RuntimeException("关闭ps失败：" + e.getMessage());
	  			  } finally {
	  				  try {
					  	 if(connection != null)
					  	 	connection.close();
	  				  } catch(Exception e) {
	  					 e.printStackTrace();
	  	  	  			 throw new RuntimeException("关闭数据库连接失败：" + e.getMessage());
	  				  }
	  			  }
	  			  
	  		 }
	  	  }
		
		return newsList;
	}

	@Override
	public News findById(int id) {
		News news = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
	  	  Connection connection = null;
	  	  PreparedStatement ps = null;
	  	  ResultSet rs = null;
	  	  
	  	  try {
		  	  connection = dataSource.getConnection();
		  	  
		  	  String sql = "select * from News where id = ?";
		  	  ps = connection.prepareStatement(sql);
		  	  ps.setInt(1, id);
		  	  rs = ps.executeQuery();
		  	  while(rs.next()) {
		  		  int newsId = rs.getInt(1);
		  		  String title = rs.getString(2);
		  		  String content = rs.getString(3);
		  		  String strDate = rs.getString(4);
		  		  Date pubDate = sdf.parse(strDate);
		  		  String author = rs.getString(5);
		  		  String source = rs.getString(6);
		  		  
		  		  int catalogId = rs.getInt(7);
		  		  Catalog catalog = new Catalog();
		  		  catalog.setId(catalogId);
		  		  
		  		  news = new News(newsId, title, content, author, pubDate, source);
		  		  news.setCatalog(catalog);
		  	  }
	  	  } catch(Exception e) {
	  		  e.printStackTrace();
	          throw new RuntimeException("根据Id查找新闻操作失败：" + e.getMessage());
	  	  } finally {
	  		  try {
		  		 if(rs != null)
		  		 	rs.close();
	  		  } catch(Exception e) {
	  			  e.printStackTrace();
	  			  throw new RuntimeException("关闭结果集失败：" + e.getMessage());
	  		  } finally {
	  			  try {
				  	 if(ps != null)
			  		 	ps.close();
	  			  } catch(Exception e) {
	  				 e.printStackTrace();
	  	  			 throw new RuntimeException("关闭ps失败：" + e.getMessage());
	  			  } finally {
	  				  try {
					  	 if(connection != null)
					  	 	connection.close();
	  				  } catch(Exception e) {
	  					 e.printStackTrace();
	  	  	  			 throw new RuntimeException("关闭数据库连接失败：" + e.getMessage());
	  				  }
	  			  }
	  			  
	  		 }
	  	  }
	  	  
		return news;
	}

	@Override
	public List<News> findByCatalogId(int catalogId) {
		List<News> newsList = new ArrayList<News>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
	  	  Connection connection = null;
	  	  PreparedStatement ps = null;
	  	  ResultSet rs = null;
	  	  
	  	  try {
		  	  connection = dataSource.getConnection();
		  	  
		  	  String sql = "select id,title, publishedTime from News where catalogId = ?";
		  	  ps = connection.prepareStatement(sql);
		  	  ps.setInt(1, catalogId);
		  	  rs = ps.executeQuery();
		  	  while(rs.next()) {
	  		     //处理结果集
		  		 News news = new News();
		  		 news.setId(rs.getInt(1));
		  		 news.setTitle(rs.getString(2));
		  		 String strDate = rs.getString(3);
		  		 Date pubDate = sdf.parse(strDate);
		  		 news.setPubDate(pubDate);
		  		 newsList.add(news);
		  	  }
	  	  } catch(Exception e) {
	  		  e.printStackTrace();
	          throw new RuntimeException("获取指定分类下的新闻操作失败：" + e.getMessage());
	  	  } finally {
	  		  try {
		  		 if(rs != null)
		  		 	rs.close();
	  		  } catch(Exception e) {
	  			  e.printStackTrace();
	  			  throw new RuntimeException("关闭结果集失败：" + e.getMessage());
	  		  } finally {
	  			  try {
				  	 if(ps != null)
			  		 	ps.close();
	  			  } catch(Exception e) {
	  				 e.printStackTrace();
	  	  			 throw new RuntimeException("关闭ps失败：" + e.getMessage());
	  			  } finally {
	  				  try {
					  	 if(connection != null)
					  	 	connection.close();
	  				  } catch(Exception e) {
	  					 e.printStackTrace();
	  	  	  			 throw new RuntimeException("关闭数据库连接失败：" + e.getMessage());
	  				  }
	  			  }
	  			  
	  		 }
	  	  }
		
		return newsList;
	}

	@Override
	public List<News> findAll() {
		List<News> newsList = new ArrayList<News>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
	  	  Connection connection = null;
	  	  PreparedStatement ps = null;
	  	  ResultSet rs = null;
	  	  
	  	  try {
		  	  connection = dataSource.getConnection();
		  	  
		  	  String sql = "select id,title, publishedTime from News";
		  	  ps = connection.prepareStatement(sql);
		  	  rs = ps.executeQuery();
		  	  while(rs.next()) {
	  		     //处理结果集
		  		 News news = new News();
		  		 news.setId(rs.getInt(1));
		  		 news.setTitle(rs.getString(2));
		  		 String strDate = rs.getString(3);
		  		 Date pubDate = sdf.parse(strDate);
		  		 news.setPubDate(pubDate);
		  		 newsList.add(news);
		  	  }
	  	  } catch(Exception e) {
	  		  e.printStackTrace();
	          throw new RuntimeException("获取所有新闻操作失败：" + e.getMessage());
	  	  } finally {
	  		  try {
		  		 if(rs != null)
		  		 	rs.close();
	  		  } catch(Exception e) {
	  			  e.printStackTrace();
	  			  throw new RuntimeException("关闭结果集失败：" + e.getMessage());
	  		  } finally {
	  			  try {
				  	 if(ps != null)
			  		 	ps.close();
	  			  } catch(Exception e) {
	  				 e.printStackTrace();
	  	  			 throw new RuntimeException("关闭ps失败：" + e.getMessage());
	  			  } finally {
	  				  try {
					  	 if(connection != null)
					  	 	connection.close();
	  				  } catch(Exception e) {
	  					 e.printStackTrace();
	  	  	  			 throw new RuntimeException("关闭数据库连接失败：" + e.getMessage());
	  				  }
	  			  }
	  			  
	  		 }
	  	  }
		
		return newsList;
	}

	@Override
	public void update(News news) {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = dataSource.getConnection();

			String sql = "update News set title=?,content=?,author=?,source=?,catalogId=? where id = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, news.getTitle());
			ps.setString(2, news.getContent());
			ps.setString(3, news.getAuthor());
			ps.setString(4, news.getSource());
			ps.setInt(5, news.getCatalog().getId());
			ps.setInt(6, news.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("更新新闻操作失败：" + e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("关闭ps失败：" + e.getMessage());
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("关闭数据库连接失败：" + e.getMessage());
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

			String sql = "delete from News where id = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("根据Id删除新闻操作失败：" + e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("关闭ps失败：" + e.getMessage());
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("关闭数据库连接失败：" + e.getMessage());
				}
			}
		}
	}

	@Override
	public void save(News news) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = dataSource.getConnection();

			String sql = "insert into News(title,content,author,source,publishedTime,catalogId) " +
					"values(?,?,?,?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, news.getTitle());
			ps.setString(2, news.getContent());
			ps.setString(3, news.getAuthor());
			ps.setString(4, news.getSource());
			ps.setString(5, sdf.format(news.getPubDate()));
			ps.setInt(6, news.getCatalog().getId());
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("保存新闻操作失败：" + e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("关闭ps失败：" + e.getMessage());
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("关闭数据库连接失败：" + e.getMessage());
				}
			}
		}
	}

	@Override
	public PageBean<News> findByCatalogId2(int catalogId, int page) {
		PageBean<News> pageBean = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
	  	  Connection connection = null;
	  	  PreparedStatement ps = null;
	  	  ResultSet rs = null;
	  	  
	  	  try {
		  	  connection = dataSource.getConnection();
		  	  
		  	  int recordCount = -1;
		  	  String sql = "select count(*) from News where catalogId = ?";
		  	  ps = connection.prepareStatement(sql);
		  	  ps.setInt(1, catalogId);
		  	  rs = ps.executeQuery();
		  	  if(rs.next()) {
		  		  recordCount = rs.getInt(1);
		  	  }
		  	  
		  	  List<News> newsList = new ArrayList<News>();
		  	  sql = "select top " + Constants.PAGE_SIZE + " id,title, publishedTime from News " +
		  	  		"where id not in (select top " + Constants.PAGE_SIZE * (page - 1) + " id from News where catalogId = ?) and catalogId = ?";
		  	  ps = connection.prepareStatement(sql);
		  	  ps.setInt(1, catalogId);
		  	  ps.setInt(2, catalogId);
		  	  rs = ps.executeQuery();
		  	  while(rs.next()) {
	  		     //处理结果集
		  		 News news = new News();
		  		 news.setId(rs.getInt(1));
		  		 news.setTitle(rs.getString(2));
		  		 String strDate = rs.getString(3);
		  		 Date pubDate = sdf.parse(strDate);
		  		 news.setPubDate(pubDate);
		  		 newsList.add(news);
		  	  }
		  	  
		  	  pageBean = new PageBean<News>(page, Constants.PAGE_SIZE, recordCount, newsList);
	  	  } catch(Exception e) {
	  		  e.printStackTrace();
	          throw new RuntimeException("获取指定分类下的新闻操作失败：" + e.getMessage());
	  	  } finally {
	  		  try {
		  		 if(rs != null)
		  		 	rs.close();
	  		  } catch(Exception e) {
	  			  e.printStackTrace();
	  			  throw new RuntimeException("关闭结果集失败：" + e.getMessage());
	  		  } finally {
	  			  try {
				  	 if(ps != null)
			  		 	ps.close();
	  			  } catch(Exception e) {
	  				 e.printStackTrace();
	  	  			 throw new RuntimeException("关闭ps失败：" + e.getMessage());
	  			  } finally {
	  				  try {
					  	 if(connection != null)
					  	 	connection.close();
	  				  } catch(Exception e) {
	  					 e.printStackTrace();
	  	  	  			 throw new RuntimeException("关闭数据库连接失败：" + e.getMessage());
	  				  }
	  			  }
	  			  
	  		 }
	  	  }
		
		return pageBean;
	}

}
