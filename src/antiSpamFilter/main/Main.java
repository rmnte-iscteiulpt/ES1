package antiSpamFilter.main;

import antiSpamFilter.engines.MainEngine;
import antiSpamFilter.gui.MainWindow;
import antiSpamFilter.tools.RulesUtility;

/**
 * Main class
 * @author rmnte-iscteiulpt
 *
 */
public class Main {
	
	// Normal default paths
	public static final String defaultRulesPath = "/resources/defaultConfigFiles/rules.cf";
	public static final String defaultHamPath = "/resources/defaultConfigFiles/ham.log";
	public static final String defaultSpamPath = "/resources/defaultConfigFiles/spam.log";
	
	// Algorithm folder paths
	public static final String experimentBaseDirectory = System.getProperty("user.dir") + "/experimentBaseDirectory";	// result folder
	
	// Short testing paths
	//public static final String defaultRulesPath = "/resources/testingConfigFiles/rules.cf";
	//public static final String defaultHamPath = "/resources/testingConfigFiles/ham.log";
	//public static final String defaultSpamPath = "/resources/testingConfigFiles/spam.log";
	
	/**
	 * Main method
	 * @param args Main method launch arguments
	 */
	public static void main(String[] args)	{
		MainEngine me = new MainEngine();
		MainWindow mw = new MainWindow(me);
	}
	
}
