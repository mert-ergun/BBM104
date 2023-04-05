import java.util.Calendar;

public abstract class Smart {
    protected String name;
    protected boolean isOn;
    protected Calendar switchTime;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isOn() {
        return isOn;
    }
    public void setOn(boolean isOn) {
        this.isOn = isOn;
    }

    public String WriteInfo() {
        return "";
    }

    public String WriteZReport() {
        return "";
    }
}
