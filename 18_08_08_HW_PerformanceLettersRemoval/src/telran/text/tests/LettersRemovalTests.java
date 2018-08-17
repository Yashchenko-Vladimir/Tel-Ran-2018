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
		// ������ ��������� ���������������� test � ����� ������� perfopm-test
/*		lettersRemoval = new LettersRemovalReplaceAll(hello);
 		�������� �� ������ replaceAll  - �������� ���� 'l'
	    lettersRemoval = new LetterRemovalBuilder(); 
	   �������� char ������� �� ���������, ��������� ������� � ������ �� ������!
	   lettersRemoval = new LetterRemovalCharsArray(hello)
	   �������� ������ char � ����� �� ���� ��������� ����� ������ � ������� ��� �������� 'l',
        � ����� ��������� ��� ������������� � ������
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
