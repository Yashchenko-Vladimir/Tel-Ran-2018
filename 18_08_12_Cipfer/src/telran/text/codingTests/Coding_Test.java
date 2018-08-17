package telran.text.codingTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.text.coding.Cipher;

class Coding_Test {

	private static final double COUNT_LETTERS_WORD = 20;
	private static final double COUNT_CHAR = 128;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		Cipher cipher = new Cipher(getText());
		String textCoding = cipher.getCipher("Hello, people!!!");
		assertTrue(cipher.getPlainText(textCoding).equals("Hello, people!!!"));
		assertFalse(cipher.getPlainText(textCoding).equals("Hello!!!"));
		
		
		Cipher cipher1 = new Cipher(getText());
		String textMessage = getText();
		String textCoding1 = cipher1.getCipher(textMessage);
		assertTrue(cipher1.getPlainText(textCoding1).equals(textMessage));
		assertFalse(cipher1.getPlainText(textCoding1).equals("Hello!!!"));
		
		
	}

	private String getText() {
		StringBuilder builder = new StringBuilder("");
		int size = 0;
		while(size == 0) {
			size = (int)(Math.random()*COUNT_LETTERS_WORD);
		}
		char ch = '0';
		for (int i = 0; i < size; i++) {
			ch = (char)(Math.random()*COUNT_CHAR);
				if(!checkLetters(builder, ch))
					builder.append(ch);
				else
					i--;
			}
		return builder.toString();
	}

	private boolean checkLetters(StringBuilder builder, char ch) {
		boolean res = false;
		for (int i = 0; i < builder.length(); i++) {
			if(builder.charAt(i) == ch)
				res = true;
		}
		return res;
	}

}
