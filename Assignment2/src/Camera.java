public class Camera extends Smart {
    private int storage;
    private int mbps;

    public Camera(String name, int mbps) {
        super.name = name;
        if (mbps < 0) {
            throw new IllegalArgumentException("Mbps must be greater than 0");
        } this.mbps = mbps;
        this.storage = 0;
    }

    public Camera(String name, int mbps, String isOn) {
        super.name = name;
        if (mbps < 0) {
            throw new IllegalArgumentException("Mbps must be greater than 0");
        } this.mbps = mbps;
        if (isOn.equals("On")) {
            this.isOn = true;
        } else if (isOn.equals("Off")) {
            this.isOn = false;
        } else {
            throw new IllegalArgumentException("isOn must be either \"on\" or \"off\"");
        }
        this.storage = 0;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public int getMbps() {
        return mbps;
    }

    public void setMbps(int mbps) {
        this.mbps = mbps;
    }
}
