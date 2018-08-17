package telran.expressions.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static telran.expressions.StringExpressions.*;
import telran.expressions.StringExpressions;

class StringExpressionsTests {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void lessThan1000() {
		assertTrue("100".matches
				(StringExpressions.getRegexPositiveLessThan1000()));
		assertFalse("1000".matches
				(StringExpressions.getRegexPositiveLessThan1000()));
		assertTrue("1".matches
				(getRegexPositiveLessThan1000()));
		assertFalse("-1".matches
				(getRegexPositiveLessThan1000()));
		assertTrue("12".matches
				(StringExpressions.getRegexPositiveLessThan1000()));
		assertTrue("999".matches
				(StringExpressions.getRegexPositiveLessThan1000()));
		assertTrue("0".matches
				(StringExpressions.getRegexPositiveLessThan1000()));
	}
	
	@Test
	void lessThan256() {
		assertTrue("1".matches(getRegex0_255()));
		assertFalse("256".matches(getRegex0_255()));
		assertTrue("109".matches(getRegex0_255()));
		assertTrue("255".matches(getRegex0_255()));
		assertTrue("0".matches(getRegex0_255()));
		assertFalse("300".matches(getRegex0_255()));
		assertFalse("-255".matches(getRegex0_255()));
	}
	
	@Test
	void ipV4Address() {
		//<0-255>.<0-255>.<0-255>.<0-255>
		assertTrue("255.255.255.255".matches(getIpV4regex()));
		assertTrue("0.0.0.0".matches(getIpV4regex()));
		assertTrue("10.55.115.255".matches(getIpV4regex()));
		assertTrue("001.002.003.255".matches(getIpV4regex()));
		assertTrue("1.2.3.99".matches(getIpV4regex()));
		assertTrue("249.0.12.99".matches(getIpV4regex()));
		assertFalse("0.-1.0.0".matches(getIpV4regex()));
		assertFalse("256.0.12.99.".matches(getIpV4regex()));
		assertFalse("249.0.1299".matches(getIpV4regex()));
	}

}
