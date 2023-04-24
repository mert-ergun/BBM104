public class Printed extends Book {
    public Printed() {
        super();
        super.setBorrowable(true);
    }
    
    public static void addBook() {
        Printed book = new Printed();
        Library.books.add(book);
        Main.ow.writeOutput("Created new book: Printed [id: " + book.getId() + "]");
    }
}
