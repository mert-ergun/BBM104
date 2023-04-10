import java.util.Calendar;

/**
 * Abstract class for all smart devices.
 * It contains all the common properties.
 * It contains name, on/off status, last switched date, last auto-switched date.
 * It also contains methods to write info and z-report.
 */
public abstract class Smart {
    protected String name; // name of the device
    protected boolean isOn; // on/off status
    public Calendar switchTime; // time to switch on/off
    protected Calendar lastSwitchedDate; // last switched date
    protected Calendar lastRemoteSwitch; // last auto-switched date
    protected boolean isReversed = false;

    /**
     * Get the name of the device.
     * @return name of the device
     */
    public String getName() {
        return name;
    }
    /**
     * Set the name of the device.
     * @param name name of the device
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Get the on/off status of the device.
     * @return on/off status of the device
     */
    public boolean isOn() {
        return isOn;
    }
    /**
     * Set the on/off status of the device.
     * @param isOn on/off status of the device
     */
    public void setOn(boolean isOn) {
        this.isOn = isOn;
    }
    /**
     * Get if the device is reversed in the schedule.
     * @return true if the device is reversed, false otherwise
     */
    public boolean isReversed() {
        return isReversed;
    }
    /**
     * Set if the device is reversed in the schedule.
     * @param isReversed - true if the device is reversed, false otherwise
     */
    public void setReversed(boolean isReversed) {
        this.isReversed = isReversed;
    }
    /**
     * Get the last switched time.
     * @return last switched time
     */
    public Calendar getLastSwitchedDate() {
        return lastSwitchedDate;
    }
    /**
     * Set the last switched time.
     * @param lastSwitchedDate last switched time
     */
    public void setLastSwitchedDate(Calendar lastSwitchedDate) {
        this.lastSwitchedDate = (Calendar)lastSwitchedDate.clone();
    }
    /**
     * Writes the info of the device.
     * @return a string containing the info of the device
     */
    public String writeInfo() {
        return "";
    }
    /**
     * Writes the z-report of the device.
     * @return a string containing the z-report of the device
     */
    public String writeZReport() {
        return "";
    }
}
