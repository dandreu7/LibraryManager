import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // Set up folders and files
        FirstTimeSetUp.makeFolder();

        // Initialize bookshelf and load users
        Bookshelf bookshelf = new Bookshelf();
        UserManagement loggedInUser = null;
        Scanner scnr = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Library!\nHow may we be of assistance?");
            System.out.println("-------------------------------MENU-------------------------------");
            System.out.println("""
                    ~ Browse Books
                    ~ Create Membership
                    ~ User Login
                    ~ Exit
                    """);
            System.out.println("------------------------------------------------------------------");
            String command = scnr.nextLine().trim().toLowerCase();

            switch (command) {
                case "books", "browse books" -> browseBooks(scnr, bookshelf, loggedInUser);
                case "membership", "create membership" -> createMembership(scnr);
                case "login", "user login" -> loggedInUser = loginUser(scnr);
                case "exit" -> {
                    System.out.println("Thank you for visiting the Library. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid command. Please try again.");
            }
        }
    }

    private static void browseBooks(Scanner scnr, Bookshelf bookshelf, UserManagement loggedInUser) {
        while (true) {
            System.out.println("Browse Books Menu:\n1. View all books\n2. Sort books\n3. Check out a book\n4. Return to main menu");
            String choice = scnr.nextLine().trim();

            switch (choice) {
                case "1" -> bookshelf.displayBooks();
                case "2" -> sortBooks(scnr, bookshelf);
                case "3" -> checkOutBook(scnr, bookshelf, loggedInUser);
                case "4" -> {
                    System.out.println("Returning to the main menu.");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void sortBooks(Scanner scnr, Bookshelf bookshelf) {
        System.out.println("Sort by:\n1. Author\n2. Year\n3. Availability");
        String sortChoice = scnr.nextLine().trim();

        switch (sortChoice) {
            case "1" -> bookshelf.sortByAuthor();
            case "2" -> bookshelf.sortByYear();
            case "3" -> bookshelf.sortByAvailability();
            default -> System.out.println("Invalid choice. Returning to browse menu.");
        }
        System.out.println("Books sorted successfully!");
    }

    private static void checkOutBook(Scanner scnr, Bookshelf bookshelf, UserManagement loggedInUser) {
        if (loggedInUser == null || !loggedInUser.isMember()) {
            System.out.println("You must be logged in as a member to check out a book.");
            return;
        }

        System.out.println("Enter the title of the book you'd like to check out:");
        String title = scnr.nextLine().trim();
        Book book = bookshelf.findBookByTitle(title);

        if (book != null && !book.isCheckedOut()) {
            book.setCheckedOut(true);
            bookshelf.saveBooks(); // Save changes to file
            printReceipt(loggedInUser.getUsername(), book.getTitle());
        } else if (book != null && book.isCheckedOut()) {
            System.out.println("Sorry, that book is already checked out.");
        } else {
            System.out.println("Book not found. Please try again.");
        }
    }

    private static void createMembership(Scanner scnr) {
        System.out.println("Create a Membership:");
        System.out.println("Enter your username:");
        String username = scnr.nextLine().trim();
        System.out.println("Enter your password:");
        String password = scnr.nextLine().trim();

        UserManagement newUser = new UserManagement(username, password, false, true);
        UserManagement.addUser(UserManagement.loadUsers(), newUser);
        System.out.println("Membership created successfully!");
    }

    private static UserManagement loginUser(Scanner scnr) {
        System.out.println("User Login:");
        System.out.println("Enter your username:");
        String username = scnr.nextLine().trim();
        System.out.println("Enter your password:");
        String password = scnr.nextLine().trim();

        UserManagement user = UserManagement.findUser(UserManagement.loadUsers(), username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful. Welcome, " + username + "!");
            return user;
        } else {
            System.out.println("Invalid credentials. Please try again.");
            return null;
        }
    }

    private static void printReceipt(String username, String bookTitle) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        Date dueDate = new Date(today.getTime() + (14L * 24 * 60 * 60 * 1000)); // Add 14 days

        System.out.println("--------------------- RECEIPT ---------------------");
        System.out.println("Member: " + username);
        System.out.println("Book: " + bookTitle);
        System.out.println("Please return by: " + sdf.format(dueDate));
        System.out.println("--------------------------------------------------");
    }
}
