import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{

        String author = "author.txt";
        String commands = "command.txt";

        ArrayList<Author> authors = new ArrayList<Author>();

        FileReader reader = new FileReader(author);
        BufferedReader author_file = new BufferedReader(reader);

        String lines = author_file.readLine();
        int index = 0;

        while (lines != null) {
            String[] data = lines.split(" ");

            if (data.length > 2) {
                authors.add(new Author(Integer.parseInt(data[1]), data[2], data[3], data[4], data[5]));

                for (int i = 6; i < data.length; i++) {
                    authors.get(index).add(Integer.parseInt(data[i]));
                }
            }

            else {
                authors.add(new Author(Integer.parseInt(data[1])));
            }

            lines = author_file.readLine();
            index +=1;

        }
        author_file.close();
        reader.close();

        read_commands(commands, authors);

    }

    public static void read_commands (String commands, ArrayList<Author> authors) throws Exception {

        FileWriter writer = new FileWriter("output.txt", false);
        BufferedWriter output_file = new BufferedWriter(writer);
        output_file.append(" ");
        output_file.close();
        writer.close();

        HashMap<Integer, String> article_archive = new HashMap<Integer, String>();
        ArrayList<Integer> articles_id = new ArrayList<Integer>();

        FileReader reader = new FileReader(commands);
        BufferedReader command_file = new BufferedReader(reader);

        String lines = command_file.readLine();
        String[] command_list = new String[2];
        while (lines != null) {
            command_list = lines.split(" ");

            if (command_list[0].equals("read"))
                read_articles(command_list[1], article_archive, articles_id);

            else if (command_list[0].equals("list"))
                writer_all(authors, article_archive);

            else if (command_list[0].equals("completeAll"))
                completer(authors, article_archive, articles_id);

            else if (command_list[0].equals("sortedAll"))
                sorter(authors);

            else if (command_list[0].equals("del"))
                remover(authors, Integer.parseInt(command_list[1]));

            lines = command_file.readLine();
        }

        command_file.close();
        reader.close();
    }

    public static void read_articles (String article_document, HashMap<Integer, String> article_archive, ArrayList<Integer> articles_id) throws Exception {
        FileReader reader = new FileReader(article_document);
        BufferedReader article_file = new BufferedReader(reader);

        String lines = article_file.readLine();
        String details;
        String[] details_array;
        int id_article;

        while (lines != null) {
            id_article = Integer.parseInt(lines.substring(8,15));
            details = lines.substring(16);
            details_array = details.split( " ");
            details = details_array[0] + "\t" + details_array[1] + "\t" + details_array[2];
            articles_id.add(id_article);
            article_archive.put(id_article, details);
            lines = article_file.readLine();
        }

        reader.close();
        article_file.close();
    }

    public static void writer_all (ArrayList<Author> authors, HashMap<Integer, String> article_archive) throws IOException {
        FileWriter writer = new FileWriter("output.txt", true);
        BufferedWriter output_file = new BufferedWriter(writer);

        output_file.append("------------------------------------------------List------------------------------------------------\n\n");
        int index_article;
        ArrayList<Integer> arts;

        for (int number = 0; number < authors.size(); number++) {
            Author author = authors.get(number);

            if (author.getName() == null) {
                output_file.append("Author:" + author.getId() + "\n");
            }

            else {
                output_file.append("Author:" + author.getId() + "\t" + author.getName() + "\t");
                output_file.append(author.getUniversity() + "\t" + author.getDepartment() + "\t" + author.getEmail() + "\n");            }


            arts = author.getArticles();
            for (int number_a = 0; number_a < arts.size(); number_a++) {
                output_file.append("+" + arts.get(number_a) + "\t" + article_archive.get(arts.get(number_a)) + "\n");
            }

            output_file.append("\n");

        }

        output_file.append("-------------------------------------------------End------------------------------------------------\n\n");
        output_file.close();
        writer.close();
    }

    public static void completer (ArrayList<Author> authors, HashMap<Integer, String> article_archive, ArrayList<Integer> articles_id) throws IOException {

        Author author;
        ArrayList<Integer> list;

        for (int i = 0; i < authors.size(); i++) {

            author = authors.get(i);
            list = author.getArticles();

            int id_number = author.getId();
            int index = 0;

            while (list.size() < 5) {

                if (articles_id.get(index) / 10000 == author.getId() && !list.contains(articles_id.get(index))) {
                    list.add(articles_id.get(index));
                }

                index +=1;

                if (index == articles_id.size())
                    break;
            }
        }

        FileWriter writer = new FileWriter("output.txt", true);
        BufferedWriter output_file = new BufferedWriter(writer);

        output_file.append("***************************************CompleteAll Successful***************************************\n\n");
        output_file.close();
        writer.close();
    }

    public static void sorter (ArrayList<Author> authors) throws IOException {
        for (int i = 0; i < authors.size(); i++) {
            authors.get(i).sort();
        }

        FileWriter writer = new FileWriter("output.txt", true);
        BufferedWriter output_file = new BufferedWriter(writer);

        output_file.append("****************************************SortedAll Successful****************************************\n\n");
        output_file.close();
        writer.close();
    }

    public static void remover (ArrayList<Author> authors, int author_del) throws IOException {
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).getId() == author_del) {
                authors.remove(i);
                break;
            }
        }

        FileWriter writer = new FileWriter("output.txt", true);
        BufferedWriter output_file = new BufferedWriter(writer);

        output_file.append("*******************************************del Successful*******************************************\n\n");
        output_file.close();
        writer.close();
    }
}