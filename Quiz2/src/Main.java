public class Main {
    public static void main(String[] args) throws Exception {
        TripController tripController = new TripController();
        Trip[] trips = InputReader.readInput();
        tripController.trip_schedule = new TripSchedule();
        for (Trip trip : trips) {
            if (trip != null) {
                tripController.trip_schedule.addTrip(trip);
                System.out.println(trip.tripName + " depart at " + trip.parseDepartureTime() + " arrive at " + trip.parseArrivalTime() + "\tTrip State :" + trip.state);
            }
        }
        tripController.ArrivalSchedule(tripController.trip_schedule);
        tripController.DepartureSchedule(tripController.trip_schedule);
    }
}
