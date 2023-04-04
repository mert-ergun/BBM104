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

    public void SetInitTimeCommand(String time) throws Exception {
        writer.WriteOutput("SUCCESS: Time has been set to " + time + "!");
    }

    public void RemoveDeviceCommand(String deviceName) throws Exception {
        writer.WriteOutput("SUCCESS: Information about removed smart device is as follows:");
        // TODO: print device info
    }


}
