package edu.uc3m;

/**
 * This program processes command-line arguments to calculate either the sum or the maximum
 * of a series of integers. The first argument specifies the operation ("sum" or "max"),
 * and the subsequent arguments are the numbers to process. Non-numeric arguments are ignored.
 *
 * Usage examples:
 * - java edu.uc3m.Section4 sum 1 2 3 -> Output: Sum: 6
 * - java edu.uc3m.Section4 max 5 10 7 -> Output: Max: 10
 * - java edu.uc3m.Section4 sum a 5 b 3 -> Output: Sum: 8 (ignores invalid inputs "a" and "b")
 * - java edu.uc3m.Section4 -> Output: No parameters
 */
public class Section4 {

    /**
     * Main method to process command-line arguments and execute the specified operation.
     *
     * If the first argument is "sum", the program calculates the sum of all valid integers
     * in the subsequent arguments. If the first argument is "max", the program finds the
     * maximum value among all valid integers in the subsequent arguments. Non-numeric
     * arguments are ignored.
     *
     * If no arguments are provided, the program outputs "No parameters".
     *
     * @param args the command-line arguments. The first argument specifies the operation
     *             ("sum" or "max"), and the subsequent arguments are the integers to process.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No parameters");
        } else {
            if (args[0].equals("sum")) {
                int sum = 0;
                for (int i = 1; i < args.length; i++) { // Starts at 1 to skip the operation argument
                    int num = parseIntSafe(args[i]);
                    if (num != Integer.MIN_VALUE) { // Ensures only valid numbers are processed
                        sum += num;
                    }
                }
                System.out.println("Sum: " + sum);
            } else if (args[0].equals("max")) {
                int max = Integer.MIN_VALUE;
                for (int i = 1; i < args.length; i++) { // Starts at 1 to skip the operation argument
                    int num = parseIntSafe(args[i]);
                    if (num != Integer.MIN_VALUE) { // Ensures only valid numbers are processed
                        max = Math.max(max, num);
                    }
                }
                System.out.println("Max: " + (max == Integer.MIN_VALUE ? "No valid numbers" : max));
            }
        }
    }

    /**
     * Safely parses a string into an integer.
     *
     * If the string cannot be parsed as a valid integer (e.g., non-numeric values),
     * this method returns Integer.MIN_VALUE as a special indicator of invalid input.
     *
     * @param str the string to parse.
     * @return the parsed integer, or Integer.MIN_VALUE if parsing fails.
     */
    private static int parseIntSafe(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return Integer.MIN_VALUE; // Returns a special value indicating an invalid number
        }
    }
}
