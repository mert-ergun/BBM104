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

    public void SetInitialTime(String time) throws ParseException {
        switchChecker = Main.switchChecker;
        formatter.setLenient(false);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatter.parse(time));
        initDate = calendar;
        currentDate = calendar;
        isInit = true;
    }

    public void SetTime(String time) throws ParseException {
        switchChecker = Main.switchChecker;
        oldDate = currentDate;
        Calendar calendar = Calendar.getInstance();
        formatter.setLenient(false);
        calendar.setTime(formatter.parse(time));
        currentDate = calendar;
        switchChecker.SwitchTimesBetweenDates();
    }

    public void SkipMinutes(int minutes) {
        switchChecker = Main.switchChecker;
        formatter.setLenient(false);
        oldDate = currentDate;
        currentDate.add(Calendar.MINUTE, minutes);
        switchChecker.SwitchTimesBetweenDates();
    }

    public boolean CheckTime (String time) throws ParseException {
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
