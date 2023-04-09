import java.util.Calendar;
import java.util.List;

public class ZReport {
    Calendar currentDate;
    List<Tuple<Smart, Calendar>> switchTimes;

    public void calculateCurrentDate(TimeChecker timeChecker) {
        currentDate = timeChecker.getCurrentDate();
    }

    public void calculateSwitchTimes(SwitchChecker switchChecker) {
        switchTimes = switchChecker.getSwitchTimes();
    }

    public void writeZReport(OutputWriter writer) throws Exception {
        calculateCurrentDate(Main.timeChecker);
        Main.switchChecker.updateSwitchTimes();
        Main.switchChecker.sortSwitchTimes();
        calculateSwitchTimes(Main.switchChecker);
        writer.writeOutput("Time is:\t" + TimeChecker.formatter.format(currentDate.getTime()));
        for (Tuple<Smart, Calendar> switchTime : switchTimes) {
            writer.writeOutput(switchTime.getX().writeZReport());
        }
    }
}
