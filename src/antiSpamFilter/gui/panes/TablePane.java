package antiSpamFilter.gui.panes;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import antiSpamFilter.misc.RulesConfigList;

/**
 * @author rmnte-iscteiulpt
 *
 */
@SuppressWarnings("serial")
public class TablePane extends JScrollPane	{

	private boolean editable;
	private JTable table;
	
	public TablePane(Rectangle bounds, RulesConfigList configList, boolean editable)	{
		// TODO Add option for it to be editable (manual workspace)
		super();
		table = new JTable();
		this.editable = editable;
		setBounds(bounds);
		setViewportView(table);
		// TODO Add way to read file and create the matrix rules and weight field
		updateContent(configList);
	}
	
	// TODO Add a way to edit the cells and apply them in the respective engines.
	public void updateContent(RulesConfigList arg)	{
		Object[][] o = new Object[arg.getRulesList().size()][2]; 
		for(int i = 0; i<arg.getRulesList().size(); i++)	{
			o[i][0] = arg.getRulesList().get(i);
			o[i][1] = arg.getWeightList().get(i);
		}
		// TODO Is there a better way to update the table's values without making it from scratch everytime?
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

	public JTable getTable() {
		return table;
	}
	
}
