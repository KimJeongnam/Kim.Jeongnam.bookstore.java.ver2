package models;

import java.util.ArrayList;

public class Cart {
	public static String totalCount = null;
	public static String totalPrice = null;

	private static ArrayList<Cart> list = new ArrayList<Cart>();

	public static ArrayList<Cart> getList() {
		return list;
	}

	private String book_code;
	private String book_name;
	private String author;
	private String price;
	private String wish_stock;
	private String allPrice;

	public Cart(String book_code, String book_name, String author, String price, String wish_stock ,String allPrice) {
		this.book_code = book_code;
		this.book_name = book_name;
		this.author = author;
		this.price = price;
		this.wish_stock = wish_stock;
		this.allPrice = allPrice;
	}

	public String getBook_code() {
		return book_code;
	}

	public void setBook_code(String book_code) {
		this.book_code = book_code;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getWish_stock() {
		return wish_stock;
	}

	public void setWish_stock(String wish_stock) {
		this.wish_stock = wish_stock;
	}

	public String getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(String allPrice) {
		this.allPrice = allPrice;
	}
}
