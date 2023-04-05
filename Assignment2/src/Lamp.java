public class Lamp extends Smart {
    protected int kelvin, brightness;

    public Lamp() {
        super.name = "Lamp";
        this.kelvin = 4000;
        this.brightness = 100;
        this.isOn = false;
    }

    public Lamp(String name) {
        super.name = name;
        this.kelvin = 4000;
        this.brightness = 100;
        this.isOn = false;
    }

    public Lamp(String name, String isOn) {
        super.name = name;
        if (isOn.equals("On")) {
            this.isOn = true;
        } else if (isOn.equals("Off")) {
            this.isOn = false;
        } else {
            throw new IllegalArgumentException("isOn must be either \"on\" or \"off\"");
        }
        this.kelvin = 4000;
        this.brightness = 100;
    }

    public Lamp(String name, String isOn, int kelvin, int brightness) {
        super.name = name;
        if (isOn.equals("On")) {
            this.isOn = true;
        } else if (isOn.equals("Off")) {
            this.isOn = false;
        } else {
            throw new IllegalArgumentException("isOn must be either \"on\" or \"off\"");
        }
        if (kelvin < 2000 || kelvin > 6500) {
            throw new IllegalArgumentException("Kelvin must be between 0 and 10000");
        } this.kelvin = kelvin;
        if (brightness < 0 || brightness > 100) {
            throw new IllegalArgumentException("Brightness must be between 0 and 100");
        } this.brightness = brightness;
    }

    public int getKelvin() {
        return kelvin;
    }

    public void setKelvin(int kelvin) {
        this.kelvin = kelvin;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    @Override
    public String WriteInfo() {
        String type = "Smart Lamp";
        String kelvinValue = this.kelvin + "K";
        String onOff = this.isOn ? "on" : "off";
        String brightness = this.brightness + "%";
        String timetoSwitch = super.switchTime == null ? "null" : TimeChecker.formatter.format(super.switchTime.getTime());

        return type + " " + super.name + " is " + onOff + " and its kelvin value is " + kelvinValue + " with " + brightness + " brightness, and its time to switch its status is " + timetoSwitch + ".";
    }

    @Override
    public String WriteZReport() {
        String type = "Smart Lamp";
        String kelvinValue = this.kelvin + "K";
        String onOff = this.isOn ? "on" : "off";
        String brightness = this.brightness + "%";
        String timetoSwitch = super.switchTime == null ? "null" : TimeChecker.formatter.format(super.switchTime.getTime());

        return type + " " + super.name + " is " + onOff + " and its kelvin value is " + kelvinValue + " with " + brightness + " brightness, and its time to switch its status is " + timetoSwitch + ".";
    }
}

class ColoredLamp extends Lamp {
    private String color;
    private boolean isColorOn;
    
    public ColoredLamp(String name) {
        super.name = name;
        this.color = "FFFFFF";
        this.isColorOn = false;
        this.isOn = false;
    }

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isColorOn() {
        return isColorOn;
    }

    public void setColorOn(boolean isColorOn) {
        this.isColorOn = isColorOn;
    }

    @Override
    public String WriteInfo() {
        String type = "Smart Color Lamp";
        String onOff = this.isOn ? "on" : "off";
        String color = this.isColorOn ? this.color : this.kelvin + "K";
        String brightness = this.brightness + "%";
        String timetoSwitch = super.switchTime == null ? "null" : TimeChecker.formatter.format(super.switchTime.getTime());

        return type + " " + super.name + " is " + onOff + " and its color value is " + color + " with " + brightness + " brightness, and its time to switch its status is " + timetoSwitch + ".";
    }

    @Override
    public String WriteZReport() {
        String type = "Smart Color Lamp";
        String onOff = this.isOn ? "on" : "off";
        String color = this.isColorOn ? this.color : this.kelvin + "K";
        String brightness = this.brightness + "%";
        String timetoSwitch = super.switchTime == null ? "null" : TimeChecker.formatter.format(super.switchTime.getTime());

        return type + " " + super.name + " is " + onOff + " and its color value is " + color + " with " + brightness + " brightness, and its time to switch its status is " + timetoSwitch + ".";
    }
}
