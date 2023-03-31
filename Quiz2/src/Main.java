/**
 * Main class for the program.
 * Calls all the methods of the program.
 * @param readInput() Reads the input file and creates the trip objects.
 * @param clearOutput() Clears the output file.
 * @param writeOutput() Writes to the output file.
 * @throws Exception
 * 
 * @author Mert ERGÜN
 * @version 1.0
 * @since 2023-03-31
 */
public class Main {
    /**
     * Main method for the program. 
     * Creates all the objects and calls the methods.
     * Clears the output file for the first time because it is in append mode.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Trip[] trips = InputReader.readInput();
        TripSchedule trip_schedule = new TripSchedule();
        for (Trip trip : trips) {
            if (trip != null) {
                trip_schedule.addTrip(trip);
            }
        }
        TripController tripController = new TripController(trip_schedule);
    }
}
