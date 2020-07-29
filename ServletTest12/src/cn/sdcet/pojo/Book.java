package cn.sdcet.pojo;

import java.util.Date;

public class Book {

	private String name;
	private String author;
	private String publisher;
	private String isbn;
	private int price;
	private Date date;

	public Book() {
	}

	public Book(String name, String author, String publisher, String isbn, int price, Date date) {
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.isbn = isbn;
		this.price = price;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Book [name=" + name + ", author=" + author + ", publisher=" + publisher + ", isbn=" + isbn + ", price="
				+ price + ", date=" + date + "]";
	}

}
