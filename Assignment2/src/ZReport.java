import java.util.Calendar;
import java.util.List;

/**
 * Class for Z-report.
 * It contains the current date and the list of switch times.
 * It also contains methods to calculate the current date and switch times.
 */
public class ZReport {
    Calendar currentDate;  // current date
    List<Tuple<Smart, Calendar>> switchTimes;  // list of switch times

    /**
     * Constructor for the class.
     * @param timeChecker - time checker object to get the current date
     */
    public void calculateCurrentDate(TimeChecker timeChecker) {
        currentDate = timeChecker.getCurrentDate();
    }

    /**
     * Constructor for the class.
     * @param switchChecker - switch checker object to get the switch times
     */
    public void calculateSwitchTimes(SwitchChecker switchChecker) {
        switchTimes = switchChecker.getSwitchTimes();
    }

    /**
     * This method writes the z-report.
     * @param writer - output writer object to write the z-report
     * @throws Exception
     */
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
