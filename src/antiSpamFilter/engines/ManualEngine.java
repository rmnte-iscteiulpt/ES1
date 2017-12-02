/**
 * 
 */
package antiSpamFilter.engines;

import java.util.ArrayList;
import java.util.Observable;

import antiSpamFilter.datastore.RulesConfigList;

/**
 * @author skner
 *
 */
public class ManualEngine extends Observable	{
	
	private RulesConfigList configList;
	
	public ManualEngine() {
		this(new ArrayList<String>());
	}
	
	public ManualEngine(ArrayList<String> list)	{
		configList = new RulesConfigList(list);
	}

	public void updateRules(ArrayList<String> rulesList) {
		configList.updateRules(rulesList);
		updateManualPanel();
	}
	
	public void resetWeights()	{
		configList.resetWeights();
		updateManualPanel();
	}
	
	public void updateWeights(ArrayList<Float> weightList)	{
		configList.updateWeights(weightList);
	}
	
	public RulesConfigList getConfigList()	{
		return configList;
	}

	private void updateManualPanel()	{
		setChanged();
		notifyObservers(this.configList);
	}
	
}
