import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class InputReader{
    public static List<String> ReadInput() throws Exception{
        BufferedReader br = new BufferedReader(new BufferedReader(new FileReader("input.txt")));
        String line;
        List<String> lines = new ArrayList<String>();
        while ((line = br.readLine()) != null) {
            String[] split = line.split("\t");
            for (String s : split) {
                lines.add(s);
            }
        }
        br.close();
        return lines;
    }

    public static void TimeCommands(String input) throws Exception{
        switch (input) {
            case "SetInitalTime":
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
            default:
                break;
        }
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
