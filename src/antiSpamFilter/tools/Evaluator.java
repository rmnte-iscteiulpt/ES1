/**
 * 
 */
package antiSpamFilter.tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import antiSpamFilter.datastore.RulesConfigList;
import antiSpamFilter.main.Main;

/**
 * This class is responsible for evaluating configurations, checking the spam and ham log files, and returning the respective FP and FN solutions for a give configuration
 * @author rmnte-iscteiulpt
 *
 */
public class Evaluator {

	
	private String hamPath;
	private String spamPath;
	
	/*
	 * Notes and values:
	 * 
	 * Weight range: -5 to 5
	 * 
	 * Results handling:
	 * sum>5 = spam
	 * sum<5 = safe
	 * 
	 * FP (false positives) means that a message was considered spam, when it was safe
	 * FN (false negatives) means that a message was considered safe, when it was spam
	 */
	
	/**
	 * Default constructor, creating a evaluator using default log files
	 */
	public Evaluator() {
		this("","");
	}
	
	/**
	 * Creates a evaluator with specific log files
	 * @param hamPath The path for the ham.log file
	 * @param spamPath The path for the spam.log file
	 */
	public Evaluator(String hamPath, String spamPath) {
		
		updateHamPath(hamPath);
		updateSpamPath(spamPath);
	}

	/**
	 * Updates the ham.log file path
	 * @param hamPath New path
	 */
	public void updateHamPath(String hamPath) {
		if(hamPath.equals(""))	{
			this.hamPath = Main.defaultHamPath;
		}	else	{
			this.hamPath = hamPath;
		}
	}
	
	/**
	 * Updates the ham.log file path
	 * @param spamPath New path
	 */
	public void updateSpamPath(String spamPath) {
		if(spamPath.equals(""))	{
			this.spamPath = Main.defaultSpamPath;
		}	else	{
			this.spamPath = spamPath;
		}
	}


	/**
	 * This method will be given a configuration and return the respective results
	 * @param list Configuration list, containing rules and one weight for each rule
	 * @return An integer array[2] containing the FP and FN results respectively
	 */
	public int[] evaluate(RulesConfigList list)	{
		int[] res = {0,0};	// FP and FN vector
		
		// Load ham.log file from path and check for FP
		try	{
			BufferedReader br;
			if(hamPath == Main.defaultHamPath)	{
				br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(hamPath)));
			}	else	{
				br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/src" + hamPath));
			}
			String aux = br.readLine();
			String[] args = null;
			while(aux != null)	{
				args = aux.split("\t");
			    // Assuming there's no inconsistencies. For now... TODO
				float sum = 0f;
			    for(int i = 1; i<args.length; i++)	{	// For each i in args sums the weight value for the respective rule, storing it in the var sum.
			    	sum+= list.getRuleWeight(args[i]);
			    }
			    // Increments the FP if it found spam in the ham file.
			    if(sum>5)	{
			    	res[0]++;
			    }
			    aux = br.readLine();
			}
			br.close();
			
		} catch (FileNotFoundException e1) {
			System.out.println("Path for  file doesn't exist. Using the default file.");
			// TODO Add a way to use the default file if the custom file doesn't exist.
		} catch (IOException e1) {
		}
		
		// Load spam.log file from path and check for FN
		try	{
			BufferedReader br;
			if(spamPath == Main.defaultSpamPath)	{
				br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(spamPath)));
			}	else	{
				br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/src" + spamPath));
			}
			String aux = br.readLine();
			String[] args = null;
			while(aux != null)	{
				args = aux.split("\t");
			    // Assuming there's no inconsistencies. For now... TODO
				float sum = 0f;
			    for(int i = 1; i<args.length; i++)	{	// For each i in args sums the weight value for the respective rule, storing it in the var sum.
			    	sum+= list.getRuleWeight(args[i]);
			    }
			    // Increments the FN if it found safe messages in the spam file.
			    if(sum<=5)	{
			    	res[1]++;
			    }
			    aux = br.readLine();
			}
			br.close();
			
		} catch (FileNotFoundException e1) {
			System.out.println("Path for  file doesn't exist. Using the default file.");
			// TODO Add a way to use the default file if the custom file doesn't exist.
		} catch (IOException e1) {
		}
		
		//System.out.println("[Evaluator] Found " + res[0] + "FP and " + res[1] + "FN in the current configuration.");
		return res;
	}
}
