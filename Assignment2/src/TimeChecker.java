import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeChecker {
    Calendar initDate;
    Calendar currentDate;
    Calendar oldDate; 

    public void SetInitialTime(String time) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatter.parse(time));
        initDate = calendar;
        currentDate = calendar;
    }

    public void SetTime(String time) throws ParseException {
        oldDate = currentDate;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatter.parse(time));
        currentDate = calendar;
    }

    public void SkipMinutes(int minutes) {
        oldDate = currentDate;
        currentDate.add(Calendar.MINUTE, minutes);
    }
}
