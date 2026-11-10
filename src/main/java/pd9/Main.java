package pd9;

import pd9.filter.EventFilters;
import pd9.formatter.EventFormatters;
import pd9.model.Event;
import pd9.service.EventProcessor;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        EventProcessor eventProcessor = new EventProcessor();

        List<Event> events = List.of(
            new Event( "Login successful", 3),
            new Event( "Database error", 9),
            new Event( "Connection error", 7),
            new Event( "User logout", 2),
            new Event( "Minor error", 2)
        );

        List<Predicate<Event>> filters = List.of(
            EventFilters.HIGH_PRIORITY,
            EventFilters.IS_ERROR);

        List<Predicate<Event>> priorityFilters = List.of(
            EventFilters.HIGH_PRIORITY);

        List<Predicate<Event>> errorFilters = List.of(
            EventFilters.IS_ERROR);

        Consumer<String> output = System.out::println;

        System.out.println("=== TEST 1: AND + MESSAGE ===");
        eventProcessor.process(
            events, filters,
            EventFormatters.MESSAGE_FORMATTER, output
        );

        System.out.println("\n=== TEST 2: AND + PRIORITY ===");
        eventProcessor.process(
            events, filters,
            EventFormatters.PRIORITY_FORMATTER, output
        );

        System.out.println("\n=== TEST 3: HIGH PRIORITY ===");
        eventProcessor.process(
            events, priorityFilters,
            EventFormatters.MESSAGE_FORMATTER, output
        );

        System.out.println("\n=== TEST 4: ERROR MESSAGE ===");
        eventProcessor.process(
            events, errorFilters,
            EventFormatters.MESSAGE_FORMATTER, output
        );

        System.out.println("\n=== TEST 5: NO FILTERS ===");
        eventProcessor.process(
            events, List.of(),
            EventFormatters.MESSAGE_FORMATTER, output
        );
    }
}
