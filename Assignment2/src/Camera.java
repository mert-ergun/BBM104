/**
 * Camera class is a subclass of Smart class.
 * It has a name, mbps, storage, and isOn attributes.
 * It contains methods to calculate storage and write info.
 * It also contains (overrides) the methods to write info and z-report.
 */
public class Camera extends Smart {
    private double storage, mbps; // storage and mbps of the camera

    /**
     * Constructor for the class.
     * @param name - name of the camera
     * @param mbps - mbps of the camera
     */
    public Camera(String name, double mbps) {
        super.name = name;
        this.mbps = mbps;
        this.storage = 0;
    }

    /**
     * Constructor for the class.
     * @param name - name of the camera
     * @param mbps - mbps of the camera
     * @param isOn - on/off status of the camera
     */
    public Camera(String name, double mbps, String isOn) {
        super.name = name;
        this.mbps = mbps;
        if (isOn.equals("On")) {
            this.isOn = true;
            this.setLastSwitchedDate(Main.timeChecker.getCurrentDate());
        } else if (isOn.equals("Off")) {
            this.isOn = false;
        }
        this.storage = 0;
    }

    /**
     * This method calculates the storage of the camera.
     * @param mbps - mbps of the camera
     * @param time - time in seconds
     * @return storage of the camera
     */
    public double calculateStorage(double mbps, double time) {
        return mbps * time;
    }

    /**
     * This method returns the storage of the camera.
     * @return storage of the camera
     */
    public double getStorage() {
        return storage;
    }

    /**
     * This method sets the storage of the camera.
     * @param storage - storage of the camera
     */
    public void setStorage(double storage) {
        this.storage = storage;
    }

    /**
     * This method returns the mbps of the camera.
     * @return mbps of the camera
     */
    public double getMbps() {
        return mbps;
    }

    /**
     * This method sets the mbps of the camera.
     * @param mbps - mbps of the camera
     */
    public void setMbps(double mbps) {
        this.mbps = mbps;
    }

    /**
     * This method writes the info of the camera.
     * @return a string containing the info of the camera
     */
    @Override
    public String writeInfo() {
        String type = "Smart Camera";
        String name = super.name;
        String isOn = this.isOn ? "on" : "off";
        String storage = String.format("%.2f", this.storage);
        String timetoSwitch = super.switchTime == null ? "null" : TimeChecker.formatter.format(super.switchTime.getTime());

        return type + " " + name + " is " + isOn + " and used " + storage + " MB of storage so far (excluding current status), and its time to switch its status is " + timetoSwitch + ".";
    }

    /**
     * This method writes the z-report of the camera.
     * @return a string containing the z-report of the camera
     */
    @Override
    public String writeZReport() {
        String type = "Smart Camera";
        String name = super.name;
        String isOn = this.isOn ? "on" : "off";
        String storage = String.format("%.2f", this.storage);
        String timetoSwitch = super.switchTime == null ? "null" : TimeChecker.formatter.format(super.switchTime.getTime());

        return type + " " + name + " is " + isOn + " and used " + storage + " MB of storage so far (excluding current status), and its time to switch its status is " + timetoSwitch + ".";
    }
}
