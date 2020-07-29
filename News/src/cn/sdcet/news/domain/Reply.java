package cn.sdcet.news.domain;

import java.util.Date;

/**
 * ÐÂÎÅ»Ø¸´
 */
public class Reply {
	private Integer id;
	private String content;
	private String author;
	private Date pubDate;

	public Reply() {
	}

	public Reply(Integer id, String content, String author, Date pubDate) {
		this.id = id;
		this.content = content;
		this.author = author;
		this.pubDate = pubDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}
