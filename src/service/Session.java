package service;

public class Session {
	private String id;
	private boolean status=false;

	private static Session session = new Session();
	
	private Session() {};

	public static Session getInstance() { return session; }
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public void logout() {
		setStatus(false);
		setId(null);
	}
}
