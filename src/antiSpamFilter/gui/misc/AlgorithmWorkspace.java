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
 * @author skner
 *
 */
@SuppressWarnings("serial")
public class AlgorithmWorkspace extends WorkspacePanel implements Observer	{

	public AlgorithmWorkspace(Rectangle bounds, MainWindow mainWindow, RulesConfigList configList) {
		super(bounds, configList, mainWindow, false);
		generateAlgorithmLayout();
	}

	private void generateAlgorithmLayout() {
		// TODO Decide what buttons it should have, deppending on how the algorithm works, this can only be added once basic funcionality is added
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}

}
