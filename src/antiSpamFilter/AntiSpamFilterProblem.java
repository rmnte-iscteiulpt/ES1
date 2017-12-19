package antiSpamFilter;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

import antiSpamFilter.datastore.RulesConfigList;
import antiSpamFilter.tools.Evaluator;

@SuppressWarnings("serial")
public class AntiSpamFilterProblem extends AbstractDoubleProblem {

	private RulesConfigList rulesList;

	/**
	 * Default constructor
	 * @param configList The list which contains the rules and serves as a variable to be used by the evaluator
	 */
	public AntiSpamFilterProblem(RulesConfigList configList) {
		rulesList = configList;
		setNumberOfVariables(rulesList.getWeightList().size());
		setNumberOfObjectives(2);
		setName("AntiSpamFilterProblem");

		List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables());
		List<Double> upperLimit = new ArrayList<>(getNumberOfVariables());

		for (int i = 0; i < getNumberOfVariables(); i++) {
			lowerLimit.add(-5.0);
			upperLimit.add(5.0);
		}
		setLowerLimit(lowerLimit);
		setUpperLimit(upperLimit);
	}

	/**
	 * Uses the Evaluator class to evaluate a DoubleSolution
	 */
	public void evaluate(DoubleSolution solution)	{
		double[] weightBuffer = new double[getNumberOfVariables()];
		for (int i = 0; i < solution.getNumberOfVariables(); i++) {
			weightBuffer[i] = solution.getVariableValue(i);
		}
		rulesList.updateWeights(weightBuffer);
		// TODO Make evaluator accept custom files
		Evaluator evaluator = new Evaluator();
		int[] resAux = evaluator.evaluate(rulesList);
		double[] res = {(double)resAux[0], (double)resAux[1]};
		//System.out.println("Array: " + rulesList.getWeightList());
		//System.out.println("Results: " + res[0] + "," + res[1]);
	    // Stores the results (FP and FN) in solution, seen by the algorithm
	    solution.setObjective(0, res[0]);
		solution.setObjective(1, res[1]);
	}
}
