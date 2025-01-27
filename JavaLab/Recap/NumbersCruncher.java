package edu.uc3m;

/**
 * The NumbersCruncher program is used to calculate various properties of a series
 * of integers. It processes command-line arguments where the first argument specifies
 * the operation ("sum", "max", "is-neg", or "sum-squared"), and the subsequent arguments
 * are the integers to process.
 *
 * Usage examples:
 * - java edu.uc3m.NumbersCruncher sum 1 2 3 -> Output: Sum: 6
 * - java edu.uc3m.NumbersCruncher max 5 10 7 -> Output: Max: 10
 * - java edu.uc3m.NumbersCruncher is-neg -1 5 3 -> Output: is-neg: Yes
 * - java edu.uc3m.NumbersCruncher sum-squared 1 2 3 -> Output: Sum-squared: 14
 * - java edu.uc3m.NumbersCruncher -> Output: Invalid parameters
 * - java edu.uc3m.NumbersCruncher avg 5 10 7 -> Output: Invalid operation
 */
public class NumbersCruncher {

    private int[] integers;

    /**
     * Constructs a NumbersCruncher object with an array of integers to process.
     *
     * @param integers an array of integers to calculate various properties.
     */
    public NumbersCruncher(int[] integers) {
        this.integers = integers;
    }

    /**
     * Calculates the sum of the integers stored in this NumbersCruncher.
     *
     * @return the total sum of the integers.
     */
    public int sum() {
        int total = 0;
        for (int i = 0; i < integers.length; i++) {
            total += integers[i];
        }
        return total;
    }

    /**
     * Finds the maximum value among the integers stored in this NumbersCruncher.
     *
     * @return the maximum value of the integers.
     */
    public int max() {
        int max = 0;
        for (int i = 0; i < integers.length; i++) {
            max = Math.max(max, integers[i]);
        }
        return max;
    }

    /**
     * Calculates the sum of the squares of the integers stored in this NumbersCruncher.
     *
     * @return the sum of the squares of the integers.
     */
    public int sumSquared() {
        int total = 0;
        for (int number : integers) {
            total += number * number;
        }
        return total;
    }

    /**
     * Determines whether there are any negative integers in the array.
     *
     * @return "Yes" if there is at least one negative integer, otherwise "No".
     */
    public String isNeg() {
        for (int number : integers) {
            if (number < 0) {
                return "Yes";
            }
        }
        return "No";
    }

    /**
     * Main method to process command-line arguments and perform the specified operation.
     *
     * The first argument specifies the operation ("sum", "max", "is-neg", or "sum-squared"),
     * and the remaining arguments are expected to be integers. Non-numeric arguments are ignored.
     *
     * @param args the command-line arguments. The first argument is the operation,
     *             and the subsequent arguments are integers to process.
     */
    public static void main(String[] args) {
        int[] values;
        if (args.length >= 2) {
            values = new int[args.length - 1];
            for (int i = 1; i < args.length; i++) {
                try {
                    values[i - 1] = Integer.parseInt(args[i]);
                } catch (NumberFormatException e) {
                    continue;
                }
            }

            NumbersCruncher nc = new NumbersCruncher(values);
            if (args[0].equals("sum")) {
                System.out.println("Sum: " + nc.sum());
            } else if (args[0].equals("max")) {
                System.out.println("Max: " + nc.max());
            } else if (args[0].equals("is-neg")) {
                System.out.println("is-neg: " + nc.isNeg());
            } else if (args[0].equals("sum-squared")) {
                System.out.println("Sum-squared: " + nc.sumSquared());
            } else {
                System.out.println("Invalid operation");
            }
        } else {
            System.out.println("Invalid parameters");
        }
    }
}
