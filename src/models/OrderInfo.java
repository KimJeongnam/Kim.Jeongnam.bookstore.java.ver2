package models;

import java.util.ArrayList;

public class OrderInfo {
	public static ArrayList<OrderInfo> list = new ArrayList<OrderInfo>();
	
	private String book_name;
	private String author;
	private String price;
	private String order_stock;
	
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
	public String getOrder_stock() {
		return order_stock;
	}
	public void setOrder_stock(String order_stock) {
		this.order_stock = order_stock;
	}
	
	
}
