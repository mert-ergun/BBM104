import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

public class SwitchChecker {
    List<Tuple<Smart, Calendar>> switchTimes = new ArrayList<>();
    TimeChecker timeChecker = Main.timeChecker;

    public void CreateSwitchTimes() {
        for (Smart device : Main.smartList) {
            switchTimes.add(new Tuple<Smart, Calendar>(device, null));
        }
    }

    public List<Tuple<Smart,Calendar>> getSwitchTimes() {
        return switchTimes;
    }

    public void UpdateSwitchTimes() {
        for (Smart device : Main.smartList) {
            if (device.switchTime != null) {
                SetSwitchTimes(device.getName(), device.switchTime);
            } else {
                SetSwitchTimes(device.getName(), null);
            }
        }   
    }

    public void SwitchTimesBetweenDates() {
        timeChecker = Main.timeChecker;
        Calendar currentDate = timeChecker.getCurrentDate();
        Calendar oldDate = timeChecker.getOldDate();
        
        for (Tuple<Smart, Calendar> switchTime : switchTimes) {
            if (switchTime.getY() == null) continue;
            if (switchTime.getY().after(oldDate) && switchTime.getY().before(currentDate) || switchTime.getY().equals(oldDate) || switchTime.getY().equals(currentDate)) {
                if (switchTime.getX().getLastSwitchedDate() != null) {
                    long diffInMillis = switchTime.getY().getTimeInMillis() - switchTime.getX().getLastSwitchedDate().getTimeInMillis();
                    double diffInHours = diffInMillis / (1000.0 * 60.0 * 60.0);
                    if (switchTime.getX() instanceof Plug && ((Plug) switchTime.getX()).isPlugged()) {
                        ((Plug) switchTime.getX()).setTotalEnergy(((Plug) switchTime.getX()).getTotalEnergy() + 
                        ((Plug) switchTime.getX()).calculateEnergy(((Plug) switchTime.getX()).getAmpere(), diffInHours));
                    }
                }
                switchTime.getX().setOn(!switchTime.getX().isOn());
                switchTime.getX().switchTime = null;
                switchTime.getX().setLastSwitchedDate(switchTime.getY());
                switchTime.setY(null);
            }
        }
        sortSwitchTimes();
    }

    public void JumpToNop() {
        timeChecker = Main.timeChecker;
        Calendar currentDate = timeChecker.getCurrentDate();
        Calendar nextSwitch = null;
        for (Tuple<Smart, Calendar> switchTime : switchTimes) {
            if (switchTime.getY() == null) continue;
            if (switchTime.getY().after(currentDate)) {
                if (nextSwitch == null) {
                    nextSwitch = switchTime.getY();
                } else if (switchTime.getY().before(nextSwitch)) {
                    nextSwitch = switchTime.getY();
                }
            }
        }
        if (nextSwitch != null) {
            timeChecker.setCurrentDate(nextSwitch);
        }
        UpdateSwitchTimes();
        sortSwitchTimes();
    }

    public void SetSwitchTimes(String name, Calendar switchTime) {
        Smart smartDevice = null;
        for (Smart device : Main.smartList) {
            if (device.getName().equals(name)) {
                smartDevice = device;
            } 
        }
        if (smartDevice == null) return;
        for (Tuple<Smart, Calendar> switchTimes : switchTimes) {
            if (switchTimes.getX() == null) continue;
            if (switchTimes.getX().getName().equals(smartDevice.getName())) {
                switchTimes.setY(switchTime);
                return;
            } 
        }
        switchTimes.add(new Tuple<Smart, Calendar>(smartDevice, switchTime));
        UpdateSwitchTimes();
        sortSwitchTimes();
    }
    
    public void sortSwitchTimes() {
        Comparator<Tuple<Smart, Calendar>> comparator = Comparator.comparing(Tuple::getY, Comparator.nullsFirst(Comparator.naturalOrder()));
        switchTimes.sort(comparator);
    
        List<Tuple<Smart, Calendar>> calendarList = new ArrayList<>();
        List<Tuple<Smart, Calendar>> nonCalendarList = new ArrayList<>();
    
        for (Tuple<Smart, Calendar> tuple : switchTimes) {
            if (tuple.getY() != null) {
                calendarList.add(tuple);
            } else {
                nonCalendarList.add(tuple);
            }
        }
    
        List<Tuple<Smart, Calendar>> sortedList = new ArrayList<>();
        sortedList.addAll(calendarList);
        sortedList.addAll(nonCalendarList);
    
        switchTimes = sortedList;
        UpdateSwitchTimes();
    }

    public boolean CheckSwitchTimes() {
        sortSwitchTimes();
        for (Tuple<Smart, Calendar> switchTime : switchTimes) {
            if (switchTime.getY() == null) continue;
            return true;
        }
        return false;
    }
    
}
