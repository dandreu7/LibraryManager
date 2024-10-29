public class Book {
    // Define "Book"
    private String title;
    private String author;
    private int year;
    private boolean isCheckedOut;
    // Book Constructor
    public Book(String title, String author, int year, boolean isCheckedOut) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isCheckedOut = isCheckedOut;
    }
}
