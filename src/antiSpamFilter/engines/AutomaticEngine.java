package antiSpamFilter.engines;

import java.util.ArrayList;
import java.util.Observable;

/**
 * @author skner
 *
 */
public class AutomaticEngine extends Observable	{

	private ArrayList<String> rulesList;
	
	/**
	 * 
	 */
	public AutomaticEngine() {
	}

	public void updateRules(ArrayList<String> rulesList) {
		this.rulesList = rulesList;
	}

	
}
