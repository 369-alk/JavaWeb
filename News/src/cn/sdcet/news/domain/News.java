package cn.sdcet.news.domain;

import java.util.Date;

/**
 * 新闻
 */
public class News {
	private Integer id;
	private String title;
	private String content;
	private String author;
	private Date pubDate;
	private String source;

	// 关联属性
	private Catalog catalog;

	public News() {
	}

	public News(Integer id, String title, String content, String author,
			Date pubDate, String source) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
		this.pubDate = pubDate;
		this.source = source;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

}
