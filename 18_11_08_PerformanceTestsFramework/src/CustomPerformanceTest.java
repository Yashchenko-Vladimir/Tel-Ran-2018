import telran.tests.annotations.Test;

public class CustomPerformanceTest {

	static String[] strings = new String[1000];
	static {
		for (int i = 0; i < strings.length; i++) {
			strings[i] = "HELLO";
		}
	}

	@Test(1000)
	public void StringsJoinString() {
		String res = "";
		for (String string : strings) {
			res+=string;
		}
	}
	
	@Test(100000)
	public void stringJoinStringBuilder() {
		StringBuilder res = new StringBuilder("");
		for (String string : strings) {
			res.append(string);
		}
	}
}
