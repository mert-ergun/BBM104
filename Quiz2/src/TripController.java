public class TripController implements DepartureController, ArrivalController {
    protected TripSchedule trip_schedule;

    public TripController() {
        trip_schedule = new TripSchedule();
    }

    public void ArrivalSchedule(TripSchedule trip_schedule) {
        for (int i = 0; i < trip_schedule.trips.length - 1; i++) {
            if (trip_schedule.trips[i] != null) {
                for (int j = 0; j < trip_schedule.trips.length - 1 - i; j++) {
                    if (trip_schedule.trips[j] != null && trip_schedule.trips[j + 1] != null) {
                        if (trip_schedule.trips[j].getArrivalTime() > trip_schedule.trips[j + 1].getArrivalTime()) {
                            Trip temp = trip_schedule.trips[j];
                            trip_schedule.trips[j] = trip_schedule.trips[j + 1];
                            trip_schedule.trips[j + 1] = temp;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < trip_schedule.trips.length; i++) {
            if (trip_schedule.trips[i] != null) {
                for (int j = i + 1; j < trip_schedule.trips.length; j++) {
                    if (trip_schedule.trips[j] != null) {
                        if (trip_schedule.trips[i].getArrivalTime() == trip_schedule.trips[j].getArrivalTime()) {
                            trip_schedule.trips[i].setState("DELAYED");
                            trip_schedule.trips[j].setState("DELAYED");
                        }
                    }

                }
                System.out.println(trip_schedule.trips[i].tripName + " arrive at " + trip_schedule.trips[i].parseArrivalTime() + "\tTrip State :" + trip_schedule.trips[i].state);
            }
        }
    }

    public void DepartureSchedule(TripSchedule trip_schedule) {
        for (int i = 0; i < trip_schedule.trips.length - 1; i++) {
            if (trip_schedule.trips[i] != null) {
                for (int j = 0; j < trip_schedule.trips.length - 1 - i; j++) {
                    if (trip_schedule.trips[j] != null && trip_schedule.trips[j + 1] != null) {
                        if (trip_schedule.trips[j].getDepartureTime() > trip_schedule.trips[j + 1].getDepartureTime()) {
                            Trip temp = trip_schedule.trips[j];
                            trip_schedule.trips[j] = trip_schedule.trips[j + 1];
                            trip_schedule.trips[j + 1] = temp;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < trip_schedule.trips.length; i++) {
            if (trip_schedule.trips[i] != null) {
                for (int j = i + 1; j < trip_schedule.trips.length; j++) {
                    if (trip_schedule.trips[j] != null) {
                        if (trip_schedule.trips[i].getDepartureTime() == trip_schedule.trips[j].getDepartureTime()) {
                            trip_schedule.trips[i].setState("DELAYED");
                            trip_schedule.trips[j].setState("DELAYED");
                        }
                    }

                }
                System.out.println(trip_schedule.trips[i].tripName + " depart at " + trip_schedule.trips[i].parseDepartureTime() + "\tTrip State :" + trip_schedule.trips[i].state);
            }
        }
    }

}
