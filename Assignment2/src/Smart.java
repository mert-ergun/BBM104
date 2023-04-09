import java.util.Calendar;

public abstract class Smart {
    protected String name;
    protected boolean isOn;
    public Calendar switchTime;
    protected Calendar lastSwitchedDate;
    protected Calendar lastRemoteSwitch;

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
    public Calendar getLastSwitchedDate() {
        return lastSwitchedDate;
    }
    public void setLastSwitchedDate(Calendar lastSwitchedDate) {
        this.lastSwitchedDate = (Calendar)lastSwitchedDate.clone();
    }
    
    public String writeInfo() {
        return "";
    }

    public String writeZReport() {
        return "";
    }
}
