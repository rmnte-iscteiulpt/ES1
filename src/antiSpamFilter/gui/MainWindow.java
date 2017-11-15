package antiSpamFilter.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
		frame.setVisible(true);
		
		setupManualPanel();
		setupAutomaticPanel();
		// TODO Make panels ordered by hierarchy, create subclasses for each one and apply them to both panels
		
		optionsButton = new JButton("Options");
		frame.add(optionsButton);
		optionsButton.setBounds(542, 475, 142, 36);
		
		saveButton = new JButton("Save");
		frame.add(saveButton);
		saveButton.setBounds(385, 475, 142, 36);
	}

	private void setupManualPanel() {
		JPanel manualPanel = new JPanel();
		manualPanel.setBackground(Color.LIGHT_GRAY);
		manualPanel.setBounds(10, 31, 674, 200);
		frame.add(manualPanel);
		manualPanel.setLayout(null);
		
		manualTablePane = new TablePane(new Rectangle(10, 11, 249, 150));
		manualPanel.add(manualTablePane.getScrollPane());
		
		JLabel manualText = new JLabel("Manual Workspace");
		manualText.setBounds(10, 11, 674, 20);
		frame.add(manualText);
		manualText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton saveConfigButton = new JButton("Save Configuration");
		manualPanel.add(saveConfigButton);
		saveConfigButton.setBounds(10, 168, 145, 25);
		
		JButton revertButton = new JButton("Revert");
		manualPanel.add(revertButton);
		revertButton.setBounds(164, 168, 94, 25);
		
		JPanel resultsPanel = setupResultsPanel();
		manualPanel.add(resultsPanel);
		JLabel resultsText = new JLabel("Results");
		manualPanel.add(resultsText);
		resultsText.setBounds(330, 7, 100, 20);
		resultsPanel.setBounds(280, 27, 150, 100);
		
		JButton exportButton = new JButton("Export Configuration");
		manualPanel.add(exportButton);
		exportButton.setBounds(280, 168, 150, 25);
		JButton importButton = new JButton("Import Configuration");
		manualPanel.add(importButton);
		importButton.setBounds(280, 138, 150, 25);
	}
	
	private void setupAutomaticPanel() {
		JPanel automaticPanel = new JPanel();
		automaticPanel.setLayout(null);
		automaticPanel.setBackground(Color.LIGHT_GRAY);
		frame.add(automaticPanel);
		automaticPanel.setBounds(10, 264, 674, 200);
		
		automaticTablePane = new TablePane(new Rectangle(10, 11, 250, 150));
		automaticPanel.add(automaticTablePane.getScrollPane());
		
		JLabel automaticText = new JLabel("Automatic Workspace");
		automaticText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.add(automaticText);
		automaticText.setBounds(10, 242, 674, 20);
		
		JButton saveConfigButton = new JButton("Save Configuration");
		automaticPanel.add(saveConfigButton);
		saveConfigButton.setBounds(10, 168, 145, 25);
		
		JButton revertButton = new JButton("Revert");
		automaticPanel.add(revertButton);
		revertButton.setBounds(164, 168, 94, 25);
		
		JPanel resultsPanel = setupResultsPanel();
		automaticPanel.add(resultsPanel);
		JLabel resultsText = new JLabel("Results");
		automaticPanel.add(resultsText);
		resultsText.setBounds(330, 7, 100, 20);
		resultsPanel.setBounds(280, 27, 150, 100);
		
		JButton exportButton = new JButton("Export Configuration");
		automaticPanel.add(exportButton);
		exportButton.setBounds(280, 168, 150, 25);
		JButton importButton = new JButton("Import Configuration");
		automaticPanel.add(importButton);
		importButton.setBounds(280, 138, 150, 25);
	}
	
	private JPanel setupResultsPanel() {
		JPanel resultsPanel = new JPanel();
		resultsPanel.setLayout(null);
		
		JLabel fpText = new JLabel("False Positives");
		resultsPanel.add(fpText);
		fpText.setBounds(10, 11, 100, 20);
		fpText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel fnText = new JLabel("False Negatives");
		resultsPanel.add(fnText);
		fnText.setBounds(10, 34, 100, 20);
		fnText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JTextField fpWeight = new JTextField("" + 0);
		fpWeight.setEditable(false);
		fpWeight.setHorizontalAlignment(JTextField.CENTER);
		resultsPanel.add(fpWeight);
		fpWeight.setBounds(110, 10, 26, 20);
		
		JTextField fnWeight = new JTextField("" + 0);
		fnWeight.setEditable(false);
		fnWeight.setHorizontalAlignment(JTextField.CENTER);
		resultsPanel.add(fnWeight);
		fnWeight.setBounds(110, 35, 26, 20);
		
		JButton evaluateButton = new JButton("Evaluate");
		resultsPanel.add(evaluateButton);
		evaluateButton.setBounds(30, 65, 90, 25);
		
		return resultsPanel;
	}
}
