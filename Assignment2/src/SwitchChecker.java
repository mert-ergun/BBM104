import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SwitchChecker {
    List<Tuple<Smart, Calendar>> switchTimes = new ArrayList<>();
    TimeChecker timeChecker = Main.timeChecker;

    public List<Tuple<Smart,Calendar>> getSwitchTimes() {
        return switchTimes;
    }

    public void UpdateSwitchTimes() {
        for (Smart device : Main.smartList) {
            if (device.switchTime != null) {
                SetSwitchTimes(device.getName(), device.switchTime);
            } else {
                switchTimes.add(new Tuple<Smart, Calendar>(device, null));
            }
        }   
    }

    public void SwitchTimesBetweenDates() {
        timeChecker = Main.timeChecker;
        Calendar currentDate = timeChecker.getCurrentDate();
        Calendar oldDate = timeChecker.getOldDate();
        
        for (Tuple<Smart, Calendar> switchTime : switchTimes) {
            if (switchTime.getY() == null) continue;
            if (switchTime.getY().after(oldDate) && switchTime.getY().before(currentDate)) {
                switchTime.getX().setOn(!switchTime.getX().isOn());
            }
        }
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
    }
    
    public void sortSwitchTimes() {
        for (int i = 0; i < switchTimes.size(); i++) {
            for (int j = 0; j < switchTimes.size() - 1; j++) {
                if (switchTimes.get(j).getY() == null) continue;
                if (switchTimes.get(j).getY().after(switchTimes.get(j + 1).getY())) {
                    Tuple<Smart, Calendar> temp = switchTimes.get(j);
                    switchTimes.set(j, switchTimes.get(j + 1));
                    switchTimes.set(j + 1, temp);
                }
            }
        }
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
