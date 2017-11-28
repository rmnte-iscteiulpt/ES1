package antiSpamFilter.main;

import antiSpamFilter.engines.MainEngine;
import antiSpamFilter.gui.MainWindow;
import antiSpamFilter.tools.RulesUtility;

public class Main {
	
	public static void main(String[] args)	{
		// TODO Create the engines and guis here, join them in a gui method
		MainEngine me = new MainEngine();
		
		MainWindow mw = new MainWindow(me);
	}
	
}
