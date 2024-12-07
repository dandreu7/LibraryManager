public class Book {
    private String title;
    private String author;
    private int year;
    private boolean isCheckedOut;

    public Book(String title, String author, int year, boolean isCheckedOut) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isCheckedOut = isCheckedOut;
    }

    // Convert book details to a string
    @Override
    public String toString() {
        return title + "," + author + "," + year + "," + isCheckedOut;
    }

    // Parse a book from a string
    public static Book fromString(String line) {
        String[] parts = line.split(",");
        return new Book(
                parts[0],
                parts[1],
                Integer.parseInt(parts[2]),
                Boolean.parseBoolean(parts[3])
        );
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(boolean isCheckedOut) {
        this.isCheckedOut = isCheckedOut;
    }

}
