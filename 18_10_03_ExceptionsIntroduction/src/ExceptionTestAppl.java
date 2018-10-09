
public class ExceptionTestAppl {

	public static void main(String[] args) {
		String str = getString();
//		str.trim();
		int number  = Integer.parseInt(str);
	}

	private static String getString() {
		
		return "12a";
	}

}
