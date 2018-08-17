package telran.text.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.text.StringsJoin;
import telran.text.StringsJoinBuilder;
import telran.text.StringsJoinString;

class StringsJoinTests {
	String[] strings = {"Hello", "World", "!!!"};
	String expected = "Hello World !!!";
	StringsJoin stringsJoin;
	
	@BeforeEach
	void setUp() throws Exception {
		stringsJoin = new StringsJoinBuilder(strings," ");
	}

	@Test
	void join() {
		String actual = stringsJoin.join();
		assertEquals(expected, actual);
	}

}
