import java.io.BufferedReader;
import java.io.FileReader;

/**
 * The InputReader class provides a static method to read input from a file and parse it to execute library management system operations.
 * The file must contain a series of commands separated by newlines, with each command consisting of one or more whitespace-separated fields.
 * The commands and their corresponding fields are parsed and executed using the Library and Book classes.
 */
public class InputReader {

    /**
     * Reads the input from the specified file and executes the corresponding library management system operations.
     * @param filename the name of the input file to read
     */
    public static void readInput(String filename) {
        try {
            BufferedReader br = new BufferedReader(new BufferedReader(new FileReader(filename)));  // Create a BufferedReader to read the input file.
            String line;  
            String[] words;
            while ((line = br.readLine()) != null) {  // Read each line of the input file.
                words = line.split("\t");  // Split the line into an array of whitespace-separated fields.
                switch (words[0]) {
                    case "addBook":  // Add a new book to the library.
                        if (words[1].equals("P")) {
                            Printed.addBook();
                        }
                        if (words[1].equals("H")) {
                            Handwritten.addBook();
                        }
                        break;
                    case "addMember":  // Add a new member to the library.
                        if (words[1].equals("S")) {
                            Student.addMember();
                        }
                        if (words[1].equals("A")) {
                            Academic.addMember();
                        }
                        break;
                    case "borrowBook":  // Borrow a book.
                        Book book = Library.getBookById(Integer.parseInt(words[1]));
                        int memberId = Integer.parseInt(words[2]);
                        book.borrowBook(memberId, words[3]);
                        break;
                    case "readInLibrary":  // Read a book in the library.
                        book = Library.getBookById(Integer.parseInt(words[1]));
                        memberId = Integer.parseInt(words[2]);
                        book.readInLibrary(memberId, words[3]);
                        break;
                    case "returnBook":  // Return a book.
                        book = Library.getBookById(Integer.parseInt(words[1]));
                        memberId = Integer.parseInt(words[2]);
                        book.returnBook(memberId, words[3]);
                        break;
                    case "extendBook":  // Extend the due date of a book.
                        book = Library.getBookById(Integer.parseInt(words[1]));
                        memberId = Integer.parseInt(words[2]);
                        book.extendBook(memberId, words[3]);
                        break;
                    case "getTheHistory":  // Get the history of a book.
                        Library.getHistory();
                        break;
                }
            }
            br.close();  // Close the BufferedReader.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
