public class Handwritten extends Book {
    public Handwritten() {
        super();
        super.setBorrowable(false);
    }

    public static void addBook() {
        Handwritten book = new Handwritten();
        Library.books.add(book);
        Main.ow.writeOutput("Created new book: Handwritten [id: " + book.getId() + "]");
    }
}
