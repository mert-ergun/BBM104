import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Article {
    private String paperid;
    private String name;
    private String publisherName;
    private String publishYear;

    public Article(String paperid, String name, String publisherName, String publishYear) {
        this.paperid = paperid;
        this.name = name;
        this.publisherName = publisherName;
        this.publishYear = publishYear;
    }

    //Get article data from "article.txt" file
    public static Article[] getArticleData(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        int i = 0;
        Article[] articles = new Article[100];
        while ((line = br.readLine()) != null) {
            String[] data = line.split(" ");
            articles[i] = new Article(data[1], data[2], data[3], data[4]);
            i++;
        }
        br.close();
        return articles;
    }
        
}
