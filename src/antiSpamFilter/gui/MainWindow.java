package antiSpamFilter.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import antiSpamFilter.gui.dialogs.OptionsDialog;
import antiSpamFilter.gui.misc.AlgorithmWorkspace;
import antiSpamFilter.gui.misc.ManualWorkspace;
import antiSpamFilter.gui.panels.WorkspacePanel;
import antiSpamFilter.gui.panes.TablePane;
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
public class MainWindow {
	
	private JFrame frame;
	private JButton optionsButton;
	private JButton saveButton;
	private TablePane manualTablePane;
	private TablePane automaticTablePane;
	
	public MainWindow()	{
		setupFrame();
	}

	/**
	 * Creates the frame and renders it
	 */
	void setupFrame()	{
		// TODO The entire interface should be observable. So when the program runs only data is changed. The interface updates accordingly
		long startTime = System.nanoTime();
		System.out.println("Loading window...");
		frame = new JFrame("Anti Spam Filter Configuration");
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter()	{
			@Override
	        public void windowClosing(WindowEvent e) {
	            if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?", "Confirm exit", JOptionPane.OK_OPTION, 0, new ImageIcon("")) != 0) {
	                return;
	            }
	            System.exit(-1);
	        }	
		});
		frame.setLayout(null);
		frame.setBounds(100, 100, 700, 550);
		frame.setResizable(false);
		
		OptionsDialog optionWindow = new OptionsDialog(frame);
		
		setupManualPanel();
		setupAutomaticPanel();
		// TODO Make panels ordered by hierarchy, create subclasses for each one and apply them to both panels
		optionsButton = new JButton("Options");
		frame.add(optionsButton);
		optionsButton.setBounds(542, 475, 142, 36);
		optionsButton.addActionListener(new ActionListener()	{  
            public void actionPerformed(ActionEvent e)  
            {  
            	optionWindow.setVisible(true);
            }  
        });  
		
		saveButton = new JButton("Save");
		frame.add(saveButton);
		saveButton.setBounds(385, 475, 142, 36);
		frame.setVisible(true);
		long elapsedTimeNs = System.nanoTime() - startTime;
		System.out.println("Window loaded in " + elapsedTimeNs/1000000 + "ms.");
	}

	private void setupManualPanel() {
		ManualWorkspace manualPanel = new ManualWorkspace(new Rectangle(10, 31, 674, 200));
		frame.add(manualPanel);
		
		JLabel manualText = new JLabel("Manual Workspace");
		manualText.setBounds(11, 11, 674, 20);
		frame.add(manualText);
		manualText.setFont(new Font("Tahoma", Font.PLAIN, 16));
	}
	
	private void setupAutomaticPanel() {
		AlgorithmWorkspace algorithmPanel = new AlgorithmWorkspace(new Rectangle(10, 264, 674, 200));
		frame.add(algorithmPanel);

		JLabel automaticText = new JLabel("Automatic Workspace");
		automaticText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.add(automaticText);
		automaticText.setBounds(11, 242, 674, 20);
	}
	
}
