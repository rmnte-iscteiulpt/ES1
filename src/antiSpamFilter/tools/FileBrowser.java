package antiSpamFilter.tools;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import antiSpamFilter.main.Main;

@SuppressWarnings("serial")
/**
 * Dialog window that will be used to help the user locate configuration files
 * @author rmnte-iscteiulpt
 *
 */
public class FileBrowser extends JFileChooser {

	/**
	 * Default constructor
	 * @param file 
	 */
	public FileBrowser(String file)	{
		this(file, Main.defaultExportPath);
	}
	
	/**
	 * Constructor
	 * @param file File that the FileBrowser should help locate
	 * @param defaultFolder The folder that it will open when the dialog window appears
	 */ 
	public FileBrowser(String file, String defaultFolder) {
		super();
		
		// Check if directory exists, if not, creates it. If it cant due to security reasons, it will use the user's documents folder.
		if (!new File(defaultFolder).exists()) {
		    try{
		    	new File(defaultFolder).mkdir();
		    } 
		    catch(SecurityException se){
		    	System.out.println("Couldn't create folder. Opening user's documents folder.");
		    }        
		}
		setDialogTitle("Choose custom " + file + " file");
		
		setAcceptAllFileFilterUsed(false);
		setCurrentDirectory(new File(defaultFolder));
		String fileFilterDescription = "";
		String fileExtension = "";
		if(file == "rules")	{
			fileFilterDescription = "Rules.cf File";
			fileExtension = "cf";
		}	else	if(file == "spam")	{
			fileFilterDescription = "Spam.log File";
			fileExtension = "log";
		}	else	if(file == "ham")	{
			fileFilterDescription = "Ham.log File";
			fileExtension = "log";
		}	else	if(file == "cfg")	{
			fileFilterDescription = "Configuration file";
			fileExtension = "cfg";
		}	else	if(file == "r")	{
			setDialogTitle("Locate RScript.exe");
			fileFilterDescription = "RScript.exe";
			fileExtension = "exe";
		}	else	if(file == "tex")	{
			setDialogTitle("Locate pdflatex.exe");
			fileFilterDescription = "pdflatex.exe";
			fileExtension = "exe";
		}
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter(fileFilterDescription, fileExtension);
		addChoosableFileFilter(filter);
	}
	
	/**
	 * Method that will return the path chosen by the user after using the dialog
	 * @return Path chosen by the user
	 */
	public String getBrowsePath()	{
		int returnValue = showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			return getSelectedFile().getAbsolutePath();
		}
		return null;
	}
	
	/**
	 * 
	 * @return The path of which the file should be saved
	 */
	public String getSavePath()	{
		setDialogTitle("Choose save location");
		int returnValue = showSaveDialog(null);
		String path = null;
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			path = getSelectedFile().getAbsolutePath();
			if(!path.endsWith(".cfg")) {
				path += ".cfg";
			}
		}
		return path;
	}
}
