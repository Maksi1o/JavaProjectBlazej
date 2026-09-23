package zl3;

import java.util.Optional;
import java.util.Scanner;
import java.util.Arrays;

public class DepartureSchedule {


    private enum DayOfWeek {
        MONDAY(1, "Poniedziałek"),
        TUESDAY(2, "Wtorek"),
        WEDNESDAY(3, "Środa"),
        THURSDAY(4, "Czwartek"),
        FRIDAY(5, "Piątek"),
        SATURDAY(6, "Sobota"),
        SUNDAY(7, "Niedziela");

        private final int dayOfTheWeek;
        private final String dayName;

        DayOfWeek(int numberRepresentation, String dayName) {
            this.dayOfTheWeek = numberRepresentation;
            this.dayName = dayName;
        }

        public boolean isWeekend() {
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

        public String getDepartureInfo() {
            return switch (this) {
                case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> getDayName() + " - Odjazd o godzinie 05:30: ";
                case SATURDAY -> getDayName() + " - Odjazd o godzinie 07:00: ";
                case SUNDAY -> getDayName() + " - Odjazd o godzinie 09:00: ";
            };
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

        String dayName = dayOfWeek.getDepartureInfo() + dayType;
        System.out.println(dayName);
        sc.close();
    }
}
