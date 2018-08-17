package telran.text.tests;

import telran.text.LettersRemoval;

public class LettersRemovalPerformanceTests {
	private static final String STRING = "hello";
	private static final char CHAR = 'l';
	
	int nStrings;
	int nRuns;
	LettersRemoval lettersRemoval;
	String testName;
	
	public LettersRemovalPerformanceTests(int nStrings, int nRuns,LettersRemoval lettersRemoval,
			String testName) {
		this.nStrings= nStrings;
		this.nRuns = nRuns;
		this.lettersRemoval= lettersRemoval;
		this.testName = testName;
	}
	
	public void runTest() {
		String strings = getstrings();
		lettersRemoval.setStr(strings);
	//	lettersRemoval.setLetter(CHAR);
		long start = System.currentTimeMillis();
		for (int i = 0; i < nRuns; i++) {
			lettersRemoval.remove(CHAR);
		}
		long finish = System.currentTimeMillis();
		System.out.printf("%s rinning time: %d", testName,finish - start);
		
	}

	private String getstrings() {
		String [] ar = new String[nStrings];
		for (int i = 0; i < ar.length; i++) {
			ar[i] = STRING;
		}
		return String.valueOf(ar);
	}
	

}
