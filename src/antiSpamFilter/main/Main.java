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
	
	/**
	 * Main method
	 * @param args Main method launch arguments
	 */
	public static void main(String[] args)	{
		MainEngine me = new MainEngine();
		MainWindow mw = new MainWindow(me);
	}
	
}
