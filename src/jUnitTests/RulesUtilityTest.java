package jUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import antiSpamFilter.main.Main;
import antiSpamFilter.tools.RulesUtility;

class RulesUtilityTest {

	/**
	 * Tests the rules utility constructors
	 */
	@Test
	void test() {
		RulesUtility ru1 = new RulesUtility();
		assertEquals(ru1.getRulesPath(), Main.defaultRulesPath);
		
		RulesUtility ru2 = new RulesUtility("/path/");
		assertEquals(ru2.getRulesList(), ru1.getRulesList());
	}

}
