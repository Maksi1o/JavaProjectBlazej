package zl3;

import java.util.Optional;
import java.util.Scanner;
import java.util.Arrays;

public class DepartureSchedule {


    private enum DayOfWeek {
        MONDAY(1, "Poniedziałek", false),
        TUESDAY(2, "Wtorek", false),
        WEDNESDAY(3, "Środa", false),
        THURSDAY(4, "Czwartek", false),
        FRIDAY(5, "Piątek", false),
        SATURDAY(6, "Sobota", true),
        SUNDAY(7, "Niedziela", true);

        private final int dayOfTheWeek;
        private final String dayName;



        DayOfWeek(int numberRepresentation, String dayName, boolean isWeekend) {
            this.dayOfTheWeek = numberRepresentation;
            this.dayName = dayName;



        }

        public boolean isWeekend () {
            if (dayOfTheWeek == SATURDAY.dayOfTheWeek || dayOfTheWeek == SUNDAY.dayOfTheWeek) {
                return true;
            } else {
                return false;
            }
        }

        public int getDayOfTheWeek() {
            return dayOfTheWeek;
        }


        public String getDayName() {
            return dayName;
        }

        public static Optional<DayOfWeek> dayByValue(int day) {
            return Arrays.stream(DayOfWeek.values())
                    .filter(dayOfTheWeek -> dayOfTheWeek.getDayOfTheWeek() == day)
                    .findFirst();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Optional<DayOfWeek> selectedDay;

        do {
            System.out.println("Podaj dzień tygodnia (1-7): ");

            int day = sc.nextInt();

            selectedDay = DayOfWeek.dayByValue(day);

            if (selectedDay.isEmpty()) {
                System.out.println("Niepoprawny dzień tygodnia.");

            }

        } while (selectedDay.isEmpty());

        DayOfWeek dayOfWeek = selectedDay.orElseThrow();

        String dayType = dayOfWeek.isWeekend() ? "Weekend" : "Dzień pracujący";

        String dayName = switch (dayOfWeek) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> dayOfWeek.getDayName() + " - Odjazd o godzine 05:30: " + dayType;
            case SATURDAY -> dayOfWeek.getDayName() + " - Odjazd o godzinie 07:00: " + dayType;
            case SUNDAY -> dayOfWeek.getDayName() + " - Odjazd o godzinie 09:00: " + dayType;
        };

        System.out.println(dayName);
        sc.close();
    }
}
