import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class InputReader{
    public static List<String> readInput(String fileName, OutputSender sender) throws Exception{
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
        sender.commandWrite(firstLine);
        String[] firstLineSplit = firstLine.split("\t");
        if (firstLineSplit[0].equals("SetInitialTime")) {
            try {
                if (firstLineSplit[1] == null) {
                    sender.initErrorCommand("initFalse");
                }
            } catch (Exception e) {
                sender.initErrorCommand("initFalse");
            }
            try {
                Main.timeChecker.setInitialTime((String) firstLineSplit[1]);
                sender.setInitTimeCommand(firstLineSplit[1]);
            } catch (Exception e){
                sender.initErrorCommand("timeFormat");
            }
        } else {
            sender.initErrorCommand("initFalse");
        }
        Main.switchChecker.createSwitchTimes();
        while ((line = br.readLine()) != null)  {
            if (line.equals("")) continue;
            lines.add(line);
            sender.commandWrite(line);
            determineCommand(line, sender);
        }
        br.close();
        if (!lines.get(lines.size() - 1).equals("ZReport")){
            determineCommand("ZReport", sender);
        }
        return lines;
    }

    public static void determineCommand(String line, OutputSender sender) throws Exception {
        String[] split = line.split("\t");
        String command = split[0];
        switch (command) {
            case "SetInitialTime":
                if (Main.timeChecker.isInit == true) {
                    sender.initErrorCommand("initTrue");
                    break;
                }
                if (split[1] == null) {
                    sender.initErrorCommand("timeFormat");
                    break;
                }
                try {
                    Main.timeChecker.setInitialTime((String) split[1]);
                } catch (Exception e){
                    sender.initErrorCommand("timeFormat");
                    break;
                }
                break;
            case "SetTime":
                if (split.length != 2) {
                    sender.erroneousCommand();
                    break;
                } 
                try {
                    if (!Main.timeChecker.checktTime(split[1])) {
                        sender.dateAfterError();
                        break;
                    } 
                } catch (Exception e) {
                    sender.setTimeErrorCommand();
                    break;
                }
                Main.timeChecker.setTime(split[1]);
                break;
            case "SkipMinutes":
                if (split.length != 2) {
                    sender.erroneousCommand();
                    break;
                }
                try {
                    if (Integer.parseInt(split[1]) < 0) {
                        sender.dateAfterError();
                        break;
                    } else if (Integer.parseInt(split[1]) == 0) {
                        sender.timeZeroError();
                        break;
                    } else 
                        Main.timeChecker.skipMinutes(Integer.parseInt(split[1]));
                        break;
                } catch (Exception e) {
                    sender.erroneousCommand();
                    break;
                }
            case "Nop":
                if (split.length != 1) {
                    sender.erroneousCommand();
                    break;
                }
                if (!Main.switchChecker.checkSwitchTimes()) {
                    sender.nopErrorCommand();
                } else
                    Main.switchChecker.jumpToNop();
                break;
            case "Add":
                List<String> args = new ArrayList<String>();
                for (int i = 1; i < split.length; i++) {
                    args.add(split[i]);
                }
                addCommands(sender, args.get(0), args.toArray());
                break;
            case "Remove":
                if (split.length != 2) {
                    sender.erroneousCommand();
                    break;
                }
                if (searchDevice(split[1])) {
                    sender.removeDeviceCommand(split[1]);
                } 
                removeCommand(split[1]);
                break;
            case "SetSwitchTime":
                if (split.length != 3) {
                    sender.erroneousCommand();
                    break;
                }
                boolean found = false;
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        found = true;
                    }
                }
                if (!found) {
                    sender.setSwitchTimeErrorCommand();
                    break;
                }
                Calendar switchDate = Calendar.getInstance();
                try {
                    switchDate.setTime(TimeChecker.formatter.parse(split[2]));
                } catch (Exception e) {
                    sender.setTimeErrorCommand();
                    break;
                }
                Main.switchChecker.setSwitchTimes(split[1], switchDate);
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        s.switchTime = switchDate;
                    }
                }
                break;
            case "Switch":
                if (split.length != 3) {
                    sender.erroneousCommand();
                    break;
                }
                if(!searchDevice(split[1])) {
                    sender.switchErrorCommand("noDevice");
                    break;
                }
                switchDeviceCommand(split[1], split[2], sender);
                break;
            case "ChangeName":
                if (split.length != 3) {
                    sender.erroneousCommand();
                    break;
                }
                if (split[1].equals(split[2])) {
                    sender.changeNameErrorCommand("sameName");
                    break;
                }
                found = false;
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        found = true;
                    }
                }
                if (!found) {
                    sender.changeNameErrorCommand("noDevice");
                    break;
                }
                found = false;
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[2])) {
                        found = true;
                    }
                }
                if (found) {
                    sender.changeNameErrorCommand("alreadyExists");
                    break;
                }
                changeNameCommand(split[1], split[2]);
                break;
            case "PlugIn":
                if (split.length != 3) {
                    sender.erroneousCommand();
                    break;
                }
                Smart smart = null;
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        smart = s;
                    }
                }
                if (smart == null) {
                    sender.plugInErrorCommand("noDevice");
                    break;
                }
                if (!(smart instanceof Plug)) {
                    sender.plugInErrorCommand("notPlug");
                    break;
                }
                if (((Plug) smart).isPlugged()) {
                    sender.plugInErrorCommand("alreadyPlugged");
                    break;
                }
                if (Integer.parseInt(split[2]) < 0) {
                    sender.plugInErrorCommand("negativeAmpere");
                    break;
                }
                plugInCommand(split[1], Double.parseDouble(split[2]));
                break;
            case "PlugOut":
                if (split.length != 2) {
                    sender.erroneousCommand();
                    break;
                }
                smart = null;
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        smart = s;
                    }
                }
                if (smart == null) {
                    sender.plugOutErrorCommand("noDevice");
                    break;
                }
                if (!(smart instanceof Plug)) {
                    sender.plugOutErrorCommand("notPlug");
                    break;
                }
                if (!(((Plug) smart).isPlugged())) {
                    sender.plugOutErrorCommand("notPlugged");
                    break;
                }
                plugOutCommand(split[1]);
                break;
            case "SetBrightness":
                if (split.length != 3) {
                    sender.erroneousCommand();
                    break;
                }
                smart = null;
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        smart = s;
                    }
                }
                if (smart == null) {
                    sender.lampErrorCommand("noDevice");
                    break;
                }
                if (!(smart instanceof Lamp)) {
                    sender.lampErrorCommand("notLamp");
                    break;
                }
                if (Integer.parseInt(split[2]) < 0 || Integer.parseInt(split[2]) > 100) {
                    sender.lampErrorCommand("invalidBrightness");
                    break;
                }
                args = new ArrayList<String>();
                for (int i = 1; i < split.length; i++) {
                    args.add(split[i]);
                }
                lampCommands(command, args.toArray());
                break;
            case "SetKelvin":
                if (split.length != 3) {
                    sender.erroneousCommand();
                    break;
                }
                smart = null;
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        smart = s;
                    }
                }
                if (smart == null) {
                    sender.lampErrorCommand("noDevice");
                    break;
                }
                if (!(smart instanceof Lamp)) {
                    sender.lampErrorCommand("notLamp");
                    break;
                }
                if (Integer.parseInt(split[2]) < 2000 || Integer.parseInt(split[2]) > 6500) {
                    sender.lampErrorCommand("invalidKelvin");
                    break;
                }
                args = new ArrayList<String>();
                for (int i = 1; i < split.length; i++) {
                    args.add(split[i]);
                }
                lampCommands(command, args.toArray());
                break;
            case "SetColorCode":
                if (split.length != 3) {
                    sender.erroneousCommand();
                    break;
                }
                smart = null;
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        smart = s;
                    }
                }
                if (smart == null) {
                    sender.lampErrorCommand("noDevice");
                    break;
                }
                if (!(smart instanceof ColoredLamp)) {
                    sender.lampErrorCommand("notColorLamp");
                    break;
                }
                if (Integer.parseInt(split[2], 16) < 0 || Integer.parseInt(split[2], 16) > 16777215) {
                    sender.lampErrorCommand("invalidColorCode");
                    break;
                }
                args = new ArrayList<String>();
                for (int i = 1; i < split.length; i++) {
                    args.add(split[i]);
                }
                lampCommands(command, args.toArray());
                break;
            case "SetWhite":
                if (split.length != 4) {
                    sender.erroneousCommand();
                    break;
                }
                smart = null;
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        smart = s;
                    }
                }
                if (smart == null) {
                    sender.lampErrorCommand("noDevice");
                    break;
                }
                if (!(smart instanceof Lamp)) {
                    sender.lampErrorCommand("notLamp");
                    break;
                }
                if (Integer.parseInt(split[2]) < 2000 || Integer.parseInt(split[2]) > 6500) {
                    sender.lampErrorCommand("invalidKelvin");
                    break;
                }
                if (Integer.parseInt(split[3]) < 0 || Integer.parseInt(split[3]) > 100) {
                    sender.lampErrorCommand("invalidBrightness");
                    break;
                }
                args = new ArrayList<String>();
                for (int i = 1; i < split.length; i++) {
                    args.add(split[i]);
                }
                lampCommands(command, args.toArray());
                break;
            case "SetColor":
                if (split.length != 4) {
                    sender.erroneousCommand();
                    break;
                }
                smart = null;
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        smart = s;
                    }
                }
                if (smart == null) {
                    sender.lampErrorCommand("noDevice");
                    break;
                }
                if (!(smart instanceof ColoredLamp)) {
                    sender.lampErrorCommand("notColorLamp");
                    break;
                }
                split[2] = split[2].substring(2);
                if (Integer.parseInt(split[3]) < 0 || Integer.parseInt(split[3]) > 100) {
                    sender.lampErrorCommand("invalidBrightness");
                    break;
                }
                try {
                    Integer.parseInt(split[2], 16);
                } catch (Exception e) {
                    sender.erroneousCommand();
                    break;
                }
                try {
                    if (Integer.parseInt(split[2], 16) < 0 || Integer.parseInt(split[2], 16) > 16777215) {
                        sender.lampErrorCommand("invalidColorCode");
                        break;
                    }
                } catch (Exception e) {
                    sender.lampErrorCommand("invalidColorCode");
                    break;
                }
                args = new ArrayList<String>();
                for (int i = 1; i < split.length; i++) {
                    args.add(split[i]);
                }
                lampCommands(command, args.toArray());
                break;
            case "ZReport":
                if (split.length != 1) {
                    sender.erroneousCommand();
                    break;
                }
                Main.switchChecker.normalizeSwitchTimes();
                OutputWriter wr = sender.writer;
                ZReport zReport = new ZReport();
                zReport.writeZReport(wr);
                break;
            default:
                sender.erroneousCommand();
                break;
        }
    }

    public static boolean searchDevice(String name) {
        for (Smart s : Main.smartList) {
            if (s.name.equals(name)) {
                return true;
            }
        } return false;
    }

    public static void addCommands(OutputSender sender, String deviceClass, Object ... args) throws Exception {
        switch (deviceClass) {
            case "SmartPlug":
                if (args.length < 2 || args.length > 4) {
                    sender.erroneousCommand();
                    break;
                }
                if (searchDevice((String) args[1])) {
                    sender.addErrorCommand("alreadyExists");
                    break;
                }
                if (args.length == 2) {
                    Plug plug = new Plug((String) args[1]);
                    Main.smartList.add(plug);
                } else if (args.length == 3){
                    if (!((String) args[2]).equals("Off") && !((String) args[2]).equals("On")) {
                        sender.erroneousCommand();
                        break;
                    }
                    Plug plug = new Plug((String) args[1], (String) args[2]);
                    Main.smartList.add(plug);
                } else if (args.length == 4) {
                    if (!((String) args[2]).equals("Off") && !((String) args[2]).equals("On")) {
                        sender.erroneousCommand();
                        break;
                    }
                    try {
                        args[3] = Double.parseDouble((String) args[3]);
                    } catch (Exception e) {
                        sender.addErrorCommand("invalidAmper");
                        break;
                    }
                    if ((double) args[3] < 0) {
                        sender.addErrorCommand("invalidAmper");
                        break;
                    }
                    
                    Plug plug = new Plug((String) args[1], (String) args[2], (double) args[3]);
                    Main.smartList.add(plug);
                }
                break;
            case "SmartCamera":
                if (args.length < 3 || args.length > 4) {
                    sender.erroneousCommand();
                    break;
                }
                if (searchDevice((String) args[1])) {
                    sender.addErrorCommand("alreadyExists");
                    break;
                }
                try {
                    args[2] = Double.parseDouble((String) args[2]);
                } catch (Exception e) {
                    sender.addErrorCommand("invalidResolution");
                    break;
                }
                if ((double) args[2] < 0) {
                    sender.addErrorCommand("invalidResolution");
                    break;
                }
                if (args.length == 3) {
                    Camera camera = new Camera((String) args[1], (double) args[2]);
                    Main.smartList.add(camera);
                } else if (args.length == 4) {
                    if (!((String) args[3]).equals("Off") && !((String) args[3]).equals("On")) {
                        sender.erroneousCommand();
                        break;
                    }
                    Camera camera = new Camera((String) args[1], (double) args[2], (String) args[3]);
                    Main.smartList.add(camera);
                }
                break;
            case "SmartLamp":
                if (args.length < 2 || args.length > 5 || args.length == 4) {
                    sender.erroneousCommand();
                    break;
                }
                if (searchDevice((String) args[1])) {
                    sender.addErrorCommand("alreadyExists");
                    break;
                }
                if (args.length == 2) {
                    Lamp lamp = new Lamp((String) args[1]);
                    Main.smartList.add(lamp);
                } else if (args.length == 3) {
                    if (!((String) args[2]).equals("Off") && !((String) args[2]).equals("On")) {
                        sender.erroneousCommand();
                        break;
                    }
                    Lamp lamp = new Lamp((String) args[1], (String) args[2]);
                    Main.smartList.add(lamp);
                } else if (args.length == 5) {
                    if (!((String) args[2]).equals("Off") && !((String) args[2]).equals("On")) {
                        sender.erroneousCommand();
                        break;
                    }
                    try {
                        args[3] = Integer.parseInt((String) args[3]);
                    } catch (Exception e) {
                        sender.addErrorCommand("invalidKelvin");
                        break;
                    } try {
                        args[4] = Integer.parseInt((String) args[4]);
                    } catch (Exception e) {
                        sender.addErrorCommand("invalidBrightness");
                        break;
                    }
                    if ((int) args[3] < 2000 || (int) args[3] > 6500) {
                        sender.addErrorCommand("invalidKelvin");
                        break;
                    } if ((int) args[4] < 0 || (int) args[4] > 100) {
                        sender.addErrorCommand("invalidBrightness");
                        break;
                    }
                    Lamp lamp = new Lamp((String) args[1], (String) args[2], (int) args[3], (int) args[4]);
                    Main.smartList.add(lamp);
                }
                break;
            case "SmartColorLamp":
                if (args.length < 2 || args.length > 5 || args.length == 4) {
                    sender.erroneousCommand();
                    break;
                }
                if (searchDevice((String) args[1])) {
                    sender.addErrorCommand("alreadyExists");
                    break;
                }
                if (args.length == 2) {
                    ColoredLamp coloredLamp = new ColoredLamp((String) args[1]);
                    Main.smartList.add(coloredLamp);
                } else if (args.length == 3) {
                    if (!((String) args[2]).equals("Off") && !((String) args[2]).equals("On")) {
                        sender.erroneousCommand();
                        break;
                    }
                    ColoredLamp coloredLamp = new ColoredLamp((String) args[1], (String) args[2]);
                    Main.smartList.add(coloredLamp);
                } if (args.length > 3) {
                    if (!((String) args[2]).equals("Off") && !((String) args[2]).equals("On")) {
                        sender.erroneousCommand();
                        break;
                    }
                    String unknown = (String) args[3];
                    if (unknown.startsWith("0x")) {
                        if (unknown.length() > 8 || unknown.length() < 8) {
                            sender.addErrorCommand("invalidColorCode");
                            break;
                        }
                        try {
                            Integer.parseInt(unknown.substring(2), 16);
                            unknown = unknown.substring(2);
                        } catch (Exception e) {
                            sender.erroneousCommand();
                            break;
                        }
                        if (Integer.parseInt(unknown.substring(2), 16) > 16777215) {
                            sender.addErrorCommand("invalidColorCode");
                            break;
                        }
                        if (Integer.parseInt((String) args[4]) < 0 || Integer.parseInt((String) args[4]) > 100) {
                            sender.addErrorCommand("invalidBrightness");
                            break;
                        }
                        ColoredLamp coloredLamp = new ColoredLamp((String) args[1], (String) args[2], unknown, Integer.parseInt((String)args[4]));
                        Main.smartList.add(coloredLamp);
                    } else if (args.length == 5) {
                        if (Integer.parseInt((String) args[3]) < 2000 || Integer.parseInt((String) args[3]) > 6500) {
                            sender.addErrorCommand("invalidKelvin");
                            break;
                        }
                        if (Integer.parseInt((String) args[4]) < 0 || Integer.parseInt((String) args[4]) > 100) {
                            sender.addErrorCommand("invalidBrightness");
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

    public static void removeCommand(String deviceName) {
        for (Smart device : Main.smartList) {
            if (device.getName().equals(deviceName)) {
                device.setOn(false);
                Main.smartList.remove(device);
                break;
            }
        }
    }

    public static void switchDeviceCommand(String deviceName, String state, OutputSender sender) throws Exception {
        for (Smart device : Main.smartList) {
            if (device.getName().equals(deviceName)) {
                if (state.equals("On")) {
                    if (device.isOn() == false) {
                        (device).setOn(true);
                        device.setLastSwitchedDate(Main.timeChecker.getCurrentDate());
                    }
                    else if (device.isOn() == true)
                        sender.switchErrorCommand("alreadyOn");
                } else if (state.equals("Off")) {
                    if (device.isOn() == true) {
                        (device).setOn(false);
                        if (device.getLastSwitchedDate() != null) { 
                            long diffInMillis = ((Calendar) (Main.timeChecker.getCurrentDate().clone())).getTimeInMillis() -((Calendar) device.getLastSwitchedDate().clone()).getTimeInMillis() ;
                            double diffInHours = (double) (diffInMillis / (60 * 60 * 1000.0));
                            double diffInMinutes = (double) (diffInMillis / (60 * 1000.0));
                            if (device instanceof Plug && ((Plug) device).isPlugged()) {
                                ((Plug) device).setTotalEnergy(((Plug) device).getTotalEnergy() + ((Plug) device).calculateEnergy(((Plug) device).getAmpere(), diffInHours));
                            }
                            if (device instanceof Camera) {
                                ((Camera) device).setStorage(((Camera) device).getStorage() + ((Camera) device).calculateStorage(((Camera) device).getMbps(), diffInMinutes));
                            }
                        }
                        device.setLastSwitchedDate(Main.timeChecker.getCurrentDate());
                    }
                    else if (device.isOn() == false)
                        sender.switchErrorCommand("alreadyOff");
                } 
                break;
            }
        }
    }

    public static void changeNameCommand(String deviceName, String newName) {
        for (Smart device : Main.smartList) {
            if (device.getName().equals(deviceName)) {
                device.setName(newName);
                break;
            }
        }
    }

    public static void plugInCommand(String plugName, double ampere) {
        for (Smart device : Main.smartList) {
            if (device.getName().equals(plugName)) {
                if (device instanceof Plug) {
                    ((Plug) device).setAmpere(ampere);
                    ((Plug) device).setPlugged(true);
                    device.setLastSwitchedDate(Main.timeChecker.getCurrentDate());
                    break;
                }
            }
        }
    }

    public static void plugOutCommand(String plugName) {
        for (Smart device : Main.smartList) {
            if (device.getName().equals(plugName)) {
                if (device instanceof Plug) {
                    long diffInMillis = Main.timeChecker.getCurrentDate().getTimeInMillis() - device.getLastSwitchedDate().getTimeInMillis() ;
                    double diffInHours = (double) (diffInMillis / (60 * 60 * 1000.0));
                    if (device instanceof Plug && ((Plug) device).isPlugged() && device.isOn()) {
                        ((Plug) device).setTotalEnergy(((Plug) device).getTotalEnergy() + ((Plug) device).calculateEnergy(((Plug) device).getAmpere(), diffInHours));
                    }
                    ((Plug) device).setPlugged(false);
                    device.setLastSwitchedDate(Main.timeChecker.getCurrentDate());
                    break;
                }
            }
        }
    }

    public static void lampCommands(String input, Object ... args) {
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
                        ((ColoredLamp) device).setColor(colorCode);
                        ((ColoredLamp) device).setColorOn(true);
                    }
                }
                break;
            case "SetColor":
                String color = (String) args[1];
                brightness = (String) args[2];
                for (Smart device : Main.smartList) {
                    if (device.getName().equals(name)) {
                        ((ColoredLamp) device).setColor(color);
                        ((ColoredLamp) device).setBrightness(Integer.parseInt(brightness));
                        ((ColoredLamp) device).setColorOn(true);
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
}
