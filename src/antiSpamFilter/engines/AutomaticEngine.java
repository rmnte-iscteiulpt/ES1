package antiSpamFilter.engines;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import antiSpamFilter.AntiSpamFilterAutomaticConfiguration;
import antiSpamFilter.datastore.RulesConfigList;
import antiSpamFilter.main.Main;

/**
 * Engine that will conduct every operation to make the automatic workspace work
 * @author rmnte-iscteiulpt
 *
 */
public class AutomaticEngine extends Observable	{

	private RulesConfigList configList;
	private int lastRes[];
	
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
		lastRes = new int[2];
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
	 * Runs algorithm NSGAII
	 */
	public void runAlgorithm()	{
		try {
			new AntiSpamFilterAutomaticConfiguration(configList);
		} catch (IOException e) {
			System.err.println("IO Error on executing the algorithm.");
			e.printStackTrace();
		}
		int lineNumber = 0;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(Main.experimentBaseDirectory + "/referenceFronts/AntiSpamFilterProblem.rf"));
			String line = br.readLine();
			String[] resLine = line.split(" "); // Assuming the file has at least 1 line, which it should if it managed to be read by the BufferedReader
			int res[] = {(int)Float.parseFloat(resLine[0]), (int)Float.parseFloat(resLine[1])};
			lastRes[0] = res[0];
			lastRes[1] = res[1];	
			line = br.readLine();
			
			// TODO BUG: It gets the first line always
			for(int i = 1; line != null; i++) {
				resLine = line.split(" ");
				res[0] = (int)Float.parseFloat(resLine[0]);
				res[1] = (int)Float.parseFloat(resLine[1]);
				if(res[1] < lastRes[1])	{	// If finds a lower FN value that recorded before
					lastRes[0] = res[0];
					lastRes[1] = res[1];
					lineNumber = i;
				} else 	if(res[1] == lastRes[1])	{	// If finds the same FN value, but lower FP value
					if(res[0] < lastRes[0])	{
						lastRes[0] = res[0];
						lastRes[1] = res[1];
						lineNumber = i;
					}
				}
		        line = br.readLine();
		    }
			
			br.close();
			//System.out.println("Found the best configuration in line " + lineNumber + " with the following results: FP-" + lastRes[0] + " FN-" + lastRes[1]);
			br = new BufferedReader(new FileReader(Main.experimentBaseDirectory + "/referenceFronts/AntiSpamFilterProblem.rs"));
			// Get the respective line
			for(int i = 0; i<=lineNumber; i++)	{	// Assuming both files are made using the same weights and respective results, from the algorithm
				line = br.readLine();
			}
			//System.out.println("Respective configuration weights: " + line);
			// Get the values to a float array
			ArrayList<Float> weightList = new ArrayList<Float>();
			String[] weightsString = line.split(" ");
			for(int i = 0; i<weightsString.length; i++)	{
				weightList.add(Float.parseFloat(weightsString[i]));
			}
		    configList.updateWeights(weightList);
		    updateAutoPanel();
		    br.close();
		} catch (IOException e) {
			System.err.println("Couldnt read file. Something went wrong with the algorithm.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Getter for the RulesConfigList
	 * @return The current configuration list
	 */
	public RulesConfigList getConfigList()	{
		return configList;
	}
	
	/**
	 * Getter for the results, obtained from the algorithm
	 * @return The results of the current configuration loaded in the engine.
	 */
	public int[] getCurrentResults() {
		return lastRes;
	}
	
	/**
	 * Updates the GUI
	 */
	private void updateAutoPanel()	{
		setChanged();
		notifyObservers(this.configList);
	}

}
