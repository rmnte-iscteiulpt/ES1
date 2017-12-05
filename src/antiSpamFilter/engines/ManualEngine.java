package antiSpamFilter.engines;

import java.util.ArrayList;
import java.util.Observable;

import antiSpamFilter.datastore.RulesConfigList;

/**
 * Engine that will conduct every operation to make the manual workspace work
 * @author rmnte-iscteiulpt
 *
 */
public class ManualEngine extends Observable	{
	
	private RulesConfigList configList;
	
	/**
	 * Default constructor
	 */
	public ManualEngine() {
		this(new ArrayList<String>());
	}
	
	/**
	 * Constructor that creates a RulesConfigList based on a provided rule list
	 * @param list Rule list to create a new RulesConfigList
	 */
	public ManualEngine(ArrayList<String> list)	{
		configList = new RulesConfigList(list);
	}

	/**
	 * Updates the rules list, sending changes to the GUI
	 * @param rulesList New rules list
	 */
	public void updateRules(ArrayList<String> rulesList) {
		configList.updateRules(rulesList);
		updateManualPanel();
	}
	
	/**
	 * Updates weights based on a given weight list
	 * @param weightList New weights list
	 */
	public void updateWeights(ArrayList<Float> weightList)	{
		configList.updateWeights(weightList);
	}
	
	/**
	 * Resets the weights in the configuration, sending changes to the GUI
	 */
	public void resetWeights()	{
		configList.resetWeights();
		updateManualPanel();
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
	private void updateManualPanel()	{
		setChanged();
		notifyObservers(this.configList);
	}
	
}
