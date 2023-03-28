import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

// Adder Class (2. Method)

class Method_Add {

    LinkedHashMap<String, People> all_people;
    LinkedHashMap<String, Film> films;
    LinkedHashMap<String, FeatureFilm> feature_films;
    String output;
    String command;
    String separating = "-----------------------------------------------------------------------------------------------------";

    Method_Add (LinkedHashMap<String, People> all_people, LinkedHashMap<String, Film> films, LinkedHashMap<String,
            FeatureFilm> feature_films, String output, String command) {
        this.all_people = all_people;
        this.films = films;
        this.feature_films = feature_films;
        this.output = output;
        this.command = command;
    }

    void adder (String id, String title, String language, String directors, String length, String country,
                      String performers, String genres, String release_date, String writers, String budget) throws IOException {

        String[] directors_id = directors.split(",");
        String[] performers_id = performers.split(",");
        String[] writers_id = writers.split(",");

        ArrayList<String[]> humans = new ArrayList<>();
        humans.add(directors_id);
        humans.add(performers_id);
        humans.add(writers_id);

        // This method looks films, if there is no any error, the method will add new film.

        boolean checking = false;

        for (int i = 0; i < 3; i++) {
            for (int m = 0; m < humans.get(i).length; m++) {
                if (!all_people.containsKey(humans.get(i)[m])) {
                    checking = true;
                }
            }
        }

        FileWriter write_output = new FileWriter(output, true);
        BufferedWriter output_file = new BufferedWriter(write_output);
        output_file.append(command + "\n");

        if (checking || feature_films.containsKey(id)) {
            output_file.append("\nCommand Failed\nFilm ID:" + id + "\nFilm title: " + title);
        }

        else {
            FeatureFilm feature_film = new FeatureFilm(id, title, language,directors_id, length, country, performers_id,
                                                        genres.split(","),release_date, writers_id, budget);
            feature_films.put(feature_film.id, feature_film);
            films.put(feature_film.id, feature_film);
            output_file.append("\nFeatureFilm added successfully\nFilm ID:" + id + "\nFilm title: " + films.get(id).title + "\n");
        }

        output_file.append("\n" + separating + "\n");
        output_file.close();
        write_output.close();
    }
}
