import java.util.ArrayList;

/**
 * Represents an academic member of the library.
 * Extends the Member class and adds the ability to borrow up to 4 books.
 */
public class Academic extends Member {
    public final int MAX_BOOKS = 4; // The maximum number of books an academic member can borrow.
    private ArrayList<Integer> books = new ArrayList<Integer>(MAX_BOOKS); // An ArrayList of book IDs that the academic member has borrowed.

    /**
     * Constructs a new Academic object.
     * Calls the super constructor of the Member class.
     */
    public Academic() {
        super();
    }

    /**
     * Returns the ArrayList of book IDs that the academic member has borrowed.
     * @return The ArrayList of book IDs.
     */
    public ArrayList<Integer> getBooks() {
        return books;
    }

    /**
     * Sets the ArrayList of book IDs that the academic member has borrowed.
     * @param books The ArrayList of book IDs.
     */
    public void setBooks(ArrayList<Integer> books) {
        this.books = books;
    }

    /**
     * Adds a book ID to the ArrayList of book IDs that the academic member has borrowed.
     * @param id The ID of the book to add.
     */
    public void addBook(int id) {
        books.add(id);
    }

    /**
     * Adds a new academic member to the library.
     * Creates a new Academic object and adds it to the members ArrayList in the Library class.
     * Writes a message to the output file indicating that a new member has been created.
     */
    public static void addMember() {
        Academic member = new Academic();
        Library.members.add(member);
        Main.ow.writeOutput("Created new member: Academic [id: " + member.getId() + "]");
    }
}
