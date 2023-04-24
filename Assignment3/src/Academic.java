import java.util.ArrayList;

public class Academic extends Member {
    public final int MAX_BOOKS = 4;
    private ArrayList<Integer> books = new ArrayList<Integer>(MAX_BOOKS);

    public Academic() {
        super();
    }

    public ArrayList<Integer> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Integer> books) {
        this.books = books;
    }

    public void addBook(int id) {
        books.add(id);
    }
}
