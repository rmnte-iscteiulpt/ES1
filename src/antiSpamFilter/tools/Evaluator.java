/**
 * 
 */
package antiSpamFilter.tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import antiSpamFilter.misc.RulesConfigList;

/**
 * @author skner
 *
 */
public class Evaluator {

	private String defaultHamPath;
	private String defaultSpamPath;
	private String hamPath;
	private String spamPath;
	
	/*
	 * TODO
	 * How will the evaluator work
	 * First grabs the spam and ham files
	 * After that for each line (example) will see what rules are applied. We should search the rules in the config file, and sum their weights.
	 * 
	 * Safe message found in spam:	FN++;
	 * Spam message found in ham:	FP++;
	 * 
	 * Notes and values:
	 * 
	 * weight range: -5 to 5
	 * 
	 * What to do with the results:
	 * sum>5 = spam
	 * sum<5 = not spam
	 * 
	 * FP (false positives) means that a message was considered spam, when it was safe
	 * FN (false negatives) means that a message was considered safe, when it was spam
	 * 
	 * Rules+Weights algorithm - 
	 */
	
	public Evaluator() {
		this("","");
	}
	
	public Evaluator(String hamPath, String spamPath) {
		defaultHamPath = System.getProperty("user.dir") + "\\" + "AntiSpamConfigurationForLeisureMailbox\\ham.log";
		defaultSpamPath = System.getProperty("user.dir") + "\\" + "AntiSpamConfigurationForLeisureMailbox\\spam.log";
		updateHamPath(hamPath);
		updateSpamPath(spamPath);
	}

	public void updateHamPath(String hamPath) {
		if(hamPath.equals(""))	{
			this.hamPath = defaultHamPath;
		}	else	{
			this.hamPath = hamPath;
		}
	}
	
	public void updateSpamPath(String spamPath) {
		if(spamPath.equals(""))	{
			this.spamPath = defaultSpamPath;
		}	else	{
			this.spamPath = spamPath;
		}
	}
	
	public int[] evaluate(RulesConfigList list)	{
		int[] res = {0,0};	// FP and FN
		// Check for inconsistencies in rulesList and weightList. For example: they need to have the same size...
		
		// Load ham.log file from path and check for FP
		try	{
			BufferedReader br = new BufferedReader(new FileReader(hamPath));
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
			System.out.println("Found " + res[0] + " false positives in the current configuration.");
			br.close();
			
		} catch (FileNotFoundException e1) {
			System.out.println("Path for  file doesn't exist. Using the default file.");
			// TODO Add a way to use the default file if the custom file doesn't exist.
		} catch (IOException e1) {
		}
		
		// Load spam.log file from path and check for FN
		try	{
			BufferedReader br = new BufferedReader(new FileReader(spamPath));
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
			    if(sum<5)	{
			    	res[1]++;
			    }
			    aux = br.readLine();
			}
			System.out.println("Found " + res[1] + " false negatives in the current configuration.");
			br.close();
			
		} catch (FileNotFoundException e1) {
			System.out.println("Path for  file doesn't exist. Using the default file.");
			// TODO Add a way to use the default file if the custom file doesn't exist.
		} catch (IOException e1) {
		}
		
		return res;
	}
}
