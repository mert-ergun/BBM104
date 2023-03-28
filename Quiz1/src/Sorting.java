import java.io.*;

public class Sorting {
    public static void descendingOrder(BufferedWriter bw, int[] arr) {  // Sorts the array in descending order, takes in the BufferedWriter and the array as parameters
        try {
            for (int i = 0; i < arr.length - 1; i++) {  // Sorts the array in descending order
                int maxIndex = i;  // maxIndex is the index of the largest number
                for (int j = i + 1; j < arr.length; j++) {  // Finds the largest number in the array
                    if (arr[j] > arr[maxIndex]) {
                        maxIndex = j;
                    }
                }
                int temp = arr[i];  // Swaps the largest number with the first number
                arr[i] = arr[maxIndex];  // Swaps the first number with the largest number
                arr[maxIndex] = temp;  // Swaps the largest number with the first number
            }
            
            bw.write("Descending order sorting:");  // Writes the sorted array in descending order to the file
            bw.newLine();
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j <= i; j++) {
                    bw.write(arr[j] + " ");
                }
                bw.newLine();
            }
            bw.newLine();
        } catch (IOException e) {  // Catches the IOException and returns
            return;
        }
    }

    public static void ascendingOrder(BufferedWriter bw, int[] arr) {  // Sorts the array in ascending order, takes in the BufferedWriter and the array as parameters
        try {
            for (int i = 0; i < arr.length - 1; i++) {  // Sorts the array in ascending order
                int minIndex = i;  // minIndex is the index of the smallest number
                for (int j = i + 1; j < arr.length; j++) {  // Finds the smallest number in the array
                    if (arr[j] < arr[minIndex]) {
                        minIndex = j;
                    }
                }
                int temp = arr[i];  // Swaps the smallest number with the first number
                arr[i] = arr[minIndex];  // Swaps the first number with the smallest number
                arr[minIndex] = temp;  // Swaps the smallest number with the first number
            }

            bw.write("Ascending order sorting:");  // Writes the sorted array in ascending order to the file
            bw.newLine();
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j <= i; j++) {
                    bw.write(arr[j] + " ");
                }
                bw.newLine();
            }
            bw.newLine();
        } catch (IOException e) {  // Catches the IOException and returns
            return; 
        }
    }
}
