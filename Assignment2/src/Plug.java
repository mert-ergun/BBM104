/**
 * This class is a subclass of Smart class. It is used to create a smart plug.
 * It contains the properties of a smart plug.
 * It contains the methods to calculate the energy consumed by the plug.
 * It also contains (overrides) the methods to write info and z-report.
 */
public class Plug extends Smart {
    private double totalEnergy;  // total energy consumed by the plug
    private double ampere;  // ampere of the plug
    private boolean isPlugged;  // plugged status of the plug
    private final int VOLTAGE = 220;  // voltage of the plug (constant)

    /**
     * Constructor for the class.
     * @param name name of the plug
     */
    public Plug(String name) {
        super.name = name;
        this.totalEnergy = 0;
        this.isPlugged = false;
        this.isOn = false;
    }

    /**
     * Constructor for the class.
     * @param name
     * @param isOn
     */
    public Plug(String name, String isOn) {
        super.name = name;
        this.totalEnergy = 0;
        if (isOn.equals("On")) {
            this.isOn = true;
            this.setLastSwitchedDate(Main.timeChecker.getCurrentDate());
        } else if (isOn.equals("Off")) {
            this.isOn = false;
        }
        this.isPlugged = false;
    }

    /**
     * Constructor for the class.
     * @param name
     * @param isOn
     * @param ampere
     */
    public Plug(String name, String isOn, double ampere) {
        super.name = name;
        this.totalEnergy = 0;
        if (isOn.equals("On")) {
            this.isOn = true;
            this.setLastSwitchedDate(Main.timeChecker.getCurrentDate());
        } else if (isOn.equals("Off")) {
            this.isOn = false;
        }
        this.ampere = ampere;
        this.isPlugged = false;
    }

    /**
     * Calculate the energy consumed by the plug.
     * Its formula is: energy = ampere * voltage * time (in hours)
     * @param ampere
     * @param time
     * @return energy consumed by the plug
     */
    public double calculateEnergy(double ampere, double time) {
        return ampere * VOLTAGE * time;
    }

    /**
     * Get the total energy consumed by the plug.
     * @param ampere
     * @param voltage
     * @param time
     * @return total energy consumed by the plug
     */
    public double getTotalEnergy() {
        return totalEnergy;
    }

    /**
     * Set the total energy consumed by the plug.
     * @param totalEnergy - total energy consumed by the plug
     */
    public void setTotalEnergy(double totalEnergy) {
        this.totalEnergy = totalEnergy;
    }

    /**
     * Get the ampere of the device that is plugged into the plug.
     * @return ampere of the device
     */
    public double getAmpere() {
        return ampere;
    }

    /**
     * Set the ampere of the device that is plugged into the plug.
     * @param ampere - ampere of the device that is plugged into the plug
     */
    public void setAmpere(double ampere) {
        this.ampere = ampere;
    }

    /**
     * Get the plugged status of the plug.
     * If the plug is plugged, return true.
     * @return true if the plug is plugged, false otherwise
     */
    public boolean isPlugged() {
        return isPlugged;
    }

    /**
     * Set the plugged status of the plug.
     * @param isPlugged - plugged status of the plug
     */
    public void setPlugged(boolean isPlugged) {
        this.isPlugged = isPlugged;
    }

    /**
     * Get the voltage of the plug.
     * It is a constant. It is 220V.
     * @return voltage of the plug
     */
    public int getVOLTAGE() {
        return VOLTAGE;
    }

    /**
     * Override the method to write info.
     * @return string that contains the info of the plug
     */
    @Override
    public String writeInfo() {
        calculateEnergy(ampere, VOLTAGE);
        String type = "Smart Plug";
        String name = super.name;
        String onOff = this.isOn ? "on" : "off";
        String consumedPower = String.format("%.2f", this.totalEnergy);
        String timetoSwitch = super.switchTime == null ? "null" : TimeChecker.formatter.format(super.switchTime);

        return type + " " + name + " is " + onOff + " and consumed " + consumedPower + "W so far (excluding current device), and its time to switch its status is " + timetoSwitch + ".";
    }

    /**
     * Override the method to write z-report.
     * @return string that contains the z-report of the plug
     */
    @Override
    public String writeZReport() {
        String type = "Smart Plug";
        String name = super.name;
        String onOff = this.isOn ? "on" : "off";
        String consumedPower = String.format("%.2f", this.totalEnergy);
        String timetoSwitch = super.switchTime == null ? "null" : TimeChecker.formatter.format(super.switchTime.getTime());

        return type + " " + name + " is " + onOff + " and consumed " + consumedPower + "W so far (excluding current device), and its time to switch its status is " + timetoSwitch + ".";
    }
}
