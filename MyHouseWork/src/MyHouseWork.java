
public class MyHouseWork {
	public static String secret;

	public static void main(String[] args) {
//		String s = "     53     + 4        ";
//		System.out.println(s.matches("^.*[\\s\\+/\\-\\*](\\s*)?[\\+/\\-\\*].*$"));
//		System.out.println(s.matches("^.*[1]{2,}.*$"));
//		System.out.println(5/3);
//		secret = "0123456789";
//		int a = 234;
//		
//		System.out.println(getChiper(a));
//		System.out.println(getPlainText(getChiper(a)));
		
//		System.out.println(Math.log(128) / Math.log);  
		System.out.println(Math.pow(127, 1.0/3.0));
		System.out.println(Math.pow(2, 7));
		System.out.println(Math.sqrt(127));
		
		
		
	}

//	private static  getPlainText(StringBuilder chiper) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	private static StringBuilder getChiper(int a) {
		int f = 0;
		int b = 0;
		StringBuilder builder = new StringBuilder("");
		while (true) {
			System.out.println("ff");
			b = a%secret.length();
			builder.append(secret.charAt(b));
			a = a / secret.length();
			if (a == 0)
				break;
		}
		
		return builder;
		
		
	}
}
