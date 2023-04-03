public class Plug extends Smart {
    private int totalEnergy;
    private int ampere;
    private boolean isPlugged;
    private final int VOLTAGE = 220;

    public Plug() {
        this.totalEnergy = 0;
        this.isPlugged = false;
    }

    public Plug(boolean isPlugged) {
        this.totalEnergy = 0;
        this.isPlugged = isPlugged;
    }

    public int calculateEnergy(int ampere, int time) {
        return ampere * VOLTAGE * time;
    }
}
