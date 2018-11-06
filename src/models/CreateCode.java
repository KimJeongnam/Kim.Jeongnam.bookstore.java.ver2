package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CreateCode {
	public static String bookCode() {
		String code = "BOOK_";
		
		return create(code);
	}
	
	public static String orderCode() {
		String code = "ORDER_";

		return create(code);
	}
	
	private static String create(String str) {
		String code = str;
		Random random = new Random();
		
		/*141-172*/
		Date date = new Date();

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
		
		code += format.format(date);
		for(int i=0; i<4; i++) 
			code+=createRandomChar(random);
		
		
		return code;
	}
	
	private static char createRandomChar(Random r) {
		char c = (char)(65+r.nextInt(26));
		return c;
	}
}
