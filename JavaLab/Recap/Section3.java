package edu.uc3m;

/**
 * The Section3 program processes command-line arguments to calculate the sum of integers.
 * If no arguments are provided, the program outputs Integer.MIN_VALUE.
 * Invalid numeric inputs are ignored during the sum calculation.
 *
 * Usage examples:
 * - java edu.uc3m.Section3 1 2 3 -> Output: 6
 * - java edu.uc3m.Section3 a 5 b 3 -> Output: 8 (ignores invalid inputs "a" and "b")
 * - java edu.uc3m.Section3 -> Output: -2147483648 (value of Integer.MIN_VALUE)
 */
public class Section3 {

    /**
     * Main method to process command-line arguments and calculate the sum of integers.
     * If no arguments are provided, the program outputs Integer.MIN_VALUE.
     * Invalid inputs that cannot be parsed as integers are ignored.
     *
     * @param args the command-line arguments. These are expected to be integers,
     *             but non-numeric arguments are ignored.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            // Output Integer.MIN_VALUE if no arguments are provided
            System.out.println(Integer.MIN_VALUE);
        } else {
            int sum = 0; // Stores the sum of valid integers
            for (int i = 0; i < args.length; i++) {
                try {
                    // Parse each argument and add to the sum
                    sum = sum + Integer.parseInt(args[i]);
                } catch (NumberFormatException e) {
                    // Ignore invalid inputs
                    continue;
                }
            }
            // Output the total sum of valid integers
            System.out.println(sum);
        }
    }
}
