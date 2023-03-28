import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Author {
    private String id;
    private String name;
    private String university;
    private String department;
    private String email;
    private String article1;
    private String article2;
    private String article3;
    private String article4;
    private String article5;

    public Author(String id, String name, String university, String department, String email, String article1, String article2, String article3, String article4, String article5) {
        this.id = id;
        this.name = name;
        this.university = university;
        this.department = department;
        this.email = email;
        this.article1 = article1;
        this.article2 = article2;
        this.article3 = article3;
        this.article4 = article4;
        this.article5 = article5;
    }

    //Get author data from "author.txt" file
    public static Author[] getAuthorData() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("author.txt"));
        String line;
        int i = 0;
        Author[] authors = new Author[100];
        while ((line = br.readLine()) != null) {
            String[] data = line.split(" ");
            // Author may have less than 5 articles
            if (data.length == 6) {
                authors[i] = new Author(data[1], data[2], data[3], data[4], data[5], null, null, null, null, null);
            } else if (data.length == 7) {
                authors[i] = new Author(data[1], data[2], data[3], data[4], data[5], data[6], null, null, null, null);
            } else if (data.length == 8) {
                authors[i] = new Author(data[1], data[2], data[3], data[4], data[5], data[6], data[7], null, null, null);
            } else if (data.length == 9) {
                authors[i] = new Author(data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], null, null);
            } else if (data.length == 10) {
                authors[i] = new Author(data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9], null);
            } else if (data.length == 11) {
                authors[i] = new Author(data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9], data[10]);
            }
            i++;
        }
        br.close();
        return authors;
    }
}
