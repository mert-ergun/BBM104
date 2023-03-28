import java.util.ArrayList;
import java.util.Collections;

public class Author {

    private int id;
    private String name;
    private String university;
    private String department;
    private String email;
    private ArrayList<Integer> articles = new ArrayList<Integer>();

    Author (int id, String name, String university, String department, String email) {
        this.id = id;
        this.name = name;
        this.university = university;
        this.department = department;
        this.email = email;
    }

    Author (int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Integer> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Integer> articles) {
        this.articles = articles;
    }

    public void add (int article_id) {
        articles.add(article_id);
    }

    public void sort () {
        Collections.sort(articles);
    }
}