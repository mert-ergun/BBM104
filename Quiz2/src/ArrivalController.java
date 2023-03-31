/**
 * Arrival Controller Interface is used to implement the Arrival Schedule method.
 * @param ArrivalSchedule() Prints the arrival schedule of the trips. Sorts the trips by arrival time.
 * @throws Exception
 * 
 * @author Mert ERGÜN
 * @version 1.0
 * @since 2023-03-31
 */
public interface ArrivalController {
    public void ArrivalSchedule(TripSchedule trip_schedule) throws Exception;
}
