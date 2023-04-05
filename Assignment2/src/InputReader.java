import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class InputReader{
    public static List<String> ReadInput(String fileName, OutputSender sender) throws Exception{
        BufferedReader br = new BufferedReader(new BufferedReader(new FileReader(fileName)));
        String line;
        List<String> lines = new ArrayList<String>();
        String firstLine = null;
        while ((line = br.readLine()) != null) {
            if (!line.isEmpty()) {
                firstLine = line;
                break;
            }
        }
        sender.CommandCommand(firstLine);
        String[] firstLineSplit = firstLine.split("\t");
        if (firstLineSplit[0].equals("SetInitialTime")) {
            try {
                Main.timeChecker.SetInitialTime((String) firstLineSplit[1]);
                sender.SetInitTimeCommand(firstLineSplit[1]);
            } catch (Exception e){
                sender.InitErrorCommand("timeFormat");
            }
        } else {
            sender.InitErrorCommand("initFalse");
        }
        Main.switchChecker.CreateSwitchTimes();
        while ((line = br.readLine()) != null)  {
            if (line.equals("")) continue;
            lines.add(line);
            sender.CommandCommand(line);
            DetermineCommand(line, sender);
        }
        br.close();
        if (!lines.get(lines.size() - 1).equals("ZReport")){
            DetermineCommand("ZReport", sender);
        }
        return lines;
    }

    public static void DetermineCommand(String line, OutputSender sender) throws Exception {
        String[] split = line.split("\t");
        String command = split[0];
        switch (command) {
            case "SetInitialTime":
                if (Main.timeChecker.isInit == true) {
                    sender.InitErrorCommand("initTrue");
                    break;
                }
                if (split[1] == null) {
                    sender.InitErrorCommand("timeFormat");
                    break;
                }
                try {
                    Main.timeChecker.SetInitialTime((String) split[1]);
                } catch (Exception e){
                    sender.InitErrorCommand("timeFormat");
                    break;
                }
                break;
            case "SetTime":
                if (split.length != 2) {
                    sender.ErroneousCommand();
                    break;
                } 
                try {
                    if (!Main.timeChecker.CheckTime(split[1])) {
                        sender.DateAfterError();
                        break;
                    } 
                } catch (Exception e) {
                    sender.SetTimeErrorCommand();
                    break;
                }
                Main.timeChecker.SetTime(split[1]);
                break;
            case "SkipMinutes":
                if (split.length != 2) {
                    sender.ErroneousCommand();
                    break;
                }
                if (split[1] == null || Integer.parseInt(split[1]) < 0) {
                    sender.SetTimeErrorCommand();
                } else 
                    Main.timeChecker.SkipMinutes(Integer.parseInt(split[1]));
                break;
            case "Nop":
                if (split.length != 1) {
                    sender.ErroneousCommand();
                    break;
                }
                if (!Main.switchChecker.CheckSwitchTimes()) {
                    sender.NopErrorCommand();
                } else
                    Main.switchChecker.JumpToNop();
                break;
            case "Add":
                List<String> args = new ArrayList<String>();
                for (int i = 1; i < split.length; i++) {
                    args.add(split[i]);
                }
                AddCommands(sender, args.get(0), args.toArray());
                break;
            case "Remove":
                if (split.length != 2) {
                    sender.ErroneousCommand();
                    break;
                }
                if (SearchDevice(split[1])) {
                    sender.RemoveDeviceCommand(split[1]);
                } 
                RemoveCommand(split[1]);
                break;
            case "SetSwitchTime":
                if (split.length != 3) {
                    sender.ErroneousCommand();
                    break;
                }
                boolean found = false;
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        found = true;
                    }
                }
                if (!found) {
                    sender.SetSwitchTimeErrorCommand();
                    break;
                }
                Calendar switchDate = Calendar.getInstance();
                try {
                    switchDate.setTime(TimeChecker.formatter.parse(split[2]));
                } catch (Exception e) {
                    sender.SetTimeErrorCommand();
                    break;
                }
                Main.switchChecker.SetSwitchTimes(split[1], switchDate);
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        s.switchTime = switchDate;
                    }
                }
                break;
            case "Switch":
                if (split.length != 3) {
                    sender.ErroneousCommand();
                    break;
                }
                if(!SearchDevice(split[1])) {
                    sender.SwitchErrorCommand("noDevice");
                    break;
                }
                SwitchDeviceCommand(split[1], split[2], sender);
                break;
            case "ChangeName":
                if (split.length != 3) {
                    sender.ErroneousCommand();
                    break;
                }
                if (split[1].equals(split[2])) {
                    sender.ChangeNameErrorCommand("sameName");
                    break;
                }
                found = false;
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        found = true;
                    }
                }
                if (!found) {
                    sender.ChangeNameErrorCommand("noDevice");
                    break;
                }
                found = false;
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[2])) {
                        found = true;
                    }
                }
                if (found) {
                    sender.ChangeNameErrorCommand("alreadyExists");
                    break;
                }
                ChangeNameCommand(split[1], split[2]);
                break;
            case "PlugIn":
                if (split.length != 3) {
                    sender.ErroneousCommand();
                    break;
                }
                Smart smart = null;
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        smart = s;
                    }
                }
                if (smart == null) {
                    sender.PlugInErrorCommand("noDevice");
                    break;
                }
                if (!(smart instanceof Plug)) {
                    sender.PlugInErrorCommand("notPlug");
                    break;
                }
                if (((Plug) smart).isPlugged()) {
                    sender.PlugInErrorCommand("alreadyPlugged");
                    break;
                }
                if (Integer.parseInt(split[2]) < 0) {
                    sender.PlugInErrorCommand("negativeAmpere");
                    break;
                }
                PlugInCommand(split[1], Double.parseDouble(split[2]));
                break;
            case "PlugOut":
                if (split.length != 2) {
                    sender.ErroneousCommand();
                    break;
                }
                smart = null;
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        smart = s;
                    }
                }
                if (smart == null) {
                    sender.PlugOutErrorCommand("noDevice");
                    break;
                }
                if (!(smart instanceof Plug)) {
                    sender.PlugOutErrorCommand("notPlug");
                    break;
                }
                if (!(((Plug) smart).isPlugged())) {
                    sender.PlugOutErrorCommand("notPlugged");
                    break;
                }
                PlugOutCommand(split[1]);
                break;
            case "SetBrightness":
                if (split.length != 3) {
                    sender.ErroneousCommand();
                    break;
                }
                smart = null;
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        smart = s;
                    }
                }
                if (smart == null) {
                    sender.LampErrorCommand("noDevice");
                    break;
                }
                if (!(smart instanceof Lamp)) {
                    sender.LampErrorCommand("notLamp");
                    break;
                }
                if (Integer.parseInt(split[2]) < 0 || Integer.parseInt(split[2]) > 100) {
                    sender.LampErrorCommand("invalidBrightness");
                    break;
                }
                args = new ArrayList<String>();
                for (int i = 1; i < split.length; i++) {
                    args.add(split[i]);
                }
                LampCommands(command, args.toArray());
                break;
            case "SetKelvin":
                if (split.length != 3) {
                    sender.ErroneousCommand();
                    break;
                }
                smart = null;
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        smart = s;
                    }
                }
                if (smart == null) {
                    sender.LampErrorCommand("noDevice");
                    break;
                }
                if (!(smart instanceof Lamp)) {
                    sender.LampErrorCommand("notLamp");
                    break;
                }
                if (Integer.parseInt(split[2]) < 2000 || Integer.parseInt(split[2]) > 6500) {
                    sender.LampErrorCommand("invalidKelvin");
                    break;
                }
                args = new ArrayList<String>();
                for (int i = 1; i < split.length; i++) {
                    args.add(split[i]);
                }
                LampCommands(command, args.toArray());
                break;
            case "SetColorCode":
                if (split.length != 3) {
                    sender.ErroneousCommand();
                    break;
                }
                smart = null;
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        smart = s;
                    }
                }
                if (smart == null) {
                    sender.LampErrorCommand("noDevice");
                    break;
                }
                if (!(smart instanceof ColoredLamp)) {
                    sender.LampErrorCommand("notColorLamp");
                    break;
                }
                if (Integer.parseInt(split[2], 16) < 0 || Integer.parseInt(split[2], 16) > 16777215) {
                    sender.LampErrorCommand("invalidColorCode");
                    break;
                }
                args = new ArrayList<String>();
                for (int i = 1; i < split.length; i++) {
                    args.add(split[i]);
                }
                LampCommands(command, args.toArray());
                break;
            case "SetWhite":
                if (split.length != 4) {
                    sender.ErroneousCommand();
                    break;
                }
                smart = null;
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        smart = s;
                    }
                }
                if (smart == null) {
                    sender.LampErrorCommand("noDevice");
                    break;
                }
                if (!(smart instanceof ColoredLamp)) {
                    sender.LampErrorCommand("notColorLamp");
                    break;
                }
                if (Integer.parseInt(split[2]) < 2000 || Integer.parseInt(split[2]) > 6500) {
                    sender.LampErrorCommand("invalidKelvin");
                    break;
                }
                if (Integer.parseInt(split[3]) < 0 || Integer.parseInt(split[3]) > 100) {
                    sender.LampErrorCommand("invalidBrightness");
                    break;
                }
                args = new ArrayList<String>();
                for (int i = 1; i < split.length; i++) {
                    args.add(split[i]);
                }
                LampCommands(command, args.toArray());
                break;
            case "SetColor":
                if (split.length != 4) {
                    sender.ErroneousCommand();
                    break;
                }
                smart = null;
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        smart = s;
                    }
                }
                if (smart == null) {
                    sender.LampErrorCommand("noDevice");
                    break;
                }
                if (!(smart instanceof ColoredLamp)) {
                    sender.LampErrorCommand("notColorLamp");
                    break;
                }
                split[2] = split[2].substring(2);
                if (Integer.parseInt(split[3]) < 0 || Integer.parseInt(split[3]) > 100) {
                    sender.LampErrorCommand("invalidBrightness");
                    break;
                }
                try {
                    if (Integer.parseInt(split[2], 16) < 0 || Integer.parseInt(split[2], 16) > 16777215) {
                        sender.LampErrorCommand("invalidColorCode");
                        break;
                    }
                } catch (Exception e) {
                    sender.LampErrorCommand("invalidColorCode");
                    break;
                }
                args = new ArrayList<String>();
                for (int i = 1; i < split.length; i++) {
                    args.add(split[i]);
                }
                LampCommands(command, args.toArray());
                break;
            case "ZReport":
                if (split.length != 1) {
                    sender.ErroneousCommand();
                    break;
                }
                OutputWriter wr = sender.writer;
                ZReport zReport = new ZReport();
                zReport.writeZReport(wr);
                break;
            default:
                sender.ErroneousCommand();
                break;
        }
    }

    public static boolean SearchDevice(String name) {
        for (Smart s : Main.smartList) {
            if (s.name.equals(name)) {
                return true;
            }
        } return false;
    }

    public static void AddCommands(OutputSender sender, String deviceClass, Object ... args) throws Exception {
        switch (deviceClass) {
            case "SmartPlug":
                if (args.length < 2 || args.length > 4) {
                    sender.ErroneousCommand();
                    break;
                }
                if (SearchDevice((String) args[1])) {
                    sender.AddErrorCommand("alreadyExists");
                    break;
                }
                if (args.length == 2) {
                    Plug plug = new Plug((String) args[1]);
                    Main.smartList.add(plug);
                } else if (args.length == 3){
                    if (!((String) args[2]).equals("Off") && !((String) args[2]).equals("On")) {
                        sender.ErroneousCommand();
                        break;
                    }
                    Plug plug = new Plug((String) args[1], (String) args[2]);
                    Main.smartList.add(plug);
                } else if (args.length == 4) {
                    if (!((String) args[2]).equals("Off") && !((String) args[2]).equals("On")) {
                        sender.ErroneousCommand();
                        break;
                    }
                    try {
                        args[3] = Double.parseDouble((String) args[3]);
                    } catch (Exception e) {
                        sender.AddErrorCommand("invalidAmper");
                        break;
                    }
                    if ((double) args[3] < 0) {
                        sender.AddErrorCommand("invalidAmper");
                        break;
                    }
                    
                    Plug plug = new Plug((String) args[1], (String) args[2], (double) args[3]);
                    Main.smartList.add(plug);
                }
                break;
            case "SmartCamera":
                if (args.length < 3 || args.length > 4) {
                    sender.ErroneousCommand();
                    break;
                }
                if (SearchDevice((String) args[1])) {
                    sender.AddErrorCommand("alreadyExists");
                    break;
                }
                try {
                    args[2] = Integer.parseInt((String) args[2]);
                } catch (Exception e) {
                    sender.AddErrorCommand("invalidResolution");
                    break;
                }
                if ((int) args[2] < 0) {
                    sender.AddErrorCommand("invalidResolution");
                    break;
                }
                if (args.length == 3) {
                    Camera camera = new Camera((String) args[1], (int) args[2]);
                    Main.smartList.add(camera);
                } else if (args.length == 4) {
                    if (!((String) args[3]).equals("Off") && !((String) args[3]).equals("On")) {
                        sender.ErroneousCommand();
                        break;
                    }
                    Camera camera = new Camera((String) args[1], (int) args[2], (String) args[3]);
                    Main.smartList.add(camera);
                }
                break;
            case "SmartLamp":
                if (args.length < 2 || args.length > 5 || args.length == 4) {
                    sender.ErroneousCommand();
                    break;
                }
                if (SearchDevice((String) args[1])) {
                    sender.AddErrorCommand("alreadyExists");
                    break;
                }
                if (args.length == 2) {
                    Lamp lamp = new Lamp((String) args[1]);
                    Main.smartList.add(lamp);
                } else if (args.length == 3) {
                    if (!((String) args[2]).equals("Off") && !((String) args[2]).equals("On")) {
                        sender.ErroneousCommand();
                        break;
                    }
                    Lamp lamp = new Lamp((String) args[1], (String) args[2]);
                    Main.smartList.add(lamp);
                } else if (args.length == 5) {
                    if (!((String) args[2]).equals("Off") && !((String) args[2]).equals("On")) {
                        sender.ErroneousCommand();
                        break;
                    }
                    try {
                        args[3] = Integer.parseInt((String) args[3]);
                    } catch (Exception e) {
                        sender.AddErrorCommand("invalidKelvin");
                        break;
                    } try {
                        args[4] = Integer.parseInt((String) args[4]);
                    } catch (Exception e) {
                        sender.AddErrorCommand("invalidBrightness");
                        break;
                    }
                    if ((int) args[3] < 2000 || (int) args[3] > 6500) {
                        sender.AddErrorCommand("invalidKelvin");
                        break;
                    } if ((int) args[4] < 0 || (int) args[4] > 100) {
                        sender.AddErrorCommand("invalidBrightness");
                        break;
                    }
                    Lamp lamp = new Lamp((String) args[1], (String) args[2], (int) args[3], (int) args[4]);
                    Main.smartList.add(lamp);
                }
                break;
            case "SmartColorLamp":
                if (args.length < 2 || args.length > 5 || args.length == 4) {
                    sender.ErroneousCommand();
                    break;
                }
                if (SearchDevice((String) args[1])) {
                    sender.AddErrorCommand("alreadyExists");
                    break;
                }
                if (args.length == 2) {
                    ColoredLamp coloredLamp = new ColoredLamp((String) args[1]);
                    Main.smartList.add(coloredLamp);
                } else if (args.length == 3) {
                    if (!((String) args[2]).equals("Off") && !((String) args[2]).equals("On")) {
                        sender.ErroneousCommand();
                        break;
                    }
                    ColoredLamp coloredLamp = new ColoredLamp((String) args[1], (String) args[2]);
                    Main.smartList.add(coloredLamp);
                } if (args.length > 3) {
                    if (!((String) args[2]).equals("Off") && !((String) args[2]).equals("On")) {
                        sender.ErroneousCommand();
                        break;
                    }
                    String unknown = (String) args[3];
                    if (unknown.startsWith("0x")) {
                        if (unknown.length() > 8) {
                            sender.AddErrorCommand("invalidColorCode");
                            break;
                        }
                        try {
                            unknown = Integer.parseInt(unknown.substring(2), 16) + "";
                        } catch (Exception e) {
                            sender.AddErrorCommand("invalidColorCode");
                            break;
                        }
                        if (Integer.parseInt(unknown.substring(2), 16) > 16777215) {
                            sender.AddErrorCommand("invalidColorCode");
                            break;
                        }
                        if (Integer.parseInt((String) args[4]) < 0 || Integer.parseInt((String) args[4]) > 100) {
                            sender.AddErrorCommand("invalidBrightness");
                            break;
                        }
                        ColoredLamp coloredLamp = new ColoredLamp((String) args[1], (String) args[2], unknown, Integer.parseInt((String)args[4]));
                        Main.smartList.add(coloredLamp);
                    } else if (args.length == 5) {
                        if (Integer.parseInt((String) args[3]) < 2000 || Integer.parseInt((String) args[3]) > 6500) {
                            sender.AddErrorCommand("invalidKelvin");
                            break;
                        }
                        if (Integer.parseInt((String) args[4]) < 0 || Integer.parseInt((String) args[4]) > 100) {
                            sender.AddErrorCommand("invalidBrightness");
                            break;
                        }
                        ColoredLamp coloredLamp = new ColoredLamp((String) args[1], (String) args[2], Integer.parseInt((String)args[3]), Integer.parseInt((String)args[4]));
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
                device.setOn(false);
                Main.smartList.remove(device);
                break;
            }
        }
    }

    public static void SwitchDeviceCommand(String deviceName, String state, OutputSender sender) throws Exception {
        for (Smart device : Main.smartList) {
            if (device.getName().equals(deviceName)) {
                if (state.equals("On")) {
                    if (device.isOn() == false) {
                        (device).setOn(true);
                    }
                    else if (device.isOn() == true)
                        sender.SwitchErrorCommand("alreadyOn");
                } else if (state.equals("Off")) {
                    if (device.isOn() == true) {
                        (device).setOn(false);
                    }
                    else if (device.isOn() == false)
                        sender.SwitchErrorCommand("alreadyOff");
                } 
                break;
            }
        }
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
        String name = (String) args[0];
        switch (input) {
            case "SetBrightness":
                String brightness = (String) args[1];
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
                String kelvin = (String) args[1];
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
                String colorCode = (String) args[1];
                for (Smart device : Main.smartList) {
                    if (device.getName().equals(name)) {
                        if (device instanceof ColoredLamp && colorCode.startsWith("0x") && colorCode.length() == 8 && colorCode.matches("[0-9a-fA-F]+")) {
                            ((ColoredLamp) device).setColor(colorCode);
                            ((ColoredLamp) device).setColorOn(true);
                            break;
                        }
                    }
                }
                break;
            case "SetColor":
                String color = (String) args[1];
                brightness = (String) args[2];
                for (Smart device : Main.smartList) {
                    if (device.getName().equals(name)) {
                        if (device instanceof ColoredLamp && color.startsWith("0x") && color.length() == 8 && color.matches("[0-9a-fA-F]+")) {
                            ((ColoredLamp) device).setColor(color);
                            ((ColoredLamp) device).setBrightness(Integer.parseInt(brightness));
                            ((ColoredLamp) device).setColorOn(true);
                            break;
                        }
                    }
                }
                break;
            case "SetWhite":
                kelvin = (String) args[1];
                brightness = (String) args[2];
                for (Smart device : Main.smartList) {
                    if (device.getName().equals(name)) {
                        if (device instanceof ColoredLamp) {
                            ((ColoredLamp) device).setKelvin(Integer.parseInt(kelvin));
                            ((ColoredLamp) device).setBrightness(Integer.parseInt(brightness));
                            ((ColoredLamp) device).setColor("0xFFFFFF");
                            ((ColoredLamp) device).setColorOn(false);
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
