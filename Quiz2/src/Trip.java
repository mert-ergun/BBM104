import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Trip {
    public String tripName, state;
    public Date departureTime, arrivalTime;
    public int duration;
    private static DateFormat dateFormat = new SimpleDateFormat("HH:mm");
    
    public Trip(String tripName, String departureTime, String duration) throws ParseException {
        this.tripName = tripName;
        this.departureTime = dateFormat.parse(departureTime);
        this.duration = Integer.parseInt(duration);
        this.state = "Idle";
        this.arrivalTime = dateFormat.parse(dateFormat.format(calculateArrival()));
    }

    public String parseDepartureTime() {
        return dateFormat.format(departureTime);
    }

    public int getDepartureTime() {
        return departureTime.getHours() * 60 + departureTime.getMinutes();
    }

    public int getArrivalTime() {
        return arrivalTime.getHours() * 60 + arrivalTime.getMinutes();
    }

    public String parseArrivalTime() {
        return dateFormat.format(arrivalTime);
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
