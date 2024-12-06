import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Bookshelf {
    private ArrayList<Book> books = new ArrayList<>();
    private static final String FILE_PATH = FirstTimeSetUp.getDesktop() + File.separator + "Library"
            + File.separator + "Books" + File.separator + "Books.txt";

    // Constructor loads books from file
    public Bookshelf() {
        loadBooks();
    }

    // Load books from Books.txt
    private void loadBooks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                books.add(Book.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
    }

    // Save all books to Books.txt
    void saveBooks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Book book : books) {
                writer.write(book.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    // Add a new book (Admin Only)
    public void addBook(Book book) {
        books.add(book);
        saveBooks();
        System.out.println("Book added successfully!");
    }

    // Delete a book by title (Admin Only)
    public void deleteBook(String title) {
        books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
        saveBooks();
        System.out.println("Book deleted successfully!");
    }

    // Sort books by author
    public void sortByAuthor() {
        books.sort(Comparator.comparing(Book::getAuthor));
    }

    // Sort books by year
    public void sortByYear() {
        books.sort(Comparator.comparingInt(Book::getYear));
    }

    // Sort books by availability
    public void sortByAvailability() {
        books.sort(Comparator.comparing(Book::isCheckedOut));
    }

    // Find a book by its title
    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }


    // Display all books (for testing purposes)
    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
