package telran.expressions;

public class StringExpressions {
	
	static public String getRegexPositiveLessThan1000() {
		return "[0-9]{1,3}";
//		return "[0-999]"; // не проходит
	}
	
	static public String getRegex0_255() {
		//regex for number [0-255]
//		return "([0-1]?[0-9]{0,2})|(2[0-4][0-9])|(25[0-5])";
//		return "1\\d\\d|2[0-4]\\d|25[0-5]|\\d{1,2}";
//		return "(1\\d\\d)|(2[0-4]\\d)|(25[0-5])|(\\d{1,2})";
		return "(([01]?\\d?\\d)|(2[0-4]\\d)|(25[0-5]))";
	}
	
	static public String getIpV4regex() {
		return "((([01]?\\d?\\d)|(2[0-4]\\d)|(25[0-5]))\\.){3}(([01]?\\d?\\d)|(2[0-4]\\d)|(25[0-5]))";
//		return String.format("(%s\\.){3}%s", getRegex0_255(), getRegex0_255() );
	}

}
