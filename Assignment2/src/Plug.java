public class Plug extends Smart {
    private static int totalPlugs = 0;
    private double totalEnergy;
    private double ampere;
    private boolean isPlugged;
    private final int VOLTAGE = 220;

    public Plug(String name) {
        super.name = name;
        this.totalEnergy = 0;
        this.isPlugged = false;
        totalPlugs++;
    }

    public Plug(String name, String isOn) {
        super.name = name;
        this.totalEnergy = 0;
        if (isOn.equals("On")) {
            this.isOn = true;
        } else if (isOn.equals("Off")) {
            this.isOn = false;
        }
        totalPlugs++;
    }

    public Plug(String name, String isOn, double ampere) {
        super.name = name;
        this.totalEnergy = 0;
        if (isOn.equals("On")) {
            this.isOn = true;
        } else if (isOn.equals("Off")) {
            this.isOn = false;
        } else {
            throw new IllegalArgumentException("isOn must be either \"on\" or \"off\"");
        }
        if (ampere < 0) {
            throw new IllegalArgumentException("Ampere must be greater than 0");
        } this.ampere = ampere;
        totalPlugs++;
    }

    public double calculateEnergy(double ampere, int time) {
        return ampere * VOLTAGE * time;
    }

    public static int getTotalPlugs() {
        return totalPlugs;
    }

    public static void setTotalPlugs(int totalPlugs) {
        Plug.totalPlugs = totalPlugs;
    }

    public double getTotalEnergy() {
        return totalEnergy;
    }

    public void setTotalEnergy(double totalEnergy) {
        this.totalEnergy = totalEnergy;
    }

    public double getAmpere() {
        return ampere;
    }

    public void setAmpere(double ampere) {
        this.ampere = ampere;
    }

    public boolean isPlugged() {
        return isPlugged;
    }

    public void setPlugged(boolean isPlugged) {
        this.isPlugged = isPlugged;
    }

    public int getVOLTAGE() {
        return VOLTAGE;
    }
}
