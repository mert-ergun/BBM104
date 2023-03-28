import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;

// The rater class.

class Method_Rating {

    String user_id;
    String film_id;
    int rating_point;
    String separating = "-----------------------------------------------------------------------------------------------------";
    String output;
    String command;

    Method_Rating (String user_id, String film_id, int rating_point, String output, String command) {
        this.user_id = user_id;
        this.film_id = film_id;
        this.rating_point = rating_point;
        this.command = command;
        this.output = output;
    }

    int rater (LinkedHashMap<String, Film> all_films, LinkedHashMap<String, User> users) throws IOException {

        // This method rates given film and record the given user's class.

        FileWriter write_output = new FileWriter(output, true);
        BufferedWriter output_file = new BufferedWriter(write_output);

        if (rating_point > 10 || rating_point < 1) {
            System.out.println("Rating point must be between 1 - 10.");
            output_file.close();
            write_output.close();
            return 0;
        }

        output_file.append(command + "\n");

        if (users.containsKey(user_id) && all_films.containsKey(film_id)) {

            if (users.get(user_id).ratings.containsKey(film_id)) {
                output_file.append("\n" + "This film was earlier rated" + "\n");
            }

            else {
                users.get(user_id).ratings.put(film_id, rating_point);
                output_file.append("\n" + "Film rated successfully" + "\n" + "Film type: " + all_films.get(film_id).type);
                output_file.append("\n" + "Film title: " + all_films.get(film_id).title + "\n");
            }
        }

        else {
            output_file.append("Command Failed\nUser ID: " + user_id + "\nFilm ID: " + film_id);
        }

        output_file.append("\n" + separating + "\n");
        output_file.close();
        write_output.close();
        return 0;
    }
}
