package pd13;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class DateCalculator {
    private static final String DATE_PATTERN = "dd MMMM yyyy";

    private DateCalculator() {
    }

    public static int age(LocalDate birthDay) {
        return Period.between(birthDay, LocalDate.now()).getYears();
    }

    public static LocalDate nextPayday(LocalDate from) {
        LocalDate payDay = from.withDayOfMonth(from.lengthOfMonth());
        while (payDay.getDayOfWeek() == DayOfWeek.SATURDAY || payDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            payDay = payDay.minusDays(1);
        }
        if (from.isAfter(payDay)) {
            LocalDate nextMonth = from.plusMonths(1);
            payDay = nextMonth.withDayOfMonth(nextMonth.lengthOfMonth());
            while (payDay.getDayOfWeek() == DayOfWeek.SATURDAY || payDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
                payDay = payDay.minusDays(1);
            }
        }
        return payDay;
    }

    public static long businessDaysBetween(LocalDate from, LocalDate to) {
        if (to.isBefore(from)) {
            throw new IllegalArgumentException("to (" + to + ") must not be before from (" + from + ")");
        }
        LocalDate currentDay = from;
        long count = 0;
        while (currentDay.isBefore(to)) {
            if (currentDay.getDayOfWeek() != DayOfWeek.SATURDAY && currentDay.getDayOfWeek() != DayOfWeek.SUNDAY) {
                count++;
            }
            currentDay = currentDay.plusDays(1);
        }
        return count;
    }

    public static ZonedDateTime convertTimezone(ZonedDateTime dt, String targetZone) {
        return dt.withZoneSameInstant(ZoneId.of(targetZone));
    }

    public static String formatForLocale(LocalDateTime dt, String languageTag) {
        return dt.format(DateTimeFormatter.ofPattern(DATE_PATTERN, Locale.forLanguageTag(languageTag)));
    }

    public static boolean isValidDate(String input, String pattern) {
        return false;
    }
}
