public class Main {
    public static void main(String[] args) throws Exception {
        TripController tripController = new TripController();
        Trip[] trips = InputReader.readInput();
        tripController.trip_schedule = new TripSchedule();
        for (Trip trip : trips) {
            if (trip != null) {
                tripController.trip_schedule.addTrip(trip);
            }
        }

        System.out.println("Departure Order:");
        tripController.DepartureSchedule(tripController.trip_schedule);
        System.out.println("Arrival Order:");
        tripController.ArrivalSchedule(tripController.trip_schedule);
    }
}
