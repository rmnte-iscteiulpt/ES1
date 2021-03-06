package antiSpamFilter.engines;

import antiSpamFilter.tools.Evaluator;
import antiSpamFilter.tools.RulesUtility;

/**
 * The main engine will assist and link the manual and automatic engine and the respective GUIs
 * @author rmnte-iscteiulpt
 *
 */
public class MainEngine	{

	private ManualEngine manualEngine;	// Manual engine, contains all the methods related to the manual workspace
	private AutomaticEngine autoEngine;	// Automatic engine, contains all the methods related to the automatic workspace and algorithm generation
	
	private Evaluator evaluator;	// Evaluator, this class will evaluate a RulesConfigList, returning the FP and FN
	private RulesUtility rulesUtility;	// The rules holder class, used to double check imports exports and inconsistencies
	 
	/**
	 * Default constructor
	 */
	public MainEngine()	{
		rulesUtility = new RulesUtility();
		evaluator = new Evaluator();
		manualEngine = new ManualEngine(rulesUtility.getRulesList());
		autoEngine = new AutomaticEngine(rulesUtility.getRulesList());
	}

	/**
	 * Updates RulesUtility
	 * @param path Path for the new rules.cf file
	 */
	public void updateRulesUtility(String path) {
		rulesUtility.updatePath(path);
		manualEngine.updateRules(rulesUtility.getRulesList());
		autoEngine.updateRules(rulesUtility.getRulesList());
	}
	
	/**
	 * Getter for the manual engine
	 * @return The manual engine
	 */
	public ManualEngine getManualEngine()	{
		return manualEngine;
	}
	
	/**
	 * Getter for the automatic engine
	 * @return The automatic engine
	 */
	public AutomaticEngine getAutoEngine()	{
		return autoEngine;
	}
	
	/**
	 * Getter for the rules utility
	 * @return The rules utility
	 */
	public RulesUtility getRulesUtility()	{
		return rulesUtility;
	}
	
	/**
	 * Getter for the evaluator
	 * @return The evaluator
	 */
	public Evaluator getEvaluator()	{
		return evaluator;
	}
}
