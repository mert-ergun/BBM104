import java.io.BufferedReader;
import java.io.FileReader;

public class InputReader {
    public static void readInput(String filename) {
        try {
            BufferedReader br = new BufferedReader(new BufferedReader(new FileReader(filename)));
            String line;
            String[] words;
            while ((line = br.readLine()) != null) {
                words = line.split("\t");
                switch (words[0]) {
                    case "addBook":
                        if (words[1].equals("P")) {
                            Printed.addBook();
                        }
                        if (words[1].equals("H")) {
                            Handwritten.addBook();
                        }
                        break;
                    case "addMember":
                        if (words[1].equals("S")) {
                            Student.addMember();
                        }
                        if (words[1].equals("A")) {
                            Academic.addMember();
                        }
                        break;
                    case "borrowBook":
                        Book book = Library.getBookById(Integer.parseInt(words[1]));
                        int memberId = Integer.parseInt(words[2]);
                        book.borrowBook(memberId, words[3]);
                        break;
                    case "readInLibrary":
                        book = Library.getBookById(Integer.parseInt(words[1]));
                        memberId = Integer.parseInt(words[2]);
                        book.readInLibrary(memberId, words[3]);
                        break;
                    case "returnBook":
                        book = Library.getBookById(Integer.parseInt(words[1]));
                        memberId = Integer.parseInt(words[2]);
                        book.returnBook(memberId, words[3]);
                        break;
                    case "extendBook":
                        book = Library.getBookById(Integer.parseInt(words[1]));
                        memberId = Integer.parseInt(words[2]);
                        book.extendBook(memberId, words[3]);
                        break;
                    case "getTheHistory":
                        Library.getHistory();
                        break;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
