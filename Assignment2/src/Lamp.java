public class Lamp extends Smart {
    private int kelvin, brightness;
}

class ColoredLamp extends Lamp {
    private String color;
    private int decimalColor = Integer.parseInt(color, 16);
    
}
