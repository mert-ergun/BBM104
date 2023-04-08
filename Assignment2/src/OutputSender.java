public class OutputSender {
    String fileName;
    OutputWriter writer;

    public OutputSender(String fileName) throws Exception {
        this.fileName = fileName;
        writer = new OutputWriter(fileName);
        writer.ClearOutput();
    }

    public void CommandCommand(String command) throws Exception {
        writer.WriteOutput("COMMAND: " + command);
    }

    public void ErroneousCommand() throws Exception {
        writer.WriteOutput("ERROR: Erroneous command!");
    }

    public void SetInitTimeCommand(String time) throws Exception {
        writer.WriteOutput("SUCCESS: Time has been set to " + time + "!");
    }

    public void RemoveDeviceCommand(String deviceName) throws Exception {
        writer.WriteOutput("SUCCESS: Information about removed smart device is as follows:");
        for (Smart smart : Main.smartList) {
            if (smart.getName().equals(deviceName)) {
                writer.WriteOutput(smart.WriteInfo());
                break;
            }
        }
    }

    public void DateAfterError() throws Exception {
        writer.WriteOutput("ERROR: Time cannot be reversed!"); 
    }

    public void InitErrorCommand(String error) throws Exception {
        switch (error) {
            case "timeFormat":
                writer.WriteOutput("ERROR: Format of the initial date is wrong! Program is going to terminate!");
                System.exit(0);
                break;
            case "initTrue":
                writer.WriteOutput("ERROR: Erroneous command!");
                break;
            case "initFalse":
                writer.WriteOutput("ERROR: First command must be set initial time! Program is going to terminate!");
                System.exit(0);
                break;
        }
    }

    public void SetTimeErrorCommand() throws Exception {
        writer.WriteOutput("ERROR: Time format is not correct!");
    }

    public void NopErrorCommand() throws Exception {
        writer.WriteOutput("ERROR: There is nothing to switch!");
    }

    public void SetSwitchTimeErrorCommand() throws Exception {
        writer.WriteOutput("There is not such a device!");
    }

    public void SwitchErrorCommand(String error) throws Exception {
        switch (error) {
            case "noDevice":
                writer.WriteOutput("ERROR: There is not such a device!");
                break;
            case "alreadyOn":
                writer.WriteOutput("ERROR: This device is already switched on!");
                break;
            case "alreadyOff":
                writer.WriteOutput("ERROR: This device is already switched off!");
                break;
        }
    }

    public void ChangeNameErrorCommand(String error) throws Exception {
        switch (error) {
            case "noDevice":
                writer.WriteOutput("ERROR: There is not such a device!");
                break;
            case "alreadyExists":
                writer.WriteOutput("ERROR: There is already a device with this name!");
                break;
            case "sameName":
                writer.WriteOutput("ERROR: Both of the names are the same, nothing changed!");
                break;
        }
    }

    public void PlugInErrorCommand(String error) throws Exception {
        switch (error) {
            case "noDevice":
                writer.WriteOutput("ERROR: There is not such a device!");
                break;
            case "alreadyPlugged":
                writer.WriteOutput("ERROR: There is already an item plugged in to that plug!");
                break;
            case "notPlug":
                writer.WriteOutput("ERROR: This device is not a smart plug!");
                break;
            case "negativeAmpere":
                writer.WriteOutput("ERROR: Ampere value must be a positive number!");
                break;
        }
    }

    public void PlugOutErrorCommand(String error) throws Exception {
        switch (error) {
            case "noDevice":
                writer.WriteOutput("ERROR: There is not such a device!");
                break;
            case "notPlugged":
                writer.WriteOutput("ERROR: This plug has no item to plug out from that plug!");
                break;
            case "notPlug":
                writer.WriteOutput("ERROR: This device is not a smart plug!");
                break;
        }
    }

    public void LampErrorCommand(String error) throws Exception {
        switch (error) {
            case "noDevice":
                writer.WriteOutput("ERROR: There is not such a device!");
                break;
            case "notLamp":
                writer.WriteOutput("ERROR: This device is not a smart lamp!");
                break;
            case "invalidKelvin":
                writer.WriteOutput("ERROR: Kelvin value must be in range of 2000K-6500K!");
                break;
            case "invalidBrightness":
                writer.WriteOutput("ERROR: Brightness must be in range of 0%-100%!");
                break;
            case "notColorLamp":
                writer.WriteOutput("ERROR: This device is not a smart color lamp!");
                break;
            case "invalidColorCode":
                writer.WriteOutput("ERROR: Color code value must be in range of 0x0-0xFFFFFF");
                break;
        }
    }

    public void AddErrorCommand(String error) throws Exception {
        switch (error) {
            case "alreadyExists":
                writer.WriteOutput("ERROR: There is already a smart device with same name!");
                break;
            case "invalidType":
                writer.WriteOutput("ERROR: Invalid device type!");
                break;
            case "invalidAmper":
                writer.WriteOutput("ERROR: Ampere value must be a positive number!");
                break;
            case "invalidResolution":
                writer.WriteOutput("ERROR: Megabyte value has to be a positive number!");
                break;
            case "invalidKelvin":
                writer.WriteOutput("ERROR: Kelvin value must be in range of 2000K-6500K!");
                break;
            case "invalidBrightness":
                writer.WriteOutput("ERROR: Brightness must be in range of 0%-100%!");
                break;
            case "invalidColorCode":
                writer.WriteOutput("ERROR: Color code value must be in range of 0x0-0xFFFFFF");
                break;
        }
    }

    public void SendZReport(String zReport) throws Exception {
        writer.WriteOutput(zReport);
    }
}
