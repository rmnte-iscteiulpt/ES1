package antiSpamFilter.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import antiSpamFilter.engines.MainEngine;
import antiSpamFilter.engines.ManualEngine;
import antiSpamFilter.gui.dialogs.OptionsDialog;
import antiSpamFilter.gui.misc.AlgorithmWorkspace;
import antiSpamFilter.gui.misc.ManualWorkspace;
import antiSpamFilter.gui.panels.WorkspacePanel;
import antiSpamFilter.gui.panes.TablePane;
import antiSpamFilter.tools.LoadingTimer;
/**
 * Main window for the software. The code here defines all the sections, text and parts of the graphical interface.
 *
 * @author rmnte-iscteiulpt
 * 
 * @param  frame  The main frame of the software
 * @param  optionsButton (WIP) Button that opens the options pop-up window
 * @param  saveButton (WIP) Button that saves work
 * @param  manualTablePane TablePane view of the manual workspace
 * @param  automaticTablePane TablePane view of the automatic workspace
 */
public class MainWindow extends JFrame	{
	
	/*
	 * TODO
	 * There should be a mainEngine. This will have the manual engine and autoamtic engine. These engines will be threads? And process things etc...
	 * The main engine will make the panel engines work etc...
	 * 
	 * Rules.cf path is updated twice in logs. Why? TO FIX
	 */
	
	private JButton optionsButton;
	private JButton saveButton;
	private AlgorithmWorkspace autoPanel;
	private ManualWorkspace manualPanel;
	
	private MainEngine mainEngine;
	
	public MainWindow(MainEngine me)	{
		super("Anti Spam Filter Configuration");
		mainEngine = me;
		setupFrame();
		setupObserverLinks();
	}

	/**
	 * Creates the frame and renders it
	 */
	private void setupFrame()	{
		// TODO The entire interface should be observable. So when the program runs only data is changed. The interface updates accordingly
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

	private void setupManualPanel() {
		manualPanel = new ManualWorkspace(new Rectangle(10, 31, 674, 200), mainEngine.getRulesUtility().getRulesList());
		add(manualPanel);
		
		JLabel manualText = new JLabel("Manual Workspace");
		manualText.setBounds(11, 11, 674, 20);
		add(manualText);
		manualText.setFont(new Font("Tahoma", Font.PLAIN, 16));
	}
	
	private void setupAutomaticPanel() {
		autoPanel = new AlgorithmWorkspace(new Rectangle(10, 264, 674, 200), mainEngine.getRulesUtility().getRulesList());
		add(autoPanel);

		JLabel automaticText = new JLabel("Automatic Workspace");
		automaticText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(automaticText);
		automaticText.setBounds(11, 242, 674, 20);
	}
	
	private void setupObserverLinks() {
		mainEngine.getManualEngine().addObserver(manualPanel);
		mainEngine.getAutoEngine().addObserver(autoPanel);
	}
	
	public void updateRulesPathChanges(String path)	{
		// TODO Make spam and ham log files appear in engine.
		mainEngine.updateRulesUtility(path);
		System.out.println("Options saved.");
	}
}
