package antiSpamFilter.gui.panels;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
/**
 * This is a panel that is used to locate a configuration file. This means either the rules.cf, ham.log or spam.log files.
 * @author skner
 *
 */
public class FileLocationPanel extends JPanel	{
	
	private JLabel label;	// Label containing the file name to aid the user understand which file the panel locates
	private JTextField path;	// A text field that will hold the inserted path, sent and interpreted by the engine if applied
	private JCheckBox checkBox;	// Check box will enable the file location, not checking it, will make the engine use the default file, regardless of what's in the text field
	private String oldPath;	// Buffer for the path, in case of the button cancel is triggered
	
	/**
	 * Constructor
	 * @param bounds Size and position of the panel
	 * @param fileName Represents the file that the path should point to.
	 */
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

	/**
	 * Makes the panel work
	 */
	private void setupFunctionality() {
		checkBox.addActionListener(new ActionListener()	{  
            public void actionPerformed(ActionEvent e)  
            {  
                path.setEditable(checkBox.isSelected());
            }
        });  
		oldPath = path.getText();
	}
	
	/**
	 * Serves as a token to make sure something was changed in the configuration so it can be applied
	 * @return True if the path changed since the last configuration, false otherwise
	 */
	public boolean changed()	{
		if(!getFilePath().equals(oldPath))	{
			oldPath = getFilePath();
			return true;
		}	else	{
			return false;
		}

	}
	
	/**
	 * Getter for the file path
	 * @return Respective file path
	 */
	public String getFilePath()	{
		if(checkBox.isSelected())	{
			return path.getText();
		}	else	{
			return "";
		}
	}
	
	/**
	 * Sets text displayed in the path box
	 * @param text Text to be displayed in the path box
	 */
	public void setTextField(String text)	{
		path.setText(text);
	}
}
