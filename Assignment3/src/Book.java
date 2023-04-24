import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The Book class represents a book in the library system. 
 * It contains information about the book's ID, borrowable status, borrowed status, reading status, borrow date, return date, reading date, reading member ID, and time limits for borrowing. 
 * It also provides methods for borrowing, returning, extending, and reading a book in the library.
 */
public abstract class Book {
    // Class variables
    private int id;
    private static int nextId = 1;
    private boolean borrowable;
    private boolean borrowed;
    private boolean reading;
    private boolean extended;
    private Calendar borrowDate;
    private Calendar returnDate;
    private Calendar readingDate;
    private int readingMemberId;
    private final int TIME_LIMIT_ACADEMIC = 14;
    private final int TIME_LIMIT_STUDENT = 7;
    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");  // Date format for the library system

    /**
     * Constructor for objects of class Book with no parameters.
     * Initializes the book's ID.
     */
    public Book() {
        this.id = nextId++;
    }

    /**
     * Method for borrowing a book. Takes in a member ID and a date, and sets the book's borrow date, return date, and borrowed status accordingly. 
     * Also adds the book to the member's list of borrowed books.
     * @param memberId the ID of the member borrowing the book
     * @param date the date the book is being borrowed
     */
    public void borrowBook(int memberId, String date) {
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
        boolean isAcademic = false;
        if (member instanceof Academic) {
            Academic academic = (Academic) member;
            isAcademic = true;
            if (academic.getBooks().size() >= academic.MAX_BOOKS) {
                Main.ow.writeOutput("You have exceeded the borrowing limit!");
                return;
            }
        }
        else if (member instanceof Student) {
            Student student = (Student) member;
            isAcademic = false;
            if (student.getBooks().size() >= student.MAX_BOOKS) {
                Main.ow.writeOutput("You have exceeded the borrowing limit!");
                return;
            }
        }
        this.borrowed = true;
        this.borrowDate = calendar;
        this.returnDate = Calendar.getInstance();
        long returnInMillis = 0;
        returnInMillis = borrowDate.getTimeInMillis() + (member instanceof Academic ? TIME_LIMIT_ACADEMIC : TIME_LIMIT_STUDENT) * 24 * 60 * 60 * 1000;
        returnDate.setTimeInMillis(returnInMillis);
        if (isAcademic) {
            ((Academic) member).addBook(this.id);
        } else {
            ((Student) member).addBook(this.id);
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
    public void returnBook(int memberId, String date) {
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
            fee = (int) ((calendar.getTimeInMillis() - this.returnDate.getTimeInMillis()) / (24 * 60 * 60 * 1000));
        }
        Member member = Library.getMemberById(memberId);
        this.borrowed = false;
        this.borrowDate = null;
        this.returnDate = null;
        this.reading = false;
        if (member instanceof Academic) {
            Academic academic = (Academic) member;
            academic.getBooks().remove((Integer) this.id);
            setReadingMemberId(0);
        } else if (member instanceof Student) {
            Student student = (Student) member;
            student.getBooks().remove((Integer) this.id);
            setReadingMemberId(0);
        }
        Main.ow.writeOutput("The book [" + this.id + "] was returned by member [" + member.getId() + "] at " + date + " Fee: " + fee);
    }

    /**
     * Method for extending the borrowing deadline of a book. Takes in a member ID and a date, and extends the book's return date accordingly. 
     * Can only be done once per book borrowing.
     * @param memberId the ID of the member extending the deadline
     * @param date the date the deadline is being extended
     */
    public void extendBook(int memberId, String date) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(SDF.parse(date));
        } catch (Exception e) {
            Main.ow.writeOutput("Invalid date format.");
            return;
        }
        if (!this.borrowed || this.extended) {
            Main.ow.writeOutput("You cannot extend the deadline!");
            return;
        }
        Member member = Library.getMemberById(memberId);
        long returnInMillis = ((Calendar) returnDate.clone()).getTimeInMillis();
        returnInMillis += (member instanceof Academic ? TIME_LIMIT_ACADEMIC : TIME_LIMIT_STUDENT) * 24 * 60 * 60 * 1000;
        returnDate.setTimeInMillis(returnInMillis);
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
    public void readInLibrary(int memberId, String date) {
        if (this.borrowed) {
            Main.ow.writeOutput("You can not read this book!");
            return;
        }
        Member member = Library.getMemberById(memberId);
        Book book = Library.getBookById(this.id);
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
        this.reading = true;
        this.readingDate = calendar;
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

    public int getTIME_LIMIT_ACADEMIC() {
        return TIME_LIMIT_ACADEMIC;
    }

    public int getTIME_LIMIT_STUDENT() {
        return TIME_LIMIT_STUDENT;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
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

    public static SimpleDateFormat getSdf() {
        return SDF;
    }

    public int getReadingMemberId() {
        return readingMemberId;
    }

    public void setReadingMemberId(int readingMemberId) {
        this.readingMemberId = readingMemberId;
    }

    public boolean isReading() {
        return reading;
    }

    public void setReading(boolean reading) {
        this.reading = reading;
    }

    public Calendar getReadingDate() {
        return readingDate;
    }

    public void setReadingDate(Calendar readingDate) {
        this.readingDate = readingDate;
    }
}
