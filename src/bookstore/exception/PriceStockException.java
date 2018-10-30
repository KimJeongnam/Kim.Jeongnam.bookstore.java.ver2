package bookstore.exception;

import javax.swing.JOptionPane;

public class PriceStockException{
	
	public static int validation(String str) {
		int result = 0;
		
		try {
			result = parseInt(str);
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "숫자를 입력해주세요. Long number : "+str
			, "Input Error", JOptionPane.ERROR_MESSAGE);
			result = -1;
		}catch(NagetiveNumberException e1) {
			JOptionPane.showMessageDialog(null, "양수를 입력해주세요. Long number : "+e1.getMessage()
					, "Input Error", JOptionPane.ERROR_MESSAGE);
			result = -1;
		}
		
		return result;
	}
	
	private static int parseInt(String str) throws NagetiveNumberException, NumberFormatException{
		int result = 0;
		
		
		result = Integer.parseInt(str);
		
		if(result < 0)
			throw new NagetiveNumberException(str);
		
		return result;
	}
}

class NagetiveNumberException extends  Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NagetiveNumberException(String str) {
		super(str);
	}
}
