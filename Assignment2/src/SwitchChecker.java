import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SwitchChecker {
    List<Tuple<Smart, List<Calendar>>> switchTimes = new ArrayList<>();
    TimeChecker timeChecker = Main.timeChecker;

    public List<Tuple<Smart, List<Calendar>>> getSwitchTimes() {
        return switchTimes;
    }

    public void SwitchTimesBetweenDates() {
        Calendar initDate = timeChecker.getInitDate();
        Calendar currentDate = timeChecker.getCurrentDate();
        Calendar oldDate = timeChecker.getOldDate();
        
        for (Tuple<Smart, List<Calendar>> switchTime : switchTimes) {
            for (Calendar time : switchTime.getY()) {
                if (time.after(oldDate) && time.before(currentDate)) {
                    switchTime.getX().setOn(!switchTime.getX().isOn());
                }
            }
        }
    }

    public void JumpToNop() {
        Calendar currentDate = timeChecker.getCurrentDate();
        Calendar nextSwitch = null;
        for (Tuple<Smart, List<Calendar>> switchTime : switchTimes) {
            for (Calendar time : switchTime.getY()) {
                if (time.after(currentDate)) {
                    if (nextSwitch == null) {
                        nextSwitch = time;
                    } else if (time.before(nextSwitch)) {
                        nextSwitch = time;
                    }
                }
            }
        }
        if (nextSwitch != null) {
            timeChecker.setCurrentDate(nextSwitch);
        }
    }

    public void SetSwitchTimes(Smart device, Calendar switchTime) {
        for (Tuple<Smart, List<Calendar>> switchTimes : switchTimes) {
            if (switchTimes.getX().getName().equals(device.getName())) {
                switchTimes.getY().add(switchTime);
                return;
            } 
        }
        switchTimes.add(new Tuple<Smart, List<Calendar>>(device, new ArrayList<Calendar>()));
    }
    

    
}
