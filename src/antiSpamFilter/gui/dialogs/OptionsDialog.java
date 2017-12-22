package antiSpamFilter.gui.dialogs;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;

import antiSpamFilter.gui.frames.MainWindow;
import antiSpamFilter.gui.panels.FileLocationPanel;
import antiSpamFilter.tools.FileBrowser;

@SuppressWarnings("serial")

/**
 * This is the options dialog window, opens up when the user presses the option button on the main window. 
 * This window will hold most if not every option to configure the program.
 * 
 * @author rmnte-iscteiulpt
 */
public class OptionsDialog extends JDialog	{
	
	private MainWindow frame;	// The main frame window
	
	private JButton applyButton;	// The button that applies the configuration paths
	private JButton cancelButton;	// The button that cancels changes to the configuration paths
	private JButton okButton;	// The button that applies the configuration paths and closes the window
	private JButton rulesBrowseButton;	// The button that initiates the FileBrowser class to locate the rules.cf file
	private JButton spamBrowseButton;	// The button that initiates the FileBrowser class to locate the spam.log file
	private JButton hamBrowseButton;	// The button that initiates the FileBrowser class to locate the ham.log file
	
	private FileLocationPanel rulesPanel;	// Panel that contains the location path and check box for the rules.cf file
	private FileLocationPanel spamPanel;	// Panel that contains the location path and check box for the spam.log file
	private FileLocationPanel hamPanel;	// Panel that contains the location path and check box for the ham.log file

	private FileBrowser rulesBrowser;	// FileBrowser for the rules.cf file
	private FileBrowser spamBrowser;	// FileBrowser for the spam.log file
	private FileBrowser hamBrowser;	// FileBrowser for the ham.log file
	
	/**
	 * Constructor
	 * @param frame The main window
	 */
	public OptionsDialog(MainWindow frame)	{
		super(frame, "Options", true);
		this.frame = frame;
		setLayout(null);
		setResizable(false);
		setSize(468,365);
		setLocation(frame.getLocation().x + 100, frame.getLocation().y + 100);
		setupLayout();
		setupFunctionality();
	}

	/**
	 * Makes the dialog exist, creating its layout
	 */
	private void setupLayout()	{	
		// Buttons variables
		int buttonWidth = 89;
		int buttonHeight = 25;
		int controlButtonsX = 162;
		int controlButtonsY = 300;
		int browseButtonsX = 365;
		int browseButtonsY = 34;
		
		// Control buttons
		applyButton = new JButton("Apply");
		applyButton.setBounds(controlButtonsX + 198, controlButtonsY, buttonWidth, buttonHeight);
		add(applyButton);
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(controlButtonsX + 99, controlButtonsY, buttonWidth, buttonHeight);
		add(cancelButton);
		okButton = new JButton("Ok");
		okButton.setBounds(controlButtonsX, controlButtonsY, buttonWidth, buttonHeight);
		add(okButton);
		
		// Browser Buttons 
		rulesBrowseButton = new JButton("Browse");
		rulesBrowseButton.setBounds(browseButtonsX, browseButtonsY, buttonWidth, buttonHeight);
		add(rulesBrowseButton);
		spamBrowseButton = new JButton("Browse");
		spamBrowseButton.setBounds(browseButtonsX, browseButtonsY + 100, buttonWidth, buttonHeight);
		add(spamBrowseButton);
		hamBrowseButton = new JButton("Browse");
		hamBrowseButton.setBounds(browseButtonsX, browseButtonsY + 200, buttonWidth, buttonHeight);
		add(hamBrowseButton);
		
		// File location panels variables
		int panelWidth = 468;
		int panelHeight = 100;
		// File location panels
		rulesPanel = new FileLocationPanel(new Rectangle(0, 0, panelWidth, panelHeight), "Rules.cf");
		add(rulesPanel);
		spamPanel = new FileLocationPanel(new Rectangle(0, panelHeight, panelWidth, panelHeight), "Spam.log");
		add(spamPanel);
		hamPanel = new FileLocationPanel(new Rectangle(0, 2*panelHeight, panelWidth, panelHeight), "Ham.log");
		add(hamPanel);
	}
	
	/**
	 * Makes the dialog work
	 */
	private void setupFunctionality() {
		// Action listeners for the control buttons
		applyButton.addActionListener(new ActionListener()	{  
            public void actionPerformed(ActionEvent e)  
            {  
                apply();
            }
        });  
		cancelButton.addActionListener(new ActionListener()	{  
            public void actionPerformed(ActionEvent e)  
            {  
            	// TODO Revert changes made, its memorizing what we typed etc...
            	// Create backup of things and revert them here
                setVisible(false); 
            }
        });  
		okButton.addActionListener(new ActionListener()	{  
            public void actionPerformed(ActionEvent e)  
            {  
            	apply();
                setVisible(false); 
            }
        });
		
		// FileBrowsers setup
		rulesBrowser = new FileBrowser("rules");
		spamBrowser = new FileBrowser("spam");
		hamBrowser = new FileBrowser("ham");
		
		// Action listeners for the browser buttons
		rulesBrowseButton.addActionListener(new ActionListener()	{  
            public void actionPerformed(ActionEvent e)  
            {  
            	rulesPanel.setTextField(rulesBrowser.getBrowsePath());
            }
        });
		spamBrowseButton.addActionListener(new ActionListener()	{  
            public void actionPerformed(ActionEvent e)  
            {  
            	spamPanel.setTextField(spamBrowser.getBrowsePath());
            }
        });
		hamBrowseButton.addActionListener(new ActionListener()	{  
            public void actionPerformed(ActionEvent e)  
            {  
            	hamPanel.setTextField(hamBrowser.getBrowsePath());
            }
        });
	}
	
	/**
	 * Applies the changes made to the file paths. Firstly it verifies which panel has changed the file path, then updates it if it has in fact been changed.
	 */
	private void apply()	{
		if(rulesPanel.changed())	{
			frame.getMainEngine().updateRulesUtility(rulesPanel.getFilePath());
			System.out.println("Rules path updated.");
		}
		if(hamPanel.changed())	{
			frame.getMainEngine().getEvaluator().updateHamPath(hamPanel.getFilePath());
			System.out.println("Ham path updated.");
		}
		if(spamPanel.changed())	{
			frame.getMainEngine().getEvaluator().updateSpamPath(spamPanel.getFilePath());
			System.out.println("Spam path updated.");
		}
	}
	
}
