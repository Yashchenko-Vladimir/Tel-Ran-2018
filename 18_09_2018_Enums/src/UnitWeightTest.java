import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnitWeightTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void convert() {
		assertEquals(1000, UnitWeight.KG.convert(UnitWeight.GR));
		System.out.println(UnitWeight.KG.convert(UnitWeight.LBS));
	}

}
