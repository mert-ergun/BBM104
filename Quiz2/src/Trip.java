import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class represents a train trip.
 * @param tripName The name of the trip.
 * @param departureTime The departure time of the trip.
 * @param duration The duration of the trip.
 * @param state The state of the trip. (IDLE, DELAYED). Default is IDLE.
 * @param arrivalTime The arrival time of the trip.
 * @param dateFormat The date format of the trip. (HH:mm)
 * @param parseDepartureTime() Returns the departure time of the trip in the format of HH:mm. For use in the output file.
 * @param getDepartureTime() Returns the departure time of the trip in minutes.
 * @param getArrivalTime() Returns the arrival time of the trip in minutes.
 * @param parseArrivalTime() Returns the arrival time of the trip in the format of HH:mm. For use in the output file.
 * @param getState() Returns the state of the trip.
 * @param setState(String state) Sets the state of the trip. Used to set the state to DELAYED if there is a conflict.
 * @param calculateArrival() Calculates the arrival time of the trip. Used in the constructor also.
 * 
 * @author Mert ERGÜN
 * @version 1.0
 * @since 2023-03-31
 */
public class Trip {
    public String tripName, state;
    public Date departureTime, arrivalTime;
    public int duration;
    private static DateFormat dateFormat = new SimpleDateFormat("HH:mm");
    
    /**
     * Constructor for the Trip class.
     * @param tripName
     * @param departureTime
     * @param duration
     * @throws ParseException
     */
    public Trip(String tripName, String departureTime, String duration) throws ParseException {
        this.tripName = tripName;
        this.departureTime = dateFormat.parse(departureTime);
        this.duration = Integer.parseInt(duration);
        this.state = "IDLE";
        this.arrivalTime = dateFormat.parse(dateFormat.format(calculateArrival()));
    }

    /**
     * Returns the departure time of the trip in the format of HH:mm. For use in the output file.
     * @return String departureTime
     */
    public String parseDepartureTime() {
        return dateFormat.format(departureTime);
    }

    /**
     * Returns the departure time of the trip in minutes. For use in the sorting algorithm.
     * @return int departureTime
     */
    public Date getDepartureTime() {
        return departureTime;  
    }

    public int getDepartureInt() {
        return departureTime.getHours() * 60 + departureTime.getMinutes();
    }

    /**
     * Returns the arrival time of the trip in minutes. For use in the sorting algorithm.
     * @return int arrivalTime
     */
    public Date getArrivalTime() {
        return arrivalTime; 
    }

    /**
     * Returns the arrival time of the trip in the format of HH:mm. For use in the output file.
     * @return String arrivalTime
     */
    public String parseArrivalTime() {
        return dateFormat.format(arrivalTime);
    }

    public int getArrivalInt() {
        return arrivalTime.getHours() * 60 + arrivalTime.getMinutes();
    }

    /**
     * Returns the state of the trip. (IDLE, DELAYED)
     * @return String state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state of the trip. Used to set the state to DELAYED if there is a conflict.
     * @param state
     */
    public void setState(String state) { 
        this.state = state;
    }

    /**
     * Calculates the arrival time of the trip. Used in the constructor also.
     * @return Date arrivalTime
     */
    public Date calculateArrival(){
        return new Date(departureTime.getTime() + duration * 60000);
    }

}
