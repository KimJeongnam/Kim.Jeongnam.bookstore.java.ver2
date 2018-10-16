package service;

public class Session {
	private String id;
	private String permission;
	
	private static Session session = new Session();
	
	private Session() {};

	public static Session getInstance() { return session; }
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
}
