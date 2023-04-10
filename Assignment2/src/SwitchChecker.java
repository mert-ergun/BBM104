import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Class for switch checker.
 * It contains the list of switch times.
 * It also contains methods to create, update, sort and get the switch times.
 */
public class SwitchChecker {
    List<Tuple<Smart, Calendar>> switchTimes = new ArrayList<>();  // list of switch times, each switch time is a tuple of device and time
    TimeChecker timeChecker = Main.timeChecker;  // time checker object to get the current date

    /**
     * Constructor for the class.
     */
    public void createSwitchTimes() {
        for (Smart device : Main.smartList) {
            switchTimes.add(new Tuple<Smart, Calendar>(device, null));
        }
    }

    /**
     * Get the switch times.
     * @return list of switch times
     */
    public List<Tuple<Smart,Calendar>> getSwitchTimes() {
        return switchTimes;
    }

    /**
     * Update the switch times in the list.
     * If a smart device has added to smart list, add it to the switch times list.
     */
    public void updateSwitchTimes() {
        for (Smart device : Main.smartList) {
            if (device.switchTime != null) {
                setSwitchTimes(device.getName(), device.switchTime);
            } else {
                setSwitchTimes(device.getName(), null);
            }
        }   
    }

    /**
     * Switch time items in the list according to the time.
     * If a switch time has passed, switch the device.
     * If device is a plug, calculate the energy.
     * If device is a camera, calculate the storage.
     * Sort and update the switch times.
     */
    public void switchTimesBetweenDates() {
        timeChecker = Main.timeChecker;
        Calendar currentDate = (Calendar) timeChecker.getCurrentDate().clone();
        Calendar oldDate = (Calendar) timeChecker.getOldDate().clone();
    
        for (Tuple<Smart, Calendar> switchTime : new ArrayList<>(switchTimes)) {
            if (switchTime.getY() == null) continue;
            if ((switchTime.getY().after(oldDate) && switchTime.getY().before(currentDate)) || switchTime.getY().equals(oldDate) || switchTime.getY().equals(currentDate)) {
                if (switchTime.getX().getLastSwitchedDate() != null) {
                    long diffInMillis = switchTime.getY().getTimeInMillis() - switchTime.getX().getLastSwitchedDate().getTimeInMillis();
                    double diffInHours = diffInMillis / (1000.0 * 60.0 * 60.0);
                    double diffInMinutes = diffInMillis / (1000.0 * 60.0);
                    if (switchTime.getX() instanceof Plug && ((Plug) switchTime.getX()).isPlugged() && switchTime.getX().isOn()) {
                        ((Plug) switchTime.getX()).setTotalEnergy(((Plug) switchTime.getX()).getTotalEnergy() + 
                        ((Plug) switchTime.getX()).calculateEnergy(((Plug) switchTime.getX()).getAmpere(), diffInHours));
                    }
                    if (switchTime.getX() instanceof Camera && switchTime.getX().isOn()) {
                        ((Camera) switchTime.getX()).setStorage(((Camera) switchTime.getX()).getStorage() + 
                        ((Camera) switchTime.getX()).calculateStorage(((Camera) switchTime.getX()).getMbps(), diffInMinutes));
                    }
                }
                switchTime.getX().setOn(!switchTime.getX().isOn());
                switchTime.getX().setLastSwitchedDate((Calendar)switchTime.getX().switchTime.clone());
                switchTime.getX().lastRemoteSwitch = (switchTime.getX().switchTime == null) ? null : (Calendar) switchTime.getX().switchTime.clone();
                switchTime.getX().switchTime = null;
                switchTime.setY(null);
                sortSwitchTimes();
            }
        }
        List<Tuple<Smart, Calendar>> sameLastSwitchedDateList = new ArrayList<>();
        for (int i = 0; i < switchTimes.size(); i++) {
            Tuple<Smart, Calendar> switchTime = switchTimes.get(i);
            if (switchTime.getX().getLastSwitchedDate() == null) continue;
            sameLastSwitchedDateList.clear();
            sameLastSwitchedDateList.add(switchTime);
            int j = i + 1;
            while (j < switchTimes.size() && switchTimes.get(j).getX().lastRemoteSwitch != null && switchTimes.get(j).getX().lastRemoteSwitch.equals(switchTime.getX().lastRemoteSwitch)) {
                sameLastSwitchedDateList.add(switchTimes.get(j));
                j++;
            }
            Collections.reverse(sameLastSwitchedDateList);
            for (int k = 0; k < sameLastSwitchedDateList.size(); k++) {
                switchTimes.set(i + k, sameLastSwitchedDateList.get(k));
            }
            i = j - 1;
        }
        updateSwitchTimes();
    }

    /**
     * Jump to the next switch time.
     */
    public void jumpToNop() throws Exception {
        timeChecker = Main.timeChecker;
        Calendar currentDate = (Calendar)timeChecker.getCurrentDate().clone();
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
            String timetoSwitch = TimeChecker.formatter.format(nextSwitch.getTime());
            timeChecker.setTime(timetoSwitch);
        }
    }

    /**
     * Set the switch time of a device.
     * @param name - name of the device
     * @param switchTime - switch time of the device
     */
    public void setSwitchTimes(String name, Calendar switchTime) {
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
                switchTimes.setY((switchTimes.getX().switchTime == null) ? null : (Calendar) switchTimes.getX().switchTime.clone());
                return;
            } 
        }
        switchTimes.add(new Tuple<Smart, Calendar>(smartDevice, switchTime));
        updateSwitchTimes();
        sortSwitchTimes();
    }
    
    /**
     * Sort the switch times according to the time.
     * If a switch time is null, put it to the end of the list.
     */
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
        updateSwitchTimes();
    }

    /**
     * Check if there is a switch time.
     * @return true if there is a switch time, false if there is not
     */
    public boolean checkSwitchTimes() {
        sortSwitchTimes();
        for (Tuple<Smart, Calendar> switchTime : switchTimes) {
            if (switchTime.getY() == null) continue;
            return true;
        }
        return false;
    }
}
