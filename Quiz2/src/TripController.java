import java.util.Date;
import java.util.Arrays;

public class TripController implements DepartureController, ArrivalController {
    protected TripSchedule trip_schedule;

    public TripController() {
        trip_schedule = new TripSchedule();
    }

    public void ArrivalSchedule(TripSchedule trip_schedule) {
        for (int i = 0; i < trip_schedule.trips.length - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < trip_schedule.trips.length; j++) {
                if (trip_schedule.trips[j].arrivalTime.before(trip_schedule.trips[min_idx].arrivalTime)) {
                    min_idx = j;
                }
            }
            if (min_idx != i) {
                Trip temp = trip_schedule.trips[min_idx];
                trip_schedule.trips[i] = trip_schedule.trips[min_idx];
                trip_schedule.trips[min_idx] = temp;
            }
        }

        for (int i = 0; i < trip_schedule.trips.length; i++) {
            if (trip_schedule.trips[i] != null) {
                System.out.println(trip_schedule.trips[i].tripName + " arrive at" + trip_schedule.trips[i].parseArrivalTime() + "\tTrip State :" + trip_schedule.trips[i].state);
            }
        }
    }

    public void DepartureSchedule(TripSchedule trip_schedule) {
        for (int i = 0; i < trip_schedule.trips.length - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < trip_schedule.trips.length; j++) {
                if (trip_schedule.trips[j].departureTime.before(trip_schedule.trips[min_idx].departureTime)) {
                    min_idx = j;
                }
            }
            if (min_idx != i) {
                Trip temp = trip_schedule.trips[min_idx];
                trip_schedule.trips[i] = trip_schedule.trips[min_idx];
                trip_schedule.trips[min_idx] = temp;
            }
        }

        for (int i = 0; i < trip_schedule.trips.length; i++) {
            if (trip_schedule.trips[i] != null) {
                System.out.println(trip_schedule.trips[i].tripName + " depart at" + trip_schedule.trips[i].parseDepartureTime() + "\tTrip State :" + trip_schedule.trips[i].state);
            }
        }
    }

}
