import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Main {

    public static ArrayList<Sports> teams = new ArrayList<Sports>();

    public static void main(String[] args) throws IOException {

        FileReader input = new FileReader(args[0]);
        BufferedReader reader = new BufferedReader(input);
        HashMap<String, Sports> map = new HashMap<String, Sports>();
        ArrayList<Sports> list = new ArrayList<Sports>();

        String line = reader.readLine();
        String team1;
        String team2;
        int goal1;
        int goal2;
        String[] info;

        while (line != null) {
            info = line.split("\t");
            team1 = info[1];
            team2 = info[2];
            goal1 = Integer.parseInt(info[3].split(":")[0]);
            goal2 = Integer.parseInt(info[3].split(":")[1]);

            if (!map.containsKey(team1)) {

                if (info[0].equals("I")) {
                    map.put(team1, new Ice_Hockey(team1));
                }

                else {
                    map.put(team1, new Handball(team1));
                }
                list.add(map.get(team1));

            }

            if (!map.containsKey(team2)) {
                if (info[0].equals("I")) {
                    map.put(team2, new Ice_Hockey(team2));
                }

                else {
                    map.put(team2, new Handball(team2));
                }
                list.add(map.get(team2));
            }

            if (goal1 > goal2) {
                map.get(team1).winning += 1;
                map.get(team2).losing += 1;
            }

            else if (goal1 == goal2) {
                map.get(team1).tie += 1;
                map.get(team2).tie += 1;
            }

            else {
                map.get(team1).losing += 1;
                map.get(team2).winning += 1;
            }

            map.get(team1).total_match += 1;
            map.get(team2).total_match += 1;
            map.get(team1).goals += goal1;
            map.get(team2).goals += goal2;
            map.get(team1).against += goal2;
            map.get(team2).against += goal1;
            map.get(team1).score = map.get(team1).award * map.get(team1).winning + map.get(team1).tie;
            map.get(team2).score = map.get(team2).award * map.get(team2).winning + map.get(team2).tie;
            map.get(team1).average = map.get(team1).goals - map.get(team1).against;
            map.get(team2).average = map.get(team2).goals - map.get(team2).against;

            line = reader.readLine();
        }

        reader.close();
        input.close();

        list.sort(new Comparator<Sports>() {
            @Override
            public int compare(Sports spor1, Sports spor2) {
                if(spor1.score > spor2.score){
                    return -1; }
                else if (spor1.score < spor2.score){
                    return 1;
                }
                else{
                    if (spor1.average > spor2.average) {
                        return -1;
                    }

                    else if (spor1.average < spor2.average) {
                        return 1;
                    }

                    else {
                        return 0;
                    }
                }
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        });

        FileWriter i = new FileWriter("icehockey.txt", false);
        BufferedWriter ice = new BufferedWriter(i);
        FileWriter h = new FileWriter("handball.txt", false);
        BufferedWriter hand = new BufferedWriter(h);

        int ice_hockey = 1;
        int handball = 1;

        String t = "\t";

        for (Sports sport : list) {

            if (sport instanceof Ice_Hockey) {
                ice.write(ice_hockey + t + sport.getName() + t + sport.total_match + t + sport.winning + t + sport.tie + t +
                 + sport.losing + t + sport.goals + ":" + sport.against + t + sport.score);

                if (ice_hockey != 4) {
                    ice.write("\n");
                }

                ice_hockey +=1;
            }

            else {
                hand.write(handball + t + sport.getName() + t + sport.total_match + t + sport.winning + t + sport.tie + t +
                        + sport.losing + t + sport.goals + ":" + sport.against + t + sport.score);

                if (handball != 4) {
                    hand.write("\n");
                }
                handball +=1;
            }
        }

        ice.close();
        hand.close();
        i.close();
        h.close();
    }
}