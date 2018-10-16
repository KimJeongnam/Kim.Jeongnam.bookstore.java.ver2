package bookstore.Exception;

public class MapInValueException extends Exception{

	/**
	 *  맵안에 키가 있으면 발생하는 예외
	 */
	private static final long serialVersionUID = 1L;
	
	public MapInValueException(String mapName, Object key) {
		printError(mapName, key);
	}
	
	public MapInValueException(String mapName, Object key, String message) {
		super(message);
		printError(mapName, key);
	}
	
	public void printError(String mapName, Object key) {
		System.err.println(mapName+"에 이미 key='"+key+"'가 있습니다.");
	}
}
