import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The Book class represents a book in the library system. 
 * It contains information about the book's ID, borrowable status, borrowed status, reading status, borrow date, return date, reading date, reading member ID, and time limits for borrowing. 
 * It also provides methods for borrowing, returning, extending, and reading a book in the library.
 */
public abstract class Book {
    // Class variables
    private int id, readingMemberId;
    private static int nextId = 1;
    private boolean borrowable, borrowed, reading, extended;
    private Calendar borrowDate, returnDate, readingDate;
    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");  // The date format used for the borrow date, return date, and reading date.

    /**
     * Constructor for objects of class Book with no parameters.
     * Initializes the book's ID.
     */
    public Book() {
        this.id = nextId++;
        this.borrowable = true;
        this.borrowed = false;
        this.reading = false;
        this.extended = false;
        this.borrowDate = null;
        this.returnDate = null;
        this.readingDate = null;
    }

    /**
     * Method to add a new book to the library.
     */
    public static void addBook() {}

    /**
     * Method for borrowing a book. Takes in a member ID and a date, and sets the book's borrow date, return date, and borrowed status accordingly. 
     * Also adds the book to the member's list of borrowed books.
     * @param memberId the ID of the member borrowing the book
     * @param date the date the book is being borrowed
     */
    public final void borrowBook(int memberId, String date) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(SDF.parse(date));
        } catch (Exception e) {
            Main.ow.writeOutput("Invalid date format.");
            return;
        }
        if (this.borrowed) {
            Main.ow.writeOutput("You cannot borrow this book!");
            return;
        }
        Member member = Library.getMemberById(memberId);
        if (member == null) {
            Main.ow.writeOutput("Member not found!");
            return;
        }
        boolean isStudent = member instanceof Student;
        if (this instanceof Handwritten && isStudent) {
            Main.ow.writeOutput("You cannot borrow this book!");
            return;
        }
        if (!isStudent) {
            Academic academic = (Academic) member;
            if (academic.getBooks().size() >= academic.getMAX_BOOKS()) {
                Main.ow.writeOutput("You have exceeded the borrowing limit!");
                return;
            }
        } else {
            Student student = (Student) member;
            if (student.getBooks().size() >= student.getMAX_BOOKS()) {
                Main.ow.writeOutput("You have exceeded the borrowing limit!");
                return;
            }
        }
        this.borrowed = true;
        this.borrowDate = (Calendar) calendar.clone();
        this.returnDate = (Calendar) calendar.clone();
        this.returnDate.add(Calendar.DATE, member.getTIME_LIMIT());
        if (isStudent) {
            ((Student) member).addBook(this.id);
        } else {
            ((Academic) member).addBook(this.id);
        }
        setReadingMemberId(memberId);
        Main.ow.writeOutput("The book [" + this.id + "] was borrowed by member [" + member.getId() + "] at " + date);
    }

    /**
     * Method for returning a book. Takes in a member ID and a date, and sets the book's borrowed status, borrow date, return date, and reading status accordingly. 
     * Also removes the book from the member's list of borrowed books.
     * @param memberId the ID of the member returning the book
     * @param date the date the book is being returned
     */
    public final void returnBook(int memberId, String date) {
        if ((!this.borrowed) && (!this.reading)) {
            Main.ow.writeOutput("You cannot return this book!");
            return;
        }
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(SDF.parse(date));
        } catch (Exception e) {
            Main.ow.writeOutput("Invalid date format.");
            return;
        }
        int fee = 0;
        if (calendar.after(this.returnDate)) {
            fee = (int) ((calendar.getTimeInMillis() - this.returnDate.getTimeInMillis()) / (1000 * 60 * 60 * 24));
        }
        Member member = Library.getMemberById(memberId);
        if (member == null) {
            Main.ow.writeOutput("Member not found!");
            return;
        }
        this.borrowed = false;
        this.borrowDate = null;
        this.returnDate = null;
        this.reading = false;
        this.readingDate = null;
        this.extended = false;
        setReadingMemberId(-1);
        member.removeBook(this.id);
        Main.ow.writeOutput("The book [" + this.id + "] was returned by member [" + member.getId() + "] at " + date + " Fee: " + fee);
    }

    /**
     * Method for extending the borrowing deadline of a book. Takes in a member ID and a date, and extends the book's return date accordingly. 
     * Can only be done once per book borrowing.
     * @param memberId the ID of the member extending the deadline
     * @param date the date the deadline is being extended
     */
    public final void extendBook(int memberId, String date) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(SDF.parse(date));
        } catch (Exception e) {
            Main.ow.writeOutput("Invalid date format.");
            return;
        }
        if ((!this.borrowed) && (!this.reading) && (this.extended)) {
            Main.ow.writeOutput("You cannot extend the deadline!");
            return;
        }
        Member member = Library.getMemberById(memberId);
        if (member == null) {
            Main.ow.writeOutput("Member not found!");
            return;
        }
        returnDate.add(Calendar.DATE, member.getTIME_LIMIT());
        this.extended = true;
        Main.ow.writeOutput("The deadline of book [" + this.id + "] was extended by member [" + member.getId() + "] at " + date);
        Main.ow.writeOutput("New deadline of book [" + this.id + "] is "+ SDF.format(returnDate.getTime()));
    }

    /**
     * Method for reading a book in the library. 
     * Takes in a member ID and a date, and sets the book's reading status and reading date accordingly. 
     * Can only be done if the book is not currently borrowed.
     * @param memberId the ID of the member reading the book
     * @param date the date the book is being read
     */
    public final void readInLibrary(int memberId, String date) {
        if (this.borrowed) {
            Main.ow.writeOutput("You can not read this book!");
            return;
        }
        Member member = Library.getMemberById(memberId);
        if (member == null) {
            Main.ow.writeOutput("Member not found!");
            return;
        }
        Book book = Library.getBookById(this.id);
        if (book == null) {
            Main.ow.writeOutput("Book not found!");
            return;
        }
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(SDF.parse(date));
        } catch (Exception e) {
            Main.ow.writeOutput("Invalid date format.");
            return;
        }
        if (member instanceof Student && book instanceof Handwritten) {
            Main.ow.writeOutput("Students can not read handwritten books!");
            return;
        }
        if (this.reading) {
            Main.ow.writeOutput("You can not read this book!");
            return;
        }
        this.reading = true;
        this.readingDate = (Calendar) calendar.clone();
        setReadingMemberId(memberId);
        Main.ow.writeOutput("The book [" + this.id + "] was read in library by member [" + member.getId() + "] at " + date);
    }
    
    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReadingMemberId() {
        return readingMemberId;
    }

    public void setReadingMemberId(int readingMemberId) {
        this.readingMemberId = readingMemberId;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Book.nextId = nextId;
    }

    public boolean isBorrowable() {
        return borrowable;
    }

    public void setBorrowable(boolean borrowable) {
        this.borrowable = borrowable;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public boolean isReading() {
        return reading;
    }

    public void setReading(boolean reading) {
        this.reading = reading;
    }

    public boolean isExtended() {
        return extended;
    }

    public void setExtended(boolean extended) {
        this.extended = extended;
    }

    public Calendar getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Calendar borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Calendar getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Calendar returnDate) {
        this.returnDate = returnDate;
    }

    public Calendar getReadingDate() {
        return readingDate;
    }

    public void setReadingDate(Calendar readingDate) {
        this.readingDate = readingDate;
    }
}
