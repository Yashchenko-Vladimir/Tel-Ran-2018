package telran.text.tests;

import telran.text.StringsJoin;

public class StringsJoinPerformanceTests {
	
	private static final String STRING = "string";
	private static final String DELIMETER = " ";
	int nStrings;
		
	public StringsJoinPerformanceTests(int nStrings, int nRuns, StringsJoin stringsJoin, String testName) {
		super();
		this.nStrings = nStrings;
		this.nRuns = nRuns;
		this.stringsJoin = stringsJoin;
		this.testName = testName;
	}

	int nRuns;
	StringsJoin stringsJoin;
	String testName;
	
	public void runTest() {
		String[] strings = getStrings();
		stringsJoin.setStrings(strings);
		stringsJoin.setDelimeter(DELIMETER);
		long start = System.currentTimeMillis();
		for (int i = 0; i < nRuns; i++) {
			stringsJoin.join();
		}
		long finish = System.currentTimeMillis();
		System.out.printf("%s rinning time: %d", testName,finish - start);
	}

	private String[] getStrings() {
		String[] res = new String[nStrings];
		for (int i = 0; i < res.length; i++) {
			res[i] = STRING;
		}
		return res;
	}

}
