/**
 * Represents a handwritten book that cannot be borrowed from the library.
 * Inherits from the Book class.
 */
public class Handwritten extends Book {
    /**
     * Constructs a new Handwritten book object and sets its borrowable property to false.
     */
    public Handwritten() {
        super();
        super.setBorrowable(false);
    }

    /**
     * Adds a new Handwritten book to the library.
     * Prints a message to the output writer indicating that a new book has been created.
     */
    public static void addBook() {
        Handwritten book = new Handwritten();
        Library.books.add(book);
        Main.ow.writeOutput("Created new book: Handwritten [id: " + book.getId() + "]");
    }
}
