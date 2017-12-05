package antiSpamFilter.engines;

import java.util.ArrayList;
import java.util.Observable;

import antiSpamFilter.datastore.RulesConfigList;

/**
 * Engine that will conduct every operation to make the automatic workspace work
 * @author rmnte-iscteiulpt
 *
 */
public class AutomaticEngine extends Observable	{

	private RulesConfigList configList;
	
	/**
	 * Default constructor
	 */
	public AutomaticEngine() {
		this(new ArrayList<String>());
	}
	
	/**
	 * Constructor that creates a new configuration list based on a given rules list
	 * @param list Rules list to create the configuration list
	 */
	public AutomaticEngine(ArrayList<String> list)	{
		configList = new RulesConfigList(list);
	}

	/**
	 * Updates the rules list, sending changes to the GUI
	 * @param rulesList New rules list
	 */
	public void updateRules(ArrayList<String> rulesList) {
		configList.updateRules(rulesList);
		updateAutoPanel();
	}
	
	/**
	 * Getter for the RulesConfigList
	 * @return The current configuration list
	 */
	public RulesConfigList getConfigList()	{
		return configList;
	}
	
	/**
	 * Updates the GUI
	 */
	private void updateAutoPanel()	{
		setChanged();
		notifyObservers(this.configList);
	}
}
