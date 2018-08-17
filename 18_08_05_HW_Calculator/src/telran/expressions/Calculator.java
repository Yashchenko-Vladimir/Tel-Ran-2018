package telran.expressions;

//import java.util.Arrays;

public class Calculator {
	private static int[] ar;
	private static String[] str;
	private static final int LENGTH = 8;
	private static final int N_RESERV = 2;
	private static int size = 0;
	

	public static Integer calculateExpression(String expression) {
		// 3+5  *3 -> 24  а не 45
		// 3 ++ 5 return null
		// 3%5 -> null
		// 3 + a -> null при неправильном построении арифметического выражения возвращает null 
		// need to use split
		
		if(
				!(expression.matches("^(\\s*)?\\d+[\\s\\d\\+/\\-\\*]+\\d+(\\s*)?$"))||
				(expression.matches("^.*[\\+/\\-\\*](\\s*)?[\\+/\\-\\*].*$"))
		  )
			return null;
		ar  = new int[LENGTH];
		str  = new String[LENGTH];
		int k = 0;
		for(String retval: expression.split("\\+|/|-|\\*") ) {
			ar[k] = Integer.parseInt(retval.trim());
			if (k == (ar.length-1))
				creatNewInt();
			k++;
			size++;
		}
		int b=0;
		for(String retval: expression.split("(\\d+) ?") ) {
			str[b] = retval.trim();
			b++;
		}
		return resultExpression();
	}
	
	private static Integer resultExpression() {
		Integer result = ar[0];
		for (int i = 1; i < size; i++) {
			if (str[i].equals("+")) 
				result += ar[i];
			else if (str[i].equals("-"))
				result -=ar[i];
			else if (str[i].equals("*"))
				result *=ar[i];
			else if (str[i].equals("/"))
				result /=ar[i];
		}
		size=0;
		return result;
	}

	private static void creatNewInt() {
		int [] arResInt = new int [LENGTH*N_RESERV] ;
		for (int i = 0; i < ar.length; i++) {
			arResInt[i] = ar[i];
		}
		ar = arResInt;
		
		String [] arResString = new String [LENGTH*N_RESERV] ;
		for (int i = 0; i < str.length; i++) {
			arResString[i] = str[i];
		}
		str = arResString;
		
	}
}
