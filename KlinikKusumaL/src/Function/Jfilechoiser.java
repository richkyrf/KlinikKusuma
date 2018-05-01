package Function;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Jfilechoiser {

    public static String GetSaveLocationFile(String FileType, String ExstensionFile) {
        String FileLocation = null;
        JFrame parentFrame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(FileType, new String[]{ExstensionFile});

        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Save File");
        int userSelection = fileChooser.showSaveDialog(parentFrame);
        if (userSelection == 0) {
            File fileToSave = fileChooser.getSelectedFile();
            FileLocation = fileToSave.getAbsolutePath().replace(new StringBuilder().append(".").append(ExstensionFile).toString(), "") + "." + ExstensionFile;
        }
        return FileLocation;
    }

    public static String GetLoadLocationFile(String FileType, String ExstensionFile) {
        String FileLocation = null;
        JFrame parentFrame = new JFrame();
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(FileType, new String[]{ExstensionFile});

        fc.setFileFilter(filter);
        fc.setDialogTitle("Load File");
        int returnVal = fc.showOpenDialog(parentFrame);
        if (returnVal == 0) {
            File file = fc.getSelectedFile();
            FileLocation = file.getAbsolutePath();
        }
        return FileLocation;
    }
}
