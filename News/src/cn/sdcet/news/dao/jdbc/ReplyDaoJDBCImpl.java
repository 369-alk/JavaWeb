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

import cn.sdcet.news.dao.ReplyDao;
import cn.sdcet.news.domain.Reply;

public class ReplyDaoJDBCImpl implements ReplyDao {
	private DataSource dataSource;

	public ReplyDaoJDBCImpl() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/News");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("查找数据源失败：" + e.getMessage());
		}
	}

	@Override
	public List<Reply> findByNewsId(int newsId) {
		List<Reply> replies = new ArrayList<Reply>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();

			String sql = "select * from Reply where newsId = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, newsId);
			rs = ps.executeQuery();
			while (rs.next()) {
				// 处理结果集
				int id = rs.getInt(1);
				String content = rs.getString(2);
				String strDate = rs.getString(3);
				Date pubDate = sdf.parse(strDate);
				String author = rs.getString(4);

				Reply reply = new Reply(id, content, author, pubDate);
				replies.add(reply);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取指定新闻的回复列表失败：" + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("关闭结果集失败：" + e.getMessage());
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
						throw new RuntimeException("关闭数据库连接失败："
								+ e.getMessage());
					}
				}

			}
		}

		return replies;
	}

	@Override
	public void save(int newsId, Reply reply) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = dataSource.getConnection();

			String sql = "insert into Reply(content,publishedTime, author, newsId) values(?,?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, reply.getContent());
			ps.setString(2, sdf.format(reply.getPubDate()));
			ps.setString(3, reply.getAuthor());
			ps.setInt(4, newsId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("保存回复失败：" + e.getMessage());
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
	public void deleteByNewsId(int newsId) {
		// TODO Auto-generated method stub

	}

}
