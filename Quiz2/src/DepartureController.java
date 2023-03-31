/**
 * Departure Controller interface is used to implement the Departure Schedule method.
 * @param DepartureSchedule() Prints the departure schedule of the trips. Sorts the trips by departure time.
 * @throws Exception
 * 
 * @author Mert ERGÜN
 * @version 1.0
 * @since 2023-03-31
 */
public interface DepartureController {
    public void DepartureSchedule(TripSchedule trip_schedule) throws Exception;
}
