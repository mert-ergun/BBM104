import java.util.ArrayList;

/**
 * The Member abstract class represents a member in a system.
 * Each member has a unique ID assigned to them upon creation.
 * The ID is automatically incremented for each new member.
 * There will never be two members with the same ID.
 */
public abstract class Member {
    private int id;
    private static int nextId = 1;
    private ArrayList<Integer> books = new ArrayList<Integer>();

    public Member() {
        this.id = nextId++;
    }

    public static void addMember(){}
    public abstract int getTIME_LIMIT();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Integer> books) {
        this.books = books;
    }

    public void addBook(int bookId) {
        books.add(bookId);
    }

    public void removeBook(int bookId) {
        this.getBooks().remove((Integer) this.id);
    }       
}
