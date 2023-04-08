public class Camera extends Smart {
    private double storage;
    private double mbps;

    public Camera(String name, double mbps) {
        super.name = name;
        if (mbps < 0) {
            throw new IllegalArgumentException("Mbps must be greater than 0");
        } this.mbps = mbps;
        this.storage = 0;
    }

    public Camera(String name, double mbps, String isOn) {
        super.name = name;
        if (mbps < 0) {
            throw new IllegalArgumentException("Mbps must be greater than 0");
        } this.mbps = mbps;
        if (isOn.equals("On")) {
            this.isOn = true;
            this.setLastSwitchedDate(Main.timeChecker.getCurrentDate());
        } else if (isOn.equals("Off")) {
            this.isOn = false;
        } else {
            throw new IllegalArgumentException("isOn must be either \"on\" or \"off\"");
        }
        this.storage = 0;
    }

    public double calculateStorage(double mbps, double time) {
        return mbps * time;
    }

    public double getStorage() {
        return storage;
    }

    public void setStorage(double storage) {
        this.storage = storage;
    }

    public double getMbps() {
        return mbps;
    }

    public void setMbps(double mbps) {
        this.mbps = mbps;
    }

    @Override
    public String WriteInfo() {
        String type = "Smart Camera";
        String name = super.name;
        String isOn = this.isOn ? "on" : "off";
        String storage = String.format("%.2f", this.storage);
        String timetoSwitch = super.switchTime == null ? "null" : TimeChecker.formatter.format(super.switchTime.getTime());

        return type + " " + name + " is " + isOn + " and used " + storage + " MB of storage so far (excluding current status), and its time to switch its status is " + timetoSwitch + ".";
    }

    @Override
    public String WriteZReport() {
        String type = "Smart Camera";
        String name = super.name;
        String isOn = this.isOn ? "on" : "off";
        String storage = String.format("%.2f", this.storage);
        String timetoSwitch = super.switchTime == null ? "null" : TimeChecker.formatter.format(super.switchTime.getTime());

        return type + " " + name + " is " + isOn + " and used " + storage + " MB of storage so far (excluding current status), and its time to switch its status is " + timetoSwitch + ".";
    }
}
