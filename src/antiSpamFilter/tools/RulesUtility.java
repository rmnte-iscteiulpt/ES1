package antiSpamFilter.tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author skner
 *
 */
public class RulesUtility {

	private String defaultRulesPath;
	private String rulesPath;
	private ArrayList<String> rulesList;

	public RulesUtility()	{
		this("");
	}
	
	public RulesUtility(String rulesPath) {
		//LoadingTimer timer = new LoadingTimer(); // Timer debug
		defaultRulesPath = System.getProperty("user.dir") + "\\" + "AntiSpamConfigurationForLeisureMailbox\\rules.cf";
		updatePath(rulesPath);
		//System.out.println("File read and sorted in " + timer.getElapsedTime() + "ms."); // Timer debug
	}

	public void loadRules()	{
		rulesList = readFile();
		if(rulesList == null)	{	// This means the file was not found or there was an io exception
			updatePath("");
		}	else	{
			Collections.sort(rulesList);
		}
	}
	
	public void updatePath(String rulesPath)	{
		if(rulesPath.equals(""))	{
			this.rulesPath = defaultRulesPath;
		}	else	{
			this.rulesPath = rulesPath;
		}
		System.out.println("Rules.cf Path Updated to: -" + this.rulesPath + "-"); // Path debug
		loadRules();
	}

	private ArrayList<String> readFile()	{
		try	{
			ArrayList<String> ruleList = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new FileReader(rulesPath));
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
	
	public ArrayList<String> getRulesList()	{
		return rulesList;
	}
}
