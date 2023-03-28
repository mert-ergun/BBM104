import java.io.*;  // Imports the java.io package to use the BufferedReader, BufferedWriter, FileReader, and FileWriter classes

public class Main {
    public static void main(String[] args) {
        String inputFile = args[0];  // Gets the input file name from the command line
        try {
        BufferedReader br = new BufferedReader(new FileReader(inputFile));  // Reads the input file
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));  // Writes to the output file

        while (true) {  // Reads the input file line by line
            String line = br.readLine();
            if (line == null) {  // If the line is null, then the end of the file has been reached
                break;  
            }
            String choice = line;  // Gets the choice from the input file
            switch (choice) {  // Calls the appropriate method based on the choice
                case "Armstrong numbers up to:":
                    int armstrong = Integer.parseInt(br.readLine());  // Gets the number from the input file
                    Armstrong.listArmstrongNumbers(bw, armstrong);
                    break;
                case "Abundant numbers up to:":
                    int abudant = Integer.parseInt(br.readLine());
                    Abundant.listAbundantNumbers(bw, abudant);
                    break;
                case "Emirp numbers up to:":
                    int emirp = Integer.parseInt(br.readLine());
                    Emirp.listEmirpNumbers(bw, emirp);
                    break;
                case "Descending order sorting:":
                    int[] arr1 = new int[50];  // Creates an array of size 50 to store the numbers, which is the maximum size of the array
                    int j = 0;  // j is the index of the array
                    while (true) {
                        String line1 = br.readLine();  
                        if (line1.equals("-1")) {  // If the line is -1, then the end of the numbers has been reached
                            break;
                        }
                        arr1[j] = Integer.parseInt(line1);  // Stores the number in the array
                        j++;
                    }
                
                    int sizeArr1 = j;  // Gets the size of the array
                    int[] arr4 = new int[sizeArr1];  // Creates a new array of the size of the array
                    for (j = 0; j < sizeArr1; j++) {  // Copies the elements of the array to the new array
                        arr4[j] = arr1[j];  
                    }
                
                    Sorting.descendingOrder(bw, arr4);  // Calls the method to sort the array in descending order
                    break;
                case "Ascending order sorting:":
                    int[] arr2 = new int[50];
                    int i = 0;
                    while (true) {
                        String line2 = br.readLine();
                        if (line2.equals("-1")) {
                            break;
                        }
                        arr2[i] = Integer.parseInt(line2);
                        i++;
                    }
                
                    int sizeArr = i;
                    int[] arr3 = new int[sizeArr];
                    for (i = 0; i < sizeArr; i++) {
                        arr3[i] = arr2[i];
                    }
                
                    Sorting.ascendingOrder(bw, arr3);
                    break;
                default:
                    break;
            }
        }

        bw.write("Finished...");  // Writes to the output file that the program has finished
        br.close();  // Closes the input file
        bw.close();  // Closes the output file

        } catch (Exception e) {  // Catches any exceptions that may occur
            System.out.println(e);  // Prints the exception
        }
    }
}
// Mert ERGÜN b2220356062