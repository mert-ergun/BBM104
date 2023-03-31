public class TripSchedule {
    Trip[] trips;

    public TripSchedule() {
        trips = new Trip[100];
    }

    public void addTrip(Trip trip) {
        for (int i = 0; i < trips.length; i++) {
            if (trips[i] == null) {
                trips[i] = trip;
                break;
            }
        }
    }
}
