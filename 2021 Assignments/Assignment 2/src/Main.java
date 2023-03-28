import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

// This main class reads people and film files to read and analysis.

public class Main {
    private static Object Exception;

    public static void main(String[] args) throws Exception {

        String people = args[0];
        String films = args[1];
        String commands = args[2];
        String output = args[3];

        //FileWriter write_output = new FileWriter("output.txt", true);
        // BufferedWriter output_file = new BufferedWriter(write_output);

        // I will store all peoples inside these maps. The keys are id number of people, the values are class of each person.

        LinkedHashMap<String, People> all_people = new LinkedHashMap<String, People>();
        LinkedHashMap<String, Actor> actors = new LinkedHashMap<String, Actor>();
        LinkedHashMap<String, ChildActor> child_actors = new LinkedHashMap<String, ChildActor>();
        LinkedHashMap<String, Director> directors = new LinkedHashMap<String, Director>();
        LinkedHashMap<String, StuntPerformer> stunt_performers = new LinkedHashMap<String, StuntPerformer>();
        LinkedHashMap<String, User> users = new LinkedHashMap<String, User>();
        LinkedHashMap<String, Writer> writers = new LinkedHashMap<String, Writer>();

        // I will store all films inside these maps. The keys are id number of films, the values are class of each film.

        LinkedHashMap<String, Film> all_films = new LinkedHashMap<String, Film>();
        LinkedHashMap<String, FeatureFilm> feature_films = new LinkedHashMap<String, FeatureFilm>();
        LinkedHashMap<String, ShortFilm> short_films = new LinkedHashMap<String, ShortFilm>();
        LinkedHashMap<String, TVSeries> tv_series = new LinkedHashMap<String, TVSeries>();
        LinkedHashMap<String, Documentary> documentaries = new LinkedHashMap<String, Documentary>();

        // Now, I will read people file and record all persons to maps with a while loop.

        FileReader read_people = new FileReader(people);
        BufferedReader reader_people = new BufferedReader(read_people);

        String line = reader_people.readLine();
        String[] news;

        while (line != null) {
            news = line.split("\t");
            if (news[0].equals("Actor:")) {
                Actor actor = new Actor(news[1], news[2], news[3], news[4], news[5]);
                actors.put(news[1], actor);
                all_people.put(news[1], actor);
            }

            else if (news[0].equals("ChildActor:")) {
                ChildActor child_actor = new ChildActor(news[1], news[2], news[3], news[4], news[5]);
                child_actors.put(news[1], child_actor);
                all_people.put(news[1], child_actor);
            }

            else if (news[0].equals("Director:")) {
                Director director = new Director(news[1], news[2], news[3], news[4], news[5]);
                directors.put(news[1], director);
                all_people.put(news[1], director);
            }

            else if (news[0].equals("StuntPerformer:")) {

                // We must convert the sixth elements of string array to array to record actors.

                String[] _actor_ = news[6].split(",");

                StuntPerformer stunt_performer = new StuntPerformer(news[1], news[2], news[3], news[4], news[5], _actor_);
                stunt_performers.put(news[1], stunt_performer);
                all_people.put(news[1], stunt_performer);
            }

            else if (news[0].equals("Writer:")) {
                Writer writer = new Writer(news[1], news[2], news[3], news[4], news[5]);
                writers.put(news[1], writer);
                all_people.put(news[1], writer);
            }

            else {
                User user = new User(news[1], news[2], news[3], news[4]);
                users.put(news[1], user);
                all_people.put(news[1], user);
            }

            line = reader_people.readLine();
        }

        reader_people.close();
        read_people.close();

        // I recorded all persons. Now, I will record all films to maps with a while loop.

        FileReader read_films = new FileReader(films);
        BufferedReader reader_films = new BufferedReader(read_films);

        line = reader_films.readLine();

        while (line != null) {
            news = line.split("\t");

            // We must convert the fourth and seventh elements of string array to array to record directors and performers.

            String[] directors_id = news[4].split(",");
            String[] performers_id = news[7].split(",");

            if (news[0].equals("FeatureFilm:")) {
                String[] genres = news[8].split(",");
                String[] writers_id = news[10].split(",");

                FeatureFilm feature_film = new FeatureFilm(news[1], news[2], news[3], directors_id, news[5], news[6],
                        performers_id, genres, news[9], writers_id, news[11]);
                feature_films.put(news[1], feature_film);
                all_films.put(news[1], feature_film);
            }

            else if (news[0].equals("ShortFilm:")) {

                if (Integer.parseInt(news[5]) <= 40) {
                    String[] genres = news[8].split(",");
                    String[] writers_id = news[10].split(",");

                    ShortFilm short_film = new ShortFilm(news[1], news[2], news[3], directors_id, news[5], news[6],
                            performers_id, genres, news[9], writers_id);
                    short_films.put(news[1], short_film);
                    all_films.put(news[1], short_film);
                }

                else {

                    // If the length of short film is bigger than 40 minutes, the block will throw an exception.

                    try {
                        throw new Exception("The length of short film mustn't be bigger than 40 minutes.");
                    } catch (Exception e) {
                        System.out.println();
                    }
                }
            }

            else if (news[0].equals("Documentary:")) {
                Documentary documentary = new Documentary(news[1], news[2], news[3], directors_id, news[5], news[6],
                        performers_id, news[8]);
                documentaries.put(news[1], documentary);
                all_films.put(news[1], documentary);
            }

            else {
                String[] genres = news[8].split(",");
                String[] writers_id = news[9].split(",");

                TVSeries tv_serial = new TVSeries(news[1], news[2], news[3], directors_id, news[5], news[6],
                        performers_id, genres, writers_id, news[10], news[11], news[12], news[13]);

                tv_series.put(news[1], tv_serial);
                all_films.put(news[1], tv_serial);
            }

            line = reader_films.readLine();
        }

        // And then, sends all informations to command reader class to do operations.


        FileWriter write_output = new FileWriter(output, false);
        BufferedWriter output_file = new BufferedWriter(write_output);
        output_file.append("\n");
        output_file.close();
        write_output.close();

        Command_Reader command_reader = new Command_Reader(commands, output, all_people, actors, child_actors, directors,
                                                            stunt_performers, users, writers, all_films, feature_films, short_films,
                                                            tv_series, documentaries);
        command_reader.reader_method();
    }
}

