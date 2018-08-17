package telran.text.tests;


import telran.text.StringsJoinBuilder;
import telran.text.StringsJoinString;

public class StringsJoinPerformanceTestAppl {
	private static final int N_STRINGS = 100;
	private static final int N_RUNS = 1000;

	public static void main(String[] args) {
		StringsJoinPerformanceTests testString = new StringsJoinPerformanceTests(N_STRINGS, 
				N_RUNS, new StringsJoinString(null, null), "StringsJoinString");
		
		StringsJoinPerformanceTests testBuilder = new StringsJoinPerformanceTests(N_STRINGS, 
				N_RUNS, new StringsJoinBuilder(null, null), "StringsJoinBuilder");
		testBuilder.runTest();
		System.out.println();
		testString.runTest();
			
		
	}

}
