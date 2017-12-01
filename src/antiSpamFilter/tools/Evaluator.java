/**
 * 
 */
package antiSpamFilter.tools;

import java.util.ArrayList;

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
	
	public int[] evaluate(ArrayList<String> rulesList, float[] weightList)	{
		int[] res = {0,0};	// FP and FN
		// Check for inconsistencies in rulesLIst and weightList. For example: they need to have the same size...
		
		// Load ham.log file from path and check for FP
		
		// Load spam.log file from path and check for FN
		
		return res;
	}
}
