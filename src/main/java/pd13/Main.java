package pd13;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println(DateCalculator.age(LocalDate.of(1998, 11, 26)));
        System.out.println(DateCalculator.nextPayday(LocalDate.of(2026, 10, 5)));
        System.out.println(DateCalculator.nextPayday(LocalDate.of(2026, 11, 10)));
        System.out.println(DateCalculator.nextPayday(LocalDate.of(2027, 1, 30)));

        System.out.println(DateCalculator.businessDaysBetween(LocalDate.of(2026, 11, 2),
            LocalDate.of(2026, 11, 6)));
        System.out.println(DateCalculator.businessDaysBetween(LocalDate.of(2026, 11, 2),
            LocalDate.of(2026, 11, 9)));
        System.out.println(DateCalculator.businessDaysBetween(LocalDate.of(2026, 11, 7),
            LocalDate.of(2026, 11, 9)));

        System.out.println(DateCalculator.isValidDate("13.11.2026", "dd.MM.uuuu"));
        System.out.println(DateCalculator.isValidDate("abc", "dd.MM.uuuu"));
        System.out.println(DateCalculator.isValidDate("31.02.2026", "dd.MM.uuuu"));

        ZonedDateTime timeWarsaw = ZonedDateTime.now(ZoneId.of("Europe/Warsaw"));
        System.out.println(DateCalculator.convertTimezone(timeWarsaw, "Asia/Tokyo"));

        System.out.println(DateCalculator.formatForLocale(LocalDateTime.now(), "pl-PL"));
    }
}
