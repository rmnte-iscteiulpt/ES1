/**
 * 
 */
package antiSpamFilter.gui.misc;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import antiSpamFilter.datastore.RulesConfigList;
import antiSpamFilter.gui.MainWindow;
import antiSpamFilter.gui.panels.WorkspacePanel;
import antiSpamFilter.tools.FileBrowser;

/**
 * JPanel that controls and updates the automatic engine, being linked with it using the observer-observable class
 * @author skner
 *
 */
@SuppressWarnings("serial")
public class AlgorithmWorkspace extends WorkspacePanel implements Observer	{

	private JButton exportButton;
	
	/**
	 * Constructor
	 * @param bounds Size and position of the panel
	 * @param mainWindow A pointer to the main window
	 * @param configList The RulesConfigList for the automatic engine
	 */
	public AlgorithmWorkspace(Rectangle bounds, MainWindow mainWindow, RulesConfigList configList) {
		super(bounds, configList, mainWindow, false);
		generateAlgorithmLayout();
		setupFunctionality();
	}

	/**
	 * Generates the layout for the workspace panel
	 */
	private void generateAlgorithmLayout() {
		exportButton = new JButton("Export Configuration");
		add(exportButton);
		exportButton.setBounds(280, 134, 150, 25);
	}
	
	/**
	 * Makes the panel work
	 */
	private void setupFunctionality()	{
		exportButton.addActionListener(new ActionListener()	{  
	        public void actionPerformed(ActionEvent e)  
	        {  
	        	FileBrowser fb = new FileBrowser("cfg");
            	try {
            		String path = fb.getSavePath();
            		if(path!= null) {
            			getMainWindow().getMainEngine().getAutoEngine().getConfigList().exportTo(path);
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
		
		JButton generateButton = new JButton("Generate");
		getResultsPanel().add(generateButton);
		generateButton.setBounds(30, 65, 90, 25);
		generateButton.addActionListener(new ActionListener()	{  
            public void actionPerformed(ActionEvent e)  
            {  
            	runAlgorithm();
            }
        });
	}
	
	private void runAlgorithm()	{
    	getMainWindow().getMainEngine().getAutoEngine().runAlgorithm();
    	updateResults(getMainWindow().getMainEngine().getAutoEngine().getCurrentResults());
	}

	@Override
	public void update(Observable o, Object arg) {
		updateTableContent((RulesConfigList)arg);
	}

}
