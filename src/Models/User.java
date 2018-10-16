package Models;

import java.util.HashMap;
import java.util.Map;

public class User {
	private static Map<String, User> guests = new HashMap<String, User>();
	private static Map<String, User> hosts = new HashMap<String, User>();
	
	public static Map<String, User> getHosts(){ return hosts; }
	public static Map<String, User> getGuests(){ return guests; }
	
	private String id;
	private String pw;
	private String permission;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
}
