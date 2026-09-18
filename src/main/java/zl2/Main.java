package zl2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj swoją wagę w kg: ");
        double weigthkg = scanner.nextDouble();
        System.out.println("Podaj wzrost w cm: ");
        double heightcm = scanner.nextDouble();
        double heightInMeters = heightcm *0.01;
        double BMI = weigthkg/(heightInMeters * heightInMeters);

        System.out.printf("Twoje BMI to: %.2f\n", BMI);

        if (BMI < 18.5) {
            System.out.println("niedowaga");
        } else if (BMI >= 18.5 && BMI <= 24.9) {
            System.out.println("normalne");
        } else if (BMI > 25 && BMI <= 29.9) {
            System.out.println("nadwaga");
        } else if (BMI >= 30) {
            System.out.println("Otyłość");
        }
        scanner.close();
    }
}








