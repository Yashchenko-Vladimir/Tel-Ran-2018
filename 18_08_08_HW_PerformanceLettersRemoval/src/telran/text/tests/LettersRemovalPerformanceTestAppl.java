package telran.text.tests;

import telran.text.LetterRemovalBuilder;
import telran.text.LetterRemovalCharsArray;
import telran.text.LetterRemovalReplaceAll;
import telran.text.LettersRemoval;

public class LettersRemovalPerformanceTestAppl {
	private static final int N_STRINGS = 10000;
	private static final int N_RUNS = 1000000;
	
	
	public static void main(String[] args) {
		
		LettersRemovalPerformanceTests testRemoveReplaceAll = new LettersRemovalPerformanceTests(N_STRINGS, N_RUNS,
				new LetterRemovalReplaceAll(null), "RemoveReplaceAll");
		LettersRemovalPerformanceTests testRemoveCharsArray = new LettersRemovalPerformanceTests(N_STRINGS, N_RUNS,
				new LetterRemovalCharsArray(null), "RemovalCharsArray");
		LettersRemovalPerformanceTests testRemoveBuilder = new LettersRemovalPerformanceTests(N_STRINGS, N_RUNS,
				new LetterRemovalBuilder(null), "RemovalBuilder");
		testRemoveReplaceAll.runTest();
		System.out.println();
		testRemoveCharsArray.runTest();
		System.out.println();
		testRemoveBuilder.runTest();
		
		
	}

}
