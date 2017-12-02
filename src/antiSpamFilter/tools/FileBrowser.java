package antiSpamFilter.tools;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

@SuppressWarnings("serial")
public class FileBrowser extends JFileChooser {

	
	public FileBrowser(String file) {
		super();
		
		setCurrentDirectory(new File(System.getProperty("user.dir") + "\\AntiSpamConfigurationForLeisureMailbox\\"));
		String fileFilterDescription = "";
		String fileExtension = "";
		if(file == "rules")	{
			fileFilterDescription = "Rules.cf File";
			fileExtension = "cf";
		}	else	if(file == "spam")	{
			fileFilterDescription = "Spam.log File";
			fileExtension = "log";
		}	else	{
			fileFilterDescription = "Ham.log File";
			fileExtension = "log";
		}
		FileNameExtensionFilter filter = new FileNameExtensionFilter(fileFilterDescription, fileExtension);
		setDialogTitle("Choose custom " + file + " file");
		addChoosableFileFilter(filter);
		setAcceptAllFileFilterUsed(false);
		
	}
	
	public String getBrowsePath()	{
		int returnValue = showOpenDialog(null);
		String path = "";
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			path = getSelectedFile().getAbsolutePath();
		}
		return path;
	}
}
