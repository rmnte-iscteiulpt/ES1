package antiSpamFilter.datastore;

import java.util.ArrayList;

/**
 * @author rmnte-iscteiulpt
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

	/**
	 * Constructor that takes a list of rules and applies default weight values to each one
	 * @param list The list containing the rules
	 */
	public RulesConfigList(ArrayList<String> list) {
		rulesList = list;
		resetWeights();
	}
	
	/**
	 * Constructor that takes both rule list and weight list, pairing them together
	 * @param rulesList List containing the rules
	 * @param weightList List containing the respective rule weights
	 */
	public RulesConfigList(ArrayList<String> rulesList, ArrayList<Float> weightList)	{		
		this.rulesList = rulesList;
		this.weightList = weightList;
		checkErrors();
	}

	/**
	 * Updates one single weight, set on a specific index
	 * @param i	Index on weight list to change
	 * @param f New value to replace on the list
	 */
	public void updateWeightIndex(int i, float f)	{
		weightList.set(i, f);
	}
	
	/**
	 * Updates the rules list, reseting the whole class
	 * @param rulesList The list containing the rules 
	 */
	public void updateRules(ArrayList<String> rulesList)	{
		this.rulesList = rulesList;
		resetWeights();
	}
	
	/**
	 * Updates entire weightList
	 * @param weightList New weight list to replace
	 */
	public void updateWeights(ArrayList<Float> weightList) {
		this.weightList = weightList;
		checkErrors();
	}
	
	/**
	 * Resets the weight list, making every value default: 0f
	 */
	public void resetWeights()	{
		weightList = new ArrayList<Float>();
		for(int i = 0; i<rulesList.size(); i++)	{
			weightList.add(0.0f);
		}
	}

	/**
	 * Getter for the rule list
	 * @return The rule list
	 */
	public ArrayList<String> getRulesList() {
		return rulesList;
	}

	/**
	 * Getter for the weight list
	 * @return The weight list
	 */
	public ArrayList<Float> getWeightList() {
		return weightList;
	}

	/**
	 * Verifies errors between rule and weight lists
	 */
	private void checkErrors()	{
		// TODO Handle exceptions
		if(rulesList.size() != weightList.size())
			System.err.println("Rules and weights provided don't match.");
		if(rulesList.isEmpty())	{
			System.err.println("Rules list is empty.");
		}
	}
	
	/**
	 * Getter for a specific weight, using a rule to search for the value
	 * @param rule The rule to which return the respective weight
	 * @return The weight of the given rule
	 */
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
