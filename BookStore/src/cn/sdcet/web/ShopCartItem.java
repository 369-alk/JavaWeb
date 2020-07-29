package cn.sdcet.web;

public class ShopCartItem {

	private Book book;
	private int quantity;

	public ShopCartItem(Book book) {
		this(book, 1);
	}

	public ShopCartItem(Book book, int quantity) {
		this.book = book;
		this.quantity = quantity;
	}

	public void incrementQuantity() {
		quantity++;
	}

	public void decrementQuantity() {
		quantity--;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
