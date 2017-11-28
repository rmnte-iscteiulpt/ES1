/**
 * 
 */
package antiSpamFilter.gui.panels;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author skner
 *
 */
@SuppressWarnings("serial")
public class FileLocationPanel extends JPanel	{
	
	private JLabel label;
	private JTextField path;
	private JCheckBox checkBox;
	private String oldPath;
	
	public FileLocationPanel(Rectangle bounds, String fileName)	{
		setBounds(bounds);
		setLayout(null);
		
		label = new JLabel(fileName + " file location:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(10, 5, 345, 25);
		add(label);
		
		path = new JTextField();
		path.setBounds(10, 35, 345, 23);
		path.setEditable(false);
		add(path);
		path.setColumns(10);
		
		checkBox = new JCheckBox("Custom file");
		checkBox.setBounds(8, 62, 100, 23);
		add(checkBox);
		
		setupFunctionality();
	}

	private void setupFunctionality() {
		checkBox.addActionListener(new ActionListener()	{  
            public void actionPerformed(ActionEvent e)  
            {  
                path.setEditable(checkBox.isSelected());
            }
        });  
		oldPath = path.getText();	// Needs to be ""
	}
	
	public boolean changed()	{
		if(!getFilePath().equals(oldPath))	{
			oldPath = getFilePath();
			return true;
		}	else	{
			return false;
		}

	}
	
	public String getFilePath()	{
		if(checkBox.isSelected())	{
			return path.getText();
		}	else	{
			return "";
		}
		
	}
}
