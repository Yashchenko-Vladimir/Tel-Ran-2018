package telran.expressions.tests;

import static org.junit.jupiter.api.Assertions.*;
import static telran.expressions.Calculator.*;
import org.junit.jupiter.api.Test;

class CalculatorTests {

	@Test
	void test() {
		assertEquals((Integer)57,calculateExpression("     53     + 4        "));
		assertEquals((Integer)2,calculateExpression("3 + 5 * 3 - 2 / 11"));
		assertEquals((Integer)null,calculateExpression("+ 5     +  4      "));
		assertEquals((Integer)null,calculateExpression(" 5     ++  4    "));
		assertEquals((Integer)null,calculateExpression(" 5     +   /  4 + 90   "));
		assertEquals((Integer)null,calculateExpression(" 5    +  4 -g d s g   6    "));
		assertEquals((Integer)null,calculateExpression("+ 5  +  6  - "));
	}

}
