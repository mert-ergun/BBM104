import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

// The Viewer class.

class Method_View_Film {

    LinkedHashMap<String, People> humans;
    LinkedHashMap<String, Director> directors_map;
    LinkedHashMap<String, User> users;
    LinkedHashMap<String, Writer> writers_map;

    LinkedHashMap<String, Film> all_films;
    LinkedHashMap<String, FeatureFilm> feature_films;
    LinkedHashMap<String, ShortFilm> short_films;
    LinkedHashMap<String, TVSeries> tv_series;
    LinkedHashMap<String, Documentary> documentaries;
    String output;
    String command;
    String separating = "-----------------------------------------------------------------------------------------------------";

    Method_View_Film (    LinkedHashMap<String, Director> directors_map, LinkedHashMap<String, People> humans,
                          LinkedHashMap<String, User> users, LinkedHashMap<String, Writer> writers_map, LinkedHashMap<String, Film> all_films,
                          LinkedHashMap<String, FeatureFilm> feature_films, LinkedHashMap<String, ShortFilm> short_films,
                          LinkedHashMap<String, TVSeries> tv_series, LinkedHashMap<String, Documentary> documentaries,
                          String command, String output) {

        this.directors_map = directors_map;
        this.users = users;
        this.writers_map = writers_map;
        this.all_films = all_films;
        this.command = command;
        this.output = output;
        this.feature_films = feature_films;
        this.short_films = short_films;
        this.documentaries = documentaries;
        this.tv_series = tv_series;
        this.humans = humans;
    }

    void writer (String film_id) throws IOException {

        // This method prints all informations of given film.

        FileWriter write_output = new FileWriter(output, true);
        BufferedWriter output_file = new BufferedWriter(write_output);
        output_file.append(command + "\n");

        DecimalFormat decimal = new DecimalFormat("#.#");

        int user_number = 0;
        double rating_point = 0;

        for (Map.Entry _user_ : users.entrySet()) {
            User user = (User) _user_.getValue();

            if (user.ratings.containsKey(film_id)) {
                user_number += 1;
                rating_point += user.ratings.get(film_id);
            }
        }

        if (!all_films.containsKey(film_id)) {
            output_file.append("\nCommand failed.\nFilm ID: " + film_id);
        }

        else {

            if (all_films.get(film_id).type == "FeatureFilm") {
                output_file.append("\n" + feature_films.get(film_id).title + " (" + feature_films.get(film_id).release_date.substring(6,10) + ")\n");

                for (int m = 0; m < feature_films.get(film_id).genre.length; m++) {
                    output_file.append(feature_films.get(film_id).genre[m]);

                    if (feature_films.get(film_id).genre.length > m + 1) {
                        output_file.append(", ");
                    }
                }

                output_file.append("\nWriters: ");

                for (int m = 0; m < feature_films.get(film_id).writers.length; m++) {

                    String id = feature_films.get(film_id).writers[m];
                    output_file.append(writers_map.get(id).name + " " + writers_map.get(id).surname);

                    if (feature_films.get(film_id).writers.length > m + 1) {
                        output_file.append(", ");
                    }
                }

                output_file.append("\nDirectors: ");

                for (int m = 0; m < feature_films.get(film_id).directors.length; m++) {

                    String id = feature_films.get(film_id).directors[m];
                    output_file.append(directors_map.get(id).name + " " + directors_map.get(id).surname);

                    if (feature_films.get(film_id).directors.length > m + 1) {
                        output_file.append(", ");
                    }
                }

                output_file.append("\nStars: ");

                for (int m = 0; m < feature_films.get(film_id).performers.length; m++) {

                    String id = feature_films.get(film_id).performers[m];
                    output_file.append(humans.get(id).name + " " + humans.get(id).surname);

                    if (feature_films.get(film_id).performers.length > m + 1) {
                        output_file.append(", ");
                    }
                }

                if (rating_point == 0) {
                     output_file.append("\nAwaiting for votes\n");
                }

                else {
                    double rating = rating_point / user_number;
                    output_file.append("\nRatings: " + decimal.format(rating) + "/10 from " + user_number + " users.\n");
                }


            }

            else if (all_films.get(film_id).type == "ShortFilm") {

                output_file.append("\n" + short_films.get(film_id).title + " (" + short_films.get(film_id).release_date.substring(6,10) + ")\n");

                for (int m = 0; m < short_films.get(film_id).genre.length; m++) {
                    output_file.append(short_films.get(film_id).genre[m]);

                    if (short_films.get(film_id).genre.length > m + 1) {
                        output_file.append(", ");
                    }
                }

                output_file.append("\nWriters: ");

                for (int m = 0; m < short_films.get(film_id).writers.length; m++) {

                    String id = short_films.get(film_id).writers[m];
                    output_file.append(writers_map.get(id).name + " " + writers_map.get(id).surname);

                    if (short_films.get(film_id).writers.length > m + 1) {
                        output_file.append(", ");
                    }
                }

                output_file.append("\nDirectors: ");

                for (int m = 0; m < short_films.get(film_id).directors.length; m++) {

                    String id = short_films.get(film_id).directors[m];
                    output_file.append(directors_map.get(id).name + " " + directors_map.get(id).surname);

                    if (short_films.get(film_id).directors.length > m + 1) {
                        output_file.append(", ");
                    }
                }

                output_file.append("\nStars: ");

                for (int m = 0; m < short_films.get(film_id).performers.length; m++) {

                    String id = short_films.get(film_id).performers[m];
                    output_file.append(humans.get(id).name + " " + humans.get(id).surname);

                    if (short_films.get(film_id).performers.length > m + 1) {
                        output_file.append(", ");
                    }
                }

                if (rating_point == 0) {
                    output_file.append("\nAwaiting for votes\n");
                }

                else {
                    double rating = rating_point / user_number;
                    output_file.append("\nRatings: " + decimal.format(rating) + "/10 from " + user_number + " users.\n");
                }

            }

            else if (all_films.get(film_id).type == "Documentary") {

                output_file.append("\n" + documentaries.get(film_id).title + " (" + documentaries.get(film_id).release_date.substring(6,10) + ")\n");

                output_file.append("\nDirectors: ");

                for (int m = 0; m < documentaries.get(film_id).directors.length; m++) {

                    String id = documentaries.get(film_id).directors[m];
                    output_file.append(directors_map.get(id).name + " " + directors_map.get(id).surname);

                    if (documentaries.get(film_id).directors.length > m + 1) {
                        output_file.append(", ");
                    }
                }

                output_file.append("\nStars: ");

                for (int m = 0; m < documentaries.get(film_id).performers.length; m++) {

                    String id = documentaries.get(film_id).performers[m];
                    output_file.append(humans.get(id).name + " " + humans.get(id).surname);

                    if (documentaries.get(film_id).performers.length > m + 1) {
                        output_file.append(", ");
                    }
                }

                if (rating_point == 0) {
                    output_file.append("\nAwaiting for votes\n");
                }

                else {
                    double rating = rating_point / user_number;
                    output_file.append("\nRatings: " + decimal.format(rating) + "/10 from " + user_number + " users.\n");
                }


            }

            else {

                output_file.append("\n" + tv_series.get(film_id).title + " (" + tv_series.get(film_id).start_date.substring(6,10) +
                        " - " + tv_series.get(film_id).end_date.substring(6,10) + "\n");

                output_file.append("\n" + tv_series.get(film_id).seasons + " seasons, " + tv_series.get(film_id).episodes + " episodes" + "\n");

                for (int m = 0; m < tv_series.get(film_id).genre.length; m++) {
                    output_file.append(tv_series.get(film_id).genre[m]);

                    if (tv_series.get(film_id).genre.length > m + 1) {
                        output_file.append(", ");
                    }
                }

                output_file.append("\nWriters: ");

                for (int m = 0; m < tv_series.get(film_id).writers.length; m++) {

                    String id = tv_series.get(film_id).writers[m];
                    output_file.append(writers_map.get(id).name + " " + writers_map.get(id).surname);

                    if (tv_series.get(film_id).writers.length > m + 1) {
                        output_file.append(", ");
                    }
                }

                output_file.append("\nDirectors: ");

                for (int m = 0; m < tv_series.get(film_id).directors.length; m++) {

                    String id = tv_series.get(film_id).directors[m];
                    output_file.append(directors_map.get(id).name + " " + directors_map.get(id).surname);

                    if (tv_series.get(film_id).directors.length > m + 1) {
                        output_file.append(", ");
                    }
                }

                output_file.append("\nStars: ");

                for (int m = 0; m < tv_series.get(film_id).performers.length; m++) {

                    String id = tv_series.get(film_id).performers[m];
                    output_file.append(humans.get(id).name + " " + humans.get(id).surname);

                    if (tv_series.get(film_id).performers.length > m + 1) {
                        output_file.append(", ");
                    }
                }

                if (rating_point == 0) {
                    output_file.append("\nAwaiting for votes\n");
                }

                else {
                    double rating = rating_point / user_number;
                    output_file.append("\nRatings: " + decimal.format(rating) + "/10 from " + user_number + " users.\n");
                }
            }
        }

        output_file.append("\n" + separating + "\n");
        output_file.close();
        write_output.close();
    }
}
