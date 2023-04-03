public class Lamp extends Smart {
    protected int kelvin, brightness;

    public Lamp() {
        super.name = "Lamp";
        this.kelvin = 4000;
        this.brightness = 100;
    }

    public Lamp(String name) {
        super.name = name;
        this.kelvin = 4000;
        this.brightness = 100;
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
}

class ColoredLamp extends Lamp {
    private String color;
    private int decimalColor = Integer.parseInt(color, 16);
    private boolean isColorOn;
    
    public ColoredLamp(String name) {
        super.name = name;
        this.color = "FFFFFF";
        this.isColorOn = false;
    }

    public ColoredLamp(String name, String isOn) {
        super.name = name;
        if (isOn.equals("On")) {
            this.isOn = true;
        } else if (isOn.equals("Off")) {
            this.isOn = false;
        } else {
            throw new IllegalArgumentException("isOn must be either \"on\" or \"off\"");
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
        } else {
            throw new IllegalArgumentException("isOn must be either \"on\" or \"off\"");
        }
        if (kelvin < 2000 || kelvin > 6500) {
            throw new IllegalArgumentException("Kelvin must be between 0 and 10000");
        } this.kelvin = kelvin;
        if (brightness < 0 || brightness > 100) {
            throw new IllegalArgumentException("Brightness must be between 0 and 100");
        } this.brightness = brightness;
        this.color = "FFFFFF";
        this.isColorOn = false;
    }

    public ColoredLamp(String name, String isOn, String color, int brightness) {
        super.name = name;
        if (isOn.equals("On")) {
            this.isOn = true;
        } else if (isOn.equals("Off")) {
            this.isOn = false;
        } else {
            throw new IllegalArgumentException("isOn must be either \"on\" or \"off\"");
        }
        if (color.length() != 6) {
            throw new IllegalArgumentException("Color must be a 6-digit hexadecimal number");
        } else if (color.matches("[0-9A-Fa-f]+") == false) {
            throw new IllegalArgumentException("Color must be a 6-digit hexadecimal number");
        } 
        this.color = color;
        this.isColorOn = true;
        if (brightness < 0 || brightness > 100) {
            throw new IllegalArgumentException("Brightness must be between 0 and 100");
        } this.brightness = brightness;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDecimalColor() {
        return decimalColor;
    }

    public void setDecimalColor(int decimalColor) {
        this.decimalColor = decimalColor;
    }

    public boolean isColorOn() {
        return isColorOn;
    }

    public void setColorOn(boolean isColorOn) {
        this.isColorOn = isColorOn;
    }
}
