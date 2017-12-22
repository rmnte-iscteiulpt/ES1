package antiSpamFilter.gui.panes;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import antiSpamFilter.datastore.RulesConfigList;

@SuppressWarnings("serial")
/**
 * A pane that contains a set of configurations for a JTable, to be displayed in the workspaces
 * @author skner
 *
 */
public class TablePane extends JScrollPane	{

	private boolean editable;	// Distinguishes the table from manual or automatic (editable or not editable)
	private JTable table;	// This table holds temporary values. The values used in the engine are from the weightList
	private ArrayList<Float> weightList;	// This array list holds the permanent values, used by the engine

	/**
	 * Constructor
	 * @param bounds Size and position of the pane
	 * @param configList Configuration list to apply to the JTable
	 * @param editable Makes the weights column editable or not
	 */
	public TablePane(Rectangle bounds, RulesConfigList configList, boolean editable)	{
		super();
		table = new JTable();	// TODO Show decimal places in JTable? Render Format JTable boolean...
		this.editable = editable;
		setBounds(bounds);
		setViewportView(table);
		updateContent(configList);
		updateWeightList();
	}
	
	/**
	 * Updates the content of the JTable with a new configuration list
	 * @param arg The configuration list
	 */
	public void updateContent(RulesConfigList arg)	{
		Object[][] o = new Object[arg.getRulesList().size()][2]; 
		for(int i = 0; i<arg.getRulesList().size(); i++)	{
			o[i][0] = arg.getRulesList().get(i);
			o[i][1] = arg.getWeightList().get(i);
		}
		table.setModel(new DefaultTableModel(
				o,
				new String[] {
					"Rule", "Weight"
				}
			){
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					String.class, Float.class
				};
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, editable
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getTableHeader().setReorderingAllowed(false);
	}

	/**
	 * Updates the weight list, copying values from the JTable, serving as a data buffer
	 */
	public void updateWeightList()	{
		boolean rangeExceeded = false;
		weightList = new ArrayList<Float>();
		for(int i = 0; i<table.getRowCount(); i++)	{
			float f = (Float) table.getValueAt(i, 1);
			if(f > 5f)	{	// Range restrictions
				rangeExceeded = true;
				weightList.add(5f);
				table.setValueAt(5f, i, 1);
			}	else	if(f < -5f)	{
				rangeExceeded = true;
				weightList.add(-5f);
				table.setValueAt(-5f, i, 1);
			}	else	{
				weightList.add(f);
			}
		}
		if(rangeExceeded)	{
			System.out.println("Range exceeded in one or more cells. Changed weights to the range limit value.");
		}
	}
	
	/**
	 * Getter for the JTable, serving only as a buffer and visual display
	 * @return The current JTable
	 */
	public JTable getTable() {
		return table;
	}
	
	/**
	 * Getter for the weight list
	 * @return The weight list
	 */
	public ArrayList<Float> getWeightList()	{
		return weightList;
	}
	
	/**
	 * Applies changes, copying the values from the JTable to the weightList
	 */
	public void applyChanges()	{
		updateWeightList();
	}
	
	/**
	 * Cancels changes, copying the values from the weightList to the JTable, basically reverting them to the most recent saved configuration
	 */
	public void discardChanges()	{
		for(int i = 0; i<table.getRowCount(); i++)	{
			table.setValueAt(weightList.get(i), i, 1);
		}
	}
}
