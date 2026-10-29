package pd9.filter;

import pd9.model.Event;

import java.util.function.Predicate;

public class EventFilters {
   public static final Predicate<Event> HIGH_PRIORITY = event -> event != null && event.getPriority() > 5;

    public static final Predicate<Event> IS_ERROR = event -> {
        if (event == null ||
            event.getMessage() == null ||
            event.getMessage().isBlank()) {
            return false;
        }

        return event.getMessage()
            .toLowerCase()
            .contains("error");
    };
}
