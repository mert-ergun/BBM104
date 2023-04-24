import java.util.ArrayList;

/**
 * The Library class represents a library that contains a list of members and books.
 * It provides methods to retrieve a member or book by their ID, as well as a method to generate a history report of the library.
 */
public class Library {
    public static ArrayList<Member> members = new ArrayList<Member>();  // List of members in the library
    public static ArrayList<Book> books = new ArrayList<Book>();  // List of books in the library

    /**
     * Retrieves a member from the library by their ID.
     * @param id The ID of the member to retrieve.
     * @return The member with the specified ID, or null if no member with that ID exists in the library.
     */
    public static Member getMemberById(int id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    /**
     * Returns the Book object with the specified ID.
     * @param id The ID of the book to retrieve.
     * @return The Book object with the specified ID, or null if no such book exists.
     */
    public static Book getBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    /**
     * Displays the history of the library, including the number of students and academics, the number of printed and handwritten books,
     * the number of borrowed and reading books, and the details of each borrowed and reading book.
     */
    public static void getHistory() {
        Main.ow.writeOutput("History of library:");
        Main.ow.writeOutput("");
        int numberOfStudents = 0;
        int numberOfAcademics = 0;
        for (Member member : members) {
            if (member instanceof Student) {
                numberOfStudents++;
            } else {
                numberOfAcademics++;
            }
        }
        Main.ow.writeOutput("Number of students: " + numberOfStudents);
        for (Member member : members) {
            if (member instanceof Student) {
                Main.ow.writeOutput("Student [id: " + member.getId() + "]");
            }
        }
        Main.ow.writeOutput("");
        Main.ow.writeOutput("Number of academics: " + numberOfAcademics);
        for (Member member : members) {
            if (member instanceof Academic) {
                Main.ow.writeOutput("Academic [id: " + member.getId() + "]");
            }
        }
        Main.ow.writeOutput("");
        int numberOfPrintedBooks = 0;
        int numberOfHandwrittenBooks = 0;
        for (Book book : books) {
            if (book instanceof Printed) {
                numberOfPrintedBooks++;
            } else {
                numberOfHandwrittenBooks++;
            }
        }
        Main.ow.writeOutput("Number of printed books: " + numberOfPrintedBooks);
        for (Book book : books) {
            if (book instanceof Printed) {
                Main.ow.writeOutput("Printed [id: " + book.getId() + "]");
            }
        }
        Main.ow.writeOutput("");
        Main.ow.writeOutput("Number of handwritten books: " + numberOfHandwrittenBooks);
        for (Book book : books) {
            if (book instanceof Handwritten) {
                Main.ow.writeOutput("Handwritten [id: " + book.getId() + "]");
            }
        }
        int numberOfBorrowedBooks = 0;
        for (Book book : books) {
            if (book.isBorrowed()) {
                numberOfBorrowedBooks++;
            }
        }
        Main.ow.writeOutput("");
        Main.ow.writeOutput("Number of borrowed books: " + numberOfBorrowedBooks);
        for (Book book : books) {
            if (book.isBorrowed()) {
                Main.ow.writeOutput("The book [" + book.getId() + "] was borrowed by member [" + book.getReadingMemberId() + "] at " + Book.SDF.format(book.getBorrowDate().getTime()));
            }
        }
        int numberOfReadingBooks = 0;
        for (Book book : books) {
            if (book.isReading()) {
                numberOfReadingBooks++;
            }
        }
        Main.ow.writeOutput("");
        Main.ow.writeOutput("Number of books read in library: " + numberOfReadingBooks);
        for (Book book : books) {
            if (book.isReading()) {
                Main.ow.writeOutput("The book [" + book.getId() + "] was read in library by member [" + book.getReadingMemberId() + "] at " + Book.SDF.format(book.getReadingDate().getTime()));
            }
        }
    }
}
