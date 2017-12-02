package antiSpamFilter.datastore;

import java.util.ArrayList;

/**
 * @author skner
 *
 * Data algorithm to store rules and associated weights. This algorithm will make sure rules and weight are associated correctly, 
 * avoiding unnecessary checks from other classes.
 */
public class RulesConfigList {

	private ArrayList<String> rulesList;
	private ArrayList<Float> weightList;
	
	/*
	 * TODO 
	 * Add export and import functionality
	 */
	
	public RulesConfigList(ArrayList<String> list) {
		rulesList = list;
		resetWeights();
	}
	
	public RulesConfigList(ArrayList<String> rulesList, ArrayList<Float> weightList)	{		
		this.rulesList = rulesList;
		this.weightList = weightList;
		checkErrors();
	}

	public void updateWeightIndex(int i, float f)	{
		weightList.set(i, f);
	}
	
	public void updateRules(ArrayList<String> rulesList)	{
		this.rulesList = rulesList;
		resetWeights();
	}
	
	public void updateWeights(ArrayList<Float> weightList) {
		this.weightList = weightList;
	}
	
	public void resetWeights()	{
		weightList = new ArrayList<Float>();
		for(int i = 0; i<rulesList.size(); i++)	{
			weightList.add(0.0f);
		}
	}

	public ArrayList<String> getRulesList() {
		return rulesList;
	}

	public ArrayList<Float> getWeightList() {
		return weightList;
	}

	private void checkErrors()	{
		// Verify rules and weight lists for any inconsistencies
		// TODO Handle exceptions
		if(rulesList.size() != weightList.size())
			System.err.println("Rules and weights provided don't match.");
		if(rulesList.isEmpty())	{
			System.err.println("Rules list is empty.");
		}
	}
	
	public float getRuleWeight(String rule)	{
		// TODO QUESTION
		// If the rule doesn't exist in the file, should it just throw an exception? Or just give a weight of 0? How to handle that situation?
		// For now we're giving the weight value of 0
		if(rulesList.indexOf(rule) == -1)	{
			return 0f;
		}
		return weightList.get(rulesList.indexOf(rule));
	}
}
