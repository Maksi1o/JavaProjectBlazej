package pd3;

import java.util.Arrays;
import java.util.Scanner;

public class MathLibraryApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String menuPrompt = """
                
                =======MATHEMATICAL LIBRARY=======
                
                AVAILABLE OPTIONS:
                
                1. Factorial Calculation - Iterative Method
                2. Factorial Calculation - Recursive Method
                3. Check if a Number is Prime
                4. Generate Prime Numbers - Sieve of Eratosthenes
                5. GCD - Greatest Common Divisor
                6. Calculate Power
                7. Compare Factorial Performance
                
                Choose an option from 1 to 7
                """;

        int choice = InputHelper.readIntInRange(sc, menuPrompt, 1, 7);

        MenuHandler.handleChoice(choice, sc);
    }
}
