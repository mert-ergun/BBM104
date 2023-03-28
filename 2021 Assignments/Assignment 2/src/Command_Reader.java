import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;

// This class manages all and send right informations to right classes/methods.


class Command_Reader {

    String commands;
    String output;

    LinkedHashMap<String, People> all_people;
    LinkedHashMap<String, Actor> actors;
    LinkedHashMap<String, ChildActor> child_actors;
    LinkedHashMap<String, Director> directors;
    LinkedHashMap<String, StuntPerformer> stunt_performers;
    LinkedHashMap<String, User> users;
    LinkedHashMap<String, Writer> writers;

    LinkedHashMap<String, Film> all_films;
    LinkedHashMap<String, FeatureFilm> feature_films;
    LinkedHashMap<String, ShortFilm> short_films;
    LinkedHashMap<String, TVSeries> tv_series;
    LinkedHashMap<String, Documentary> documentaries;

    Command_Reader (    String commands, String output, LinkedHashMap<String, People> all_people, LinkedHashMap<String, Actor> actors,
                        LinkedHashMap<String, ChildActor> child_actors, LinkedHashMap<String, Director> directors,
                        LinkedHashMap<String, StuntPerformer> stunt_performers, LinkedHashMap<String, User> users,
                        LinkedHashMap<String, Writer> writers, LinkedHashMap<String, Film> all_films,
                        LinkedHashMap<String, FeatureFilm> feature_films, LinkedHashMap<String, ShortFilm> short_films,
                        LinkedHashMap<String, TVSeries> tv_series, LinkedHashMap<String, Documentary> documentaries) {

        this.commands = commands;
        this.output = output;
        this.all_people = all_people;
        this.actors = actors;
        this.child_actors = child_actors;
        this.directors = directors;
        this.stunt_performers = stunt_performers;
        this.users = users;
        this.writers = writers;
        this.all_films = all_films;
        this.feature_films = feature_films;
        this.short_films = short_films;
        this.tv_series = tv_series;
        this.documentaries = documentaries;

    }

    void reader_method() throws IOException {
        FileReader read_people = new FileReader(commands);
        BufferedReader reader_people = new BufferedReader(read_people);

        String line = reader_people.readLine();
        String[] news;

        while (line != null) {

            news = line.split("\\s+");

            if (news[0].equals("RATE")) {

                // For rating method

                Method_Rating rating = new Method_Rating(news[1], news[2], Integer.parseInt(news[3]), output, line);
                rating.rater(all_films, users);
            }

            else if (news[0].equals("ADD")) {

                // For adding method

                Method_Add adder = new Method_Add(all_people, all_films, feature_films, output, line);
                adder.adder(news[2], news[3], news[4], news[5], news[6], news[7], news[8], news[9], news[10], news[11], news[12]);
            }

            else if (news[0].equals("VIEWFILM")) {

                // For viewfilm method

                Method_View_Film view = new Method_View_Film(directors, all_people, users, writers, all_films, feature_films, short_films,
                        tv_series, documentaries, line, output);

                view.writer(news[1]);
            }

            else if (news[0].equals("REMOVE")) {
                // For removing rating method
                Method_Remove remover = new Method_Remove(users, all_films, output, line);
                remover.remover(news[2], news[3]);
            }

            else if (news[0].equals("EDIT")) {
                 // For editing rating method
                Method_Edit edit = new Method_Edit(users, all_films, output, line);
                edit.editor(news[2], news[3], Integer.parseInt(news[4]));
            }

            else if (news[0].equals("LIST") && news[1].equals("USER")) {
                // For 4. list method. Prints all films with their ratings of given user
                Method_List_4_7 list_1 = new Method_List_4_7(all_films, users, output, line, news[2]);
            }

            else if (news[0].equals("LIST") && news[1].equals("FILM")) {
                // Prints all TV Series
                Method_List_4_7 list_1 = new Method_List_4_7(tv_series, output, line);
            }

            else if (news[0].equals("LIST") && news[1].equals("FILMS") && news[3].equals("COUNTRY")) {
                // Prints films of given country
                Method_List_8_9_10 list_2 = new Method_List_8_9_10(all_films, output, line, news[4]);
            }

            else if (news[0].equals("LIST") && news[1].equals("FEATUREFILMS")) {
                // Prints all feature films which made before/after given year
                Method_List_8_9_10 list_2 = new Method_List_8_9_10(feature_films, output, line, news[2], Integer.parseInt(news[3]));
            }

            else if (news[0].equals("LIST") && news[1].equals("FILMS") && news[3].equals("RATE")) {
                // Sorts and prints all films by their rating degree
                Method_List_11_12 list_3 = new Method_List_11_12(all_films, feature_films, short_films, tv_series, documentaries, users, line, output);
            }

            else if (news[0].equals("LIST") && news[1].equals("ARTISTS")) {
                // Prints all artists who is from given country
                Method_List_11_12 list_3 = new Method_List_11_12(all_people, line, output, news[3]);
            }

            line = reader_people.readLine();
        }

        reader_people.close();
        read_people.close();
    }

}
