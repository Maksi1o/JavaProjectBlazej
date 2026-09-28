package pd3;

import java.util.Scanner;

public class InputHelper {

    public static int readInt(Scanner sc, String prompt) {
        do {
            System.out.println(prompt);
            if (sc.hasNextInt()) {
                return sc.nextInt();
            } else {
                sc.nextLine();
                System.out.println("Invalid input. Try again.");
            }
        } while (true);
    }

    public static double readDouble(Scanner sc, String prompt) {
        do {
            System.out.println(prompt);
            if (sc.hasNextDouble()) {
                return sc.nextDouble();
            } else {
                sc.nextLine();
                System.out.println("Invalid Input. Try again.");
            }
        } while (true);
    }

    public static int readIntInRange(Scanner sc, String prompt, int min, int max) {
        do {
            System.out.println(prompt);
            if (sc.hasNextInt()) {
                int value = sc.nextInt();
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Value must be between " + min + " - " + max + ".");
                }

            } else {
                sc.nextLine();
                System.out.println("Input must be an integer.");
            }

        } while (true);
    }
}
