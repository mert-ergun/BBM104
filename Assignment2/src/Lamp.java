public class Lamp extends Smart {
    private int kelvin, brightness;

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

    public Lamp(String name, int kelvin, int brightness) {
        super.name = name;
        if (kelvin < 2000 || kelvin > 6500) {
            throw new IllegalArgumentException("Kelvin must be between 0 and 10000");
        } this.kelvin = kelvin;
        if (brightness < 0 || brightness > 100) {
            throw new IllegalArgumentException("Brightness must be between 0 and 100");
        } this.brightness = brightness;
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

    public ColoredLamp(String name, String color) {
        super.name = name;
        if (color.length() != 6) {
            throw new IllegalArgumentException("Color must be a 6-digit hexadecimal number");
        } else if (color.matches("[0-9A-Fa-f]+") == false) {
            throw new IllegalArgumentException("Color must be a 6-digit hexadecimal number");
        } 
        this.color = color;
        this.isColorOn = true;
    }
}
