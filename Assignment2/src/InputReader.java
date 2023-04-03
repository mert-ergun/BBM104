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

    public static void AddCommands(String deviceClass) {
        switch (deviceClass) {
            case "SmartPlug":
                //TODO
                break;
            case "SmartCamera":
                //TODO
                break;
            case "SmartLamp":
                //TODO
                break;
            case "SmartColoredLamp":
                //TODO
                break;
            default:
                break;
        }
    }

    public static void RemoveCommand(String deviceName) {
        // TODO remove device
    }

    public static void SetSwitchCommand(String deviceName) {
        // TODO set switch time for device
    }

    public static void SwitchDeviceCommand(String deviceName) {
        // TODO switch device
    }

    public static void ChangeNameCommand(String deviceName) {
        // TODO change device name
    }

    public static void PlugInCommand(String plugName) {
        // TODO plug in device
    }

    public static void PlugOutCommand(String plugName) {
        // TODO plug out device
    }

    public static void LampCommands(String input) {
        switch (input) {
            case "SetBrightness":
                //TODO
                break;
            case "SetKelvin":
                //TODO
                break;
            case "SetColorCode":
                //TODO
                break;
            case "SetColor":
                //TODO
                break;
            case "SetWhite":
                //TODO
                break;
            default:
                break;
        }
    }

    public static void ZReportCommand() {
        // TODO print report
    }
}
