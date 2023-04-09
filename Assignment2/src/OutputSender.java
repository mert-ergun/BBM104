/**
 * This class is used to send the output to the output file.
 * It contains error messages and success messages.
 */
public class OutputSender {
    String fileName;  // file name of the output file
    OutputWriter writer;  // output writer object

    /**
     * Constructor for the class.
     * @param fileName - file name of the output file
     * @throws Exception if the file is not found
     */
    public OutputSender(String fileName) throws Exception {
        this.fileName = fileName;
        writer = new OutputWriter(fileName);
        writer.clearOutput();
    }

    /**
     * This method sends the last command to the output file.
     * @param command - last command from the input file
     * @throws Exception if the file is not found
     */
    public void commandWrite(String command) throws Exception {
        writer.writeOutput("COMMAND: " + command);
    }

    /**
     * This method sends the Error message to the output file.
     * Erroneous command if the command is not correct.
     * If there is no command with this type.
     * @throws Exception if the file is not found
     */
    public void erroneousCommand() throws Exception {
        writer.writeOutput("ERROR: Erroneous command!");
    }

    /**
     * This method sends the Success message to the output file.
     * If the set initial time command is correct.
     * @param time - initial time in format "yyyy-MM-dd_HH:mm:ss"
     * @throws Exception if the file is not found
     */
    public void setInitTimeCommand(String time) throws Exception {
        writer.writeOutput("SUCCESS: Time has been set to " + time + "!");
    }

    /**
     * This method sends the Success message to the output file.
     * If the remove device command is correct and there is a device with this name.
     * @param deviceName - name of the device to be removed
     * @throws Exception if the file is not found
     */
    public void removeDeviceCommand(String deviceName) throws Exception {
        writer.writeOutput("SUCCESS: Information about removed smart device is as follows:");
        for (Smart smart : Main.smartList) {
            if (smart.getName().equals(deviceName)) {
                writer.writeOutput(smart.writeInfo());
                break;
            }
        }
    }

    /**
     * This method sends the Error message to the output file.
     * If the date is before the current date.
     * @throws Exception if the file is not found
     */
    public void dateAfterError() throws Exception {
        writer.writeOutput("ERROR: Time cannot be reversed!"); 
    }

    /**
     * Initializes an error message if the initial time is not correct.
     * @param error - The error type to be initialized.
     * @throws Exception If an error occurs while initializing the error command.
     */
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

    /**
     * Sets an error command for incorrect time format.
     * @throws Exception If an error occurs while setting the time error command.
     */
    public void setTimeErrorCommand() throws Exception {
        writer.writeOutput("ERROR: Time format is not correct!");
    }

    /**
     * Sets an error command for zero time. 
     * If skip time is zero there is nothing to skip.
     * @throws Exception If an error occurs while setting the zero time error command.
     */
    public void timeZeroError() throws Exception {
        writer.writeOutput("ERROR: There is nothing to skip!");
    }

    /**
     * Sets an error command for Nop command if there is nothing to switch.
     * @throws Exception If an error occurs while setting the NOP error command.
     */
    public void nopErrorCommand() throws Exception {
        writer.writeOutput("ERROR: There is nothing to switch!");
    }

    /**
     * Sets an error command if there is no device to switch with the given name.
     * @throws Exception If an error occurs while setting the switch time error command.
     */
    public void setSwitchTimeErrorCommand() throws Exception {
        writer.writeOutput("There is not such a device!");
    }

    /**
     * Sets an error command if:
     * There is no device with the given name.
     * Device is already switched on.
     * Device is already switched off.
     * @param error - The error type to be initialized.
     * @throws Exception If an error occurs while setting the switch error command.
     */
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

    /**
     * Sets an error command if:
     * There is no device with the given name.
     * A device is already named with the given name.
     * Both of the names are the same.
     * @param error - The error type to be initialized.
     * @throws Exception If an error occurs while changing the name error command.
     */
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

    /**
     * Sets an error command if:
     * There is no device with the given name.
     * Device is not a smart plug.
     * There is already an item plugged in to that plug.
     * Ampere value must be a positive number.
     * @param error - The error type to be initialized.
     * @throws Exception If an error occurs while plugging in the device error command.
     */
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

    /**
     * Sets an error command if:
     * There is no device with the given name.
     * Device is not a smart plug.
     * There is no item plugged in to that plug.
     * @param error - The error type to be initialized.
     * @throws Exception If an error occurs while plugging out the device error command.
     */
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

    /**
     * Sets an error command if:
     * There is no device with the given name.
     * Device is not a smart lamp.
     * Kelvin value must be in range of 2000K-6500K.
     * Brightness must be in range of 0%-100%.
     * Device is not a smart color lamp.
     * Color code value must be in range of 0x0-0xFFFFFF.
     * @param error - The error type to be initialized.
     * @throws Exception If an error occurs while setting the lamp error command.
     */
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

    /**
     * Sets an error command if:
     * A device is already named with the given name.
     * Device type is invalid.
     * Ampere value must be a positive number.
     * Megabyte value has to be a positive number.
     * Kelvin value must be in range of 2000K-6500K.
     * Brightness must be in range of 0%-100%.
     * Color code value must be in range of 0x0-0xFFFFFF.
     * @param error - The error type to be initialized.
     * @throws Exception If an error occurs while setting the add device error command.
     */
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

    /**
     * Sends the Z Report to the output.
     * @param zReport - The Z Report to be sent.
     * @throws Exception If an error occurs while sending the Z Report.
     */
    public void sendZReport(String zReport) throws Exception {
        writer.writeOutput(zReport);
    }
}
