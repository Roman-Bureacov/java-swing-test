package Sandbox;

import java.util.Scanner;

public class BaseExpansionConverter {

    public static void main(String... args) {
        final Scanner input = new Scanner(System.in);

        // converting from
        System.out.print("Number: ");
        final int fromNumber = input.nextInt();

        //converting to
        System.out.println("New Base: ");
        final int toBase = input.nextInt();

        final String toNumber = toBase(fromNumber, toBase);

        System.out.println("The new number in base " + toBase + " is " + toNumber);
    }

    /**
     * Converts a number from base 10 to a new number in the new base.
     * <br>
     * Based on the algorithm described in Discrete Mathematics, by Rosen, Page 263.
     *
     * @param theOldNumber the number to convert from (base 10)
     * @param theNewBase the base that the output number should be in
     * @return the old number converted from the old base to the new base
     */
    public static String toBase(final int theOldNumber, final int theNewBase) {
        final String spacer = theNewBase > 10 ? " " : "";
        String newNumber = "";

        int oldNumber = theOldNumber;

        while (oldNumber > 0) {
            newNumber = (oldNumber % theNewBase) + spacer + newNumber;
            // String builder newNumber.insert(0, (oldNumber % theOldBase) + spacer);
            oldNumber = Math.floorDiv(oldNumber, theNewBase);
        }

        return newNumber;
    }
}
