import java.io.File;

public class FirstTimeSetUp {
    /* TODO: Create folders necessary for program operation if not already existing */

    //Find Users Desktop
    public static String desktop = (System.getProperty("user.home") +
            File.separator + "Desktop");

    //Display Path Method
    public static String getDesktop() {
        return desktop;
    }

    // Create Base Folder "Library" if not exists
    File libFolder = new File(desktop + File.separator + "Library");

    if (!libFolder.isFile()) {
        boolean folderCreated = libFolder.mkdir();
        if (folderCreated) {
            System.out.println("Folder 'Library' created on the Desktop.");
        } else {
            System.out.println("Failed to create the folder.");
        }
    } else {
        System.out.println("Folder 'Library' already exists on the Desktop.");
    }

}
