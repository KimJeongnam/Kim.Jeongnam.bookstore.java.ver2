package service;

public interface Host_Order {
	public void orderList(); // 주문 목록
    public void orderConfirm(); // 결제 승인
    public void orderCancle(); // 결제 취소
    public void saleTotal(); // 결산
}
