package telran.util.words.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.words.AnagramClass;

class AnagramTests {
	String word="electricity";
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	public void testAnagram() {
		assertTrue(AnagramClass.isAnagramLetters(word,"tic"));
		assertTrue(AnagramClass.isAnagramLetters(word,"lecter"));
		assertTrue(AnagramClass.isAnagramLetters(word,"city"));
		assertTrue(AnagramClass.isAnagramLetters(word,"tric"));
	}
	@Test
	public void testNotAnagram() {
		assertFalse(AnagramClass.isAnagramLetters(word,"cell"));
		assertFalse(AnagramClass.isAnagramLetters(word,"yello"));
		assertFalse(AnagramClass.isAnagramLetters(word,"vector"));
		assertFalse(AnagramClass.isAnagramLetters(word,""));
		
	}

}
