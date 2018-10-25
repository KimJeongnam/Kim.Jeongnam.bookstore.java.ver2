package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bookstore.exception.MapInValueException;

public class Book {
	private static Map<String, Book> shelfMap = new HashMap<String, Book>();
	private static ArrayList<Book> shelfList = new ArrayList<Book>();

	public static void setShelf(Map<String, Book> shelfMap) {
		Book.shelfMap = shelfMap;
	}

	public static Map<String, Book> getShelfMap() {
		return shelfMap;
	}
	
	public static ArrayList<Book> getShelfList(){
		return shelfList;
	}

	public static void initShelf(ArrayList<Book> books) throws MapInValueException {
		for (Book book : books) {
			if (shelfMap.containsKey(book.getBook_code())) {
				throw new MapInValueException("shelf", book, "[Error] initShelf()");
			}
			shelfMap.put(book.getBook_code(), book);
		}
	}

	private String book_code;
	private String book_name;
	private String author;
	private String price;
	private String stock;

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

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Book Code:"+getBook_code()+
				" 제목:"+getBook_name()+
				" 저자:"+getAuthor()+
				" 가격 :"+getPrice()+
				" 수량:"+getStock();
	}

}
