/**
 * 
 */
package antiSpamFilter.gui.misc;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import antiSpamFilter.datastore.RulesConfigList;
import antiSpamFilter.gui.MainWindow;
import antiSpamFilter.gui.panels.WorkspacePanel;

/**
 * JPanel that controls and updates the automatic engine, being linked with it using the observer-observable class
 * @author skner
 *
 */
@SuppressWarnings("serial")
public class AlgorithmWorkspace extends WorkspacePanel implements Observer	{

	/**
	 * Constructor
	 * @param bounds Size and position of the panel
	 * @param mainWindow A pointer to the main window
	 * @param configList The RulesConfigList for the automatic engine
	 */
	public AlgorithmWorkspace(Rectangle bounds, MainWindow mainWindow, RulesConfigList configList) {
		super(bounds, configList, mainWindow, false);
		generateAlgorithmLayout();
	}

	/**
	 * Generates the layout for the workspace panel
	 */
	private void generateAlgorithmLayout() {
		// TODO Decide what buttons it should have, deppending on how the algorithm works, this can only be added once basic funcionality is added
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}

}
