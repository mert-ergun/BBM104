/**
 * Represents an academic member of the library.
 * Extends the Member class and adds the ability to borrow up to 4 books.
 */
public class Academic extends Member {
    private final int MAX_BOOKS = 4; // The maximum number of books an academic member can borrow.
    private final int TIME_LIMIT = 14;  // The time limit for academic members to borrow a book.

    /**
     * Constructs a new Academic object.
     * Calls the super constructor of the Member class.
     */
    public Academic() {
        super();
    }

    /**
     * Returns the maximum number of books an academic member can borrow.
     * @return the maximum number of books an academic member can borrow.
     */
    public int getMAX_BOOKS() {
        return MAX_BOOKS;
    }

    /**
     * Returns the time limit for academic members to borrow a book.
     * @return the time limit for academic members to borrow a book.
     */
    public int getTIME_LIMIT() {
        return TIME_LIMIT;
    }

    /**
     * Adds a new academic member to the library.
     * Creates a new Academic object and adds it to the members ArrayList in the Library class.
     * Writes a message to the output file indicating that a new member has been created.
     */
    public static void addMember() {
        Academic academic = new Academic();
        Library.members.add(academic);
        Main.ow.writeOutput("Created new member: Academic [id: " + academic.getId() + "]");
    }
}
