/**
 * This class represents a train trip schedule. It contains an array of trips.
 * @param trips The array of trips. Default size is 100.
 * @param addTrip(Trip trip) Adds a trip to the array of trips.
 * @throws Exception
 * 
 * @author Mert ERGÜN
 * @version 1.0
 * @since 2023-03-31
 */
public class TripSchedule {
    Trip[] trips;

    /**
     * Constructor for the TripSchedule class.
     * @param trips The array of trips. Default size is 100.
     */
    public TripSchedule() {
        trips = new Trip[100];
    }

    /**
     * Adds a trip to the array of trips. If the array is full, it throws an exception.
     * @param trip The trip to be added.
     * @throws Exception
     */
    public void addTrip(Trip trip) throws Exception {
        for (int i = 0; i < trips.length; i++) {
            if (trips[i] == null) {
                trips[i] = trip;
                break;
            }
        }
    }
}
