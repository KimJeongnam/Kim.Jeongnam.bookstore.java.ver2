package service;

import java.util.HashMap;
import java.util.Map;

import Models.Code;
import service.main.GuestLogin;
import service.main.HostLogin;
import service.main.UserAdd;

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
	}
	
	public Map<Integer, Service> getMap() { return map; }
}
