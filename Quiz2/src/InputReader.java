import java.io.*;

/**
 * InputReader class reads the input file and creates an array of Trip objects.
 * @param readInput() Reads the input file and creates an array of Trip objects.
 * @throws Exception
 * 
 * @author Mert ERGÜN
 * @version 1.0
 * @since 2023-03-31
 */
public class InputReader {
    public static Trip[] readInput(String inputFile) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            Trip[] trips = new Trip[100];
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                String tripName = parts[0];  // Trip name is the first part of the line.
                String departureTime = parts[1];  // Departure time is the second part of the line.
                String duration = parts[2];  // Duration is the third part of the line. It is in minutes.
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