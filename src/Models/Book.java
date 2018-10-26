package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import bookstore.exception.MapInValueException;
import forms.panels.host.InsertBookPanel;

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

	
	public static Book validation(String setting) {
		Book book = null;
		InsertBookPanel insertBookPanel = InsertBookPanel.getInstance();
		
		String book_code = insertBookPanel.getLabel_bookCode().getText().trim();
		String book_name = insertBookPanel.getTf_book_name().getText().trim();
		String author = insertBookPanel.getTf_author().getText().trim();
		String price = insertBookPanel.getTf_price().getText().trim();
		String stock = insertBookPanel.getTf_stock().getText().trim();

		if(setting.equals("add")) {
			if(book_code.length() != 0) {
				JOptionPane.showMessageDialog(null, "입력칸을 Clear 하고 진행해주세요.", "Book Code 오류"
						, JOptionPane.WARNING_MESSAGE);
				return book;
			}
		}else if(setting.equals("update")) {
			if(book_code.length() == 0) {
				JOptionPane.showMessageDialog(null, "수정을 원하는 책을 테이블에서 책을 선택 후 진행해주세요!", "Book Code 오류"
						, JOptionPane.WARNING_MESSAGE);
				return book;
			}
		} else if (setting.equals("delete")) {
			book = new Book();
			book.setBook_code(book_code);
			return book;
		}

		if (book_name.length() == 0) {
			JOptionPane.showMessageDialog(null, "책 이름을 입력해주세요!", "공백 오류"
					, JOptionPane.WARNING_MESSAGE);
			insertBookPanel.getTf_book_name().requestFocus();
			return book;
		} else if (author.length() == 0) {
			JOptionPane.showMessageDialog(null, "책의 저자를 입력해주세요!", "공백 오류"
					, JOptionPane.WARNING_MESSAGE);
			insertBookPanel.getTf_author().requestFocus();
			return book;
		} else if (price.length() == 0) {
			JOptionPane.showMessageDialog(null, "가격을 입력해주세요!", "공백 오류"
					, JOptionPane.WARNING_MESSAGE);
			insertBookPanel.getTf_author().requestFocus();
			return book;
		} else if (stock.length() == 0) {
			JOptionPane.showMessageDialog(null, "수량을 입력해주세요!", "공백 오류"
					, JOptionPane.WARNING_MESSAGE);
			insertBookPanel.getTf_author().requestFocus();
			return book;
		} else {
			try {
				Integer.parseInt(price);
				Integer.parseInt(stock);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "가격 및 수량은 숫자입니다!", "숫자 오류", JOptionPane.WARNING_MESSAGE);
				insertBookPanel.getTf_author().requestFocus();
				return book;
			}
		}
		
		book = new Book();
		book.setBook_code(book_code);
		book.setBook_name(book_name);
		book.setAuthor(author);
		book.setPrice(price);
		book.setStock(stock);
		return book;
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
