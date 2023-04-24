import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class Book {
    private int id;
    private static int nextId = 1;
    private boolean borrowable;
    private boolean borrowed;
    private Calendar dueDate;
    private Calendar returnDate;
    private final int TIME_LIMIT_ACADEMIC = 14;
    private final int TIME_LIMIT_STUDENT = 7;
    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd"); 

    public Book() {
        this.id = nextId++;
    }

    public void borrowBook(int memberId, String date) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(SDF.parse(date));
        } catch (Exception e) {
            System.out.println("Invalid date format.");
            return;
        }
        if (this.borrowed) {
            System.out.println("You cannot borrow this book!");
            return;
        }
        Member member = Library.getMemberById(memberId);
        this.borrowed = true;
        this.dueDate = calendar;
        long returnInMillis = returnDate.getTimeInMillis();
        returnInMillis = dueDate.getTimeInMillis() + (member instanceof Academic ? TIME_LIMIT_ACADEMIC : TIME_LIMIT_STUDENT) * 24 * 60 * 60 * 1000;
        returnDate.setTimeInMillis(returnInMillis);
        if (member instanceof Academic) {
            Academic academic = (Academic) member;
            if (academic.getBooks().size() >= academic.MAX_BOOKS) {
                System.out.println("You cannot borrow this book!");
                return;
            }
            academic.addBook(this.id);
        } else if (member instanceof Student) {
            Student student = (Student) member;
            if (student.getBooks().size() >= student.MAX_BOOKS) {
                System.out.println("You cannot borrow this book!");
                return;
            }
            student.addBook(this.id);
        }
    }

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
}
