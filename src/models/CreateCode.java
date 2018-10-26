package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CreateCode {
	public static String run() {
		String code = "";
		
		Random random = new Random();
		
		/*141-172*/
		Date date = new Date();

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		
		code += format.format(date);
		
		for(int i=0; i<4; i++) {
			code += random.nextInt(10);
			if(i%2==0) {
				code += random.nextInt(1000);
			}
			if(i%3==0) {
				code += createRandomChar(random);
				code += createRandomChar(random);
			}
		}
		code += random.nextInt(10);
		return code;
	}
	
	private static char createRandomChar(Random r) {
		char c = (char)(97+r.nextInt(26));
		return c;
	}
}
