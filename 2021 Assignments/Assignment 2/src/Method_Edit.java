import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;

// The Editor Class

class Method_Edit {

    LinkedHashMap<String, User> users;
    LinkedHashMap<String, Film> all_films;
    String command;
    String output;
    String separating = "-----------------------------------------------------------------------------------------------------";

    Method_Edit (LinkedHashMap<String, User> users, LinkedHashMap<String, Film> all_films,
                 String output, String command) {
        this.users = users;
        this.all_films = all_films;
        this.output = output;
        this.command = command;
    }

    void editor (String user_id, String film_id, int new_point) throws IOException {

        FileWriter write_output = new FileWriter(output, true);
        BufferedWriter output_file = new BufferedWriter(write_output);

        if (new_point > 10 || new_point < 1) {
            System.out.println("Rating point must be between 1 - 10.");
            output_file.close();
            write_output.close();
        }

        // This method looks user's ratings. If there is no error, the method will change rating with new value.

        output_file.append(command + "\n");

        if (users.containsKey(user_id) && all_films.containsKey(film_id)) {

            if (users.get(user_id).ratings.containsKey(film_id)) {
                users.get(user_id).ratings.replace(film_id, new_point);

                output_file.append("\nNew writing done successfully");
                output_file.append("\nFilm title: " + all_films.get(film_id).title);
                output_file.append("\nYour rating: " + new_point + "\n");
            }

            else {
                output_file.append("Command Failed\nUser ID: " + user_id + "\nFilm ID: " + film_id + "\n");
            }
        }

        else {
            output_file.append("Command Failed\nUser ID: " + user_id + "Film ID: " + film_id + "\n");
        }

        output_file.append("\n" + separating + "\n");
        output_file.close();
        write_output.close();
    }
}
