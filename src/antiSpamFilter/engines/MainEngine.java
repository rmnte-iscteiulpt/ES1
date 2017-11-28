/**
 * 
 */
package antiSpamFilter.engines;

import antiSpamFilter.tools.RulesUtility;

/**
 * @author skner
 *
 */
public class MainEngine {

	private ManualEngine manualEngine;
	private AutomaticEngine autoEngine;
	
	private RulesUtility rulesUtility;
	 
	public MainEngine()	{
		this(new ManualEngine(), new AutomaticEngine());
	}
	
	public MainEngine(ManualEngine me, AutomaticEngine ae) {
		manualEngine = me;
		autoEngine = ae;
		rulesUtility = new RulesUtility();
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
}
