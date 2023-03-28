import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

// The first list class. ( 4. and 7. )

class Method_List_4_7 {

    LinkedHashMap<String, User> users;
    LinkedHashMap<String, TVSeries> tv_series;
    LinkedHashMap<String, Film> all_films;
    String command;
    String output;
    String user_id;
    String separating = "-----------------------------------------------------------------------------------------------------";

    // COMMAND 4

    Method_List_4_7 (LinkedHashMap<String, Film> all_films,LinkedHashMap<String, User> users, String output, String command, String user_id) throws IOException {
        this.all_films = all_films;
        this.users = users;
        this.output = output;
        this.command = command;
        this.user_id = user_id;

        // This method prints all films with their degree which user rated before.

        FileWriter write_output = new FileWriter(this.output, true);
        BufferedWriter output_file = new BufferedWriter(write_output);
        output_file.append(this.command + "\n");

        if (!this.users.containsKey(user_id)) {
            output_file.append("\nCommand Failed\nUser ID: " + user_id + "\n");
        }

        else if (this.users.get(user_id).ratings.size() == 0) {
            output_file.append("There is not any ratings so far.\n");
        }

        else {
            for (Map.Entry<String, Integer> set : this.users.get(user_id).ratings.entrySet()) {
                String id = set.getKey();
                int point = set.getValue();
                output_file.append("\n" + this.all_films.get(id).title + ": " + point);
            }
        }

        output_file.append("\n\n" + separating + "\n");
        output_file.close();
        write_output.close();
    }

    // COMMAND 7

    Method_List_4_7 (LinkedHashMap<String, TVSeries> tv_series, String output, String command) throws IOException {
        this.tv_series = tv_series;
        this.output = output;
        this.command = command;

        FileWriter write_output = new FileWriter(this.output, true);
        BufferedWriter output_file = new BufferedWriter(write_output);
        output_file.append(this.command + "\n");


        // This method prints all series in file.

        if (tv_series.size() == 0) {
            output_file.append("\nNo result\n");
        }

        else {
            for (Map.Entry set: this.tv_series.entrySet()) {
                TVSeries tv = (TVSeries) set.getValue();

                output_file.append("\n" + tv.title + " (" + tv.start_date + "-" + tv.end_date + ")");
                output_file.append("\n" + tv.seasons + " seasons and " + tv.episodes + " episodes\n");

            }
        }

        output_file.append("\n" + separating + "\n");
        output_file.close();
        write_output.close();
    }
}
