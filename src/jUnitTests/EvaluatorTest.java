package jUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import antiSpamFilter.main.Main;
import antiSpamFilter.tools.Evaluator;

class EvaluatorTest {

	/**
	 * Tests the evaluator constructors
	 */
	@Test
	void testConstructors() {
		// Default path
		Evaluator e1 = new Evaluator();
		assertEquals(e1.getHamPath(), Main.defaultHamPath);
		assertEquals(e1.getSpamPath(), Main.defaultSpamPath);
		
		// Unexisting path
		Evaluator e2 = new Evaluator("/path","/path");
		assertEquals(e2.getHamPath(), Main.defaultHamPath);
		assertEquals(e2.getSpamPath(), Main.defaultSpamPath);
	}

}
