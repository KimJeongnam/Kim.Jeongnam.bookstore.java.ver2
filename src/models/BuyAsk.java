package models;

import java.util.ArrayList;

public class BuyAsk {
	public static ArrayList<BuyAsk> list = new ArrayList<BuyAsk>();
	
	private String order_code;		// 주문 코드
	private String date;		// 주문 일
	private String time;
	private String count;			// 주문 내역 개수
	private String totalPrice;		// total
	
	public BuyAsk() {}
	
	public BuyAsk(String order_code, String date, String time, String count, String totalPrice) {
		this.order_code = order_code;
		this.date = date;
		this.time = time;
		this.count = count;
		this.totalPrice = totalPrice;
	}

	public String getOrder_code() {
		return order_code;
	}

	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
}
