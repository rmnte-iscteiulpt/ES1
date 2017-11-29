/**
 * 
 */
package antiSpamFilter.gui.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import antiSpamFilter.gui.panes.TablePane;
import antiSpamFilter.misc.RulesConfigList;

/**
 * @author skner
 *
 */
@SuppressWarnings("serial")
public class WorkspacePanel extends JPanel	{
	
	private TablePane tablePane;
	private JPanel resultsPanel;
	
	public WorkspacePanel(Rectangle bounds, RulesConfigList configList, boolean editableWeights)	{
		tablePane = new TablePane(new Rectangle(10, 11, 249, 150), configList, editableWeights);
		setBackground(Color.LIGHT_GRAY);
		setBounds(bounds);
		setLayout(null);
		add(tablePane);
		JLabel resultsText = new JLabel("Results");
		add(resultsText);
		resultsText.setBounds(330, 7, 100, 20);
		//generateManualLayout();
		add(resultsPanel = setupResultsPanel());
		resultsPanel.setBounds(280, 27, 150, 100);
	}

	private JPanel setupResultsPanel() {
		resultsPanel = new JPanel();
		resultsPanel.setLayout(null);
		
		JLabel fpText = new JLabel("False Positives");
		resultsPanel.add(fpText);
		fpText.setBounds(10, 11, 100, 20);
		fpText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JLabel fnText = new JLabel("False Negatives");
		resultsPanel.add(fnText);
		fnText.setBounds(10, 34, 100, 20);
		fnText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		// Fields to be updated as the data changes
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
		
		// Button that triggers a function
		JButton evaluateButton = new JButton("Evaluate");
		resultsPanel.add(evaluateButton);
		evaluateButton.setBounds(30, 65, 90, 25);
		
		return resultsPanel;
	}
	
	public void updateTableContent(RulesConfigList arg)	{
		tablePane.updateContent(arg);
	}
}
