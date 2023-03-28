import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    // Read commands from "command.txt" file, commands can be: "read _articlefilename_", "list", "completeAll", "del _authorID_", "sortedAll"
    // "read _articlefilename_" command: read article data from _articlefilename_ and store it in memory
    // "list" command: list all authors and their articles
    // "completeAll" command: complete all authors' articles
    // "del_authorID_" command: delete author without deleting his/her articles
    // "sortedAll" command: sort all authors' articles by article ID

    public static void main(String[] args) throws IOException {
        // Read commands from "command.txt" file

        BufferedReader br = new BufferedReader(new FileReader("command.txt"));
        String line;
        Article[] articles = null;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(" ");
            if (data[0].equals("read")) {
                // Read article data from _articlefilename_ and add them to the articles array's end without deleting the previous articles and overwrite the articles array in memory
                if (articles != null) {
                    Article[] newArticles = new Article[articles.length + Article.getArticleData(data[1]).length];
                    for (int i = 0; i < articles.length; i++) {
                        newArticles[i] = articles[i];
                    }
                    for (int i = articles.length; i < newArticles.length; i++) {
                        newArticles[i] = Article.getArticleData(data[1])[i - articles.length];
                    }
                    articles = newArticles;
                } else {
                    articles = Article.getArticleData(data[1]);
                }
            } else if (data[0].equals("list")) {
                // List all authors and their articles
                Author[] authors = Author.getAuthorData();
            } else if (data[0].equals("completeAll")) {
                // Complete all authors' articles
            } else if (data[0].equals("del")) {
                // Delete author without deleting his/her articles
            } else if (data[0].equals("sortedAll")) {
                // Sort all authors' articles by article ID
            }
        }
    }

    
}
