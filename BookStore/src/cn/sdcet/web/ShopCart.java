package cn.sdcet.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShopCart {

	private HashMap<Integer, ShopCartItem> cart = new HashMap<Integer, ShopCartItem>();

	public void add(int id, Book book) {
		if (cart.containsKey(id)) {
			ShopCartItem item = cart.get(id);
			item.incrementQuantity();
		} else {
			ShopCartItem item = new ShopCartItem(book);
			cart.put(id, item);
		}
	}

	public void remove(int id) {
		if (cart.containsKey(id)) {
			cart.remove(id);
		}
	}

	public void setQuantity(int id, int quantity) {
		if (cart.containsKey(id)) {
			ShopCartItem item = cart.get(id);
			item.setQuantity(quantity);
		}
	}

	public List<ShopCartItem> getItrms() {
		return new ArrayList<ShopCartItem>(cart.values());
	}

	public int getItemNumbers() {
		int number = 0;
		List<ShopCartItem> items = new ArrayList<ShopCartItem>(cart.values());
		for (ShopCartItem item : items) {
			number += item.getQuantity();
		}
		return number;
	}

	public int getPayment() {
		int payment = 0;
		List<ShopCartItem> items = new ArrayList<ShopCartItem>(cart.values());
		for (ShopCartItem item : items) {
			Book book = item.getBook();
			payment += item.getQuantity() * book.getPrice();
		}
		return payment;
	}

	public void clear() {
		cart.clear();
	}
}
