package service;

public interface Guest {
	public void buy();	   			// 구매요청
	public void buyAskList();       // 구매요청 목록
	public void nowBuy(); 			// 바로구매
	public void refund();	        // 환불
}
