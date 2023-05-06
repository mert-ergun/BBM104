public class Main {
    public static OutputWriter ow = new OutputWriter("output.txt");
    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                throw new ParamNum("There should be only 1 paremeter");
            }
        } catch (ParamNum e) {
            ow.writeOutput(e.getMessage());
            System.exit(0);
        }
        String filename = args[0];
        ExceptChecker.checkException(InputReader.readInput(filename));
    }
}
