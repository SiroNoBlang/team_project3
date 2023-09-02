package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatorTest {

	@Test
	void testSum() {
		Calculator cal = new Calculator();
		assertEquals(3, cal.sum(1, 2));
	}

}
