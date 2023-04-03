import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class InputReader{
    public static List<String> ReadInput() throws Exception{
        BufferedReader br = new BufferedReader(new BufferedReader(new FileReader("input.txt")));
        String line;
        List<String> lines = new ArrayList<String>();
        while ((line = br.readLine()) != null) {
            String[] split = line.split("\t");
            DetermineCommand(split[0]);
            for (String s : split) {
                lines.add(s);
            }
        }
        br.close();
        return lines;
    }

    public static void DetermineCommand(String command) {
        switch (command) {
            case "SetInitialTime":
                //TODO
                break;
            case "SetTime":
                //TODO
                break;
            case "SkipMinutes":
                //TODO
                break;
            case "Nop":
                //TODO
                break;
            case "Add":
                //TODO
                break;
            case "Remove":
                //TODO
                break;
            case "SetSwitchTime":
                //TODO
                break;
            case "Switch":
                //TODO
                break;
            case "ChangeName":
                //TODO
                break;
            case "PlugIn":
                //TODO
                break;
            case "PlugOut":
                //TODO
                break;
            case "SetBrightness":
                //TODO
                break;
            case "SetKelvin":
                //TODO
                break;
            case "SetColorCode":
                //TODO
                break;
            case "SetWhite":
                //TODO
                break;
            case "Color":
                //TODO
                break;
            case "ZReport":
                //TODO
                break;
            default:
                break;
        }
    }

    public static void SetInitialTimeCommand(Calendar time) {
        // TODO set initial time
    }

    public static void SetTimeCommand(Calendar time) {
        // TODO set time
    }

    public static void SkipMinutesCommand(int minutes) {
        // TODO skip minutes
    }

    public static void NopCommand() {
        // TODO nop
    }

    public static void AddCommands(String deviceClass, Object ... args) {
        switch (deviceClass) {
            case "SmartPlug":
                if (args.length == 3) {
                    Plug plug = new Plug((String) args[2]);
                    Main.smartList.add(plug);
                } else if (args.length == 4){
                    Plug plug = new Plug((String) args[2], (String) args[3]);
                    Main.smartList.add(plug);
                } else if (args.length == 5) {
                    Plug plug = new Plug((String) args[2], (String) args[3], (double) args[4]);
                    Main.smartList.add(plug);
                }
                break;
            case "SmartCamera":
                if (args.length == 4) {
                    Camera camera = new Camera((String) args[2], (int) args[3]);
                    Main.smartList.add(camera);
                } else if (args.length == 5) {
                    Camera camera = new Camera((String) args[2], (int) args[3], (String) args[4]);
                    Main.smartList.add(camera);
                }
                break;
            case "SmartLamp":
                if (args.length == 3) {
                    Lamp lamp = new Lamp((String) args[2]);
                    Main.smartList.add(lamp);
                } else if (args.length == 4) {
                    Lamp lamp = new Lamp((String) args[2], (String) args[3]);
                    Main.smartList.add(lamp);
                } else if (args.length == 6) {
                    Lamp lamp = new Lamp((String) args[2], (String) args[3], (int) args[4], (int) args[5]);
                    Main.smartList.add(lamp);
                }
                break;
            case "SmartColorLamp":
                if (args.length == 3) {
                    ColoredLamp coloredLamp = new ColoredLamp((String) args[2]);
                    Main.smartList.add(coloredLamp);
                } else if (args.length == 4) {
                    ColoredLamp coloredLamp = new ColoredLamp((String) args[2], (String) args[3]);
                    Main.smartList.add(coloredLamp);
                } if (args.length > 4) {
                    String unknown = (String) args[4];
                    if (unknown.startsWith("0x")) {
                        ColoredLamp coloredLamp = new ColoredLamp((String) args[2], (String) args[3], unknown, (int) args[5]);
                        Main.smartList.add(coloredLamp);
                    } else if (args.length == 6) {
                            ColoredLamp coloredLamp = new ColoredLamp((String) args[2], (String) args[3], (int) args[4], (int) args[5]);
                            Main.smartList.add(coloredLamp);
                    }
                }
                break;
            default:
                break;
        }
    }

    public static void RemoveCommand(String deviceName) {
        for (Smart device : Main.smartList) {
            if (device.getName().equals(deviceName)) {
                Main.smartList.remove(device);
                break;
            }
        }
    }

    public static void SetSwitchCommand(String deviceName) {
        // TODO set switch time for device
    }

    public static void SwitchDeviceCommand(String deviceName) {
        // TODO switch device
    }

    public static void ChangeNameCommand(String deviceName, String newName) {
        for (Smart device : Main.smartList) {
            if (device.getName().equals(deviceName)) {
                device.setName(newName);
                break;
            }
        }
    }

    public static void PlugInCommand(String plugName, double ampere) {
        for (Smart device : Main.smartList) {
            if (device.getName().equals(plugName)) {
                if (device instanceof Plug) {
                    ((Plug) device).setAmpere(ampere);
                    ((Plug) device).setPlugged(true);
                    break;
                }
            }
        }
    }

    public static void PlugOutCommand(String plugName) {
        for (Smart device : Main.smartList) {
            if (device.getName().equals(plugName)) {
                if (device instanceof Plug) {
                    ((Plug) device).setPlugged(false);
                    break;
                }
            }
        }
    }

    public static void LampCommands(String input, Object ... args) {
        String name = (String) args[1];
        switch (input) {
            case "SetBrightness":
                String brightness = (String) args[2];
                for (Smart device : Main.smartList) {
                    if (device.getName().equals(name)) {
                        if (device instanceof Lamp) {
                            ((Lamp) device).setBrightness(Integer.parseInt(brightness));
                            break;
                        }
                    }
                }
                break;
            case "SetKelvin":
                String kelvin = (String) args[2];
                for (Smart device : Main.smartList) {
                    if (device.getName().equals(name)) {
                        if (device instanceof Lamp) {
                            ((Lamp) device).setKelvin(Integer.parseInt(kelvin));
                            break;
                        }
                    }
                }
                break;
            case "SetColorCode":
                String colorCode = (String) args[2];
                for (Smart device : Main.smartList) {
                    if (device.getName().equals(name)) {
                        if (device instanceof ColoredLamp && colorCode.startsWith("0x") && colorCode.length() == 8 && colorCode.matches("[0-9a-fA-F]+")) {
                            ((ColoredLamp) device).setColor(colorCode);
                            break;
                        }
                    }
                }
                break;
            case "SetColor":
                String color = (String) args[2];
                brightness = (String) args[3];
                for (Smart device : Main.smartList) {
                    if (device.getName().equals(name)) {
                        if (device instanceof ColoredLamp && color.startsWith("0x") && color.length() == 8 && color.matches("[0-9a-fA-F]+")) {
                            ((ColoredLamp) device).setColor(color);
                            ((ColoredLamp) device).setBrightness(Integer.parseInt(brightness));
                            break;
                        }
                    }
                }
                break;
            case "SetWhite":
                kelvin = (String) args[2];
                brightness = (String) args[3];
                for (Smart device : Main.smartList) {
                    if (device.getName().equals(name)) {
                        if (device instanceof ColoredLamp) {
                            ((ColoredLamp) device).setKelvin(Integer.parseInt(kelvin));
                            ((ColoredLamp) device).setBrightness(Integer.parseInt(brightness));
                            ((ColoredLamp) device).setColor("0xFFFFFF");
                            break;
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    public static void ZReportCommand() {
        // TODO print report
    }
}
