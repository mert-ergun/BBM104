public class OutputSender {
    String fileName;
    OutputWriter writer;

    public OutputSender(String fileName) throws Exception {
        this.fileName = fileName;
        writer = new OutputWriter(fileName);
        writer.clearOutput();
    }

    public void commandWrite(String command) throws Exception {
        writer.writeOutput("COMMAND: " + command);
    }

    public void erroneousCommand() throws Exception {
        writer.writeOutput("ERROR: Erroneous command!");
    }

    public void setInitTimeCommand(String time) throws Exception {
        writer.writeOutput("SUCCESS: Time has been set to " + time + "!");
    }

    public void removeDeviceCommand(String deviceName) throws Exception {
        writer.writeOutput("SUCCESS: Information about removed smart device is as follows:");
        for (Smart smart : Main.smartList) {
            if (smart.getName().equals(deviceName)) {
                writer.writeOutput(smart.writeInfo());
                break;
            }
        }
    }

    public void dateAfterError() throws Exception {
        writer.writeOutput("ERROR: Time cannot be reversed!"); 
    }

    public void initErrorCommand(String error) throws Exception {
        switch (error) {
            case "timeFormat":
                writer.writeOutput("ERROR: Format of the initial date is wrong! Program is going to terminate!");
                System.exit(0);
                break;
            case "initTrue":
                writer.writeOutput("ERROR: Erroneous command!");
                break;
            case "initFalse":
                writer.writeOutput("ERROR: First command must be set initial time! Program is going to terminate!");
                System.exit(0);
                break;
        }
    }

    public void setTimeErrorCommand() throws Exception {
        writer.writeOutput("ERROR: Time format is not correct!");
    }

    public void timeZeroError() throws Exception {
        writer.writeOutput("ERROR: There is nothing to skip!");
    }

    public void nopErrorCommand() throws Exception {
        writer.writeOutput("ERROR: There is nothing to switch!");
    }

    public void setSwitchTimeErrorCommand() throws Exception {
        writer.writeOutput("There is not such a device!");
    }

    public void switchErrorCommand(String error) throws Exception {
        switch (error) {
            case "noDevice":
                writer.writeOutput("ERROR: There is not such a device!");
                break;
            case "alreadyOn":
                writer.writeOutput("ERROR: This device is already switched on!");
                break;
            case "alreadyOff":
                writer.writeOutput("ERROR: This device is already switched off!");
                break;
        }
    }

    public void changeNameErrorCommand(String error) throws Exception {
        switch (error) {
            case "noDevice":
                writer.writeOutput("ERROR: There is not such a device!");
                break;
            case "alreadyExists":
                writer.writeOutput("ERROR: There is already a smart device with same name!");
                break;
            case "sameName":
                writer.writeOutput("ERROR: Both of the names are the same, nothing changed!");
                break;
        }
    }

    public void plugInErrorCommand(String error) throws Exception {
        switch (error) {
            case "noDevice":
                writer.writeOutput("ERROR: There is not such a device!");
                break;
            case "alreadyPlugged":
                writer.writeOutput("ERROR: There is already an item plugged in to that plug!");
                break;
            case "notPlug":
                writer.writeOutput("ERROR: This device is not a smart plug!");
                break;
            case "negativeAmpere":
                writer.writeOutput("ERROR: Ampere value must be a positive number!");
                break;
        }
    }

    public void plugOutErrorCommand(String error) throws Exception {
        switch (error) {
            case "noDevice":
                writer.writeOutput("ERROR: There is not such a device!");
                break;
            case "notPlugged":
                writer.writeOutput("ERROR: This plug has no item to plug out from that plug!");
                break;
            case "notPlug":
                writer.writeOutput("ERROR: This device is not a smart plug!");
                break;
        }
    }

    public void lampErrorCommand(String error) throws Exception {
        switch (error) {
            case "noDevice":
                writer.writeOutput("ERROR: There is not such a device!");
                break;
            case "notLamp":
                writer.writeOutput("ERROR: This device is not a smart lamp!");
                break;
            case "invalidKelvin":
                writer.writeOutput("ERROR: Kelvin value must be in range of 2000K-6500K!");
                break;
            case "invalidBrightness":
                writer.writeOutput("ERROR: Brightness must be in range of 0%-100%!");
                break;
            case "notColorLamp":
                writer.writeOutput("ERROR: This device is not a smart color lamp!");
                break;
            case "invalidColorCode":
                writer.writeOutput("ERROR: Color code value must be in range of 0x0-0xFFFFFF");
                break;
        }
    }

    public void addErrorCommand(String error) throws Exception {
        switch (error) {
            case "alreadyExists":
                writer.writeOutput("ERROR: There is already a smart device with same name!");
                break;
            case "invalidType":
                writer.writeOutput("ERROR: Invalid device type!");
                break;
            case "invalidAmper":
                writer.writeOutput("ERROR: Ampere value must be a positive number!");
                break;
            case "invalidResolution":
                writer.writeOutput("ERROR: Megabyte value has to be a positive number!");
                break;
            case "invalidKelvin":
                writer.writeOutput("ERROR: Kelvin value must be in range of 2000K-6500K!");
                break;
            case "invalidBrightness":
                writer.writeOutput("ERROR: Brightness must be in range of 0%-100%!");
                break;
            case "invalidColorCode":
                writer.writeOutput("ERROR: Color code value must be in range of 0x0-0xFFFFFF!");
                break;
        }
    }

    public void sendZReport(String zReport) throws Exception {
        writer.writeOutput(zReport);
    }
}
