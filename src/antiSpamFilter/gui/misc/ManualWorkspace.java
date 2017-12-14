package antiSpamFilter.gui.misc;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;

import antiSpamFilter.datastore.RulesConfigList;
import antiSpamFilter.gui.frames.MainWindow;
import antiSpamFilter.gui.panels.WorkspacePanel;
import antiSpamFilter.tools.FileBrowser;

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
		exportButton = new JButton("Export Configuration");
		add(exportButton);
		exportButton.setBounds(280, 168, 150, 25);
		importButton = new JButton("Import Configuration");
		add(importButton);
		importButton.setBounds(280, 134, 150, 25);
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
		JButton evaluateButton = new JButton("Evaluate");
		getResultsPanel().add(evaluateButton);
		evaluateButton.setBounds(30, 65, 90, 25);
		evaluateButton.addActionListener(new ActionListener()	{  
            public void actionPerformed(ActionEvent e)  
            {  
            	int res[] = getMainWindow().getMainEngine().getEvaluator().evaluate(getMainWindow().getMainEngine().getManualEngine().getConfigList());
            	updateResults(res);
            }
        });
		
		exportButton.addActionListener(new ActionListener()	{  
            public void actionPerformed(ActionEvent e)  
            {  
            	FileBrowser fb = new FileBrowser("cfg");
            	try {
            		String path = fb.getSavePath();
            		if(path!= null) {
            			getMainWindow().getMainEngine().getManualEngine().getConfigList().exportTo(path);
            		}
				} 	catch (FileNotFoundException e1) {
					System.err.println("Export folder doesn't exist. File export unsuccessful.");
					e1.printStackTrace();
				} 	catch (UnsupportedEncodingException e1) {
					System.err.println("Enconding not supported. File export unsuccessful.");
					e1.printStackTrace();
				}
            	updateTableContent(getMainWindow().getMainEngine().getManualEngine().getConfigList());
            }
        });
		importButton.addActionListener(new ActionListener()	{  
            public void actionPerformed(ActionEvent e)  
            {  
            	FileBrowser fb = new FileBrowser("cfg");
            	try {
            		String path = fb.getBrowsePath();
            		if(path!= null) {
            			getMainWindow().getMainEngine().getManualEngine().getConfigList().importFrom(path);
            		}
				} catch (IOException e1) {
					System.err.println("File IO exception. File import unsuccessful.");
					e1.printStackTrace();
				}
            	updateTableContent(getMainWindow().getMainEngine().getManualEngine().getConfigList());
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
		getMainWindow().getMainEngine().getManualEngine().resetWeights();
	}
	
	/**
	 * Apply button functionality: Applies the changes made to the buffer JTable, sending it to the manual engine
	 */
	public void apply()	{
		getTablePane().applyChanges();
		getMainWindow().getMainEngine().getManualEngine().updateWeights(getTablePane().getWeightList());
	}
	
	/**
	 * Discards changes made to the buffer
	 */
	public void discard()	{
		getTablePane().discardChanges();
	}

}
