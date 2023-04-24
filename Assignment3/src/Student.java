import java.util.ArrayList;

/**
 * This class represents a student member of the library.
 * It extends the Member class and has a maximum number of books that can be borrowed.
 */
public class Student extends Member {
    public final int MAX_BOOKS = 2;  // The maximum number of books a student can borrow.
    private ArrayList<Integer> books = new ArrayList<Integer>(MAX_BOOKS);  // The list of books the student has borrowed.

    /**
     * Default constructor for the Student class.
     * Calls the constructor of the superclass.
     */
    public Student() {
        super();
    }

    /**
     * Getter method for the books ArrayList.
     * @return The books ArrayList.
     */
    public ArrayList<Integer> getBooks() {
        return books;
    }

    /**
     * Setter method for the books ArrayList.
     * @param books The new books ArrayList.
     */
    public void setBooks(ArrayList<Integer> books) {
        this.books = books;
    }

    /**
     * Adds a book to the books ArrayList.
     * @param id The ID of the book to be added.
     */
    public void addBook(int id) {
        books.add(id);
    }

    /**
     * Static method to add a new student member to the library.
     * Creates a new Student object, adds it to the members ArrayList in the Library class,
     * and writes a message to the output file.
     */
    public static void addMember() {
        Student member = new Student();
        Library.members.add(member);
        Main.ow.writeOutput("Created new member: Student [id: " + member.getId() + "]");
    }
}
