package models;

import java.util.ArrayList;


public class Order {
	public static ArrayList<Order> confirmAsklist = new ArrayList<Order>();		//주문요청 리스트
	public static ArrayList<Order> buylist = new ArrayList<Order>();			//구매 리스트
	public static ArrayList<Order> refundlist = new ArrayList<Order>();
	
	public static int getCheckCount( ArrayList<Order>  list) {
		int cnt=0;
		
		for(Order o : list) {
			if(o.isChecked())
				cnt++;
		}
		
		return cnt;
	}
	
	private Boolean checked;
	private String order_code;		// 주문 코드
	private String user_id;			// 요청 인
	private String date;				// 주문 일
	private String time;			// 시간
	private String count;			// 주문 내역 개수
	private String totalPrice;		// total
	private Boolean refund_status; 	//환불 요청 상태
	
	public Boolean isChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public String getOrder_code() {
		return order_code;
	}
	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Boolean getRefund_status() {
		return refund_status;
	}
	public void setRefund_status(Boolean refund_status) {
		this.refund_status = refund_status;
	}
}
