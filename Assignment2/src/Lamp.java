/**
 * Lamp class is a subclass of Smart class.
 * It has 4 constructors and several methods.
 * It overrides the methods to write info and z-report.
 */
public class Lamp extends Smart {
    protected int kelvin, brightness;  // kelvin and brightness of the lamp

    /**
     * Constructor for the class.
     * Used for constructor for ColorLamp class.
     */
    public Lamp() {
        super.name = "Lamp";
        this.kelvin = 4000;
        this.brightness = 100;
        this.isOn = false;
    }

    /**
     * Constructor for the class.
     * @param name - name of the lamp
     */
    public Lamp(String name) {
        super.name = name;
        this.kelvin = 4000;
        this.brightness = 100;
        this.isOn = false;
    }

    /**
     * Constructor for the class.
     * @param name - name of the lamp
     * @param isOn - on/off status of the lamp
     */
    public Lamp(String name, String isOn) {
        super.name = name;
        if (isOn.equals("On")) {
            this.isOn = true;
        } else if (isOn.equals("Off")) {
            this.isOn = false;
        }
        this.kelvin = 4000;
        this.brightness = 100;
    }

    /**
     * Constructor for the class.
     * @param name - name of the lamp
     * @param isOn - on/off status of the lamp
     * @param kelvin - kelvin value of the lamp
     * @param brightness - brightness of the lamp
     */
    public Lamp(String name, String isOn, int kelvin, int brightness) {
        super.name = name;
        if (isOn.equals("On")) {
            this.isOn = true;
        } else if (isOn.equals("Off")) {
            this.isOn = false;
        }
        this.kelvin = kelvin;
        this.brightness = brightness;
    }

    /**
     * This method returns the kelvin value of the lamp.
     * @return kelvin value of the lamp
     */
    public int getKelvin() {
        return kelvin;
    }

    /**
     * This method sets the kelvin value of the lamp.
     * @param kelvin - kelvin value of the lamp
     */
    public void setKelvin(int kelvin) {
        this.kelvin = kelvin;
    }

    /**
     * This method returns the brightness of the lamp.
     * @return brightness of the lamp
     */
    public int getBrightness() {
        return brightness;
    }

    /**
     * This method sets the brightness of the lamp.
     * @param brightness - brightness of the lamp
     */
    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    /**
     * This method overrides the method to write info of the device.
     * @return info of the device
     */
    @Override
    public String writeInfo() {
        String type = "Smart Lamp";
        String kelvinValue = this.kelvin + "K";
        String onOff = this.isOn ? "on" : "off";
        String brightness = this.brightness + "%";
        String timetoSwitch = super.switchTime == null ? "null" : TimeChecker.formatter.format(super.switchTime.getTime());

        return type + " " + super.name + " is " + onOff + " and its kelvin value is " + kelvinValue + " with " + brightness + " brightness, and its time to switch its status is " + timetoSwitch + ".";
    }

    /**
     * This method overrides the method to write z-report of the device.
     * @return z-report of the device
     */
    @Override
    public String writeZReport() {
        String type = "Smart Lamp";
        String kelvinValue = this.kelvin + "K";
        String onOff = this.isOn ? "on" : "off";
        String brightness = this.brightness + "%";
        String timetoSwitch = super.switchTime == null ? "null" : TimeChecker.formatter.format(super.switchTime.getTime());

        return type + " " + super.name + " is " + onOff + " and its kelvin value is " + kelvinValue + " with " + brightness + " brightness, and its time to switch its status is " + timetoSwitch + ".";
    }
}

/**
 * ColoredLamp class is a subclass of Lamp class.
 * It has 3 constructors and several methods.
 * It overrides the methods to write info and z-report.
 */
class ColoredLamp extends Lamp {
    private String color; // color of the lamp (hexadecimal)
    private boolean isColorOn; // on/off status of the color

    /**
     * Constructor for the class.
     * @param name - name of the lamp
     */
    public ColoredLamp(String name) {
        super.name = name;
        this.color = "FFFFFF";
        this.isColorOn = false;
        this.isOn = false;
    }

    /**
     * Constructor for the class.
     * @param name - name of the lamp
     * @param isOn - on/off status of the lamp
     */
    public ColoredLamp(String name, String isOn) {
        super.name = name;
        if (isOn.equals("On")) {
            this.isOn = true;
        } else if (isOn.equals("Off")) {
            this.isOn = false;
        }
        this.color = "FFFFFF";
        this.isColorOn = false;
    }

    /**
     * Constructor for the class.
     * @param name - name of the lamp
     * @param isOn - on/off status of the lamp
     * @param kelvin - kelvin value of the lamp
     * @param brightness - brightness of the lamp
     */
    public ColoredLamp(String name, String isOn, int kelvin, int brightness) {
        super.name = name;
        if (isOn.equals("On")) {
            this.isOn = true;
        } else if (isOn.equals("Off")) {
            this.isOn = false;
        }
        this.kelvin = kelvin;
        this.brightness = brightness;
        this.color = "FFFFFF";
        this.isColorOn = false;
    }

    /**
     * Constructor for the class.
     * @param name - name of the lamp
     * @param isOn - on/off status of the lamp
     * @param color - color of the lamp (hexadecimal)
     * @param brightness - brightness of the lamp
     */
    public ColoredLamp(String name, String isOn, String color, int brightness) {
        super.name = name;
        if (isOn.equals("On")) {
            this.isOn = true;
        } else if (isOn.equals("Off")) {
            this.isOn = false;
        }
        this.color = color;
        this.isColorOn = true;
        this.brightness = brightness;
    }

    /**
     * This method returns the color of the lamp.
     * @return color of the lamp
     */
    public String getColor() {
        return color;
    }

    /**
     * This method sets the color of the lamp.
     * @param color - color of the lamp
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * This method returns the on/off status of the color.
     * @return on/off status of the color
     */
    public boolean isColorOn() {
        return isColorOn;
    }

    /**
     * This method sets the on/off status of the color.
     * @param isColorOn - on/off status of the color
     */
    public void setColorOn(boolean isColorOn) {
        this.isColorOn = isColorOn;
    }

    /**
     * This method overrides the method to write info of the device.
     * @return info of the device
     */
    @Override
    public String writeInfo() {
        String type = "Smart Color Lamp";
        String onOff = this.isOn ? "on" : "off";
        String color = this.isColorOn ? "0x" + String.format("%06X", Integer.parseInt(this.color, 16)).toUpperCase() : this.kelvin + "K";
        String brightness = this.brightness + "%";
        String timetoSwitch = super.switchTime == null ? "null" : TimeChecker.formatter.format(super.switchTime.getTime());

        return type + " " + super.name + " is " + onOff + " and its color value is " + color + " with " + brightness + " brightness, and its time to switch its status is " + timetoSwitch + ".";
    }

    /**
     * This method overrides the method to write z-report of the device.
     * @return z-report of the device
     */
    @Override
    public String writeZReport() {
        String type = "Smart Color Lamp";
        String onOff = this.isOn ? "on" : "off";
        String color = this.isColorOn ? "0x" + String.format("%06X", Integer.parseInt(this.color, 16)).toUpperCase() : this.kelvin + "K";
        String brightness = this.brightness + "%";
        String timetoSwitch = super.switchTime == null ? "null" : TimeChecker.formatter.format(super.switchTime.getTime());

        return type + " " + super.name + " is " + onOff + " and its color value is " + color + " with " + brightness + " brightness, and its time to switch its status is " + timetoSwitch + ".";
    }
}
