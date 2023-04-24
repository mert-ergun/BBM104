public class Main {
    public static OutputWriter ow = new OutputWriter("out1.txt");
    public static void main(String[] args) {
        ow.changeOutputFile(args[1]);
        ow.clearOutput();
        InputReader.readInput(args[0]);
    }
}
