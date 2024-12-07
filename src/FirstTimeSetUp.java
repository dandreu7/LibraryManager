import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FirstTimeSetUp {
    // Find Users Desktop
    public static String desktop = (System.getProperty("user.home") +
            File.separator + "Desktop");

    // Display Path Method
    public static String getDesktop() {
        return desktop;
    }

    public static void makeFolder() throws IOException {
        // Create Base Folder "Library" if not exists
        File libFolder = new File(desktop + File.separator + "Library");
        File bookFolder = new File(libFolder + File.separator + "Books");
        File membersFolder = new File(libFolder + File.separator + "Members");
        //File moviesFolder = new File(libFolder + File.separator + "Movies");

        // TODO: make subfolders and .txt files (make a new class that can be used for making books)
        // that go in them for Library management
        if (libFolder.exists()) {
            System.out.println("File 'Library' Exists Already\nTo reset please delete folder and try again");
        } else {
            System.out.println("File 'Library' Does Not Exist");
            System.out.println("Creating 'Library' Folder and subsiquent subfolders at " +
                    desktop + File.separator + "Library");
            if(libFolder.mkdir()){
                System.out.println("Library Folder created Successfully, creating subfolders...");
                if(bookFolder.mkdir()){
                    System.out.println("Book Folder created Successfully");
                    //Create books.txt in bookFolder
                    File books = new File(bookFolder + File.separator + "Books.txt");
                    books.getParentFile().mkdirs();
                    books.createNewFile();
                    System.out.println("Books.txt created Successfully");
                }
                if(membersFolder.mkdir()){
                    System.out.println("Member Folder created Successfully");
                    //Create members.txt in membersFolder
                    File members = new File(membersFolder + File.separator + "Members.txt");
                    members.getParentFile().mkdirs();
                    members.createNewFile();
                    System.out.println("Members.txt created Successfully");
                    //Use UserManagement.java to create a default admin in Members.txt
                    FileWriter writer = new FileWriter(membersFolder + File.separator + "Members.txt");
                    System.out.println("Please create an admin password: ");
                    Scanner scnr = new Scanner(System.in);
                    String adminPass = scnr.nextLine().trim();
                    scnr.close();
                    writer.write("Admin," + adminPass +",true,true\n");
                    writer.close();
                }
                /*if(moviesFolder.mkdir()){
                    System.out.println("Movie Folder created Successfully");
                }*/
            } else {
                System.out.println("Library Folder creation Failed");
            }
        }
    }

}
