import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RangeTest {
	int min = 100;
	int max = 200;
	Range range;

	@BeforeEach
	void setUp() throws Exception {
		range = new Range(min, max);
	}

	@Test
	void chexkInRange() {
		assertEquals(RangeCode.OK, range.checkNumberInRange(150));
		assertEquals(RangeCode.OUT_LEFT, range.checkNumberInRange(90));
		assertEquals(RangeCode.OUT_RIGHT, range.checkNumberInRange(210));
	}

}
