package antiSpamFilter.main;

import antiSpamFilter.engines.MainEngine;
import antiSpamFilter.gui.frames.MainWindow;

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
	
	// Default Export folder
	public static final String defaultExportPath = System.getProperty("user.dir") + "\\AntiSpamConfigurationForLeisureMailbox\\customConfigFiles\\";
	
	/* 
	 * BUGS:
	 * 
	 * TODO
	 * Code Inspection Template reunion
	 * Youtube showcase video
	 * 
	 */
	
	/**
	 * Main method
	 * @param args Main method launch arguments
	 */
	public static void main(String[] args)	{
		MainEngine me = new MainEngine();
		new MainWindow(me);
	}
	
}
