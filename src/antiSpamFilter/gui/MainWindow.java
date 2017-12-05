package antiSpamFilter.gui;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

import antiSpamFilter.engines.MainEngine;
import antiSpamFilter.gui.dialogs.OptionsDialog;
import antiSpamFilter.gui.misc.AlgorithmWorkspace;
import antiSpamFilter.gui.misc.ManualWorkspace;
import antiSpamFilter.tools.LoadingTimer;

@SuppressWarnings("serial")
/**
 * This is the main window, the window the user will see most of the time. It contains most of the GUI elements and buttons to assist the user in working with the
 * configuration and the algorithm
 * 
 * @author rmnte-iscteiulpt
 *
 */
public class MainWindow extends JFrame {

	private JButton optionsButton;
	private JButton saveButton;
	private AlgorithmWorkspace autoPanel;
	private ManualWorkspace manualPanel;
	
	private MainEngine mainEngine;
	
	/**
	 * Constructor
	 * @param me Main Engine to link with this class
	 */
	public MainWindow(MainEngine me)	{
		super("Anti Spam Filter Configuration");
		mainEngine = me;
		setupFrame();
		setupObserverLinks();
	}

	/**
	 * Creates the window, setting up the layout
	 */
	private void setupFrame()	{
		LoadingTimer timer = new LoadingTimer();
		System.out.println("Loading window...");
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		JFrame frame = this;
		addWindowListener(new WindowAdapter()	{
			@Override
	        public void windowClosing(WindowEvent e) {
	            if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?", "Confirm exit", JOptionPane.OK_OPTION, 0, new ImageIcon("")) != 0) {
	                return;
	            }
	            System.exit(-1);
	        }	
		});
		setLayout(null);
		setBounds(100, 100, 700, 550);
		setResizable(false);
		
		OptionsDialog optionWindow = new OptionsDialog(this);
		setupManualPanel();
		setupAutomaticPanel();
		optionsButton = new JButton("Options");
		add(optionsButton);
		optionsButton.setBounds(542, 475, 142, 36);
		optionsButton.addActionListener(new ActionListener()	{  
            public void actionPerformed(ActionEvent e)  
            {  
            	optionWindow.setVisible(true);
            }  
        });  
		
		saveButton = new JButton("Save");
		add(saveButton);
		saveButton.setBounds(385, 475, 142, 36);
		setVisible(true);
		System.out.println("Window loaded in " + timer.getElapsedTime() + "ms.");
	}

	/**
	 * Setup the manual workspace panel
	 */
	private void setupManualPanel() {
		manualPanel = new ManualWorkspace(new Rectangle(10, 31, 674, 200), this, mainEngine.getManualEngine().getConfigList());
		add(manualPanel);
		
		JLabel manualText = new JLabel("Manual Workspace");
		manualText.setBounds(11, 11, 674, 20);
		add(manualText);
		manualText.setFont(new Font("Tahoma", Font.PLAIN, 16));
	}
	
	/**
	 * Setup the automatic workspace panel
	 */
	private void setupAutomaticPanel() {
		autoPanel = new AlgorithmWorkspace(new Rectangle(10, 264, 674, 200), this, mainEngine.getAutoEngine().getConfigList());
		add(autoPanel);

		JLabel automaticText = new JLabel("Automatic Workspace");
		automaticText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(automaticText);
		automaticText.setBounds(11, 242, 674, 20);
	}
	
	/**
	 * Links the engines and respective interfaces
	 */
	private void setupObserverLinks() {
		mainEngine.getManualEngine().addObserver(manualPanel);
		mainEngine.getAutoEngine().addObserver(autoPanel);
	}

	/**
	 * Getter for the main engine
	 * @return The main engine
	 */
	public MainEngine getMainEngine() {
		return mainEngine;
	}

}
