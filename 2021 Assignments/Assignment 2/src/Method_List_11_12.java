import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

// The third list class. (11. and 12. commands)

class Method_List_11_12 {

    LinkedHashMap<String, Film> all_films;
    LinkedHashMap<String, FeatureFilm> feature_films;
    LinkedHashMap<String, ShortFilm> short_films;
    LinkedHashMap<String, TVSeries> tv_series;
    LinkedHashMap<String, Documentary> documentaries;
    LinkedHashMap<String, User> users;
    LinkedHashMap<String, People> all_people;

    String command;
    String output;
    String country;
    String separating = "-----------------------------------------------------------------------------------------------------";

    // COMMAND 11

    Method_List_11_12 (     LinkedHashMap<String, Film> all_films, LinkedHashMap<String, FeatureFilm> feature_films, LinkedHashMap<String,
                            ShortFilm> short_films, LinkedHashMap<String, TVSeries> tv_series, LinkedHashMap<String,
                            Documentary> documentaries, LinkedHashMap<String, User> users, String command, String output) throws IOException {

        this.all_films = all_films;
        this.feature_films = feature_films;
        this.short_films = short_films;
        this.tv_series = tv_series;
        this.documentaries = documentaries;
        this.users = users;
        this.command = command;
        this.output = output;

        // This method prints all films in system in order by looking their average rating degree.

        LinkedHashMap<String, Film>[] films_all = new LinkedHashMap[]{this.feature_films, this.short_films, this.documentaries, this.tv_series };
        DecimalFormat decimal = new DecimalFormat("#.#");

        FileWriter write_output = new FileWriter(this.output, true);
        BufferedWriter output_file = new BufferedWriter(write_output);
        output_file.append(this.command + "\n");

        String[][] scores_feature = new String[this.feature_films.size()][2];
        HashMap<String, Integer> number_user_f = new HashMap<String, Integer>();

        int m = 0;

	// FEATURE FILM

        for (Map.Entry feature : this.feature_films.entrySet()) {
            String id = (String) feature.getKey();
            FeatureFilm film = (FeatureFilm) feature.getValue();

            int number = 0;
            double score = 0;

            for (Map.Entry _user_ : this.users.entrySet()) {
                User user = (User) _user_.getValue();

                if (user.ratings.containsKey(id)) {
                    number +=1;
                    score += user.ratings.get(id);
                }
            }

            if (!(number == 0)) {
                scores_feature[m][0] = decimal.format(score / number);
            }

            else {
                scores_feature[m][0] = "0";
            }
            scores_feature[m][1] = id;

            number_user_f.put(id, number);

            m +=1;
        }

        for (int x = 0; x < scores_feature.length - 1; x++) {
            for (int y = 0; y < scores_feature.length - 1 - x; y++) {
                if (scores_feature[y][0].compareTo(scores_feature[y + 1][0]) < 0) {
                    String f = scores_feature[y][0];
                    String s = scores_feature[y][1];
                    scores_feature[y][0] = scores_feature[y + 1][0];
                    scores_feature[y][1] = scores_feature[y + 1][1];
                    scores_feature[y + 1][0] = f;
                    scores_feature[y + 1][1] = s;
                }
            }
        }

        output_file.append("\nFeatureFilm:\n");

        for (int k = 0; k < scores_feature.length; k++) {
            FeatureFilm feature = this.feature_films.get(scores_feature[k][1]);
            output_file.append(feature.title + " (" + feature.release_date.substring(6,10) + ") Ratings: ");
            output_file.append(scores_feature[k][0] + "/10 from " + number_user_f.get(feature.id) + " Users\n");
        }

        // SHORT FILM

        String[][] scores_short = new String[this.short_films.size()][2];
        HashMap<String, Integer> number_user_s = new HashMap<String, Integer>();
        m = 0;

        for (Map.Entry short_film : this.short_films.entrySet()) {
            String id = (String) short_film.getKey();
            ShortFilm film = (ShortFilm) short_film.getValue();

            int number = 0;
            double score = 0;

            for (Map.Entry _user_ : this.users.entrySet()) {
                User user = (User) _user_.getValue();

                if (user.ratings.containsKey(id)) {
                    number +=1;
                    score += user.ratings.get(id);
                }
            }

            if (!(number == 0)) {
                scores_short[m][0] = decimal.format(score / number);
            }

            else {
                scores_short[m][0] = "0";
            }
            scores_short[m][1] = id;

            number_user_s.put(id, number);

            m +=1;
        }

        for (int x = 0; x < scores_short.length - 1; x++) {
            for (int y = 0; y < scores_short.length - 1 - x; y++) {
                if (scores_short[y][0].compareTo(scores_short[y + 1][0]) < 0) {
                    String f = scores_short[y][0];
                    String s = scores_short[y][1];
                    scores_short[y][0] = scores_short[y + 1][0];
                    scores_short[y][1] = scores_short[y + 1][1];
                    scores_short[y + 1][0] = f;
                    scores_short[y + 1][1] = s;
                }
            }
        }

        output_file.append("\nShortFilm:\n");

        for (int k = 0; k < scores_short.length; k++) {
            ShortFilm short_film = this.short_films.get(scores_short[k][1]);
            output_file.append(short_film.title + " (" + short_film.release_date.substring(6,10) + ") Ratings: ");
            output_file.append(scores_short[k][0] + "/10 from " + number_user_s.get(short_film.id) + " Users\n");
        }

        // DOCUMENTARY

        String[][] scores_doc = new String[this.documentaries.size()][2];
        HashMap<String, Integer> number_user_d = new HashMap<String, Integer>();
        m = 0;

        for (Map.Entry documentary : this.documentaries.entrySet()) {
            String id = (String) documentary.getKey();
            Documentary film = (Documentary) documentary.getValue();

            int number = 0;
            double score = 0;

            for (Map.Entry _user_ : this.users.entrySet()) {
                User user = (User) _user_.getValue();

                if (user.ratings.containsKey(id)) {
                    number +=1;
                    score += user.ratings.get(id);
                }
            }

            if (!(number == 0)) {
                scores_doc[m][0] = decimal.format(score / number);
            }

            else {
                scores_doc[m][0] = "0";
            }
            scores_doc[m][1] = id;

            number_user_d.put(id, number);

            m +=1;
        }

        for (int x = 0; x < scores_doc.length - 1; x++) {
            for (int y = 0; y < scores_doc.length - 1 - x; y++) {
                if (scores_doc[y][0].compareTo(scores_doc[y + 1][0]) < 0) {
                    String f = scores_doc[y][0];
                    String s = scores_doc[y][1];
                    scores_doc[y][0] = scores_doc[y + 1][0];
                    scores_doc[y][1] = scores_doc[y + 1][1];
                    scores_doc[y + 1][0] = f;
                    scores_doc[y + 1][1] = s;
                }
            }
        }

        output_file.append("\nDocumentary:\n");

        for (int k = 0; k < scores_doc.length; k++) {
            Documentary doc = this.documentaries.get(scores_doc[k][1]);
            output_file.append(doc.title + " (" + doc.release_date.substring(6,10) + ") Ratings: ");
            output_file.append(scores_doc[k][0] + "/10 from " + number_user_d.get(doc.id) + " Users\n");
        }

        // TV SERIES

        String[][] scores_tv= new String[this.tv_series.size()][2];
        HashMap<String, Integer> number_user_t = new HashMap<String, Integer>();
        m = 0;

        for (Map.Entry TV : this.tv_series.entrySet()) {
            String id = (String) TV.getKey();
            TVSeries film = (TVSeries) TV.getValue();

            int number = 0;
            double score = 0;

            for (Map.Entry _user_ : this.users.entrySet()) {
                User user = (User) _user_.getValue();

                if (user.ratings.containsKey(id)) {
                    number +=1;
                    score += user.ratings.get(id);
                }
            }

            if (!(number == 0)) {
                scores_tv[m][0] = decimal.format(score / number);
            }

            else {
                scores_tv[m][0] = "0";
            }
            scores_tv[m][1] = id;

            number_user_t.put(id, number);

            m +=1;
        }

        for (int x = 0; x < scores_tv.length - 1; x++) {
            for (int y = 0; y < scores_tv.length - 1 - x; y++) {
                if (scores_tv[y][0].compareTo(scores_tv[y + 1][0]) < 0) {
                    String f = scores_tv[y][0];
                    String s = scores_tv[y][1];
                    scores_tv[y][0] = scores_tv[y + 1][0];
                    scores_tv[y][1] = scores_tv[y + 1][1];
                    scores_tv[y + 1][0] = f;
                    scores_tv[y + 1][1] = s;
                }
            }
        }

        output_file.append("\nTVSeries:\n");

        for (int k = 0; k < scores_tv.length; k++) {
            TVSeries tv = this.tv_series.get(scores_tv[k][1]);
            output_file.append(tv.title + " (" + tv.start_date.substring(6,10) +  "-" + tv.end_date.substring(6,10) +") Ratings: ");
            output_file.append(scores_tv[k][0] + "/10 from " + number_user_t.get(tv.id) + " Users\n");
        }

        output_file.append("\n" + separating + "\n");
        output_file.close();
        write_output.close();
    }

    // COMMAND 12

    Method_List_11_12 (LinkedHashMap<String, People> all_people, String command, String output, String country) throws IOException {

        this.all_people = all_people;
        this.command = command;
        this.output = output;
        this.country = country;

        // This method prints all artists who is from given country.

        HashMap<Integer, String> people_types = new HashMap<>();
        people_types.put(0, "Director");
        people_types.put(1, "Writer");
        people_types.put(2, "Actor");
        people_types.put(3, "ChildActor");
        people_types.put(4, "StuntPerformer");

        FileWriter write_output = new FileWriter(this.output, true);
        BufferedWriter output_file = new BufferedWriter(write_output);
        output_file.append(this.command + "\n");

        int m = 0;

        for ( int x = 0; x < 5; x++) {

            output_file.append("\n" + people_types.get(x) + "s:\n");
            int counter = 0;

            for (Map.Entry people : this.all_people.entrySet()) {

                try {
                    Artist artist = (Artist) people.getValue();

                    if (artist.type.equals(people_types.get(x))) {

                        if (artist.type.equals("Director") && artist.country.equals(this.country)) {
                            output_file.append(artist.name + " " +  artist.surname + " " +((Director) artist).agent +"\n");
                            counter += 1;
                        }

                        else if (artist.type.equals("Writer") && artist.country.equals(this.country)) {
                            output_file.append(artist.name + " " +  artist.surname + " " +((Writer) artist).writing_type +"\n");
                            counter += 1;
                        }

                        else if (artist.type.equals("Actor") && artist.country.equals(this.country)) {
                            output_file.append(artist.name + " " +  artist.surname + " " +((Actor) artist).height +" cm\n");
                            counter += 1;
                        }

                        else if (artist.type.equals("ChildActor") && artist.country.equals(this.country)) {
                            output_file.append(artist.name + " " +  artist.surname + " " +((ChildActor) artist).age +"\n");
                            counter += 1;
                        }

                        else if (artist.type.equals("StuntPerformer") && artist.country.equals(this.country)){
                            output_file.append(artist.name + " " +  artist.surname + " " +((StuntPerformer) artist).height +" cm\n");
                            counter += 1;
                        }
                    }

                } catch (Exception ignored) {
                }
            }

            if (counter == 0) {
                output_file.append("No result\n");
            }
        }

        output_file.append("\n" + separating + "\n");
        output_file.close();
        write_output.close();

    }
}
