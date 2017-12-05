package antiSpamFilter.gui.misc;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;

import antiSpamFilter.datastore.RulesConfigList;
import antiSpamFilter.gui.MainWindow;
import antiSpamFilter.gui.panels.WorkspacePanel;

/**
 * JPanel that controls and updates the manual engine, being linked with it using the observer-observable class
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
	 * Constructor
	 * @param bounds Size and position of the panel
	 * @param mainWindow A pointer to the main window
	 * @param configList The RulesConfigList for the manual engine
	 */
	public ManualWorkspace(Rectangle bounds, MainWindow mainWindow, RulesConfigList configList) {
		super(bounds, configList, mainWindow, true);
		generateManualLayout();
		setupFunctionality();
	}
	
	/**
	 * Generates the manual layout
	 */
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
	
	/**
	 * Makes the panel work
	 */
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

	/**
	 * Reset button functionality: Resets the weights displayed on the table, sending them to the manual engine
	 */
	public void reset()	{
		mainWindow.getMainEngine().getManualEngine().resetWeights();
	}
	
	/**
	 * Apply button functionality: Applies the changes made to the buffer JTable, sending it to the manual engine
	 */
	public void apply()	{
		tablePane.applyChanges();
		mainWindow.getMainEngine().getManualEngine().updateWeights(tablePane.getWeightList());
	}
	
	/**
	 * Discards changes made to the buffer
	 */
	public void discard()	{
		tablePane.discardChanges();
	}

}
