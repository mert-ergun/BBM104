import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Class for input reader.
 * It reads the input file and sends the commands to the output file.
 * It also determines the commands and sends the errors to the output file.
 */
public class InputReader{
    /**
     * This method reads the input file.
     * @param fileName - file name of the input file
     * @param sender - output sender object to send the commands
     * @return list of lines in the input file
     * @throws Exception if the file is not found
     */
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
        if (firstLineSplit[0].equals("SetInitialTime")) {  // check if the first line is SetInitialTime
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
        Main.switchChecker.createSwitchTimes();  // create the switch times list for the first time
        while ((line = br.readLine()) != null)  {  // read the rest of the file
            if (line.equals("")) continue;
            lines.add(line);
            sender.commandWrite(line);
            determineCommand(line, sender);  // determine the command
        }
        br.close();
        if (!lines.get(lines.size() - 1).equals("ZReport")){  // check if the last line is ZReport, if not, send ZReport
            OutputWriter.writeZReport(sender.fileName);
            determineCommand("ZReport", sender);
        }
        return lines;
    }

    /**
     * This method determines the command and sends the errors to the output file.
     * Checks if the command is valid.
     * Sends the errors to the output file if the command is invalid.
     * @param line - line to be determined
     * @param sender - output sender object to send the commands
     * @throws Exception if the file is not found
     */
    public static void determineCommand(String line, OutputSender sender) throws Exception {
        String[] split = line.split("\t");
        String command = split[0];
        switch (command) {  
            case "SetInitialTime":
                if (Main.timeChecker.isInit == true) {  // check if the initial time is already set
                    sender.initErrorCommand("initTrue");
                    break;
                }
                if (split[1] == null) {  // check if the initial time is null
                    sender.initErrorCommand("timeFormat");
                    break;
                }
                try {  // check if the initial time is in the correct format
                    Main.timeChecker.setInitialTime((String) split[1]);
                } catch (Exception e){
                    sender.initErrorCommand("timeFormat");
                    break;
                }
                break;
            case "SetTime":
                if (split.length != 2) {  // check if there is time after the command
                    sender.erroneousCommand();
                    break;
                } 
                try {
                    if (!Main.timeChecker.checkTime(split[1])) {  // check if the time is after the current time
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
                if (split.length != 2) {  // check if there is a number after the command
                    sender.erroneousCommand();
                    break;
                }
                try {
                    if (Integer.parseInt(split[1]) < 0) {  // check if the number is negative
                        sender.dateAfterError();
                        break;
                    } else if (Integer.parseInt(split[1]) == 0) {  // check if the number is 0
                        sender.timeZeroError();
                        break;
                    } else 
                        Main.timeChecker.skipMinutes(Integer.parseInt(split[1]));
                        break;
                } catch (Exception e) {  // check if the number is not an integer or command is not valid
                    sender.erroneousCommand();
                    break;
                }
            case "Nop":
                if (split.length != 1) {  // check if there is no other command after Nop
                    sender.erroneousCommand();
                    break;
                }
                if (!Main.switchChecker.checkSwitchTimes()) {  // check if there is a switch time
                    sender.nopErrorCommand();
                    break;
                }
                Main.switchChecker.jumpToNop();
                break;
            case "Add":
                List<String> args = new ArrayList<String>();  // create a list to store the arguments
                for (int i = 1; i < split.length; i++) {  
                    args.add(split[i]);
                }
                addCommands(sender, args.get(0), args.toArray());  // call the addCommands method
                break;
            case "Remove":
                if (split.length != 2) {  // check if there is a device name after the command
                    sender.erroneousCommand();
                    break;
                }
                if (searchDevice(split[1])) {  // check if the device exists
                    removeCommand(split[1], sender);
                } 
                  // call the removeCommand method
                break;
            case "SetSwitchTime":
                if (split.length != 3) {  // check if there is a device name and time after the command
                    sender.erroneousCommand();
                    break;
                }
                boolean found = false;  // check if the device exists
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        found = true;
                    }
                }
                if (!found) {
                    sender.setSwitchTimeErrorCommand();
                    break;
                }
                Calendar switchDate = (Calendar) Calendar.getInstance().clone(); 
                try {
                    switchDate.setTime(TimeChecker.formatter.parse(split[2]));  // check if the time is in the correct format
                } catch (Exception e) {
                    sender.setTimeErrorCommand();
                    break;
                }
                Main.switchChecker.setSwitchTimes(split[1], switchDate);
                for (Smart s : Main.smartList) {  // set the switch time for the device
                    if (s.name.equals(split[1])) {
                        s.switchTime = switchDate;
                    }
                }
                break;
            case "Switch":
                if (split.length != 3) {  // check if there is a device name and state after the command
                    sender.erroneousCommand();
                    break;
                }
                if(!searchDevice(split[1])) {  // check if the device exists
                    sender.switchErrorCommand("noDevice");
                    break;
                }
                switchDeviceCommand(split[1], split[2], sender);  // call the switchDeviceCommand method
                break;
            case "ChangeName":
                if (split.length != 3) {  // check if there is a device name and new name after the command
                    sender.erroneousCommand();
                    break;
                }
                if (split[1].equals(split[2])) {  // check if the new name is the same as the old name
                    sender.changeNameErrorCommand("sameName");
                    break;
                }
                found = false;  // check if the device exists
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        found = true;
                    }
                }
                if (!found) {
                    sender.changeNameErrorCommand("noDevice");
                    break;
                }
                found = false;  // check if the new name already exists
                for (Smart s : Main.smartList) {  
                    if (s.name.equals(split[2])) {  
                        found = true;
                    }
                }
                if (found) {
                    sender.changeNameErrorCommand("alreadyExists");
                    break;
                }
                changeNameCommand(split[1], split[2]);  // call the changeNameCommand method
                break;
            case "PlugIn":
                if (split.length != 3) {  // check if there is a device name and ampere after the command
                    sender.erroneousCommand();
                    break;
                }
                Smart smart = null;  // check if the device exists
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        smart = s;
                    }
                }
                if (smart == null) {
                    sender.plugInErrorCommand("noDevice");
                    break;
                }
                if (!(smart instanceof Plug)) {  // check if the device is a plug
                    sender.plugInErrorCommand("notPlug");
                    break;
                }
                if (((Plug) smart).isPlugged()) {  // check if the plug is already plugged
                    sender.plugInErrorCommand("alreadyPlugged");
                    break;
                }
                try {
                    if (Double.parseDouble(split[2]) <= 0) {  // check if the ampere is negative
                        sender.plugInErrorCommand("negativeAmpere");
                        break;
                    }
                } catch (Exception e) {  // check if the ampere is not a number
                    sender.erroneousCommand();
                    break;
                }
                plugInCommand(split[1], Double.parseDouble(split[2]));
                break;
            case "PlugOut":
                if (split.length != 2) {  // check if there is a device name after the command
                    sender.erroneousCommand();
                    break;
                }
                smart = null;  // check if the device exists
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        smart = s;
                    }
                }
                if (smart == null) {
                    sender.plugOutErrorCommand("noDevice");
                    break;
                }
                if (!(smart instanceof Plug)) {  // check if the device is a plug
                    sender.plugOutErrorCommand("notPlug");
                    break;
                }
                if (!(((Plug) smart).isPlugged())) {  // check if the plug is already unplugged
                    sender.plugOutErrorCommand("notPlugged");
                    break;
                }
                plugOutCommand(split[1]);
                break;
            case "SetBrightness":
                if (split.length != 3) {  // check if there is a device name and brightness after the command
                    sender.erroneousCommand();
                    break;
                }
                smart = null;  // check if the device exists
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        smart = s;
                    }
                }
                if (smart == null) {
                    sender.lampErrorCommand("noDevice");
                    break;
                }
                if (!(smart instanceof Lamp)) {  // check if the device is a lamp
                    sender.lampErrorCommand("notLamp");
                    break;
                }
                if (Integer.parseInt(split[2]) < 0 || Integer.parseInt(split[2]) > 100) {  // check if the brightness is between 0 and 100
                    sender.lampErrorCommand("invalidBrightness");
                    break;
                }
                args = new ArrayList<String>();  // call the lampCommands method
                for (int i = 1; i < split.length; i++) {
                    args.add(split[i]);
                }
                lampCommands(command, args.toArray());
                break;
            case "SetKelvin":
                if (split.length != 3) {  // check if there is a device name and kelvin after the command
                    sender.erroneousCommand();
                    break;
                }
                smart = null;
                for (Smart s : Main.smartList) {  // check if the device exists
                    if (s.name.equals(split[1])) {
                        smart = s;
                    }
                }
                if (smart == null) {  
                    sender.lampErrorCommand("noDevice");
                    break;
                }
                if (!(smart instanceof Lamp)) {  // check if the device is a lamp
                    sender.lampErrorCommand("notLamp");
                    break;
                }
                if (Integer.parseInt(split[2]) < 2000 || Integer.parseInt(split[2]) > 6500) {  // check if the kelvin is between 2000 and 6500
                    sender.lampErrorCommand("invalidKelvin");
                    break;
                }
                args = new ArrayList<String>();  // call the lampCommands method
                for (int i = 1; i < split.length; i++) {
                    args.add(split[i]);
                }
                lampCommands(command, args.toArray());
                break;
            case "SetColorCode":
                if (split.length != 3) {  // check if there is a device name and color code after the command
                    sender.erroneousCommand();
                    break;
                }
                smart = null;  // check if the device exists
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        smart = s;
                    }
                }
                if (smart == null) {
                    sender.lampErrorCommand("noDevice");
                    break;
                }
                if (!(smart instanceof ColoredLamp)) {  // check if the device is a colored lamp
                    sender.lampErrorCommand("notColorLamp");
                    break;
                }
                split[2] = split[2].replace("0x", "");  // remove the 0x from the color code
                try {  // check if the color code is a valid hex code
                    Integer.parseInt(split[2], 16);
                } catch (Exception e) {
                    sender.erroneousCommand();
                    break;
                }
                if (Integer.parseInt(split[2], 16) < 0 || Integer.parseInt(split[2], 16) > 16777215) {  // check if the color code is between 0x000000 and 0xFFFFFF
                    sender.lampErrorCommand("invalidColorCode");
                    break;
                }
                args = new ArrayList<String>();  // call the lampCommands method
                for (int i = 1; i < split.length; i++) {
                    args.add(split[i]);
                }
                lampCommands(command, args.toArray());
                break;
            case "SetWhite":
                if (split.length != 4) {  // check if there is a device name, kelvin and brightness after the command
                    sender.erroneousCommand();
                    break;
                }
                smart = null;  // check if the device exists
                for (Smart s : Main.smartList) {  
                    if (s.name.equals(split[1])) {
                        smart = s;
                    }
                }
                if (smart == null) {  
                    sender.lampErrorCommand("noDevice");
                    break;
                }
                if (!(smart instanceof Lamp)) {  // check if the device is a lamp
                    sender.lampErrorCommand("notLamp");
                    break;
                }
                if (Integer.parseInt(split[2]) < 2000 || Integer.parseInt(split[2]) > 6500) {  // check if the kelvin is between 2000 and 6500
                    sender.lampErrorCommand("invalidKelvin");
                    break;
                }
                if (Integer.parseInt(split[3]) < 0 || Integer.parseInt(split[3]) > 100) {  // check if the brightness is between 0 and 100
                    sender.lampErrorCommand("invalidBrightness");
                    break;
                }
                args = new ArrayList<String>();  // call the lampCommands method
                for (int i = 1; i < split.length; i++) {
                    args.add(split[i]);
                }
                lampCommands(command, args.toArray());
                break;
            case "SetColor":
                if (split.length != 4) {  // check if there is a device name, color code and brightness after the command
                    sender.erroneousCommand();
                    break;
                }
                smart = null;  // check if the device exists
                for (Smart s : Main.smartList) {
                    if (s.name.equals(split[1])) {
                        smart = s;
                    }
                }
                if (smart == null) {
                    sender.lampErrorCommand("noDevice");
                    break;
                }
                if (!(smart instanceof ColoredLamp)) {  // check if the device is a colored lamp
                    sender.lampErrorCommand("notColorLamp");
                    break;
                }
                split[2] = split[2].substring(2);  // remove the 0x from the color code
                if (Integer.parseInt(split[3]) < 0 || Integer.parseInt(split[3]) > 100) {  // check if the brightness is between 0 and 100
                    sender.lampErrorCommand("invalidBrightness"); 
                    break;
                }
                try {  // check if the color code is a valid hex code
                    Integer.parseInt(split[2], 16);
                } catch (Exception e) {
                    sender.erroneousCommand();
                    break;
                }
                try {
                    if (Integer.parseInt(split[2], 16) < 0 || Integer.parseInt(split[2], 16) > 16777215) {  // check if the color code is between 0x000000 and 0xFFFFFF
                        sender.lampErrorCommand("invalidColorCode");
                        break;
                    }
                } catch (Exception e) {  
                    sender.lampErrorCommand("invalidColorCode");
                    break;
                }
                args = new ArrayList<String>();  // call the lampCommands method
                for (int i = 1; i < split.length; i++) {
                    args.add(split[i]);
                }
                lampCommands(command, args.toArray());
                break;
            case "ZReport":
                if (split.length != 1) {  // check if there is no other argument after the command
                    sender.erroneousCommand();
                    break;
                }
                OutputWriter wr = sender.writer;  
                ZReport zReport = new ZReport();  // create a new ZReport object
                zReport.writeZReport(wr);  // write the ZReport
                break;
            default: 
                sender.erroneousCommand();  // if the command is not recognized, send an error message
                break;
        }
    }

    /**
     * This method is used to check if a device exists
     * @param name - the name of the device
     * @return true if the device exists, false otherwise
     */
    public static boolean searchDevice(String name) {
        for (Smart s : Main.smartList) {
            if (s.name.equals(name)) {
                return true;
            }
        } return false;
    }

    /**
     * This method is used to add a new device
     * @param sender - the OutputSender object
     * @param deviceClass - the class of the device
     * @param args - the arguments of the command
     * @throws Exception if the command is not recognized or invalid
     */
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
                    if ((double) args[3] <= 0) {
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
                if ((double) args[2] <= 0) {
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

    /**
     * This method removes a device from the smartList
     * @param deviceName - the name of the device to be removed
     */
    public static void removeCommand(String deviceName, OutputSender sender) throws Exception {
        for (Smart device : Main.smartList) {
            if (device.getName().equals(deviceName)) {
                device.setOn(false);
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
                sender.removeDeviceCommand(deviceName);
                Main.smartList.remove(device);
                for (Tuple<Smart, Calendar> device2 : Main.switchChecker.getSwitchTimes()) {
                    if (((Smart)device2.getX()).getName().equals(deviceName)) {
                        Main.switchChecker.getSwitchTimes().remove(device2);
                        break;
                    }
                }
                break;
            }
        }
    }

    /**
     * This method switches a device on or off.
     * If the device is already on or off, it will send an error message.
     * If the device is switched on, it will set the lastSwitchedDate to the current date.
     * If the device is Plug or Camera, it calculates the energy consumption and storage usage.
     * @param deviceName - the name of the device to be switched
     * @param state - the state to which the device will be switched
     * @param sender - the sender of the command
     * @throws Exception
     */
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

    /**
     * This method changes the name of a device.
     * @param deviceName - the name of the device to be changed
     * @param newName - the new name of the device
     */
    public static void changeNameCommand(String deviceName, String newName) {
        for (Smart device : Main.smartList) {
            if (device.getName().equals(deviceName)) {
                device.setName(newName);
                break;
            }
        }
    }

    /**
     * This method plugs in a device into specific plug.
     * If the device is already plugged in, it will send an error message.
     * It also sets the lastSwitchedDate to the current date.
     * @param plugName - the name of the plug
     * @param ampere - the ampere of the device
     */
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

    /**
     * This method plugs out a device from specific plug.
     * If the device is already plugged out, it will send an error message.
     * It also sets the lastSwitchedDate to the current date and calculates the energy consumption.
     * @param plugName - the name of the plug
     */
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

    /**
     * This method initializes the lamp commands.
     * @param input - the command to be executed
     * @param args - the arguments of the command (name, brightness, kelvin, colorCode)
     */
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
