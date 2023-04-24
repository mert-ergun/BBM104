/**
 * Represents a printed book in the library.
 * Extends the Book class and sets the borrowable property to true.
 */
public class Printed extends Book {
    /**
     * Creates a new Printed book object.
     * Sets the borrowable property to true.
     */
    public Printed() {
        super();
        super.setBorrowable(true);
    }
    
    /**
     * Adds a new Printed book to the library.
     * Creates a new Printed book object, adds it to the library's list of books,
     * and writes a message to the output file indicating the creation of the new book.
     */
    public static void addBook() {
        Printed book = new Printed();
        Library.books.add(book);
        Main.ow.writeOutput("Created new book: Printed [id: " + book.getId() + "]");
    }
}
