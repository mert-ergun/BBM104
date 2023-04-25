/**
 * This class represents a student member of the library.
 * It extends the Member class and has a maximum number of books that can be borrowed.
 */
public class Student extends Member {
    private final int MAX_BOOKS = 2;  // The maximum number of books a student member can borrow.
    private final int TIME_LIMIT = 7;  // The time limit for student members to borrow a book.

    /**
     * Default constructor for the Student class.
     * Calls the constructor of the superclass.
     */
    public Student() {
        super();
    }

    /**
     * Returns the maximum number of books a student member can borrow.
     * @return the maximum number of books a student member can borrow.
     */
    public int getMAX_BOOKS() {
        return MAX_BOOKS;
    }

    /**
     * Returns the time limit for student members to borrow a book.
     * @return the time limit for student members to borrow a book.
     */
    public int getTIME_LIMIT() {
        return TIME_LIMIT;
    }

    /**
     * Static method to add a new student member to the library.
     * Creates a new Student object, adds it to the members ArrayList in the Library class,
     * and writes a message to the output file.
     */
    public static void addMember() {
        Student student = new Student();
        Library.members.add(student);
        Main.ow.writeOutput("Created new member: Student [id: " + student.getId() + "]");
    }
}
