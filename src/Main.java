//Public Library Manager
//By Donovan Andreu
//2024

import java.io.IOException;
import java.util.Scanner;

public static void main(String[] args) throws IOException {
    //System.out.println(FirstTimeSetUp.getDesktop());
    FirstTimeSetUp.makeFolder();

    System.out.println("Welcome to the Library!\nHow may we be of assistance?");
    Scanner scnr = new Scanner(System.in);
    System.out.println("-------------------------------MENU-------------------------------");
    System.out.println("""
            ~ Browse Books
            ~ Create Membership
            ~ User Login
            """);
    System.out.println("------------------------------------------------------------------");

    // TODO: take scnr input and set it all to lowercase with no space. Also allow "Books" "membership" and
    //  "login" as valid commands. Otherwise, ask user to please try again
    scnr.nextLine();

    // TODO: If User Logs in as an admin, bring up secret Admin Menu with features such as "book Management"
    //  and "User management". Each have sub menus of "Add book", "delete book" and "delete user" respectively
}