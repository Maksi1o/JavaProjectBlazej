package pd3;

import java.util.Arrays;
import java.util.Scanner;

public class MenuHandler {
    public static void handleChoice(int choice, Scanner sc) {
        switch (choice) {
            case 1 -> {
                int iterativeInput = InputHelper.readInt(sc, "Insert a number: ");
                System.out.println("Iterative result = " + MathLibrary.factorialIterative(iterativeInput));
            }
            case 2 -> {
                int recursiveInput = InputHelper.readInt(sc, "Insert a number: ");
                System.out.println("Recursive result = " + MathLibrary.factorialRecursive(recursiveInput));
            }
            case 3 -> {
                int primeInput = InputHelper.readInt(sc, "Insert a number: ");
                System.out.println("Primie result = " + MathLibrary.isPrime(primeInput));
            }
            case 4 -> {
                int sieveInput = InputHelper.readInt(sc, "Insert a number: ");
                System.out.println("Eratosthenes result = " +
                        Arrays.toString(MathLibrary.sieveOfEratosthenes(sieveInput)));
            }
            case 5 -> {
                int a = InputHelper.readInt(sc, "Insert first number: ");
                int b = InputHelper.readInt(sc, "Insert second number: ");
                System.out.println("GCD result = " + MathLibrary.gcd(a, b));
            }
            case 6 -> {
                double base = InputHelper.readDouble(sc, "Insert the base number: ");
                int exp = InputHelper.readInt(sc, "Insert the exponent");
                System.out.println("Power result = " + MathLibrary.power(base, exp));
            }
            case 7 -> MathLibrary.benchmarkFactorials();
        }
    }
}
