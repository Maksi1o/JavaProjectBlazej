package pd3;

public class MathLibrary {
    static long factorialIterative(int n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }

    static long factorialRecursive(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorialRecursive(n - 1);
    }

    static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    static double power(double base, int exp) {
        if (exp == 0) {
            return 1;
        } else if (exp % 2 == 0) {
            double power = power(base, exp / 2);
            return power * power;
        } else {
            return base * power(base, exp - 1);
        }
    }

    static int[] sieveOfEratosthenes(int limit) {
        boolean[] composite = new boolean[limit + 1];
        for (int i = 2; i <= Math.sqrt(limit); i++) {
            if (composite[i] == false) {
                for (int j = i * i; j <= limit; j += i) {
                    composite[j] = true;
                }
            }
        }
        int count = 0;
        for (int i = 2; i <= limit; i++) {
            if (composite[i] == false) {
                count++;
            }
        }
        int[] prime = new int[count];
        int index = 0;
        for (int i = 2; i <= limit; i++) {
            if (composite[i] == false) {
                prime[index] = i;
                index++;
            }

        }
        return prime;
    }

    static void benchmarkFactorials() {
        long startIterative = System.nanoTime();
        long iterativeResult = factorialIterative(20);
        long endIterative = System.nanoTime();
        long elapsedIterative = endIterative - startIterative;

        long startRecursive = System.nanoTime();
        long recursiveResult = factorialRecursive(20);
        long endRecursive = System.nanoTime();
        long elapsedRecursive = endRecursive - startRecursive;

        System.out.println(
                "Iterative factorial time = " + elapsedIterative + " ns\n" +
                "Recursive factorial time = " + elapsedRecursive + " ns");
    }
}

