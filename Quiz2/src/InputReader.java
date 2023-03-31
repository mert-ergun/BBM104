import java.io.*;

public class InputReader {
    public static Trip[] readInput() throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            Trip[] trips = new Trip[100];
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                String tripName = parts[0];
                String departureTime = parts[1];
                String duration = parts[2];
                for (int i = 0; i < trips.length; i++) {
                    if (trips[i] == null) {
                        trips[i] = new Trip(tripName, departureTime, duration);
                        break;
                    }
                }
            }
            return trips;
        }
    }
}