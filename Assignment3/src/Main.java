public class Main {
    public static OutputWriter ow = new OutputWriter("out1.txt");
    public static void main(String[] args) {
        ow.changeOutputFile("my_out4.txt");
        ow.clearOutput();
        InputReader.readInput("inp4.txt");
    }
}
