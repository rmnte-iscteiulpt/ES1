package antiSpamFilter.gui.panes;

import java.awt.Rectangle;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author rmnte-iscteiulpt
 *
 */
@SuppressWarnings("serial")
public class TablePane extends JScrollPane	{

	private JTable table;
	
	public TablePane(Rectangle bounds)	{
		super();
		table = new JTable();
		setBounds(bounds);
		setViewportView(table);
		// TODO Add way to read file and create the matrix rules and weight field
		table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null},
					{null, null},
					{null, null},
					{null, null},
				},
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
					false, false
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
