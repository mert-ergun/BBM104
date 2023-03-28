import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

// The second list class. (8., 9., 10)

class Method_List_8_9_10 {

    LinkedHashMap<String, Film> all_films;
    LinkedHashMap<String, FeatureFilm> feature_films;
    String command;
    String output;
    String country;
    int year;
    String which;
    String separating = "-----------------------------------------------------------------------------------------------------";

    // COMMAND 8

    Method_List_8_9_10 (LinkedHashMap<String, Film> all_films, String output, String command, String country) throws IOException {
        this.all_films = all_films;
        this.output = output;
        this.command = command;
        this.country = country;

        // This method prints all films which is from given country.

        FileWriter write_output = new FileWriter(this.output, true);
        BufferedWriter output_file = new BufferedWriter(write_output);
        output_file.append(this.command + "\n");

        int counter = 0;

        for (Map.Entry set: this.all_films.entrySet()) {
            Film film = (Film) set.getValue();

            if (film.country.equals(this.country)) {
                output_file.append("\nFilm title: " + film.title + "\n" + film.length + " min\nLanguage: " + film.language + "\n");
                counter += 1;
            }
        }

        if (counter == 0) {
            output_file.append("\nNO result\n");
        }

        output_file.append("\n" + separating + "\n");
        output_file.close();
        write_output.close();
    }

    // COMMAND 9 - 10

    Method_List_8_9_10 (LinkedHashMap<String, FeatureFilm> feature_films, String output, String command, String which, int year) throws IOException {
        this.feature_films = feature_films;
        this.output = output;
        this.command = command;
        this.which = which;
        this.year = year;

        // This method prints all feature films by looking their release date. If the command is before, prints films which made before,
        // else, prints film which made after.

        FileWriter write_output = new FileWriter(this.output, true);
        BufferedWriter output_file = new BufferedWriter(write_output);
        output_file.append(this.command + "\n");

        int counter = 0;

        if (which.equals("BEFORE")) {
            for (Map.Entry set: this.feature_films.entrySet()) {
                FeatureFilm film = (FeatureFilm) set.getValue();

                int film_year = Integer.parseInt(film.release_date.substring(6,10));

                if (this.year >= film_year) {
                    output_file.append("\nFilm title: " + film.title + " (" + film_year + ")");
                    output_file.append("\n" + film.length + " min\nLanguage: " + film.language + "\n\n");
                    counter += 1;
                }
            }
        }

        else if (which.equals("AFTER")) {
            for (Map.Entry set: this.feature_films.entrySet()) {
                FeatureFilm film = (FeatureFilm) set.getValue();

                int film_year = Integer.parseInt(film.release_date.substring(6,10));

                if (this.year <= film_year) {
                    output_file.append("\nFilm title: " + film.title + " (" + film_year + ")");
                    output_file.append("\n" + film.length + " min\nLanguage: " + film.language + "\n\n");
                    counter += 1;
                }
            }
        }

        if (counter == 0) {
            output_file.append("\nNo result\n");
        }

        output_file.append("\n" + separating + "\n");
        output_file.close();
        write_output.close();
    }
}
