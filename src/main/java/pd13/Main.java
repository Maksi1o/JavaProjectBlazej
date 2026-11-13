package pd13;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println(DateCalculator.age(LocalDate.of(1998, 11, 26)));
        System.out.println(DateCalculator.nextPayday(LocalDate.of(2026, 10, 5)));
        System.out.println(DateCalculator.nextPayday(LocalDate.of(2026, 11, 10)));
        System.out.println(DateCalculator.nextPayday(LocalDate.of(2027, 1, 30)));

        System.out.println(DateCalculator.businessDaysBetween(LocalDate.of(1998, 11, 26),
            LocalDate.of(2026, 11, 26)));
    }
}
