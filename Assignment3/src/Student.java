import java.util.ArrayList;

public class Student extends Member {
    public final int MAX_BOOKS = 2;
    private ArrayList<Integer> books = new ArrayList<Integer>(MAX_BOOKS);

    public Student() {
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

    public static void addMember() {
        Student member = new Student();
        Library.members.add(member);
        Main.ow.writeOutput("Created new member: Student [id: " + member.getId() + "]");
    }
}
