package antiSpamFilter.main;

import antiSpamFilter.engines.MainEngine;
import antiSpamFilter.gui.MainWindow;
import antiSpamFilter.tools.RulesUtility;

public class Main {
	
	public static void main(String[] args)	{
		MainEngine me = new MainEngine();
		MainWindow mw = new MainWindow(me);
	}
	
}
