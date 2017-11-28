/**
 * 
 */
package antiSpamFilter.engines;

import java.util.ArrayList;
import java.util.Observable;

/**
 * @author skner
 *
 */
public class ManualEngine extends Observable	{
	
	private ArrayList<String> rulesList;
	
	public ManualEngine() {
		this(new ArrayList<String>());
	}
	
	public ManualEngine(ArrayList<String> list)	{
		rulesList = list;
	}

	public void updateRules(ArrayList<String> rulesList) {
		this.rulesList = rulesList;
		setChanged();
		notifyObservers(rulesList);
	}


}
