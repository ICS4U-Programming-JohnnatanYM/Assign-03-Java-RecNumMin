import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This program reads integers from a mixed-content file,
 * finds the min, max, sum, and count of negative numbers using recursion,
 * and writes the results to output.txt.
 *
 * @author Johnnatan
 * @version 1.0
 * @since 2025-05-19
 */
final class RecMinFind {

    /**
     * This is to satisfy the style checker.
     *
     * @exception IllegalStateException
     * @see IllegalStateException
     */
    private RecMinFind() {
        throw new IllegalStateException("Utility Class");
    }

    /**
     * Main method.
     *
     * @param args Unused.
     */
    public static void main(final String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java RecMinFind <inputFileName>");
            return;
        }
        // Check if the input file name is provided
        String inputFileName = args[0];
        // Default output file name
        String outputFileName = "output.txt";

        try (Scanner scanner = new Scanner(new File(inputFileName))) {
            // Read integers from the file into a list
            List<Integer> intList = new ArrayList<>();

            while (scanner.hasNext()) {
                // Read the next token
                // and try to parse it as an integer
                // If it fails, ignore it
                String token = scanner.next();
                try {
                    int value = Integer.parseInt(token);
                    intList.add(value);
                } catch (NumberFormatException e) {
                    // Ignore non-integer values
                }
            }
            // Check if the list is empty
            if (intList.isEmpty()) {
                // If no integers were found, print a message and exit
                System.out.println("No valid integers found in the file.");
                return;
            }
            // Convert the list of integers to an array
            int[] array = intList.stream().mapToInt(i -> i).toArray();

            // Declare variables for min, max, sum, and negative count
            int min = findMin(array, 0);
            int max = findMax(array, 0);
            int sum = sumArray(array, 0);
            int negatives = countNegatives(array, 0);

            // Print the results to the console
            System.out.println("Minimum: " + min);
            System.out.println("Maximum: " + max);
            System.out.println("Sum: " + sum);
            System.out.println("Negative count: " + negatives);

            // Write the results to output.txt
            try (FileWriter writer = new FileWriter(outputFileName)) {
                writer.write("Minimum: " + min + "\n");
                writer.write("Maximum: " + max + "\n");
                writer.write("Sum: " + sum + "\n");
                writer.write("Negative count: " + negatives + "\n");
            }
            // Print a message indicating that the results have been saved
            System.out.println("Results saved to output.txt.");
            // Catch any exceptions that occur during file reading/writing
        } catch (FileNotFoundException e) {
            System.out.println("Error: File \""
             + inputFileName + "\" not found.");
        } catch (IOException e) {
            System.out.println("Error writing to output.txt.");
        }
    }

    /**
     * Recursive method to find the minimum value in an array.
     *
     * @param array The array to search.
     * @param index The current index.
     * @return The minimum value in the array.
     */

    // Recursive method to find the minimum value in an array.
    private static int findMin(final int[] array, final int index) {
        // Base case: if the index is equal to the
        // last index of the array, return the current element
        if (index == array.length - 1) {
            return array[index];
        }
        // Recursive case: find the minimum of the rest of the array
        int minRest = findMin(array, index + 1);
        return Math.min(array[index], minRest);
    }

    /**
     * Recursive method to find the maximum value in an array.
     *
     * @param array The array to search.
     * @param index The current index.
     * @return The maximum value in the array.
     */

    // Recursive method to find the maximum value in an array
    private static int findMax(final int[] array, final int index) {
        // Base case: if the index is equal to the
        // last index of the array, return the current element
        if (index == array.length - 1) {
            return array[index];
        }
        // Recursive case: find the maximum of the rest of the array
        int maxRest = findMax(array, index + 1);
        return Math.max(array[index], maxRest);
    }

    /**
     * Recursive method to calculate the sum of an array.
     *
     * @param array The array to sum.
     * @param index The current index.
     * @return The sum of the array.
     */

    // Recursive method to calculate the sum of an array
    private static int sumArray(final int[] array, final int index) {
        // Base case: if the index is equal
        // to the length of the array, return 0
        if (index == array.length) {
            return 0;
        }
        // Recursive case: add the current element
        // to the sum of the rest of the array
        return array[index] + sumArray(array, index + 1);
    }

    /**
     * Recursive method to count the number of negative numbers in an array.
     *
     * @param array The array to search.
     * @param index The current index.
     * @return The count of negative numbers in the array.
     */

    // Recursive method to count the number of negative numbers in an array
    private static int countNegatives(final int[] array, final int index) {
        // Base case: if the index is equal to the length of the array, return 0
        if (index == array.length) {
            return 0;
        }
        // Recursive case: check the current element and count negatives
        int countRest = countNegatives(array, index + 1);
        if (array[index] < 0) {
            return 1 + countRest;
        } else {
            return countRest;
        }
    }
}
