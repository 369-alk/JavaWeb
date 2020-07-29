package cn.sdcet.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class BookDB {

	Properties prop = new Properties();

	Connection connection = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	private String file;

	public List<Book> getAllBook() {
		try {
			prop.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<Book> bookList = new ArrayList<Book>();

		try {
			Class.forName(prop.getProperty("DBDRIVER"));
			connection = DriverManager.getConnection(prop.getProperty("DBURL"), prop.getProperty("USER"),
					prop.getProperty("PASSWORD"));

			ps = connection.prepareStatement("select * from book");

			rs = ps.executeQuery();
			while (rs.next()) {

				Book book = new Book();
				book.setName(rs.getString(1));
				book.setAuthor(rs.getString(2));
				book.setPublisher(rs.getString(3));
				book.setIsbn(rs.getString(4));
				book.setPrice(rs.getInt(5));
				book.setDate(rs.getDate(6));

				bookList.add(book);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("执行查询操作失败：" + e.getMessage());
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
						throw new RuntimeException("关闭数据库连接失败：" + e.getMessage());
					}
				}

			}
		}
		return bookList;

	}

	public List<Book> getAllBook(String name, String publisher) {
		try {
			prop.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<Book> bookList = new ArrayList<Book>();

		try {
			Class.forName(prop.getProperty("DBDRIVER"));
			connection = DriverManager.getConnection(prop.getProperty("DBURL"), prop.getProperty("USER"),
					prop.getProperty("PASSWORD"));

			ps = connection.prepareStatement("select * from book where name = ? and publisher = ?");
			ps.setString(1, name);
			ps.setString(2, publisher);

			rs = ps.executeQuery();
			while (rs.next()) {

				Book book = new Book();
				book.setName(rs.getString(1));
				book.setAuthor(rs.getString(2));
				book.setPublisher(rs.getString(3));
				book.setIsbn(rs.getString(4));
				book.setPrice(rs.getInt(5));
				book.setDate(rs.getDate(6));

				bookList.add(book);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("执行查询操作失败：" + e.getMessage());
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
						throw new RuntimeException("关闭数据库连接失败：" + e.getMessage());
					}
				}

			}
		}
		return bookList;

	}

	public List<Book> deleteBook(String[] name) {
		String sql = "";
		String isql = "";
		try {
			prop.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<Book> bookList = new ArrayList<Book>();

		try {
			Class.forName(prop.getProperty("DBDRIVER"));
			connection = DriverManager.getConnection(prop.getProperty("DBURL"), prop.getProperty("USER"),
					prop.getProperty("PASSWORD"));

			
			sql = "  delete from book where name = ?";
			isql = "delete from book where name = ?";
			for(int i = 1; i< name.length; i++){
				isql += sql;
			}

			ps = connection.prepareStatement(isql);
			for(int i = 0; i< name.length; i++){
				ps.setString((i+1), name[i]);
			}
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("执行查询操作失败：" + e.getMessage());
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
		return bookList;

	}

	public Set<String> getAllBookPublisher() {
		try {
			prop.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Set<String> publisherList = new HashSet<String>();

		try {
			Class.forName(prop.getProperty("DBDRIVER"));
			connection = DriverManager.getConnection(prop.getProperty("DBURL"), prop.getProperty("USER"),
					prop.getProperty("PASSWORD"));

			ps = connection.prepareStatement("select * from book");

			rs = ps.executeQuery();
			while (rs.next()) {

				String publisher = rs.getString(3);

				publisherList.add(publisher);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("执行查询操作失败：" + e.getMessage());
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
						throw new RuntimeException("关闭数据库连接失败：" + e.getMessage());
					}
				}

			}
		}
		return publisherList;

	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

}
