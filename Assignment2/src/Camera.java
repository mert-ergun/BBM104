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
}
