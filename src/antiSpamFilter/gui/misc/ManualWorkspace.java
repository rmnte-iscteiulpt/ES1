/**
 * 
 */
package antiSpamFilter.gui.misc;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;

import antiSpamFilter.gui.panels.WorkspacePanel;

/**
 * @author skner
 *
 */
@SuppressWarnings("serial")
public class ManualWorkspace extends WorkspacePanel implements Observer	{

	private JButton revertButton;
	private JButton discardButton;
	private JButton applyButton;
	private JButton exportButton;
	private JButton importButton;
	
	/**
	 * 
	 * @param bounds
	 */
	public ManualWorkspace(Rectangle bounds, ArrayList<String> rulesList) {
		super(bounds, rulesList);
		generateManualLayout();
	}
	
	private void generateManualLayout() {
		// Buttons
		revertButton = new JButton("Search");
		add(revertButton);
		revertButton.setBounds(11, 168, 75, 25);
		discardButton = new JButton("Discard");
		add(discardButton);
		discardButton.setBounds(98, 168, 80, 25);
		applyButton = new JButton("Apply");
		add(applyButton);
		applyButton.setBounds(188, 168, 70, 25);
		JButton exportButton = new JButton("Export Configuration");
		add(exportButton);
		exportButton.setBounds(280, 168, 150, 25);
		JButton importButton = new JButton("Import Configuration");
		add(importButton);
		importButton.setBounds(280, 138, 150, 25);
	}

	@Override
	public void update(Observable o, Object arg) {
		updateTableContent((ArrayList<String>)arg);
	}



}
