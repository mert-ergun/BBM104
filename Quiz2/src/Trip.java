import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Trip {
    public String tripName, state;
    public Date departureTime, arrivalTime;
    public int duration;
    private static DateFormat dateFormat = new SimpleDateFormat("HH:MM");
    
    public Trip(String tripName, String departureTime, String duration) throws ParseException {
        this.tripName = tripName;
        this.departureTime = dateFormat.parse(departureTime);
        this.duration = Integer.parseInt(duration);
        this.state = "Idle";
        this.arrivalTime = calculateArrival();
    }

    public String parseDepartureTime() {
        return dateFormat.format(departureTime);
    }

    public int getDepartureTime() {
        return departureTime.getDate();
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date calculateArrival(){
        return new Date(departureTime.getTime() + duration * 60000);
    }

}
