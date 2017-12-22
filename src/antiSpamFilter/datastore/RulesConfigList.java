package antiSpamFilter.datastore;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * @author rmnte-iscteiulpt
 *
 * Data algorithm to store rules and associated weights. This algorithm will make sure rules and weight are associated correctly, 
 * avoiding unnecessary checks from other classes.
 */
public class RulesConfigList {

	private ArrayList<String> rulesList;	// The list containing the rules
	private ArrayList<Float> weightList;	// The list containing the respective weights
	
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
	 * @param weightList New weight array to replace the list
	 */
	public void updateWeights(double[] weightList)	{
		this.weightList = new ArrayList<Float>();
		for(int i = 0; i<weightList.length; i++)	{
			this.weightList.add((float) weightList[i]);
		}
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
	 * Exports current configuration list to a .cfg file on a specific folder
	 * @param path Path to the file that the file needs to be saved.
	 * @throws UnsupportedEncodingException Exception if the file has a different enconding
	 * @throws FileNotFoundException If the file isn't found, shouldn't happen since its an export
	 */
	public void exportTo(String path) throws FileNotFoundException, UnsupportedEncodingException	{

		PrintWriter writer = new PrintWriter(path, "UTF-8");
		for(int i = 0; i<rulesList.size(); i++)	{
			writer.println(rulesList.get(i) + " " + weightList.get(i));
		}
		writer.close();
	}
	
	/**
	 * Imports a configuration list from a given .cfg file. It verifies if the given list matches the list stored in the RulesUtility class.
	 * @param path The path pointing to the .cfg file
	 * @throws IOException IOException with the BufferedReader
	 */
	public void importFrom(String path) throws IOException	{
		ArrayList<String> newRuleList = new ArrayList<String>();
		ArrayList<Float> newWeightList = new ArrayList<Float>();
		BufferedReader br = new BufferedReader(new FileReader(path));
		String buffer = br.readLine();
		String[] args = {};
	    String rule = "";
	    float weight = 0f;
	    boolean read = true;
	    while (read) {
	    	args = buffer.split(" ");
	    	rule = args[0];
	    	weight = Float.parseFloat(args[1]);
	    	if(!rulesList.contains(rule))	{
	        	System.out.println("Configuration file is using different rules. File import unsuccessful.");
	        	return;
	        }	else	{
	        	newRuleList.add(rule);
	        	newWeightList.add(weight);
	        }
	        buffer = br.readLine();
	        if(buffer != null)	{
	        	read = false;
	        	br.close();
	        }
	    }
	    rulesList = newRuleList;
	    weightList = newWeightList;
	    System.out.println("Configuration file imported successfully.");
	}
	
	/**
	 * Verifies errors between rule and weight lists
	 */
	private void checkErrors()	{
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
		// If the rule doesn't exist in the file we're giving the weight value of 0
		if(rulesList.indexOf(rule) == -1)	{
			return 0f;
		}
		return weightList.get(rulesList.indexOf(rule));
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
}
