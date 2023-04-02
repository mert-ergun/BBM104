/**
 * TripController class is the controller class of the program. It contains the sorting algorithms for the departure and arrival times. 
 * It implements the DepartureController and ArrivalController interfaces.
 * @param trip_schedule The trip schedule object.
 * @param DepartureSchedule() Prints the departure schedule of the trips. Sorts the trips by departure time.
 * @param ArrivalSchedule() Prints the arrival schedule of the trips. Sorts the trips by arrival time.
 * @throws Exception
 * 
 * @author Mert ERGÜN
 * @version 1.0
 * @since 2023-03-31
 */
public class TripController implements DepartureController, ArrivalController {
    protected TripSchedule trip_schedule;

    /**
     * Constructor for the TripController class.
     * @param trip_schedule The trip schedule object.
     */
    public TripController(TripSchedule trip_schedule, String outputFile) throws Exception {
        OutputWriter.clearOutput(outputFile);
        DepartureSchedule(trip_schedule, outputFile);
        OutputWriter.writeOutput("", outputFile);
        ArrivalSchedule(trip_schedule, outputFile);
        this.trip_schedule = trip_schedule;
    }

    /**
     * Prints the departure schedule of the trips. Sorts the trips by departure time.
     * If two trips have the same departure time, it prints "DELAYED" for both of them, switches their states to "DELAYED" and writes the output to the output file.
     * Uses the bubble sort algorithm.
     * Calls the writeOutput() method of the OutputWriter class.
     * @param trip_schedule The trip schedule object.
     * @throws Exception
     */
    public void ArrivalSchedule(TripSchedule trip_schedule, String outputFile) throws Exception {
        for (int i = 0; i < trip_schedule.trips.length - 1; i++) {
            if (trip_schedule.trips[i] != null) {
                for (int j = 0; j < trip_schedule.trips.length - 1 - i; j++) {
                    if (trip_schedule.trips[j] != null && trip_schedule.trips[j + 1] != null) {
                        if (trip_schedule.trips[j].getArrivalTime().after(trip_schedule.trips[j + 1].getArrivalTime())) {
                            Trip temp = trip_schedule.trips[j];
                            trip_schedule.trips[j] = trip_schedule.trips[j + 1];
                            trip_schedule.trips[j + 1] = temp;
                        }
                    }
                }
            }
        }
        OutputWriter.writeOutput("Arrival order:", outputFile);
        for (int i = 0; i < trip_schedule.trips.length; i++) {
            if (trip_schedule.trips[i] != null) {
                for (int j = i + 1; j < trip_schedule.trips.length; j++) {
                    if (trip_schedule.trips[j] != null) {
                        if (trip_schedule.trips[i].getArrivalInt() == trip_schedule.trips[j].getArrivalInt()) {
                            trip_schedule.trips[i].setState("DELAYED");
                            trip_schedule.trips[j].setState("DELAYED");
                        }
                    }
                }
                OutputWriter.writeOutput(trip_schedule.trips[i].tripName + " arrive at " + trip_schedule.trips[i].parseArrivalTime() + "\tTrip State :" + trip_schedule.trips[i].state, outputFile);
            }
        }
    }

    /**
     * Prints the departure schedule of the trips. Sorts the trips by departure time.
     * If two trips have the same departure time, it prints "DELAYED" for both of them, switches their states to "DELAYED" and writes the output to the output file.
     * Uses the bubble sort algorithm.
     * Calls the writeOutput() method of the OutputWriter class.
     * @param trip_schedule The trip schedule object.
     * @throws Exception
     */
    public void DepartureSchedule(TripSchedule trip_schedule, String outputFile) throws Exception {
        for (int i = 0; i < trip_schedule.trips.length - 1; i++) {
            if (trip_schedule.trips[i] != null) {
                for (int j = 0; j < trip_schedule.trips.length - 1 - i; j++) {
                    if (trip_schedule.trips[j] != null && trip_schedule.trips[j + 1] != null) {
                        if (trip_schedule.trips[j].getDepartureTime().after(trip_schedule.trips[j + 1].getDepartureTime())) {
                            Trip temp = trip_schedule.trips[j];
                            trip_schedule.trips[j] = trip_schedule.trips[j + 1];
                            trip_schedule.trips[j + 1] = temp;
                        }
                    }
                }
            }
        }
        OutputWriter.writeOutput("Departure order:", outputFile);
        for (int i = 0; i < trip_schedule.trips.length; i++) {
            if (trip_schedule.trips[i] != null) {
                for (int j = i + 1; j < trip_schedule.trips.length; j++) {
                    if (trip_schedule.trips[j] != null) {
                        if (trip_schedule.trips[i].getDepartureInt() == trip_schedule.trips[j].getDepartureInt()) {
                            trip_schedule.trips[i].setState("DELAYED");
                            trip_schedule.trips[j].setState("DELAYED");
                        }
                    }
                }
                OutputWriter.writeOutput(trip_schedule.trips[i].tripName + " depart at " + trip_schedule.trips[i].parseDepartureTime() + "\tTrip State :" + trip_schedule.trips[i].state, outputFile);
            }
        }
    }

}
