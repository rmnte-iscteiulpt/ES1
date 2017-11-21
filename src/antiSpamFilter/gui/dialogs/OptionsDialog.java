package antiSpamFilter.gui.dialogs;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import antiSpamFilter.gui.panels.FileLocationPanel;

@SuppressWarnings("serial")

/** 
 * @author skner
 *
 * This is the options dialog window, opens up when the user presses the option button on the main window. 
 * This window will hold most if not every option to configure the program.
 */
public class OptionsDialog extends JDialog	{
	
	private JButton applyButton;
	private JButton cancelButton;
	private JButton okButton;

	/**
	 * Constructor
	 */
	public OptionsDialog(JFrame frame)	{
		super(frame, "Options", true);
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
		// TODO Add variables for width and height
		// Buttons
		int buttonsX = 162;
		int buttonsY = 300;
		// Initialize buttons
		applyButton = new JButton("Apply");
		applyButton.setBounds(buttonsX + 198, buttonsY, 89, 25);
		add(applyButton);
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(buttonsX + 99, buttonsY, 89, 25);
		add(cancelButton);
		okButton = new JButton("Ok");
		okButton.setBounds(buttonsX, buttonsY, 89, 25);
		add(okButton);
		
		// File location panels
		int panelWidth = 468;
		int panelHeight = 100;
		// Initialize panels
		FileLocationPanel rulesPanel = new FileLocationPanel(new Rectangle(0, 0, panelWidth, panelHeight), "Rules.cf");
		add(rulesPanel);
		FileLocationPanel spamPanel = new FileLocationPanel(new Rectangle(0, panelHeight, panelWidth, panelHeight), "Spam.log");
		add(spamPanel);
		FileLocationPanel hamPanel = new FileLocationPanel(new Rectangle(0, 2*panelHeight, panelWidth, panelHeight), "Ham.log");
		add(hamPanel);
	}
	
	/**
	 * Makes the dialog work
	 */
	private void setupFunctionality() {
		// Add action listeners to the buttons
		applyButton.addActionListener(new ActionListener()	{  
            public void actionPerformed(ActionEvent e)  
            {  
                //apply();
            }
        });  
		cancelButton.addActionListener(new ActionListener()	{  
            public void actionPerformed(ActionEvent e)  
            {  
                setVisible(false); 
            }
        });  
		okButton.addActionListener(new ActionListener()	{  
            public void actionPerformed(ActionEvent e)  
            {  
            	//apply();
                setVisible(false); 
            }
        });  
	}

}
