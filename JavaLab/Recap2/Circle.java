package edu.uc3m;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * The Circle class represents a circle with a specified precision for area
 * calculation.
 */
public class Circle {

    private int precision;

    /**
     * Constructs a Circle with the specified precision.
     *
     * @param precision the number of decimal places to round the area to
     */
    public Circle(int precision) {
        this.precision = precision;
    }

    /**
     * Calculates the area of the circle with the given radius and rounds it to
     * the specified precision.
     *
     * @param radius the radius of the circle
     * @return the area of the circle rounded to the specified precision
     */
    public double area(double radius) {
        double area = Math.PI * (radius * radius);
        BigDecimal roundedArea = new BigDecimal(area)
                .round(new MathContext(this.precision));
        return roundedArea.doubleValue();
    }

    /**
     * The main method to run the Circle program. It expects command-line
     * arguments for precision and radii.
     *
     * @param args the command-line arguments where the first argument is the
     * precision and the subsequent arguments are the radii
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println(
                "Command-line arguments expected: precision and radii (1..n).");
        } else {
            try {
                int precision = Integer.parseInt(args[0]);
                if (precision < 0) {
                    System.err.println("Precision must be positive.");
                } else {
                    Circle circle = new Circle(precision);
                    for (int i = 1; i < args.length; i++) {
                        try {
                            double radius = Double.parseDouble(args[i]);
                            System.out.println(
                                "Area of circle with radius " + radius +
                                " is " + circle.area(radius));
                        } catch (NumberFormatException e) {
                            System.err.println("Radius must be a number.");
                        }
                    }
                }
            } catch (NumberFormatException e) {
                System.err.println("Precision must be an integer.");
            }
        }
    }
}
