import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeChecker {
    Calendar initDate;
    Calendar currentDate;
    Calendar oldDate; 
    SwitchChecker switchChecker = Main.switchChecker;
    boolean isInit = false;
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

    public void setInitialTime(String time) throws ParseException {
        switchChecker = Main.switchChecker;
        formatter.setLenient(false);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatter.parse(time));
        initDate = calendar;
        currentDate = calendar;
        isInit = true;
    }

    public void setTime(String time) throws ParseException {
        switchChecker = Main.switchChecker;
        oldDate = (Calendar) currentDate.clone();
        Calendar calendar = Calendar.getInstance();
        formatter.setLenient(false);
        calendar.setTime(formatter.parse(time));
        currentDate = calendar;
        switchChecker.switchTimesBetweenDates();
    }

    public void skipMinutes(int minutes) {
        switchChecker = Main.switchChecker;
        formatter.setLenient(false);
        oldDate = (Calendar) currentDate.clone();
        currentDate.add(Calendar.MINUTE, minutes);
        switchChecker.switchTimesBetweenDates();
    }

    public boolean checktTime(String time) throws ParseException {
        switchChecker = Main.switchChecker;
        formatter.setLenient(false);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatter.parse(time));
        if (calendar.after(currentDate) || calendar.equals(currentDate)) {
            return true;
        }
        return false;
    }

    public Calendar getInitDate() {
        return initDate;
    }

    public void setInitDate(Calendar initDate) {
        this.initDate = initDate;
    }

    public Calendar getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Calendar currentDate) {
        this.currentDate = currentDate;
    }

    public Calendar getOldDate() {
        return oldDate;
    }

    public void setOldDate(Calendar oldDate) {
        this.oldDate = oldDate;
    }
}
