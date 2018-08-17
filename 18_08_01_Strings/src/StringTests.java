import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StringTests {

	@BeforeEach
	void setUp() throws Exception {
	}
	

	@Test
	void StringImmutable() {
		final String str = new String("Hello"); // 'a' char constant
		                                        // "a" string
		String str1;
		str1 = str.replaceAll("l", "");
		assertTrue(str1.equals("Heo")); 
	}

	@Test
	void teststirngsIntroduction() {
		String str = "Hello";
		String hello = "Hello";
		String str1 = new String("Hello");
		int a = 10;
//		String str2 = String.format("Hello " + a);
//         делается более краство
		String str2= String.format("Hello %d", a);
		String str3 = new String(new char[] {
			'H', 'e', 'l', 'l', 'o'	
		}) ;
		String strEmpty = "";  // String strEmpty; ---- this null

// isEmpty не проверяет на  null, а на 	пустоту ""	
		
		assertTrue(str == hello); // Использеутся константа
		assertFalse(str == str1); //  в str1 Создается новоя ссылочная переменная
		assertTrue(str.equals(str1));
		assertTrue(str2.equals("Hello 10"));
		assertTrue(str.equals(str3));
		assertTrue(strEmpty.isEmpty());
	}
	
	@Test 
	void regexIntroduction() {
		String str = "abcdef12";
		assertTrue(str.matches("abc.*"));     // or abc.+  + -> должжен быть один и более
		// .? любой символ  один раз, даже ничего
		// .{3} любой символ должен повтор 3 раза
		// .+\\*   ( \\ - экранирование) str должен заканчиваться на *
	}
	
	@Test
	void nonLettersReplace() {
		String str = "+ -a1 100 * bc";
		String expected = "#a#bc";
//		str = str.replaceAll("","");
		assertEquals(expected, str.replaceAll("[^a-zA-Z]+","#"));
	}
	
	@Test
	void regexMobileIsrael() {
//		String regexExp = "^(\\+972|0)\\d{2}-?\\d{2}-?\\d{2}-?\\d{3}";
//		String regexExp = "^(\\+972|0)([\\d]{9}|[\\d\\-]{12})";
		String regexExp = "^(\\+972|0)([0-9]-?){9}";
		assertTrue("0501234567".matches(regexExp));
		assertFalse("05012345".matches(regexExp));
		assertTrue("+97250-12-34-567".matches(regexExp));
		assertFalse("97250-12-34-567".matches(regexExp));
		assertFalse("+9720501234567".matches(regexExp));
		assertTrue("+972501234567".matches(regexExp));
	}
	
	
	@Test 
	void split() {
		String expr="123+56/20";
		String[] tokens=expr.split("\\d+");
		String[] expected = {"", "+", "/"}; // String.trim удаляет пробелы
		assertArrayEquals(expected, tokens);
	}
}
















