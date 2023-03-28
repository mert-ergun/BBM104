import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;

// The remover class.

class Method_Remove {

    LinkedHashMap<String, Film> all_films;
    LinkedHashMap<String, User> users;
    String command;
    String output;
    String separating = "-----------------------------------------------------------------------------------------------------";

    Method_Remove (LinkedHashMap<String, User> users, LinkedHashMap<String, Film> all_films,  String output, String command) {
        this.users = users;
        this.output = output;
        this.command = command;
        this.all_films = all_films;
    }

    void remover (String user_id, String film_id) throws IOException {

        // This method removes rating of given film from user.

        FileWriter write_output = new FileWriter(output, true);
        BufferedWriter output_file = new BufferedWriter(write_output);
        output_file.append(command + "\n");

        if (users.containsKey(user_id) && all_films.containsKey(film_id)) {

            if (users.get(user_id).ratings.containsKey(film_id)) {

                users.get(user_id).ratings.remove(film_id);
                output_file.append("\nYour film rating was removed successfully\n");
                output_file.append("Film ID: " + all_films.get(film_id).title + "\n");
            }

            else {
                output_file.append("Command Failed\nUser ID: " + user_id + "Film ID: " + film_id);
            }
        }

        else {
            output_file.append("Command Failed\nUser ID: " + user_id + "Film ID: " + film_id);
        }

        output_file.append("\n" + separating + "\n");
        output_file.close();
        write_output.close();

    }


}
