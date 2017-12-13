package antiSpamFilter;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.operator.impl.crossover.SBXCrossover;
import org.uma.jmetal.operator.impl.mutation.PolynomialMutation;
import org.uma.jmetal.qualityindicator.impl.hypervolume.PISAHypervolume;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.ExperimentBuilder;
import org.uma.jmetal.util.experiment.component.*;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

import antiSpamFilter.datastore.RulesConfigList;
import antiSpamFilter.main.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AntiSpamFilterAutomaticConfiguration	{
	
	private static final int INDEPENDENT_RUNS = 5;
	
	public AntiSpamFilterAutomaticConfiguration(RulesConfigList configList) throws IOException {
		List<ExperimentProblem<DoubleSolution>> problemList = new ArrayList<>();
	    problemList.add(new ExperimentProblem<>(new AntiSpamFilterProblem(configList)));
	    List<ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>> algorithmList =
	            configureAlgorithmList(problemList);
	    Experiment<DoubleSolution, List<DoubleSolution>> experiment =
	        new ExperimentBuilder<DoubleSolution, List<DoubleSolution>>("AntiSpamStudy")
	            .setAlgorithmList(algorithmList)
	            .setProblemList(problemList)
	            .setExperimentBaseDirectory(Main.experimentBaseDirectory)
	            .setOutputParetoFrontFileName("FUN")
	            .setOutputParetoSetFileName("VAR")
	            
	            .setReferenceFrontDirectory(Main.experimentBaseDirectory+"/referenceFronts")
	            .setIndicatorList(Arrays.asList(new PISAHypervolume<DoubleSolution>()))
	            .setIndependentRuns(INDEPENDENT_RUNS)
	            .setNumberOfCores(8)
	            .build();

	    new ExecuteAlgorithms<>(experiment).run();
	    try {
	    	new GenerateReferenceParetoSetAndFrontFromDoubleSolutions(experiment).run();
		    new ComputeQualityIndicators<>(experiment).run();
		    new GenerateLatexTablesWithStatistics(experiment).run();
			new GenerateBoxplotsWithR<>(experiment).setRows(1).setColumns(1).run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>> configureAlgorithmList(List<ExperimentProblem<DoubleSolution>> problemList) {
		List<ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>> algorithms = new ArrayList<>();
		for (int i = 0; i < problemList.size(); i++) {
			Algorithm<List<DoubleSolution>> algorithm = new NSGAIIBuilder<>(
					problemList.get(i).getProblem(), 
					new SBXCrossover(1.0, 5), 
					new PolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
					.setMaxEvaluations(2500)	// Changed from 25000 to 2500 to remove the 0 value error
		            .setPopulationSize(100)
		            .build();
			
		   algorithms.add(new ExperimentAlgorithm<>(algorithm, "NSGAII", problemList.get(i).getTag()));
		} 
		return algorithms;
	}
}