package service;

import java.util.HashMap;
import java.util.Map;

import models.Code;
import service.main.GuestLogin;
import service.main.HostLogin;
import service.main.UserAdd;
import service.main.host.BookAdd;
import service.main.host.BookDelete;
import service.main.host.BookList;
import service.main.host.BookUpdate;

/*
 * 각 서비스들을 저장하고 있는 클래스 
 */

public class Services {
	private static final Services instance = new Services();
	public static Services getInstance() { return instance; }
	private Map<Integer, Service> map = new HashMap<Integer, Service>();
	
	private Services() {
		// Main service add 
		map.put(Code.MAIN_HOST_LOGIN, new HostLogin());
		map.put(Code.MAIN_GUEST_LOGIN, new GuestLogin());
		map.put(Code.MAIN_USER_ADD, new UserAdd());
		
		// host stock service 
		map.put(Code.HOST_BOOK_LIST, new BookList());
		map.put(Code.HOST_BOOK_ADD, new BookAdd());
		map.put(Code.HOST_BOOK_UPDATE, new BookUpdate());
		map.put(Code.HOST_BOOK_DELETE, new BookDelete());
		
	}
	
	public Map<Integer, Service> getMap() { return map; }
}
