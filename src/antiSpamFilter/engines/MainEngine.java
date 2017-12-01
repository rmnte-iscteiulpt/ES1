package antiSpamFilter.engines;

import antiSpamFilter.tools.Evaluator;
import antiSpamFilter.tools.RulesUtility;

public class MainEngine {

	private ManualEngine manualEngine;
	private AutomaticEngine autoEngine;
	
	private Evaluator evaluator;
	private RulesUtility rulesUtility;
	 
	public MainEngine()	{
		rulesUtility = new RulesUtility();
		evaluator = new Evaluator();
		manualEngine = new ManualEngine(rulesUtility.getRulesList());
		autoEngine = new AutomaticEngine(rulesUtility.getRulesList());
	}

	public void updateRulesUtility(String path) {
		rulesUtility.updatePath(path);
		manualEngine.updateRules(rulesUtility.getRulesList());
		autoEngine.updateRules(rulesUtility.getRulesList());
	}
	
	public ManualEngine getManualEngine()	{
		return manualEngine;
	}
	
	public AutomaticEngine getAutoEngine()	{
		return autoEngine;
	}
	
	public RulesUtility getRulesUtility()	{
		return rulesUtility;
	}
	
	public Evaluator getEvaluator()	{
		return evaluator;
	}
}
