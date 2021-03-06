package models;

public class Code {
	public static final String PERMISSION_HOST = "host";
	public static final String PERMISSION_GUEST = "guest";
	
	// MAIN
	public static final int MAIN_GUST_LOGIN_VIEW = 50;  // 카드뷰 핸들러
	public static final int MAIN_HOST_LOGIN_VIEW = 51;
	public static final int MAIN_USER_ADD_VIEW = 52;
	
	public static final int MAIN_HOST_LOGIN = 110;		// 호스트 로그인
	public static final int MAIN_GUEST_LOGIN = 111;		// 고객 로그인 
    public static final int MAIN_USER_ADD= 120; 		// 유저 추가
    
	// GUEST
    public static final int GUEST_CART_COUNT_TOTAL = 299;
	public static final int GUEST_CART_LIST = 201;   	// 장바구니 목록 
	public static final int GUEST_CART_ADD = 202;   	// 장바구니에 책 추가 
	public static final int GUEST_CART_UPDATE = 203; 	// 장바구니 수정
	public static final int GUEST_CART_DEL = 204;     	// 장바구니 삭제
	public static final int GUEST_CART_ALLDEL = 205;   // 장바구니 삭제
	public static final int GUEST_CART_BUY = 206;	   	// 구매요청
	
	public static final int GUEST_NOW_BUY = 210; 		// 바로구매
	public static final int GUEST_BUY_ASK_LIST = 211;  // 구매요청 목록
	public static final int GUEST_BUY_LIST = 212;		//구매 완료 목록
	public static final int GUEST_REFUND = 212;	      // 환불
	
	// HOST
	public static final int HOST_BOOK_LIST = 300; 		// 책 목록
	public static final int HOST_BOOK_ADD = 301;			// 책 추가
	public static final int HOST_BOOK_UPDATE = 302; 	// 책 수정
	public static final int HOST_BOOK_DELETE = 303; 	// 책삭제
	
	public static final int HOST_CONFIRM_ASK_LIST = 310; 	// 주문 목록
	public static final int HOST_ORDER_CONFIRM = 311;			// 결제 승인 
	public static final int HOST_ORDER_CANCLE = 312; 			// 결제 취소  
	public static final int HOST_REFUND_LIST =320;			// 환불요청 승인
	public static final int HOST_REFUND_CONFIRM =321;			// 환불요청 승인
	public static final int HOST_REFUND_CANCLE =322;			// 환불요청 취소
	public static final int HOST_SALE_TOTAL = 398; 			// 결산
	public static final int HOST_CONFIRM_DONE_LIST = 399; 	// 결산 완료 목록
}
