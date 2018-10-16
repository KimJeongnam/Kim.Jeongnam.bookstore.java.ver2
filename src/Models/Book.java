package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bookstore.Exception.MapInValueException;

public class Book {
	private static Map<Integer, Book> shelf = new HashMap<Integer, Book>();
	public static Map<Integer, Book> getShelf(){ return shelf;}
	
	public static void initShelf(ArrayList<Book> books)throws MapInValueException{
		for(Book book : books) {
			if(shelf.containsKey(book.getBook_code())) {
				throw new MapInValueException("shelf", book,"[Error] initShelf()");
			}
			shelf.put(book.getBook_code(), book);
		}
	}
	
	private int book_code;
		private String book_name;
	private String author;
	private int stock;

	public int getBook_code() {
		return book_code;
	}
	public void setBook_code(int book_code) {
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
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
}
