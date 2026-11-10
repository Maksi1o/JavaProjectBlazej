package pd9.service;

import pd9.model.Event;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class EventProcessor {

    public void process(List<Event> events,
                        List<Predicate<Event>> filters,
                        Function<Event, String> formatter,
                        Consumer<String> output) {

        Objects.requireNonNull(events, "Events cannot be null");
        Objects.requireNonNull(filters, "Filters cannot be null");
        Objects.requireNonNull(formatter, "Formatter cannot be null");
        Objects.requireNonNull(output, "Output cannot be null");

        if (filters.contains(null)) {
            throw new IllegalArgumentException("Filters cannot contain null");
        }

        events.forEach(event -> {
            if (event == null) {
                return;
            }

            boolean passed = true;

            for (Predicate<Event> filter : filters) {
                if (!filter.test(event)) {
                    passed = false;
                    break;
                }
            }

            if (passed) {
                String formattedEvent = formatter.apply(event);
                output.accept(formattedEvent);
            }
        });
    }
}
