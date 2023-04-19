import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class for checking the time.
 * It contains the initial date, current date and old date.
 * It also contains methods to set the initial date, set the current date, skip minutes, check the time and write the z-report.
 */
public class TimeChecker {
    Calendar initDate;  // initial date (time when the program starts)
    Calendar currentDate;  // current date
    Calendar oldDate;   // old date
    SwitchChecker switchChecker = Main.switchChecker;  // switch checker object to call its methods
    boolean isInit = false;  // flag to check if the initial date is set
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");  // formatter for the date

    /**
     * This method sets the initial and current date.
     * @param time - initial time
     * @throws ParseException
     */
    public void setInitialTime(String time) throws ParseException {
        switchChecker = Main.switchChecker;
        formatter.setLenient(false);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatter.parse(time));
        initDate = calendar;
        currentDate = calendar;
        isInit = true;
    }

    /**
     * This method sets the current date.
     * It also calls the switch times method to check if there are any switch times between the old date and the new date.
     * @param time - current time
     * @throws ParseException
     */
    public void setTime(String time) throws ParseException {
        switchChecker = Main.switchChecker;
        oldDate = (Calendar) currentDate.clone();
        Calendar calendar = Calendar.getInstance();
        formatter.setLenient(false);
        calendar.setTime(formatter.parse(time));
        currentDate = (Calendar) calendar.clone();
        switchChecker.switchTimesBetweenDates();
    }

    /**
     * This method skips the time by the given number of minutes.
     * It also calls the switch times method to check if there are any switch times between the old date and the new date.
     * @param minutes - number of minutes to skip
     */
    public void skipMinutes(int minutes) {
        switchChecker = Main.switchChecker;
        formatter.setLenient(false);
        oldDate = (Calendar) currentDate.clone();
        currentDate.add(Calendar.MINUTE, minutes);
        switchChecker.switchTimesBetweenDates();
    }

    /**
     * This method checks if the given time is after the current date.
     * @param time - time to check
     * @return true if the given time is after the current date, false otherwise
     * @throws ParseException
     */
    public boolean checkTime(String time) throws ParseException {
        switchChecker = Main.switchChecker;
        formatter.setLenient(false);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatter.parse(time));
        if (calendar.after(currentDate)) {
            return true;
        }
        return false;
    }

    /**
     * This method gets the initial date.
     * @return initial date
     */
    public Calendar getInitDate() {
        return initDate;
    }

    /**
     * This method sets the initial date.
     * @param initDate - initial date
     */
    public void setInitDate(Calendar initDate) {
        this.initDate = initDate;
    }

    /**
     * This method gets the current date.
     * @return current date
     */
    public Calendar getCurrentDate() {
        return currentDate;
    }

    /**
     * This method sets the current date.
     * @param currentDate - current date
     */
    public void setCurrentDate(Calendar currentDate) {
        this.currentDate = currentDate;
    }

    /**
     * This method gets the old date.
     * Old date is the date before the current date.
     * @return old date
     */
    public Calendar getOldDate() {
        return oldDate;
    }

    /**
     * This method sets the old date.
     * @param oldDate - old date
     */
    public void setOldDate(Calendar oldDate) {
        this.oldDate = oldDate;
    }
}
