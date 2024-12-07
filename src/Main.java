import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // Set up folders and files
        FirstTimeSetUp.makeFolder();

        // Initialize bookshelf and load users
        Bookshelf bookshelf = new Bookshelf();
        UserManagement user = null;
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
                case "books", "browse books", "book", "browse" -> browseBooks(scnr, bookshelf, user);
                case "membership", "create membership", "create" -> createMembership(scnr);
                case "login", "user login" , "user" -> {
                    user = loginUser(scnr);
                    if (user != null && user.isAdmin()) {
                    adminMenu(bookshelf, scnr);
                }
            }
                case "exit" -> {
                    System.out.println("Thank you for visiting the Library, goodbye!");
                    return;
                }
                default -> System.out.println("Invalid command, please try again.");
            }
        }
    }

    // Browse Books
    private static void browseBooks(Scanner scnr, Bookshelf bookshelf, UserManagement user) {
        while (true) {
            System.out.println("""
                    Browse Books Menu:
                    1. View all books
                    2. Sort books
                    3. Check out a book
                    4. Return to main menu
                    [Please Select a Number]
                    """);
            String choice = scnr.nextLine().trim();

            switch (choice) {
                case "1" -> bookshelf.displayBooks();
                case "2" -> sortBooks(scnr, bookshelf);
                case "3" -> checkOutBook(scnr, bookshelf, user);
                case "4" -> {
                    System.out.println("Returning to main menu.");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void sortBooks(Scanner scnr, Bookshelf bookshelf) {
        System.out.println("""
                Sort by:
                1. Author
                2. Year
                3. Availability
                [Please Select a Number]
                """);
        String sortChoice = scnr.nextLine().trim();

        switch (sortChoice) {
            case "1" -> bookshelf.sortByAuthor();
            case "2" -> bookshelf.sortByYear();
            case "3" -> bookshelf.sortByAvailability();
            default -> System.out.println("Invalid choice! Returning to previous menu.");
        }
        System.out.println("Books sorted successfully!");
    }
    // Checking out a book
    private static void checkOutBook(Scanner scnr, Bookshelf bookshelf, UserManagement user) {
        // Check if user is logged in
        if (user == null || !user.isMember()) {
            System.out.println("You must be logged in to check out a book.");
            return;
        }
        System.out.println("Enter the title of the book you'd like to check out:");
        String title = scnr.nextLine().trim();
        Book book = bookshelf.findBookByTitle(title);
        if (book != null && !book.isCheckedOut()) {
            book.setCheckedOut(true);
            bookshelf.saveBooks(); // Save changes to file
            printReceipt(user.getUsername(), book.getTitle());
        } else if (book != null && book.isCheckedOut()) {
            System.out.println("Sorry, that book is currently checked out.");
        } else {
            System.out.println("Book not found. Please try again.");
        }
    }
    // Create new Membership
    private static void createMembership(Scanner scnr) {
        System.out.println("----Create a Membership----");
        System.out.println("Enter your username:");
        String username = scnr.nextLine().trim();
        System.out.println("Enter your password:");
        String password = scnr.nextLine().trim();

        UserManagement newUser = new UserManagement(username, password, false, true);
        UserManagement.addUser(UserManagement.loadUsers(), newUser);
        System.out.println("Membership created successfully! Please Login to access member features.");
    }

    // Log in
    private static UserManagement loginUser(Scanner scnr) {
        System.out.println("----User Login----");
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

    // Admin Menu
    private static void adminMenu(Bookshelf bookshelf, Scanner scnr) {
        ArrayList<UserManagement> users = UserManagement.loadUsers();
        while (true) {
            System.out.println("--------------------ADMIN MENU--------------------");
            System.out.println("""
                    ~ Add Book
                    ~ Delete Book
                    ~ Delete User
                    ~ Return to Main Menu
                    """);
            String input = scnr.nextLine().trim().toLowerCase();
            switch (input) {
                case "add book", "add" -> {
                    System.out.println("Enter book title:");
                    String title = scnr.nextLine();
                    System.out.println("Enter book author:");
                    String author = scnr.nextLine();
                    System.out.println("Enter publication year:");
                    int year = Integer.parseInt(scnr.nextLine());
                    Book newBook = new Book(title, author, year, false);
                    bookshelf.addBook(newBook);
                }
                case "delete book","delete b" -> {
                    System.out.println("Enter the title of the book to delete:");
                    String title = scnr.nextLine();
                    bookshelf.deleteBook(title);
                }
                case "delete user", "user" -> {
                    System.out.println("Enter the username of the user to delete:");
                    String username = scnr.nextLine();
                    UserManagement.deleteUser(users, username);
                }
                case "return to main menu", "menu", "main menu", "return", "main" -> {
                    System.out.println("Returning to the main menu.");
                    return;
                }
                default -> System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static void printReceipt(String username, String bookTitle) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        Date dueDate = new Date(today.getTime() + (14L * 24 * 60 * 60 * 1000)); // Add 14 days

        System.out.println("----------------------RECEIPT----------------------");
        System.out.println("Member: " + username);
        System.out.println("Book: " + bookTitle);
        System.out.println("Please return by: " + sdf.format(dueDate));
        System.out.println("Have a great day!");
        System.out.println("--------------------------------------------------");
    }
}
