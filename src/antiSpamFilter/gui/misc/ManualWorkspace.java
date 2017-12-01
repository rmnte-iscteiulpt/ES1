/**
 * 
 */
package antiSpamFilter.gui.misc;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import antiSpamFilter.gui.MainWindow;
import antiSpamFilter.gui.panels.WorkspacePanel;
import antiSpamFilter.misc.RulesConfigList;

/**
 * @author skner
 *
 */
@SuppressWarnings("serial")
public class ManualWorkspace extends WorkspacePanel implements Observer	{

	private JButton resetButton;
	private JButton discardButton;
	private JButton applyButton;
	private JButton exportButton;
	private JButton importButton;
	
	/**
	 * 
	 * @param bounds
	 */
	public ManualWorkspace(Rectangle bounds, MainWindow mainWindow, RulesConfigList configList) {
		super(bounds, configList, mainWindow, true);
		generateManualLayout();
		setupFunctionality();
	}
	
	private void generateManualLayout() {
		// Buttons
		resetButton = new JButton("Reset");
		add(resetButton);
		resetButton.setBounds(11, 168, 75, 25);
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
	
	private void setupFunctionality()	{
		resetButton.addActionListener(new ActionListener()	{  
            public void actionPerformed(ActionEvent e)  
            {
            	reset();
            }
        });
		discardButton.addActionListener(new ActionListener()	{  
            public void actionPerformed(ActionEvent e)  
            {  
                discard();
            }
        });
		applyButton.addActionListener(new ActionListener()	{  
            public void actionPerformed(ActionEvent e)  
            {  
            	// TODO Add a change token for confirmation. Optional
                apply();
            }
        });
	}

	@Override
	public void update(Observable o, Object arg) {
		updateTableContent((RulesConfigList)arg);
	}

	public void reset()	{
		mainWindow.getMainEngine().getManualEngine().resetWeights();
	}
	
	public void apply()	{
		tablePane.applyChanges();
		mainWindow.getMainEngine().getManualEngine().updateWeights(tablePane.getWeightList());
	}
	
	public void discard()	{
		tablePane.discardChanges();
	}

}
