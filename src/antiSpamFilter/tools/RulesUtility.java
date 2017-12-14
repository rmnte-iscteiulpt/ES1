package antiSpamFilter.tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import antiSpamFilter.main.Main;

/**
 * The class that holds the rules list, including the file path
 * @author skner
 *
 */
public class RulesUtility {

	private String rulesPath;
	private ArrayList<String> rulesList;

	/**
	 * Default constructor
	 */
	public RulesUtility()	{
		this("");
	}
	
	/**
	 * Constructor
	 * @param rulesPath Specific path for the rules.cf file
	 */
	public RulesUtility(String rulesPath) {
		//LoadingTimer timer = new LoadingTimer(); // Timer debug
		updatePath(rulesPath);
		//System.out.println("File read and sorted in " + timer.getElapsedTime() + "ms."); // Timer debug
	}

	/**
	 * Loads the rules from the file into memory
	 */
	public void loadRules()	{
		rulesList = readFile();
		if(rulesList == null)	{	// This means the file was not found or there was an io exception
			updatePath("");
		}	else	{
			Collections.sort(rulesList);
		}
	}
	
	/**
	 * Updates the rules list, based on a new path
	 * @param rulesPath The new path
	 */
	public void updatePath(String rulesPath)	{
		if(rulesPath.equals(""))	{
			this.rulesPath = Main.defaultRulesPath;
		}	else	{
			this.rulesPath = rulesPath;
		}
		//System.out.println("Rules.cf Path Updated to: -" + this.rulesPath + "-"); // Path debug
		loadRules();
	}

	/**
	 * Reads the rules.cf file, returning the list of rules
	 * @return The new list containing the rules
	 */
	private ArrayList<String> readFile()	{
		try	{
			ArrayList<String> ruleList = new ArrayList<String>();
			BufferedReader br;
			if(rulesPath == Main.defaultRulesPath)	{
				br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(rulesPath)));
			}	else	{
				br = new BufferedReader(new FileReader(rulesPath));
				
			}
		    String line = br.readLine();
		    while (line != null) {
		        ruleList.add(line);
		        //System.out.println(line);
		        line = br.readLine();
		    }
		    br.close();
		    return ruleList;
		} catch (FileNotFoundException e1) {
			System.out.println("Path for rules.cf file doesn't exist. Using the default file.");
		} catch (IOException e1) {
		}
		return null;
	}
	
	/**
	 * Getter for the current rules list
	 * @return The rules list
	 */
	public ArrayList<String> getRulesList()	{
		return rulesList;
	}
	
	/**
	 * Getter for the current rules.cf path
	 * @return The current rules path
	 */
	public String getRulesPath()	{
		return rulesPath;
	}
	
}
