package zl3;

import java.util.Optional;
import java.util.Scanner;


public class Departure_schedule {


    private enum DzienTygodnia {
        PONIEDZIALEK, WTOREK, SRODA, CZWARTEK, PIATEK, SOBOTA, NIEDZIELA;

        public static Optional<DzienTygodnia> DayByValue(int day) {
            return Optional.ofNullable(switch (day) {
                case 1 -> PONIEDZIALEK;
                case 2 -> WTOREK;
                case 3 -> SRODA;
                case 4 -> CZWARTEK;
                case 5 -> PIATEK;
                case 6 -> SOBOTA;
                case 7 -> NIEDZIELA;
                default -> null;
            });
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DzienTygodnia wybranyDzien = null;

        do {
            System.out.println("Podaj dzień tygodnia (1-7): ");

            int day = sc.nextInt();

            wybranyDzien = DzienTygodnia.DayByValue(day)
                    .orElse(null);

            if (wybranyDzien == null) {
                System.out.println("Nieprawidłowa wartość.");
            }
        } while (wybranyDzien == null);

        String dayName = switch (wybranyDzien) {
            case PONIEDZIALEK, WTOREK, SRODA, CZWARTEK, PIATEK -> wybranyDzien + " Odjazd o godzine 05:30.";
            case SOBOTA -> wybranyDzien + " Odjazd o godzinie 07:00.";
            case NIEDZIELA -> wybranyDzien + " Odjazd o godzinie 09:00.";
        };

        System.out.println(dayName);
        sc.close();
    }
}
