import java.io.File;

public class FirstTimeSetUp {
    //Find Users Desktop
    public static String desktop = (System.getProperty("user.home") +
            File.separator + "Desktop");

    //Display Path Method
    public static String getDesktop() {
        return desktop;
    }

    public static void makeFolder() {
        // Create Base Folder "Library" if not exists
        File libFolder = new File(desktop + File.separator + "Library");
        // TODO: make subfolders and .txt files that go in them for Library management
        if (libFolder.exists()) {
            System.out.println("File 'Library' Exists Already");
        } else {
            System.out.println("File 'Library' Does Not Exist");
            System.out.println("Creating 'Library' Folder at " +
                    desktop + File.separator + "Library");
            if(libFolder.mkdir()){
                System.out.println("Library Folder created Successfully");
            } else {
                System.out.println("Library Folder creation Failed");
            }
        }
    }

}
