package antiSpamFilter.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

/**
 * Main window for the software. The code here defines all the sections, text and parts of the graphical interface.
 *
 * @param  frame  The main frame of the software
 * @param  optionsButton Button that opens the options popup window
 * @param  saveButton (WIP) Button that saves work
 */
public class MainWindow {
	
	private JFrame frame;
	private JButton optionsButton;
	private JButton saveButton;
	
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
		
		JPanel manualPanel = new JPanel();
		manualPanel.setBackground(Color.LIGHT_GRAY);
		manualPanel.setBounds(10, 31, 674, 200);
		frame.add(manualPanel);
		manualPanel.setLayout(null);
		
		JLabel manualText = new JLabel("Manual Workspace");
		manualText.setBounds(10, 11, 674, 20);
		frame.getContentPane().add(manualText);
		manualText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JPanel automaticPanel = new JPanel();
		automaticPanel.setLayout(null);
		automaticPanel.setBackground(Color.LIGHT_GRAY);
		automaticPanel.setBounds(10, 264, 674, 200);
		frame.getContentPane().add(automaticPanel);
		
		JLabel automaticText = new JLabel("Automatic Workspace");
		automaticText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		automaticText.setBounds(10, 242, 674, 20);
		frame.getContentPane().add(automaticText);
		
		optionsButton = new JButton("Options");
		optionsButton.setBounds(542, 475, 142, 36);
		frame.getContentPane().add(optionsButton);
		saveButton = new JButton("Save");
		saveButton.setBounds(385, 475, 142, 36);
		frame.getContentPane().add(saveButton);
	}

}
