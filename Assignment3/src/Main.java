/**
 * The Main class is the entry point of the program. 
 * It initializes the OutputWriter and reads the input file using the InputReader.
 */
public class Main {
    public static OutputWriter ow = new OutputWriter("");  // The OutputWriter instance used to write output to a file.

    /**
     * The main method of the program.
     * @param args The command line arguments. args[0] should be the input file path and args[1] should be the output file path.
     */
    public static void main(String[] args) {
        ow.changeOutputFile(args[1]);  // Change the output file to the one specified in the command line arguments.
        ow.clearOutput();  // Clear the output file.
        InputReader.readInput(args[0]);  // Read the input file. The InputReader class will handle the rest.
    }
}
