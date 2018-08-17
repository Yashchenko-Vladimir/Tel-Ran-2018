package telran.text.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.text.LetterRemovalBuilder;
import telran.text.LetterRemovalCharsArray;
import telran.text.LetterRemovalReplaceAll;
import telran.text.LettersRemoval;

class LettersRemovalTests {
	String str = "hello";
	String expected = "heo";
	LettersRemoval lettersRemoval;
	char letter = 'l';

	@BeforeEach
	void setUp() throws Exception {
		lettersRemoval = new LetterRemovalReplaceAll(str);
//		lettersRemoval = new LetterRemovalBuilder(str);
//		lettersRemoval = new LetterRemovalCharsArray(str);
		// сперав проверить функциональность test а потом сделать perfopm-test
/*		lettersRemoval = new LettersRemovalReplaceAll(hello);
 		удаление на основе replaceAll  - вставить туда 'l'
	    lettersRemoval = new LetterRemovalBuilder(); 
	   помещать char которые не удаляемые, удаляемые символы в билдер не класть!
	   lettersRemoval = new LetterRemovalCharsArray(hello)
	   получить массив char и потом из него посторить новый массив в котором нет символов 'l',
        а потом перегнать его конструктором в строку
*/
	}

	@Test
	void test() {
		lettersRemoval.remove(letter);
		String h = (String) lettersRemoval.getString();
		System.out.println(h);
		assertEquals(expected, h);
	}

}
