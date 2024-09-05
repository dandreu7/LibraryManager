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
        File bookFolder = new File(libFolder + File.separator + "Books");
        File membersFolder = new File(libFolder + File.separator + "Members");
        File moviesFolder = new File(libFolder + File.separator + "Movies");

        // TODO: make subfolders and .txt files (make a new class that can be used for making books)
        // that go in them for Library management
        if (libFolder.exists()) {
            System.out.println("File 'Library' Exists Already\nTo reset please delete folder and try again");
        } else {
            System.out.println("File 'Library' Does Not Exist");
            System.out.println("Creating 'Library' Folder and subsiquent subfolders at " +
                    desktop + File.separator + "Library");
            if(libFolder.mkdir()){
                System.out.println("Library Folder created Successfully");
                if(bookFolder.mkdir()){
                    System.out.println("Book Folder created Successfully");
                }
                if(membersFolder.mkdir()){
                    System.out.println("Member Folder created Successfully");
                }
                if(moviesFolder.mkdir()){
                    System.out.println("Movie Folder created Successfully");
                }
            } else {
                System.out.println("Library Folder creation Failed");
            }
        }
    }

}
